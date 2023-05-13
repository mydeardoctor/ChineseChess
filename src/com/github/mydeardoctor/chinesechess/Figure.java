package com.github.mydeardoctor.chinesechess;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public abstract class Figure
{
    private Player player;
    private BufferedImage icon;
    public Figure(Player player, BufferedImage icon)
    {
        this.player = player;
        this.icon = icon;
    }
    //TODO: генерал определяется по буфферед имадж. это буллщит, потому что они могут не загрузиться.генерал должен определяться по объекту general
    public HashSet<Location> getAllowedMoves(HashMap<Location, Tile> grid, Player turn, Figure generalRed, Figure generalBlack)
    {
        HashSet<Location> allowedMoves = new HashSet<>();
        HashSet<Location> possibleMoves = getPossibleMoves(grid, turn, generalRed, generalBlack);
        HashSet<Location> enemyPossibleMoves = new HashSet<>();
        Location origin = Game.findLocationOfFigure(this, grid);

        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();

        for(Location possibleMove : possibleMoves)
        {
            //Move figure
            Figure figureThatWasAtOrigin = grid.get(origin).getFigure();
            Figure figureThatWasAtDestination = grid.get(possibleMove).getFigure();
            grid.get(origin).setFigure(null);
            grid.get(possibleMove).setFigure(this);

            //General sight check
            //Find General Red and General Black
            Location generalRedLocation = Game.findLocationOfFigure(generalRed, grid);
            Location generalBlackLocation = Game.findLocationOfFigure(generalBlack, grid);
            if((generalRedLocation==null) || (generalBlackLocation == null)) //General was killed. Illegal move.
            {
                //Unmove figure
                grid.get(origin).setFigure(figureThatWasAtOrigin);
                grid.get(possibleMove).setFigure(figureThatWasAtDestination);
                continue;
            }
            if(generalRedLocation.getX()==generalBlackLocation.getX())
            {
                boolean generalsSeeEachOther = true;
                int x = generalBlackLocation.getX();
                for(int y = generalBlackLocation.getY()+1; y < generalRedLocation.getY(); y++)
                {
                    Location destination = new Location(x, y);
                    TileType tileType = Game.checkTileType(destination, grid, turn);
                    if(tileType != TileType.EMPTY)
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
            Location generalLocation;
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
            for(Map.Entry<Location, Tile> gridEntry : gridSet)
            {
                Figure figure = gridEntry.getValue().getFigure();
                if(figure!=null)
                {
                    Player player = figure.getPlayer();
                    if(player==nextTurn)
                    {
                        enemyPossibleMoves = figure.getPossibleMoves(grid, nextTurn, generalRed, generalBlack);
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
    public abstract HashSet<Location> getPossibleMoves(HashMap<Location, Tile> grid, Player turn, Figure generalRed, Figure generalBlack);
    public Player getPlayer()
    {
        return player;
    }
    public BufferedImage getIcon()
    {
        return icon;
    }
}