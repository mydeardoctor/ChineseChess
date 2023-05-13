package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;

public class GridTile //TODO: поиск key по value. override hashcode and equals. сделать зависимость от id фигуры. генерал определяется по буфферед имадж. это буллщит, потому что они могут не загрузиться
{
    private Figure figure;
    private BufferedImage selection;
    public GridTile(Figure figure, BufferedImage selection)
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
