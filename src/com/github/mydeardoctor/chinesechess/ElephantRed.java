package com.github.mydeardoctor.chinesechess;

import java.util.HashSet;
import java.util.HashMap;

public class ElephantRed extends Elephant
{
    public ElephantRed()
    {
        super(Player.RED);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Player turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.x();
        int y = origin.y();

        //Check tile above-left.
        if((x>=2)&&(y>=7))
        {
            checkTile(x-2,y-2,x-1,y-1, game, grid, turn, possibleMoves);
        }

        //Check tile above-right.
        if((x<=6)&&(y>=7))
        {
            checkTile(x+2,y-2,x+1,y-1, game, grid, turn, possibleMoves);
        }

        //Check tile below-left.
        if((x>=2)&&(y<=7))
        {
            checkTile(x-2,y+2,x-1,y+1, game, grid, turn, possibleMoves);
        }

        //Check tile below-right.
        if((x<=6)&&(y<=7))
        {
            checkTile(x+2,y+2,x+1,y+1, game, grid, turn, possibleMoves);
        }

        return possibleMoves;
    }
}