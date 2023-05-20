package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public abstract class General extends Figure
{
    public General(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    public void checkTile(int xDestination, int yDestination,
                          Game game, HashMap<Location, Tile> grid, Player turn, HashSet<Location> possibleMoves)
    {
        Location destination = new Location(xDestination, yDestination);
        TileType destinationType = game.getTileType(destination, grid, turn);
        if((destinationType == TileType.EMPTY)||(destinationType == TileType.ENEMY))
        {
            possibleMoves.add(destination);
        }
    }
}