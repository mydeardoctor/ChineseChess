package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Side;
import java.util.HashMap;
import java.util.HashSet;

public class GeneralBlack extends General
{
    public GeneralBlack()
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

        if(y!=0) //If not top of the Palace.
        {
            //Check tile above.
            checkTile(x, y-1, game, grid, turn, possibleMoves);
        }
        if(y!=2) //If not bottom of the Palace.
        {
            //Check tile below.
            checkTile(x, y+1, game, grid, turn, possibleMoves);
        }
        if(x!=3) //If not left side of the Palace.
        {
            //Check tile on the left.
            checkTile(x-1, y, game, grid, turn, possibleMoves);
        }
        if(x!=5) //If not right side of the Palace.
        {
            //Check tile on the right.
            checkTile(x+1, y, game, grid, turn, possibleMoves);
        }

        return possibleMoves;
    }
}