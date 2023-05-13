package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public class SoldierBlack extends Soldier
{
    public SoldierBlack(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    public HashSet<Location> getPossibleMoves(HashMap<Location, Tile> grid, Player turn, Figure generalRed, Figure generalBlack)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = Game.findLocationOfFigure(this, grid);
        int x = origin.getX();
        int y = origin.getY();

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