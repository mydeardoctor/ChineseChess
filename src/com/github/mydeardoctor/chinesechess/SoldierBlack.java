package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class SoldierBlack extends Soldier
{
    SoldierBlack(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        HashSet<GridLocation> possibleMoves = new HashSet<>();
        int x = origin.getXgrid();
        int y = origin.getYgrid();

        if((y >= 0)&&(y <= 4)) //If above River.
        {
            //Check tile below.
            checkTile(x,y+1, possibleMoves, grid, turn);
        }
        else //If below River.
        {
            if(y!=9) //If not bottom of the board.
            {
                //Check tile below.
                checkTile(x,y+1, possibleMoves, grid, turn);
            }
            if(x!=0) //If not left side of the board.
            {
                //Check tile on the left.
                checkTile(x-1,y, possibleMoves, grid, turn);
            }
            if(x!=8) //If not right side of the board.
            {
                //Check tile on the right.
                checkTile(x+1,y, possibleMoves, grid, turn);
            }
        }

        return possibleMoves;
    }
}
