package com.github.mydeardoctor.chinesechess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.*;

class PanelBoard extends PanelBackground implements MouseListener
{
    private Game gameReference;
    PanelBoard(Text text)
    {
        super(text);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        //Calculation of dimensions.
        int side = Math.min(this.getWidth(), this.getHeight());
        int tile = side/11;
        int x = (this.getWidth()-tile*10)/2;
        int y = (this.getHeight()-tile*11)/2;
        int figureDiameter = (int)(tile*0.85);
        int figureRadius = figureDiameter/2;

        //Rectangles.
        g2d.setColor(new Color(207, 92, 1));
        g2d.fillRect(x, y, tile*10, tile*11);
        g2d.setColor(new Color(252, 174, 63));
        g2d.fillRect(x+tile, y+tile, tile*8, tile*9);

        //Lines.
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        //Horizontal lines.
        g2d.drawLine(x,y,x+tile*10,y);
        for(int i = 1; i <= 10; i++)
        {
            g2d.drawLine(x+tile,y+tile*i,x+tile*9,y+tile*i);
        }
        g2d.drawLine(x,y+tile*11,x+tile*10,y+tile*11);
        //Vertical lines.
        g2d.drawLine(x,y,x,y+tile*11);
        g2d.drawLine(x+tile,y+tile,x+tile,y+tile*10);
        for(int i = 2; i <= 8; i++)
        {
            g2d.drawLine(x+tile*i,y+tile,x+tile*i,y+tile*5);
            g2d.drawLine(x+tile*i,y+tile*6,x+tile*i,y+tile*10);
        }
        g2d.drawLine(x+tile*9,y+tile,x+tile*9,y+tile*10);
        g2d.drawLine(x+tile*10,y,x+tile*10,y+tile*11);
        //Diagonal lines.
        g2d.drawLine(x+tile*4,y+tile*3,x+tile*6,y+tile);
        g2d.drawLine(x+tile*4,y+tile,x+tile*6,y+tile*3);
        g2d.drawLine(x+tile*4,y+tile*10,x+tile*6,y+tile*8);
        g2d.drawLine(x+tile*4,y+tile*8,x+tile*6,y+tile*10);

        //Figures.
        ArrayList<Figure> figures = gameReference.getFigures();
        if(figures!=null)
        {
            for(Figure figure : figures)
            {
                g2d.drawImage(figure.getIcon(),
                        x+tile + tile*figure.getLocation().getX()-figureRadius,
                        y+tile+tile*figure.getLocation().getY()-figureRadius,
                        figureDiameter, figureDiameter, this);
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {

    }
    @Override
    public void mousePressed(MouseEvent e)
    {
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
    }
    public void setGameReference(Game gameReference)
    {
        this.gameReference = gameReference;
    }
}
