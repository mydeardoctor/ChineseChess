package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Advisor extends Figure
{
    public Advisor(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    public void checkTile(int xDestination, int yDestination, HashSet<GridLocation> possibleMoves, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        GridLocation destination = new GridLocation(xDestination, yDestination);
        GridTileType destinationType = Game.checkGridTileType(destination, grid, turn);
        if((destinationType==GridTileType.EMPTY)||(destinationType==GridTileType.ENEMY_FIGURE))
        {
            possibleMoves.add(destination);
        }
    }
}
