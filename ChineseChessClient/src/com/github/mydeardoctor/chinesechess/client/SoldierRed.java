package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Side;

import java.util.HashMap;
import java.util.HashSet;

public class SoldierRed extends Soldier
{
    public SoldierRed()
    {
        super(Side.RED);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Side turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.x();
        int y = origin.y();

        if((y>=5)&&(y<=9)) //If below River.
        {
            //Check tile above.
            checkTile(x, y-1, game, grid, turn, possibleMoves);
        }
        else //If above River.
        {
            if(y!=0) //If not top of the board.
            {
                //Check tile above.
                checkTile(x, y-1, game, grid, turn, possibleMoves);
            }
            if(x!=0) //If not left side of the board.
            {
                //Check tile on the left.
                checkTile(x-1, y, game, grid, turn, possibleMoves);
            }
            if(x!=8) //If not right side of the board.
            {
                //Check tile on the right.
                checkTile(x+1, y, game, grid, turn, possibleMoves);
            }
        }

        return possibleMoves;
    }
}