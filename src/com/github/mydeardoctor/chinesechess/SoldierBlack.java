package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class SoldierBlack extends Figure
{
    SoldierBlack(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid) //TODO: implement
    {
        HashSet<GridLocation> possibleMoves = new HashSet<>();
        int x = origin.getXgrid();
        int y = origin.getYgrid();
        GridLocation destination;

        if((y >= 0)&&(y <= 4)) //If above River.
        {
            //Check tile below.
            destination = new GridLocation(x, y+1);
            if(this.isPossibleMove(destination, grid))
            {
                possibleMoves.add(destination);
            }
        }
        else //If below River.
        {
            if(y!=9) //If not bottom of the board.
            {
                //Check tile below.
                destination = new GridLocation(x, y+1);
                if(this.isPossibleMove(destination, grid))
                {
                    possibleMoves.add(destination);
                }
            }
            if(x!=0) //If not left side of the board.
            {
                //Check tile on the left.
                destination = new GridLocation(x-1,y);
                if(this.isPossibleMove(destination, grid))
                {
                    possibleMoves.add(destination);
                }
            }
            if(x!=8) //If not right side of the board.
            {
                //Check tile on the right.
                destination = new GridLocation(x+1,y);
                if(this.isPossibleMove(destination, grid))
                {
                    possibleMoves.add(destination);
                }
            }
        }

        return possibleMoves;
    }
}
