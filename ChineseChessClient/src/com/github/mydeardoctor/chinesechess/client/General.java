package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Side;

import java.util.HashMap;
import java.util.HashSet;

public abstract class General extends Figure
{
    public General(Side side)
    {
        super(side);
    }
    public void checkTile(int xDestination, int yDestination,
                          Game game, HashMap<Location, Tile> grid, Side turn, HashSet<Location> possibleMoves)
    {
        Location destination = new Location(xDestination, yDestination);
        TileType destinationType = game.getTileType(destination, grid, turn);
        if((destinationType == TileType.EMPTY)||(destinationType == TileType.ENEMY))
        {
            possibleMoves.add(destination);
        }
    }
}