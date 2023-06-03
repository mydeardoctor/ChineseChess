package com.github.mydeardoctor.chinesechess.client;

import java.util.HashMap;
import java.util.HashSet;

public class SoldierBlack extends Soldier
{
    public SoldierBlack()
    {
        super(Player.BLACK);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Player turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.x();
        int y = origin.y();

        if((y>=0)&&(y<=4)) //If above River.
        {
            //Check tile below.
            checkTile(x, y+1, game, grid, turn, possibleMoves);
        }
        else //If below River.
        {
            if(y!=9) //If not bottom of the board.
            {
                //Check tile below.
                checkTile(x, y+1, game, grid, turn, possibleMoves);
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