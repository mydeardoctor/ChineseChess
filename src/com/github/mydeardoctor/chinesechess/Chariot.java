package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public class Chariot extends Figure
{
    Chariot(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid)
    {
        HashSet<GridLocation> possibleMoves = new HashSet<>();
        int x = origin.getXgrid();
        int y = origin.getYgrid();
        GridLocation destination;
        DestinationType destinationType;

        //Check tiles above.
        for(int yAbove = y - 1; yAbove >= 0; yAbove--)
        {
            destination = new GridLocation(x, yAbove);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                possibleMoves.add(destination);
            }
            else if(destinationType==DestinationType.ENEMY_FIGURE)
            {
                possibleMoves.add(destination);
                break;
            }
            else if(destinationType==DestinationType.FRIENDLY_FIGURE)
            {
                break;
            }
        }

        //Check tiles below.
        for(int yBelow = y + 1; yBelow <= 9; yBelow++)
        {
            destination = new GridLocation(x, yBelow);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                possibleMoves.add(destination);
            }
            else if(destinationType==DestinationType.ENEMY_FIGURE)
            {
                possibleMoves.add(destination);
                break;
            }
            else if(destinationType==DestinationType.FRIENDLY_FIGURE)
            {
                break;
            }
        }

        //Check tiles on the left.
        for(int xLeft = x - 1; xLeft >= 0; xLeft--)
        {
            destination = new GridLocation(xLeft, y);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                possibleMoves.add(destination);
            }
            else if(destinationType==DestinationType.ENEMY_FIGURE)
            {
                possibleMoves.add(destination);
                break;
            }
            else if(destinationType==DestinationType.FRIENDLY_FIGURE)
            {
                break;
            }
        }
        //Check tiles on the right.
        for(int xRight = x + 1; xRight <= 8; xRight++)
        {
            destination = new GridLocation(xRight, y);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                possibleMoves.add(destination);
            }
            else if(destinationType==DestinationType.ENEMY_FIGURE)
            {
                possibleMoves.add(destination);
                break;
            }
            else if(destinationType==DestinationType.FRIENDLY_FIGURE)
            {
                break;
            }
        }

        return possibleMoves;
    }
}
