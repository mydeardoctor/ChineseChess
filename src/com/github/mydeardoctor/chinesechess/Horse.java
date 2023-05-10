package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

class Horse extends Figure
{
    Horse(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        HashSet<GridLocation> possibleMoves = new HashSet<>();
        int x = origin.getXgrid();
        int y = origin.getYgrid();

        //Check tile above, above-left.
        if(  ((x-1)>=0)  &&  ((y-2)>=0)  ) //If destination is inside the board.
        {
            checkTile(x-1,y-2,x,y-1, possibleMoves, grid, turn);
        }

        //Check tile above, above-right.
        if(  ((x+1)<=8)  &&  ((y-2)>=0)  ) //If destination is inside the board.
        {
            checkTile(x+1,y-2,x,y-1, possibleMoves, grid, turn);
        }

        //Check tile right, right-above.
        if(  ((x+2)<=8)  &&  ((y-1)>=0)  ) //If destination is inside the board.
        {
            checkTile(x+2,y-1,x+1,y, possibleMoves, grid, turn);
        }

        //Check tile right, right-below.
        if(  ((x+2)<=8)  &&  ((y+1)<=9)  ) //If destination is inside the board.
        {
            checkTile(x+2,y+1,x+1,y, possibleMoves, grid, turn);
        }

        //Check tile below, below-right.
        if(  ((x+1)<=8)  &&  ((y+2)<=9)  ) //If destination is inside the board.
        {
            checkTile(x+1,y+2,x,y+1, possibleMoves, grid, turn);
        }

        //Check tile below, below-left.
        if(  ((x-1)>=0)  &&  ((y+2)<=9)  ) //If destination is inside the board.
        {
            checkTile(x-1,y+2,x,y+1, possibleMoves, grid, turn);
        }

        //Check tile left, left-below.
        if(  ((x-2)>=0)  &&  ((y+1)<=9)  ) //If destination is inside the board.
        {
            checkTile(x-2,y+1,x-1,y, possibleMoves, grid, turn);
        }

        //Check tile left, left-above.
        if(  ((x-2)>=0)  &&  ((y-1)>=0)  ) //If destination is inside the board.
        {
            checkTile(x-2,y-1,x-1,y, possibleMoves, grid, turn);
        }

        return possibleMoves;
    }
    private void checkTile(int xDestination, int yDestination, int xIntermediate, int yIntermediate,
                           HashSet<GridLocation> possibleMoves, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        GridLocation destination = new GridLocation(xIntermediate, yIntermediate);
        GridTileType destinationType = Game.checkGridTileType(destination, grid, turn);
        if(destinationType== GridTileType.EMPTY)
        {
            destination = new GridLocation(xDestination, yDestination);
            destinationType = Game.checkGridTileType(destination, grid, turn);
            if((destinationType== GridTileType.EMPTY)||(destinationType== GridTileType.ENEMY_FIGURE))
            {
                possibleMoves.add(destination);
            }
        }
    }
}
