package com.github.mydeardoctor.chinesechess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    public Figure(Player player, BufferedImage icon)
    {
        this.player = player;
        this.icon = icon;
    }

    //TODO: Check for king check.
    public HashSet<GridLocation> getAllowedMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn, BufferedImage generalRed, BufferedImage generalBlack)
    {
        HashSet<GridLocation> allowedMoves = new HashSet<>();
        HashSet<GridLocation> possibleMoves = getPossibleMoves(origin, grid, turn);
        HashSet<GridLocation> enemyPossibleMoves = new HashSet<>();

        Set<Map.Entry<GridLocation,GridTile>> gridSet = grid.entrySet();

        for(GridLocation possibleMove : possibleMoves)
        {
            //Move figure
            Figure figureThatWasAtOrigin = grid.get(origin).getFigure();
            Figure figureThatWasAtDestination = grid.get(possibleMove).getFigure();
            grid.get(origin).setFigure(null);
            grid.get(possibleMove).setFigure(this);

            //General sight check
            //Find General Red and General Black
            GridLocation generalRedLocation = null;
            GridLocation generalBlackLocation = null;
            gridSet = grid.entrySet();
            for(Map.Entry<GridLocation,GridTile> gridEntry : gridSet)
            {
                Figure figure = gridEntry.getValue().getFigure();
                if(figure!=null)
                {
                    BufferedImage icon = figure.getIcon();
                    if(icon == generalRed)
                    {
                        generalRedLocation = gridEntry.getKey();
                    }
                    else if(icon == generalBlack)
                    {
                        generalBlackLocation = gridEntry.getKey();
                    }
                }
            }
            if((generalRedLocation==null) || (generalBlackLocation == null)) //General was killed. Illegal move.
            {
                //Unmove figure
                grid.get(origin).setFigure(figureThatWasAtOrigin);
                grid.get(possibleMove).setFigure(figureThatWasAtDestination);
                continue;
            }
            if(generalRedLocation.getXgrid()==generalBlackLocation.getXgrid())
            {
                boolean generalsSeeEachOther = true;
                int x = generalBlackLocation.getXgrid();
                for(int y = generalBlackLocation.getYgrid()+1; y < generalRedLocation.getYgrid(); y++)
                {
                    GridLocation destination = new GridLocation(x, y);
                    GridTileType gridTileType = Game.checkGridTileType(destination, grid, turn);
                    if(gridTileType!=GridTileType.EMPTY)
                    {
                        generalsSeeEachOther = false;
                        break;
                    }
                }
                if(generalsSeeEachOther) //Generals see each other. Illegal move.
                {
                    //Unmove figure
                    grid.get(origin).setFigure(figureThatWasAtOrigin);
                    grid.get(possibleMove).setFigure(figureThatWasAtDestination);
                    continue;
                }
            }

            //General exposed check
            boolean generalExposed = false;
            //Work out my General and next turn
            GridLocation generalLocation;
            Player nextTurn;
            if(turn == Player.RED)
            {
                generalLocation = generalRedLocation;
                nextTurn = Player.BLACK;
            }
            else
            {
                generalLocation = generalBlackLocation;
                nextTurn = Player.RED;
            }
            enemyPossibleMoves.clear();
            for(Map.Entry<GridLocation,GridTile> gridEntry : gridSet)
            {
                Figure figure = gridEntry.getValue().getFigure();
                if(figure!=null)
                {
                    Player player = figure.getPlayer();
                    if(player==nextTurn)
                    {
                        enemyPossibleMoves = figure.getPossibleMoves(gridEntry.getKey(), grid, nextTurn);
                        if(enemyPossibleMoves.contains(generalLocation))
                        {
                            generalExposed = true;
                            break;
                        }
                    }
                }
            }
            if(generalExposed==false)
            {
                allowedMoves.add(possibleMove);
            }
            //Unmove figure
            grid.get(origin).setFigure(figureThatWasAtOrigin);
            grid.get(possibleMove).setFigure(figureThatWasAtDestination);
        }


        possibleMoves = null;
        enemyPossibleMoves = null;
        return allowedMoves;
    }
    public abstract HashSet<GridLocation> getPossibleMoves(GridLocation origin, HashMap<GridLocation, GridTile> grid, Player turn);
    public Player getPlayer()
    {
        return player;
    }
    public BufferedImage getIcon()
    {
        return icon;
    }
}
