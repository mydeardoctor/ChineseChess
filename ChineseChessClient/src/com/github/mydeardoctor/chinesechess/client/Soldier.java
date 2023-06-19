package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.*;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Soldier extends Figure
{
    public Soldier(Player player)
    {
        super(player);
    }
    public void checkTile(int xDestination, int yDestination,
                          Game game, HashMap<Location, Tile> grid, Player turn, HashSet<Location> possibleMoves)
    {
        Location destination = new Location(xDestination, yDestination);
        TileType destinationType = game.getTileType(destination, grid, turn);
        if((destinationType==TileType.EMPTY)||(destinationType==TileType.ENEMY))
        {
            possibleMoves.add(destination);
        }
    }
}