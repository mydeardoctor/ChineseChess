package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public class AdvisorBlack extends Advisor
{
    public AdvisorBlack(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        HashMap<Location, Tile> grid = game.getGrid();
        Location origin = getLocation(this, grid);
        int x = origin.getX();
        int y = origin.getY();

        //Check tile above-left.
        if((x>=4)&&(y>=1))
        {
            checkTile(x-1, y-1, possibleMoves, game);
        }

        //Check tile above-right.
        if((x<=4)&&(y>=1))
        {
            checkTile(x+1, y-1, possibleMoves, game);
        }

        //Check tile below-left.
        if((x>=4)&&(y<=1))
        {
            checkTile(x-1, y+1, possibleMoves, game);
        }

        //Check tile below-right.
        if((x<=4)&&(y<=1))
        {
            checkTile(x+1, y+1, possibleMoves, game);
        }

        return possibleMoves;
    }
}