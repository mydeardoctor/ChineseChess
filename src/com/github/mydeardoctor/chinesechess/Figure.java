package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;

abstract class Figure
{
    private Team team;
    private BufferedImage icon;
    private Location location;
    Figure(Team team, BufferedImage icon, int x, int y)
    {
        this.team = team;
        this.icon = icon;
        location = new Location(x, y);
    }
}
