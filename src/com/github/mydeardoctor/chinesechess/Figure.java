package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;

abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    private Location location;
    Figure(Player player, BufferedImage icon, int x, int y)
    {
        this.player = player;
        this.icon = icon;
        location = new Location(x, y);
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
