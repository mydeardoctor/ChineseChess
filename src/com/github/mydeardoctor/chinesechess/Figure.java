package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    Figure(Player player, BufferedImage icon)
    {
        this.player = player;
        this.icon = icon;
    }
    abstract HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid);
    boolean isPossibleMove(GridLocation destination, HashMap<GridLocation, GridTile> grid)
    {
        Figure figureAtDestination = grid.get(destination).getFigure();
        if(figureAtDestination == null)
        {
            return true;
        }
        else
        {
            Player playerOfFigureMoving = this.getPlayer();
            Player playerOfFigureAtDestination = figureAtDestination.getPlayer();
            if(playerOfFigureMoving!=playerOfFigureAtDestination)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    public Player getPlayer()
    {
        return player;
    }
    public BufferedImage getIcon()
    {
        return icon;
    }
}
