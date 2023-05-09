package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class SoldierRed extends Figure
{
    SoldierRed(Player player, BufferedImage icon)
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

        if((y >= 5)&&(y <= 9)) //If below River.
        {
            //Check tile above.
            destination = new GridLocation(x, y-1);
            destinationType = this.checkDestinationType(destination, grid);
            if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
            {
                possibleMoves.add(destination);
            }
        }
        else //If above River.
        {
            if(y!=0) //If not top of the board.
            {
                //Check tile above.
                destination = new GridLocation(x, y-1);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
            if(x!=0) //If not left side of the board.
            {
                //Check tile on the left.
                destination = new GridLocation(x-1,y);
                destinationType = this.checkDestinationType(destination, grid);
                if((destinationType==DestinationType.EMPTY)||(destinationType==DestinationType.ENEMY_FIGURE))
                {
                    possibleMoves.add(destination);
                }
            }
            if(x!=8) //If not right side of the board.
            {
                //Check tile on the right.
                destination = new GridLocation(x+1,y);
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
