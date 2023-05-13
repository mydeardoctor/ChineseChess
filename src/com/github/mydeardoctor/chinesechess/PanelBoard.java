package com.github.mydeardoctor.chinesechess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.*;
import java.util.Map;
import java.util.Set;

public class PanelBoard extends PanelBackground implements MouseListener
{
    private int tile;
    private int x0board;
    private int y0board;
    private int figureRadius;
    private Game game;
    public PanelBoard(BufferedImage background)
    {
        super(background);
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
        int selectionDiameter = (int)(figureDiameter*1.2);
        int selectionRadius = (int)(figureRadius*1.2);

        //Rectangles.
        g2d.setColor(new Color(207, 92, 1));
        g2d.fillRect(x0board, y0board, tile*10, tile*11);
        g2d.setColor(new Color(252, 174, 63));
        g2d.fillRect(x0board +tile, y0board +tile, tile*8, tile*9);
        g2d.setColor(new Color(69, 175, 252));
        g2d.fillRect(x0board +tile, y0board +tile*5, tile*8, tile);

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
        HashMap<GridLocation, GridTile> gridReference = game.getGrid();
        Set<Map.Entry<GridLocation,GridTile>> gridSet = gridReference.entrySet();
        for(Map.Entry<GridLocation,GridTile> gridEntry : gridSet)
        {
            Figure figure = gridEntry.getValue().getFigure();
            if(figure!=null)
            {
                g2d.drawImage(figure.getIcon(),
                        x0board + tile + tile*gridEntry.getKey().getXgrid() - figureRadius,
                        y0board + tile + tile*gridEntry.getKey().getYgrid() - figureRadius,
                        figureDiameter, figureDiameter, this);
            }
            BufferedImage selection = gridEntry.getValue().getSelection();
            if(selection!=null)
            {
                g2d.drawImage(selection,
                        x0board + tile + tile*gridEntry.getKey().getXgrid() - selectionRadius,
                        y0board + tile + tile*gridEntry.getKey().getYgrid() - selectionRadius,
                        selectionDiameter, selectionDiameter, this);
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
        if(e.getButton()!=MouseEvent.BUTTON1)
        {
            return;
        }

        int xMouse = e.getX();
        int yMouse = e.getY();
        HashMap<GridLocation, GridTile> gridReference = game.getGrid();
        Set<Map.Entry<GridLocation,GridTile>> gridSet = gridReference.entrySet();
        GridLocation gridLocationSelected = null;
        for(Map.Entry<GridLocation,GridTile> gridEntry : gridSet)
        {
            int xGrid = gridEntry.getKey().getXgrid();
            int yGrid = gridEntry.getKey().getYgrid();
            int xCenterTile = x0board + tile + tile*xGrid;
            int yCenterTile = y0board + tile + tile*yGrid;

            //Closed disk formula. (x-x0)^2 + (y-y0)^2 <= r^2
            if((Math.pow(xMouse - xCenterTile, 2) +
                    Math.pow(yMouse - yCenterTile, 2))
                    <= Math.pow(figureRadius, 2))
            {
                gridLocationSelected = gridEntry.getKey();
                break;
            }
        }

        if(gridLocationSelected !=null)
        {
            game.gridLocationSelected(gridLocationSelected);
        }
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
    }
    public void setGame(Game game)
    {
        this.game = game;
    }
}
