package com.github.mydeardoctor.chinesechess;

import java.util.HashSet;
import java.util.HashMap;

public class AdvisorRed extends Advisor
{
    public AdvisorRed()
    {
        super(Player.RED);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Player turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.getX();
        int y = origin.getY();

        //Check tile above-left.
        if((x>=4)&&(y>=8))
        {
            checkTile(x-1, y-1, game, grid, turn, possibleMoves);
        }

        //Check tile above-right.
        if((x<=4)&&(y>=8))
        {
            checkTile(x+1, y-1, game, grid, turn, possibleMoves);
        }

        //Check tile below-left.
        if((x>=4)&&(y<=8))
        {
            checkTile(x-1, y+1, game, grid, turn, possibleMoves);
        }

        //Check tile below-right.
        if((x<=4)&&(y<=8))
        {
            checkTile(x+1, y+1, game, grid, turn, possibleMoves);
        }

        return possibleMoves;
    }
}