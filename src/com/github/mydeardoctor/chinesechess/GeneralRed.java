package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class GeneralRed extends Figure
{
    GeneralRed(Player player, BufferedImage icon)
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

        if(y!=7) //If not top of the Palace.
        {
            //Check tile above.
            destination = new GridLocation(x, y-1);
            if(this.isPossibleMove(destination, grid))
            {
                possibleMoves.add(destination);
            }
        }
        if(y!=9) //If not bottom of the Palace.
        {
            //Check tile below.
            destination = new GridLocation(x, y+1);
            if(this.isPossibleMove(destination, grid))
            {
                possibleMoves.add(destination);
            }
        }
        if(x!=3) //If not left side of the Palace.
        {
            //Check tile on the left.
            destination = new GridLocation(x-1,y);
            if(this.isPossibleMove(destination, grid))
            {
                possibleMoves.add(destination);
            }
        }
        if(x!=5) //If not right side of the Palace.
        {
            //Check tile on the right.
            destination = new GridLocation(x+1,y);
            if(this.isPossibleMove(destination, grid))
            {
                possibleMoves.add(destination);
            }
        }

        return possibleMoves;
    }
}
