package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Side;

import java.util.HashMap;
import java.util.HashSet;

public class AdvisorBlack extends Advisor
{
    public AdvisorBlack()
    {
        super(Side.BLACK);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Side turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.x();
        int y = origin.y();

        //Check tile above-left.
        if((x>=4)&&(y>=1))
        {
            checkTile(x-1, y-1, game, grid, turn, possibleMoves);
        }

        //Check tile above-right.
        if((x<=4)&&(y>=1))
        {
            checkTile(x+1, y-1, game, grid, turn, possibleMoves);
        }

        //Check tile below-left.
        if((x>=4)&&(y<=1))
        {
            checkTile(x-1, y+1, game, grid, turn, possibleMoves);
        }

        //Check tile below-right.
        if((x<=4)&&(y<=1))
        {
            checkTile(x+1, y+1, game, grid, turn, possibleMoves);
        }

        return possibleMoves;
    }
}