package com.github.mydeardoctor.chinesechess;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelBoard extends JPanel
{
    private int x0Board;
    private int y0Board;
    private int tileLength;
    private int figureRadius;
    private HashMap<Location, Tile> grid;
    public PanelBoard()
    {
        super();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        //Calculation of dimensions.
        int boardSide = Math.min(this.getWidth(), this.getHeight());
        tileLength = boardSide/11;
        x0Board = (this.getWidth() - tileLength*10)/2;
        y0Board = (this.getHeight() - tileLength*11)/2;
        int figureDiameter = (int)(tileLength*0.85);
        figureRadius = figureDiameter/2;
        int selectionDiameter = (int)(figureDiameter*1.2);
        int selectionRadius = selectionDiameter/2;

        //Painting the board.
        //Rectangles.
        g2d.setColor(new Color(207, 92, 1));
        g2d.fillRect(x0Board, y0Board, tileLength*10, tileLength*11);
        g2d.setColor(new Color(252, 174, 63));
        g2d.fillRect(x0Board + tileLength, y0Board + tileLength, tileLength*8, tileLength*9);
        g2d.setColor(new Color(69, 175, 252));
        g2d.fillRect(x0Board + tileLength, y0Board + tileLength*5, tileLength*8, tileLength);

        //Lines.
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        //Horizontal lines.
        g2d.drawLine(x0Board, y0Board, x0Board + tileLength*10, y0Board);
        for(int i = 1; i <= 10; i++)
        {
            g2d.drawLine(x0Board + tileLength, y0Board + tileLength*i,
                         x0Board + tileLength*9, y0Board + tileLength*i);
        }
        g2d.drawLine(x0Board, y0Board + tileLength*11, x0Board + tileLength*10, y0Board + tileLength*11);
        //Vertical lines.
        g2d.drawLine(x0Board, y0Board, x0Board, y0Board + tileLength*11);
        g2d.drawLine(x0Board + tileLength, y0Board + tileLength,
                     x0Board + tileLength, y0Board + tileLength*10);
        for(int i = 2; i <= 8; i++)
        {
            g2d.drawLine(x0Board + tileLength*i, y0Board + tileLength,
                         x0Board + tileLength*i, y0Board + tileLength*5);
            g2d.drawLine(x0Board + tileLength*i, y0Board + tileLength*6,
                         x0Board + tileLength*i, y0Board + tileLength*10);
        }
        g2d.drawLine(x0Board + tileLength*9, y0Board + tileLength,
                     x0Board + tileLength*9, y0Board + tileLength*10);
        g2d.drawLine(x0Board + tileLength*10, y0Board, x0Board + tileLength*10, y0Board + tileLength*11);
        //Diagonal lines.
        g2d.drawLine(x0Board + tileLength*4, y0Board + tileLength*3,
                     x0Board + tileLength*6, y0Board + tileLength);
        g2d.drawLine(x0Board + tileLength*4, y0Board + tileLength,
                     x0Board + tileLength*6, y0Board + tileLength*3);
        g2d.drawLine(x0Board + tileLength*4, y0Board + tileLength*10,
                     x0Board + tileLength*6, y0Board + tileLength*8);
        g2d.drawLine(x0Board + tileLength*4, y0Board + tileLength*8,
                     x0Board + tileLength*6, y0Board + tileLength*10);

        //Painting figures and selections.
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            Figure figure = gridEntry.getValue().getFigure();
            if(figure!=null)
            {
                g2d.drawImage(figure.getIcon(),
                        x0Board + tileLength + tileLength* gridEntry.getKey().getX() - figureRadius,
                        y0Board + tileLength + tileLength* gridEntry.getKey().getY() - figureRadius,
                        figureDiameter, figureDiameter, this);
            }
            BufferedImage selection = gridEntry.getValue().getSelection();
            if(selection!=null)
            {
                g2d.drawImage(selection,
                        x0Board + tileLength + tileLength* gridEntry.getKey().getX() - selectionRadius,
                        y0Board + tileLength + tileLength* gridEntry.getKey().getY() - selectionRadius,
                        selectionDiameter, selectionDiameter, this);
            }
        }
    }
    public int getX0Board()
    {
        return x0Board;
    }
    public int getY0Board()
    {
        return y0Board;
    }
    public int getTileLength()
    {
        return tileLength;
    }
    public int getFigureRadius()
    {
        return figureRadius;
    }
    public void setGrid(HashMap<Location, Tile> grid)
    {
        this.grid = grid;
    }
}