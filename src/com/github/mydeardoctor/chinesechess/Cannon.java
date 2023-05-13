package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public class Cannon extends Figure
{
    public Cannon(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    public HashSet<Location> getPossibleMoves(HashMap<Location, Tile> grid, Player turn, Figure generalRed, Figure generalBlack)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = Game.findLocationOfFigure(this, grid);
        int x = origin.getX();
        int y = origin.getY();
        Location destination;
        TileType destinationType;
        boolean platformInPath;

        //Check tiles above.
        platformInPath = false;
        for(int yAbove = y - 1; yAbove >= 0; yAbove--)
        {
            destination = new Location(x, yAbove);
            destinationType = Game.checkTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== TileType.EMPTY)
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
                if(destinationType== TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== TileType.FRIENDLY)
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
            destinationType = Game.checkTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== TileType.EMPTY)
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
                if(destinationType== TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== TileType.FRIENDLY)
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
            destinationType = Game.checkTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== TileType.EMPTY)
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
                if(destinationType== TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== TileType.FRIENDLY)
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
            destinationType = Game.checkTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== TileType.EMPTY)
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
                if(destinationType== TileType.ENEMY)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== TileType.FRIENDLY)
                {
                    break;
                }
            }
        }

        return possibleMoves;
    }
}