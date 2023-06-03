package com.github.mydeardoctor.chinesechess.client;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Horse extends Figure
{
    public Horse(Player player)
    {
        super(player);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Player turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.x();
        int y = origin.y();

        //Check tile above, above-left.
        if(  ((x-1)>=0)  &&  ((y-2)>=0)  ) //If destination is inside the board.
        {
            checkTile(x-1, y-2, x, y-1, game, grid, turn, possibleMoves);
        }

        //Check tile above, above-right.
        if(  ((x+1)<=8)  &&  ((y-2)>=0)  ) //If destination is inside the board.
        {
            checkTile(x+1, y-2, x, y-1, game, grid, turn, possibleMoves);
        }

        //Check tile right, right-above.
        if(  ((x+2)<=8)  &&  ((y-1)>=0)  ) //If destination is inside the board.
        {
            checkTile(x+2, y-1, x+1, y, game, grid, turn, possibleMoves);
        }

        //Check tile right, right-below.
        if(  ((x+2)<=8)  &&  ((y+1)<=9)  ) //If destination is inside the board.
        {
            checkTile(x+2, y+1, x+1, y, game, grid, turn, possibleMoves);
        }

        //Check tile below, below-right.
        if(  ((x+1)<=8)  &&  ((y+2)<=9)  ) //If destination is inside the board.
        {
            checkTile(x+1, y+2, x, y+1, game, grid, turn, possibleMoves);
        }

        //Check tile below, below-left.
        if(  ((x-1)>=0)  &&  ((y+2)<=9)  ) //If destination is inside the board.
        {
            checkTile(x-1, y+2, x, y+1, game, grid, turn, possibleMoves);
        }

        //Check tile left, left-below.
        if(  ((x-2)>=0)  &&  ((y+1)<=9)  ) //If destination is inside the board.
        {
            checkTile(x-2, y+1, x-1, y, game, grid, turn, possibleMoves);
        }

        //Check tile left, left-above.
        if(  ((x-2)>=0)  &&  ((y-1)>=0)  ) //If destination is inside the board.
        {
            checkTile(x-2, y-1, x-1, y, game, grid, turn, possibleMoves);
        }

        return possibleMoves;
    }
    private void checkTile(int xDestination, int yDestination, int xIntermediate, int yIntermediate,
                           Game game, HashMap<Location, Tile> grid, Player turn, HashSet<Location> possibleMoves)
    {
        Location destination = new Location(xIntermediate, yIntermediate);
        TileType destinationType = game.getTileType(destination, grid, turn);
        if(destinationType==TileType.EMPTY)
        {
            destination = new Location(xDestination, yDestination);
            destinationType = game.getTileType(destination, grid, turn);
            if((destinationType==TileType.EMPTY)||(destinationType==TileType.ENEMY))
            {
                possibleMoves.add(destination);
            }
        }
    }
}