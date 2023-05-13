package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public abstract class General extends Figure
{
    public General(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    public void checkTile(int xDestination, int yDestination,
                          HashSet<Location> possibleMoves, HashMap<Location, Tile> grid, Player turn)
    {
        Location destination = new Location(xDestination, yDestination);
        TileType destinationType = Game.checkTileType(destination, grid, turn);
        if((destinationType == TileType.EMPTY)||(destinationType == TileType.ENEMY))
        {
            possibleMoves.add(destination);
        }
    }
}