package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    Figure(Player player, BufferedImage icon)
    {
        this.player = player;
        this.icon = icon;
    }

    //TODO: Check for king check.
    abstract HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn);
    public Player getPlayer()
    {
        return player;
    }
    public BufferedImage getIcon()
    {
        return icon;
    }
}
