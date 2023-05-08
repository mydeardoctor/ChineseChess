package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;

abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    private Location location;
    Figure(Player player, BufferedImage icon, int xGrid, int yGrid)
    {
        this.player = player;
        this.icon = icon;
        location = new Location(xGrid, yGrid);
    }
    public BufferedImage getIcon()
    {
        return icon;
    }
    public Location getLocation()
    {
        return location;
    }
}
