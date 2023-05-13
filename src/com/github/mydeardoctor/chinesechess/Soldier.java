package com.github.mydeardoctor.chinesechess;

import java.util.HashSet;
import java.awt.image.BufferedImage;

public abstract class Soldier extends Figure
{
    public Soldier(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    public void checkTile(int xDestination, int yDestination, HashSet<Location> possibleMoves, Game game)
    {
        Location destination = new Location(xDestination, yDestination);
        TileType destinationType = game.getTileType(destination);
        if((destinationType==TileType.EMPTY)||(destinationType==TileType.ENEMY))
        {
            possibleMoves.add(destination);
        }
    }
}