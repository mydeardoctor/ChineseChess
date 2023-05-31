package com.github.mydeardoctor.chinesechess;

import java.util.HashSet;
import java.util.HashMap;

public abstract class Cannon extends Figure
{
    public Cannon(Player player)
    {
        super(player);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Player turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.x();
        int y = origin.y();
        boolean platformInPath;
        Location destination;
        TileType destinationType;

        //Check tiles above.
        platformInPath = false;
        for(int yAbove = y - 1; yAbove >= 0; yAbove--)
        {
            destination = new Location(x, yAbove);
            destinationType = game.getTileType(destination, grid, turn);
            if(!platformInPath)
            {
                if(destinationType==TileType.EMPTY)
                {
                    possibleMoves.add(destination);
                }
                else
                {
                    platformInPath = true;
                }
            }
            else //platformInPath==true
            {
                if(destinationType==TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType==TileType.FRIENDLY)
                {
                    break;
                }
            }
        }

        //Check tiles below.
        platformInPath = false;
        for(int yBelow = y + 1; yBelow <= 9; yBelow++)
        {
            destination = new Location(x, yBelow);
            destinationType = game.getTileType(destination, grid, turn);
            if(!platformInPath)
            {
                if(destinationType==TileType.EMPTY)
                {
                    possibleMoves.add(destination);
                }
                else
                {
                    platformInPath = true;
                }
            }
            else //platformInPath==true
            {
                if(destinationType==TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType==TileType.FRIENDLY)
                {
                    break;
                }
            }
        }

        //Check tiles on the left.
        platformInPath = false;
        for(int xLeft = x - 1; xLeft >= 0; xLeft--)
        {
            destination = new Location(xLeft, y);
            destinationType = game.getTileType(destination, grid, turn);
            if(!platformInPath)
            {
                if(destinationType==TileType.EMPTY)
                {
                    possibleMoves.add(destination);
                }
                else
                {
                    platformInPath = true;
                }
            }
            else //platformInPath==true
            {
                if(destinationType==TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType==TileType.FRIENDLY)
                {
                    break;
                }
            }
        }
        //Check tiles on the right.
        platformInPath = false;
        for(int xRight = x + 1; xRight <= 8; xRight++)
        {
            destination = new Location(xRight, y);
            destinationType = game.getTileType(destination, grid, turn);
            if(!platformInPath)
            {
                if(destinationType==TileType.EMPTY)
                {
                    possibleMoves.add(destination);
                }
                else
                {
                    platformInPath = true;
                }
            }
            else //platformInPath==true
            {
                if(destinationType==TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType==TileType.FRIENDLY)
                {
                    break;
                }
            }
        }

        return possibleMoves;
    }
}