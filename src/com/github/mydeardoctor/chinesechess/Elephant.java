package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public abstract class Elephant extends Figure
{
    public Elephant(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    public void checkTile(int xDestination, int yDestination, int xIntermediate, int yIntermediate,
                          Game game, HashMap<Location, Tile> grid, Player turn, HashSet<Location> possibleMoves)
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