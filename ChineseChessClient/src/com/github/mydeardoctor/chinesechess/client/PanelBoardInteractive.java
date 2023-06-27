package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PanelBoardInteractive extends PanelBoard implements MouseListener
{
    //Game attributes.
    private Game game;

    public PanelBoardInteractive(HashMap<Class<? extends Figure>, BufferedImage> imagesOfFigures,
                                 BufferedImage selectionFigure)
    {
        super(imagesOfFigures, selectionFigure);
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
        if
        (
            (e.getButton() != MouseEvent.BUTTON1) ||
            game.getState().equals(State.OVER) ||
            ((game.getClass().equals(GameSinglePlayer.class) || game.getClass().equals(GameOnlineMultiplayer.class)) &&
            game.getTurn().equals(game.getOpponentSide()))
        )
        {
            return;
        }

        Location selectedLocation = null;
        int xMouse = e.getX();
        int yMouse = e.getY();
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            int x = gridEntry.getKey().x();
            int y = gridEntry.getKey().y();
            int x0Tile = x0Board + tileLength + tileLength*x;
            int y0Tile = y0Board + tileLength + tileLength*y;

            //Closed disk formula.
            if((Math.pow(xMouse - x0Tile, 2) + Math.pow(yMouse - y0Tile, 2)) <= Math.pow(figureRadius, 2))
            {
                selectedLocation = gridEntry.getKey();
                break;
            }
        }

        if(selectedLocation != null)
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
