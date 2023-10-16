package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Side;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Elephant extends Figure
{
    public Elephant(Side side)
    {
        super(side);
    }
    public void checkTile(int xDestination, int yDestination, int xIntermediate, int yIntermediate,
                          Game game, HashMap<Location, Tile> grid, Side turn, HashSet<Location> possibleMoves)
    {
        Location destination = new Location(xIntermediate, yIntermediate);
        TileType destinationType = game.getTileType(destination, grid, turn);
        if(destinationType == TileType.EMPTY)
        {
            destination = new Location(xDestination, yDestination);
            destinationType = game.getTileType(destination, grid, turn);
            if((destinationType == TileType.EMPTY)||(destinationType == TileType.ENEMY))
            {
                possibleMoves.add(destination);
            }
        }
    }
}