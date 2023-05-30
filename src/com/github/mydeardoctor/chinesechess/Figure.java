package com.github.mydeardoctor.chinesechess;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public abstract class Figure implements Serializable
{
    private final Player player;

    public Figure(Player player)
    {
        this.player = player;
    }
    public HashSet<Location> getAllowedMoves(Game game)
    {
        HashSet<Location> allowedMoves = new HashSet<>();

        HashMap<Location, Tile> grid = game.getGrid();
        Location origin = getLocation(this, grid);

        HashMap<Location, Tile> gridCopy = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, false);
                gridCopy.put(location, tile);
            }
        }

        HashSet<Location> possibleMoves = getPossibleMoves(game, grid, game.getTurn());
        for(Location possibleMove : possibleMoves)
        {
            //Reset grid copy.
            Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
            for(Map.Entry<Location, Tile> gridEntry : gridSet)
            {
                Location location = gridEntry.getKey();
                Figure figure = gridEntry.getValue().getFigure();
                boolean selected = gridEntry.getValue().getSelected();
                gridCopy.get(location).setFigure(figure);
                gridCopy.get(location).setSelected(selected);
            }

            gridCopy.get(origin).setFigure(null);       //Move figure from initial location...
            gridCopy.get(possibleMove).setFigure(this); //...to a new location.

            //Check if generals see each other.
            Location generalRedLocation = getLocation(game.getGeneralRed(), gridCopy);
            Location generalBlackLocation = getLocation(game.getGeneralBlack(), gridCopy);

            if((generalRedLocation==null) || (generalBlackLocation==null)) //General was killed. Illegal move.
            {
                continue;
            }

            if(generalRedLocation.getX()==generalBlackLocation.getX())
            {
                boolean generalsSeeEachOther = true;
                int x = generalBlackLocation.getX();
                for(int y = generalBlackLocation.getY() + 1; y < generalRedLocation.getY(); y++)
                {
                    Location destination = new Location(x, y);
                    TileType tileType = game.getTileType(destination, gridCopy, game.getTurn());
                    if(tileType!=TileType.EMPTY)
                    {
                        generalsSeeEachOther = false;
                        break;
                    }
                }
                if(generalsSeeEachOther) //Generals see each other. Illegal move.
                {
                    continue;
                }
            }

            //Check if friendly general is now under attack.
            boolean generalUnderAttack = false;
            Location friendlyGeneralLocation=null;
            Player nextTurn = null;
            switch(game.getTurn())
            {
                case RED ->
                {
                    friendlyGeneralLocation = generalRedLocation;
                    //Pretend that the game is in the next turn.
                    //This is necessary for correct calculation of enemy possible moves.
                    nextTurn = Player.BLACK;
                }
                case BLACK ->
                {
                    friendlyGeneralLocation = generalBlackLocation;
                    //Pretend that the game is in the next turn.
                    //This is necessary for correct calculation of enemy possible moves.
                    nextTurn = Player.RED;
                }
            }

            Set<Map.Entry<Location, Tile>> gridCopySet = gridCopy.entrySet();
            for(Map.Entry<Location, Tile> gridCopyEntry : gridCopySet)
            {
                Figure figure = gridCopyEntry.getValue().getFigure();
                if(figure!=null)
                {
                    Player player = figure.getPlayer();
                    if(player==nextTurn) //For every enemy figure.
                    {
                        HashSet<Location> enemyPossibleMoves = figure.getPossibleMoves(game, gridCopy, nextTurn);
                        //Check if enemy figure can attack friendly general.
                        if(enemyPossibleMoves.contains(friendlyGeneralLocation))
                        {
                            generalUnderAttack = true;
                            break;
                        }
                    }
                }
            }
            if(!generalUnderAttack)
            {
                allowedMoves.add(possibleMove);
            }
        }

        return allowedMoves;
    }
    public abstract HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Player turn);
    public Location getLocation(Figure figure, HashMap<Location, Tile> grid)
    {
        Location location = null;
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            Figure figureTemp = gridEntry.getValue().getFigure();
            if(figureTemp!=null)
            {
                if(figureTemp.equals(figure))
                {
                    location = gridEntry.getKey();
                    break;
                }
            }
        }
        return location;
    }
    public Player getPlayer()
    {
        return player;
    }
}