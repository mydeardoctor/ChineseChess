package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Elephant extends Figure
{
    public Elephant(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    public void checkTile(int xDestination, int yDestination, int xIntermediate, int yIntermediate,
                          HashSet<Location> possibleMoves, HashMap<Location, Tile> grid, Player turn)
    {
        Location destination = new Location(xIntermediate, yIntermediate);
        TileType destinationType = Game.checkTileType(destination, grid, turn);
        if(destinationType == TileType.EMPTY)
        {
            destination = new Location(xDestination, yDestination);
            destinationType = Game.checkTileType(destination, grid, turn);
            if((destinationType == TileType.EMPTY)||(destinationType == TileType.ENEMY))
            {
                possibleMoves.add(destination);
            }
        }
    }
}