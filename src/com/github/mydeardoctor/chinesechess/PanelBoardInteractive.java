package com.github.mydeardoctor.chinesechess;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelBoardInteractive extends PanelBoard implements MouseListener
{
    private Game game;
    public PanelBoardInteractive()
    {
        super();
    }
    public void setGame(Game game)
    {
        super.setGrid(game.getGrid());
        this.game = game;
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

        Location selectedLocation = null;
        int xMouse = e.getX();
        int yMouse = e.getY();
        HashMap<Location, Tile> grid = game.getGrid();
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            int x = gridEntry.getKey().getX();
            int y = gridEntry.getKey().getY();
            int x0Tile = getX0Board() + getTileLength() + getTileLength()*x;
            int y0Tile = getY0Board() + getTileLength() + getTileLength()*y;

            //Closed disk formula.
            if((Math.pow(xMouse - x0Tile, 2) + Math.pow(yMouse - y0Tile, 2)) <= Math.pow(getFigureRadius(), 2))
            {
                selectedLocation = gridEntry.getKey();
                break;
            }
        }

        if(selectedLocation!=null)
        {
            game.handleSelectedLocation(selectedLocation);
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
}
