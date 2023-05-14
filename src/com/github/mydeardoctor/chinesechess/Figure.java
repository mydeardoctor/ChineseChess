package com.github.mydeardoctor.chinesechess;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    public Figure(Player player, BufferedImage icon)
    {
        this.player = player;
        this.icon = icon;
    }
    public HashSet<Location> getAllowedMoves(Game game)
    {
        HashSet<Location> allowedMoves = new HashSet<>();
        HashSet<Location> possibleMoves = getPossibleMoves(game);
        HashSet<Location> enemyPossibleMoves = new HashSet<>();

        HashMap<Location, Tile> grid = game.getGrid();
        Location origin = getLocation(this, grid);

        for(Location possibleMove : possibleMoves)
        {
            Figure figureThatWasAtOrigin = grid.get(origin).getFigure();            //Save figure to move back later.
            Figure figureThatWasAtDestination = grid.get(possibleMove).getFigure(); //Save figure to move back later.
            grid.get(origin).setFigure(null);       //Move figure from initial location...
            grid.get(possibleMove).setFigure(this); //...to a new location.

            //Check if generals see each other.
            Location generalRedLocation = getLocation(game.getGeneralRed(), grid);
            Location generalBlackLocation = getLocation(game.getGeneralBlack(), grid);

            if((generalRedLocation==null) || (generalBlackLocation==null)) //General was killed. Illegal move.
            {
                //Move figure back.
                grid.get(origin).setFigure(figureThatWasAtOrigin);
                grid.get(possibleMove).setFigure(figureThatWasAtDestination);
                continue;
            }

            if(generalRedLocation.getX()==generalBlackLocation.getX())
            {
                boolean generalsSeeEachOther = true;
                int x = generalBlackLocation.getX();
                for(int y = generalBlackLocation.getY() + 1; y < generalRedLocation.getY(); y++)
                {
                    Location destination = new Location(x, y);
                    TileType tileType = game.getTileType(destination);
                    if(tileType!=TileType.EMPTY)
                    {
                        generalsSeeEachOther = false;
                        break;
                    }
                }
                if(generalsSeeEachOther) //Generals see each other. Illegal move.
                {
                    //Move figure back.
                    grid.get(origin).setFigure(figureThatWasAtOrigin);
                    grid.get(possibleMove).setFigure(figureThatWasAtDestination);
                    continue;
                }
            }

            //Check if friendly general is now under attack.
            boolean generalUnderAttack = false;
            Location friendlyGeneralLocation=null;
            switch(game.getTurn())
            {
                case RED ->
                {
                    friendlyGeneralLocation = generalRedLocation;
                    //Pretend that the game is in the next turn.
                    //This is necessary for correct calculation of enemy possible moves.
                    game.setTurn(Player.BLACK);
                }
                case BLACK ->
                {
                    friendlyGeneralLocation = generalBlackLocation;
                    //Pretend that the game is in the next turn.
                    //This is necessary for correct calculation of enemy possible moves.
                    game.setTurn(Player.RED);
                }
            }

            enemyPossibleMoves.clear();
            Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
            for(Map.Entry<Location, Tile> gridEntry : gridSet)
            {
                Figure figure = gridEntry.getValue().getFigure();
                if(figure!=null)
                {
                    Player player = figure.getPlayer();
                    if(player==game.getTurn()) //For every enemy figure.
                    {
                        enemyPossibleMoves = figure.getPossibleMoves(game);
                        //Check if enemy figure can attack friendly general.
                        if(enemyPossibleMoves.contains(friendlyGeneralLocation))
                        {
                            generalUnderAttack = true;
                            break;
                        }
                    }
                }
            }
            if(generalUnderAttack==false)
            {
                allowedMoves.add(possibleMove);
            }

            //Move figure back.
            grid.get(origin).setFigure(figureThatWasAtOrigin);
            grid.get(possibleMove).setFigure(figureThatWasAtDestination);
            //Set turn back.
            switch(game.getTurn())
            {
                case RED -> game.setTurn(Player.BLACK);
                case BLACK -> game.setTurn(Player.RED);
            }
        }

        return allowedMoves;
    }
    public abstract HashSet<Location> getPossibleMoves(Game game);
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
    public BufferedImage getIcon()
    {
        return icon;
    }
}