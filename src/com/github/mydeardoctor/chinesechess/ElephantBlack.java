package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class ElephantBlack extends Elephant
{
    ElephantBlack(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        HashSet<GridLocation> possibleMoves = new HashSet<>();
        int x = origin.getXgrid();
        int y = origin.getYgrid();

        //Check tile above-left.
        if((x>=2)&&(y>=2))
        {
            checkTile(x-2,y-2,x-1,y-1, possibleMoves, grid, turn);
        }

        //Check tile above-right.
        if((x<=6)&&(y>=2))
        {
            checkTile(x+2,y-2,x+1,y-1, possibleMoves, grid, turn);
        }

        //Check tile below-left.
        if((x>=2)&&(y<=2))
        {
            checkTile(x-2,y+2,x-1,y+1, possibleMoves, grid, turn);
        }

        //Check tile below-right.
        if((x<=6)&&(y<=2))
        {
            checkTile(x+2,y+2,x+1,y+1, possibleMoves, grid, turn);
        }

        return possibleMoves;
    }
}
