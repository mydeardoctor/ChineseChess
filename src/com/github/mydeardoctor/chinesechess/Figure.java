package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;

abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    Figure(Player player, BufferedImage icon)
    {
        this.player = player;
        this.icon = icon;
    }
    public BufferedImage getIcon()
    {
        return icon;
    }
}
