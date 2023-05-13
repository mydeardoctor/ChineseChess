package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public class AdvisorRed extends Advisor
{
    public AdvisorRed(Player player, BufferedImage icon)
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

        //Check tile above-left.
        if((x>=4)&&(y>=8))
        {
            checkTile(x-1, y-1, possibleMoves, grid, turn);
        }

        //Check tile above-right.
        if((x<=4)&&(y>=8))
        {
            checkTile(x+1, y-1, possibleMoves, grid, turn);
        }

        //Check tile below-left.
        if((x>=4)&&(y<=8))
        {
            checkTile(x-1, y+1, possibleMoves, grid, turn);
        }

        //Check tile below-right.
        if((x<=4)&&(y<=8))
        {
            checkTile(x+1, y+1, possibleMoves, grid, turn);
        }

        return possibleMoves;
    }
}