package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class AdvisorRed extends Figure
{
    AdvisorRed(Player player, BufferedImage icon)
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

        //Check tile above-left.
        if((x>=4)&&(y>=8))
        {
            destination = new GridLocation(x-1, y-1);
            destinationType=this.checkDestinationType(destination, grid);
            if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
            {
                possibleMoves.add(destination);
            }
        }

        //Check tile above-right.
        if((x<=4)&&(y>=8))
        {
            destination = new GridLocation(x+1, y-1);
            destinationType=this.checkDestinationType(destination, grid);
            if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
            {
                possibleMoves.add(destination);
            }
        }

        //Check tile below-left.
        if((x>=4)&&(y<=8))
        {
            destination = new GridLocation(x-1, y+1);
            destinationType=this.checkDestinationType(destination, grid);
            if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
            {
                possibleMoves.add(destination);
            }
        }

        //Check tile below-right.
        if((x<=4)&&(y<=8))
        {
            destination = new GridLocation(x+1, y+1);
            destinationType=this.checkDestinationType(destination, grid);
            if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
            {
                possibleMoves.add(destination);
            }
        }

        return possibleMoves;
    }
}
