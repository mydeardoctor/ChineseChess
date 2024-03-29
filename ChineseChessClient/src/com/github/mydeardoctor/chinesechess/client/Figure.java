package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Side;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Figure implements Serializable
{
    private final Side side;

    public Figure(Side side)
    {
        this.side = side;
    }
    public HashSet<Location> getAllowedMoves(Game game, Side turn)
    {
        HashSet<Location> allowedMoves = new HashSet<>();

        HashMap<Location, Tile> grid = game.getGrid();
        Location origin = getLocation(this, grid);

        HashMap<Location, Tile> gridCopy = new HashMap<>();
        for(byte y = 0; y <= 9; y++)
        {
            for(byte x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, false);
                gridCopy.put(location, tile);
            }
        }

        HashSet<Location> possibleMoves = getPossibleMoves(game, grid, turn);
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

            if(generalRedLocation.x()==generalBlackLocation.x())
            {
                boolean generalsSeeEachOther = true;
                int x = generalBlackLocation.x();
                for(int y = generalBlackLocation.y() + 1; y < generalRedLocation.y(); y++)
                {
                    Location destination = new Location(x, y);
                    TileType tileType = game.getTileType(destination, gridCopy, turn);
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
            Side nextTurn = null;
            switch(turn)
            {
                case RED ->
                {
                    friendlyGeneralLocation = generalRedLocation;
                    //Pretend that the game is in the next turn.
                    //This is necessary for correct calculation of enemy possible moves.
                    nextTurn = Side.BLACK;
                }
                case BLACK ->
                {
                    friendlyGeneralLocation = generalBlackLocation;
                    //Pretend that the game is in the next turn.
                    //This is necessary for correct calculation of enemy possible moves.
                    nextTurn = Side.RED;
                }
            }

            Set<Map.Entry<Location, Tile>> gridCopySet = gridCopy.entrySet();
            for(Map.Entry<Location, Tile> gridCopyEntry : gridCopySet)
            {
                Figure figure = gridCopyEntry.getValue().getFigure();
                if(figure!=null)
                {
                    Side side = figure.getSide();
                    if(side ==nextTurn) //For every enemy figure.
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
    public abstract HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Side turn);
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
    public Side getSide()
    {
        return side;
    }
}