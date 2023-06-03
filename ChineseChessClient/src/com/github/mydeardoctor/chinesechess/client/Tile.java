package com.github.mydeardoctor.chinesechess.client;

import java.io.Serializable;

public class Tile implements Serializable
{
    private Figure figure;
    private boolean selected;
    public Tile(Figure figure, boolean selected)
    {
        this.figure = figure;
        this.selected = selected;
    }
    public Figure getFigure()
    {
        return figure;
    }
    public void setFigure(Figure figure)
    {
        this.figure = figure;
    }
    public boolean getSelected()
    {
        return selected;
    }
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}