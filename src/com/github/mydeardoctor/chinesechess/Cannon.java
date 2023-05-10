package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class Cannon extends Figure
{
    Cannon(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        HashSet<GridLocation> possibleMoves = new HashSet<>();
        int x = origin.getXgrid();
        int y = origin.getYgrid();
        GridLocation destination;
        GridTileType destinationType;
        boolean platformInPath;

        //Check tiles above.
        platformInPath = false;
        for(int yAbove = y - 1; yAbove >= 0; yAbove--)
        {
            destination = new GridLocation(x, yAbove);
            destinationType = Game.checkGridTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== GridTileType.EMPTY)
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
                if(destinationType== GridTileType.ENEMY_FIGURE)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== GridTileType.FRIENDLY_FIGURE)
                {
                    break;
                }
            }
        }

        //Check tiles below.
        platformInPath = false;
        for(int yBelow = y + 1; yBelow <= 9; yBelow++)
        {
            destination = new GridLocation(x, yBelow);
            destinationType = Game.checkGridTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== GridTileType.EMPTY)
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
                if(destinationType== GridTileType.ENEMY_FIGURE)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== GridTileType.FRIENDLY_FIGURE)
                {
                    break;
                }
            }
        }

        //Check tiles on the left.
        platformInPath = false;
        for(int xLeft = x - 1; xLeft >= 0; xLeft--)
        {
            destination = new GridLocation(xLeft, y);
            destinationType = Game.checkGridTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== GridTileType.EMPTY)
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
                if(destinationType== GridTileType.ENEMY_FIGURE)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== GridTileType.FRIENDLY_FIGURE)
                {
                    break;
                }
            }
        }
        //Check tiles on the right.
        platformInPath = false;
        for(int xRight = x + 1; xRight <= 8; xRight++)
        {
            destination = new GridLocation(xRight, y);
            destinationType = Game.checkGridTileType(destination, grid, turn);
            if(platformInPath==false)
            {
                if(destinationType== GridTileType.EMPTY)
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
                if(destinationType== GridTileType.ENEMY_FIGURE)
                {
                    possibleMoves.add(destination);
                    break;
                }
                else if(destinationType== GridTileType.FRIENDLY_FIGURE)
                {
                    break;
                }
            }
        }

        return possibleMoves;
    }
}
