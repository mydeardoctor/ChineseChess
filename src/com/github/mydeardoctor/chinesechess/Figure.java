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

    //TODO: Check for king check.
    //TODO: Move common code to separate functions inside Chariot and Cannon.
    abstract HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid);
    DestinationType checkDestinationType(GridLocation destination, HashMap<GridLocation, GridTile> grid) //TODO: Use this function in Game.gridLocationSelected().
    {
        Figure figureAtDestination = grid.get(destination).getFigure();
        if(figureAtDestination == null)
        {
            return DestinationType.EMPTY;
        }
        else
        {
            Player playerOfFigureMoving = this.getPlayer();
            Player playerOfFigureAtDestination = figureAtDestination.getPlayer();
            if(playerOfFigureMoving==playerOfFigureAtDestination)
            {
                return DestinationType.FRIENDLY_FIGURE;
            }
            else
            {
                return DestinationType.ENEMY_FIGURE;
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
