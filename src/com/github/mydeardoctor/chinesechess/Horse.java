package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public class Horse extends Figure
{
    public Horse(Player player, BufferedImage icon)
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
                           HashSet<Location> possibleMoves, HashMap<Location, Tile> grid, Player turn)
    {
        Location destination = new Location(xIntermediate, yIntermediate);
        TileType destinationType = Game.checkTileType(destination, grid, turn);
        if(destinationType== TileType.EMPTY)
        {
            destination = new Location(xDestination, yDestination);
            destinationType = Game.checkTileType(destination, grid, turn);
            if((destinationType== TileType.EMPTY)||(destinationType== TileType.ENEMY))
            {
                possibleMoves.add(destination);
            }
        }
    }
}