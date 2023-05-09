package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class Horse extends Figure
{
    Horse(Player player, BufferedImage icon)
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

        //Check tile above, above-left.
        if(  ((x-1)>=0)  &&  ((y-2)>=0)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x, y-1);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x-1, y-2);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        //Check tile above, above-right.
        if(  ((x+1)<=8)  &&  ((y-2)>=0)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x, y-1);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x+1, y-2);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        //Check tile right, right-above.
        if(  ((x+2)<=8)  &&  ((y-1)>=0)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x+1, y);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x+2, y-1);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        //Check tile right, right-below.
        if(  ((x+2)<=8)  &&  ((y+1)<=9)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x+1, y);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x+2, y+1);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        //Check tile below, below-right.
        if(  ((x+1)<=8)  &&  ((y+2)<=9)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x, y+1);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x+1, y+2);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        //Check tile below, below-left.
        if(  ((x-1)>=0)  &&  ((y+2)<=9)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x, y+1);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x-1, y+2);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        //Check tile left, left-below.
        if(  ((x-2)>=0)  &&  ((y+1)<=9)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x-1, y);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x-2, y+1);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        //Check tile left, left-above.
        if(  ((x-2)>=0)  &&  ((y-1)>=0)  ) //If destination is inside the board.
        {
            destination= new GridLocation(x-1, y);
            destinationType = this.checkDestinationType(destination, grid);
            if(destinationType==DestinationType.EMPTY)
            {
                destination = new GridLocation(x-2, y-1);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        return possibleMoves;
    }
}
