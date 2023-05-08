package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;

class GridTile
{
    private Figure figure;
    private BufferedImage selection;
    GridTile(Figure figure, BufferedImage selection)
    {
        this.figure = figure;
        this.selection = selection;
    }
    public Figure getFigure()
    {
        return figure;
    }
    public void setFigure(Figure figure)
    {
        this.figure = figure;
    }
    public BufferedImage getSelection()
    {
        return selection;
    }
    public void setSelection(BufferedImage selection)
    {
        this.selection = selection;
    }
}
