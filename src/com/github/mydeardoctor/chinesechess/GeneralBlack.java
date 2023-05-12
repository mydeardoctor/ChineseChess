package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public class GeneralBlack extends General
{
    public GeneralBlack(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    public HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        HashSet<GridLocation> possibleMoves = new HashSet<>();
        int x = origin.getXgrid();
        int y = origin.getYgrid();

        if(y!=0) //If not top of the Palace.
        {
            //Check tile above.
            checkTile(x, y-1, possibleMoves, grid, turn);
        }
        if(y!=2) //If not bottom of the Palace.
        {
            //Check tile below.
            checkTile(x, y+1, possibleMoves, grid, turn);
        }
        if(x!=3) //If not left side of the Palace.
        {
            //Check tile on the left.
            checkTile(x-1, y, possibleMoves, grid, turn);
        }
        if(x!=5) //If not right side of the Palace.
        {
            //Check tile on the right.
            checkTile(x+1, y, possibleMoves, grid, turn);
        }

        return possibleMoves;
    }
}
