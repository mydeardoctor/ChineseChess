package com.github.mydeardoctor.chinesechess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.*;

class PanelBoard extends PanelBackground implements MouseListener
{
    private int tile;
    private int x0board;
    private int y0board;
    private int figureRadius;
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
        tile = side/11;
        x0board = (this.getWidth()-tile*10)/2;
        y0board = (this.getHeight()-tile*11)/2;
        int figureDiameter = (int)(tile*0.85);
        figureRadius = figureDiameter/2;

        //Rectangles.
        g2d.setColor(new Color(207, 92, 1));
        g2d.fillRect(x0board, y0board, tile*10, tile*11);
        g2d.setColor(new Color(252, 174, 63));
        g2d.fillRect(x0board +tile, y0board +tile, tile*8, tile*9);

        //Lines.
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        //Horizontal lines.
        g2d.drawLine(x0board, y0board, x0board +tile*10, y0board);
        for(int i = 1; i <= 10; i++)
        {
            g2d.drawLine(x0board +tile, y0board +tile*i, x0board +tile*9, y0board +tile*i);
        }
        g2d.drawLine(x0board, y0board +tile*11, x0board +tile*10, y0board +tile*11);
        //Vertical lines.
        g2d.drawLine(x0board, y0board, x0board, y0board +tile*11);
        g2d.drawLine(x0board +tile, y0board +tile, x0board +tile, y0board +tile*10);
        for(int i = 2; i <= 8; i++)
        {
            g2d.drawLine(x0board +tile*i, y0board +tile, x0board +tile*i, y0board +tile*5);
            g2d.drawLine(x0board +tile*i, y0board +tile*6, x0board +tile*i, y0board +tile*10);
        }
        g2d.drawLine(x0board +tile*9, y0board +tile, x0board +tile*9, y0board +tile*10);
        g2d.drawLine(x0board +tile*10, y0board, x0board +tile*10, y0board +tile*11);
        //Diagonal lines.
        g2d.drawLine(x0board +tile*4, y0board +tile*3, x0board +tile*6, y0board +tile);
        g2d.drawLine(x0board +tile*4, y0board +tile, x0board +tile*6, y0board +tile*3);
        g2d.drawLine(x0board +tile*4, y0board +tile*10, x0board +tile*6, y0board +tile*8);
        g2d.drawLine(x0board +tile*4, y0board +tile*8, x0board +tile*6, y0board +tile*10);

        //Figures.
        ArrayList<Figure> figures = gameReference.getFigures();
        for(Figure figure : figures)
        {
            g2d.drawImage(figure.getIcon(),
                    x0board + tile + tile*figure.getLocation().getXgrid() - figureRadius,
                    y0board + tile + tile*figure.getLocation().getYgrid() - figureRadius,
                    figureDiameter, figureDiameter, this);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getButton()!=MouseEvent.BUTTON1)
        {
            return;
        }

        int xMouse = e.getX();
        int yMouse = e.getY();
        ArrayList<Figure> figures = gameReference.getFigures();
        Figure figureClicked = null;
        for(Figure figure : figures)
        {
            int xGridFigure = figure.getLocation().getXgrid();
            int yGridFigure = figure.getLocation().getYgrid();
            int xCenterFigure = x0board + tile + tile*xGridFigure;
            int yCenterFigure = y0board + tile + tile*yGridFigure;

            //Closed disk formula. (x-x0)^2 + (y-y0)^2 <= r^2
            if((Math.pow(xMouse - xCenterFigure, 2) +
                Math.pow(yMouse - yCenterFigure, 2))
                <= Math.pow(figureRadius, 2))
            {
                figureClicked = figure;
                break;
            }
        }

        if(figureClicked!=null)
        {
            gameReference.figureClicked(figureClicked);
        }
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
