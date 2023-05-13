package com.github.mydeardoctor.chinesechess;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Game
{
    //GUI attributes.
    private Text text;
    private PanelBoard panelBoard;
    private JLabel statusBar;
    private BufferedImage selection;

    //Game attributes.
    private Figure generalRed;
    private Figure advisorRed1;
    private Figure advisorRed2;
    private Figure elephantRed1;
    private Figure elephantRed2;
    private Figure horseRed1;
    private Figure horseRed2;
    private Figure chariotRed1;
    private Figure chariotRed2;
    private Figure cannonRed1;
    private Figure cannonRed2;
    private Figure soldierRed1;
    private Figure soldierRed2;
    private Figure soldierRed3;
    private Figure soldierRed4;
    private Figure soldierRed5;
    private Figure generalBlack;
    private Figure advisorBlack1;
    private Figure advisorBlack2;
    private Figure elephantBlack1;
    private Figure elephantBlack2;
    private Figure horseBlack1;
    private Figure horseBlack2;
    private Figure chariotBlack1;
    private Figure chariotBlack2;
    private Figure cannonBlack1;
    private Figure cannonBlack2;
    private Figure soldierBlack1;
    private Figure soldierBlack2;
    private Figure soldierBlack3;
    private Figure soldierBlack4;
    private Figure soldierBlack5;
    private HashMap<Location, Tile> grid;
    private Player turn;
    private Phase phase;
    private Location prevSelectedLocation;
    private Figure prevSelectedFigure;
    private HashMap<Location, HashSet<Location>> allAllowedMoves;

    public Game(GUI gui)
    {
        text = gui.getText();
        panelBoard = gui.getPanelBoard();
        statusBar = gui.getStatusBar();
        selection = gui.getSelection();
        initializeFigures(gui);
        initializeGrid();
        allAllowedMoves = new HashMap<>();
    }
    private void initializeFigures(GUI gui)
    {
        generalRed = new GeneralRed(Player.RED, gui.getGeneralRedIcon());
        advisorRed1 = new AdvisorRed(Player.RED, gui.getAdvisorRedIcon());
        advisorRed2 = new AdvisorRed(Player.RED, gui.getAdvisorRedIcon());
        elephantRed1 = new ElephantRed(Player.RED, gui.getElephantRedIcon());
        elephantRed2 = new ElephantRed(Player.RED, gui.getElephantRedIcon());
        horseRed1 = new Horse(Player.RED, gui.getHorseRedIcon());
        horseRed2 = new Horse(Player.RED, gui.getHorseRedIcon());
        chariotRed1 = new Chariot(Player.RED, gui.getChariotRedIcon());
        chariotRed2 = new Chariot(Player.RED, gui.getChariotRedIcon());
        cannonRed1 = new Cannon(Player.RED, gui.getCannonRedIcon());
        cannonRed2 = new Cannon(Player.RED, gui.getCannonRedIcon());
        soldierRed1 = new SoldierRed(Player.RED, gui.getSoldierRedIcon());
        soldierRed2 = new SoldierRed(Player.RED, gui.getSoldierRedIcon());
        soldierRed3 = new SoldierRed(Player.RED, gui.getSoldierRedIcon());
        soldierRed4 = new SoldierRed(Player.RED, gui.getSoldierRedIcon());
        soldierRed5 = new SoldierRed(Player.RED, gui.getSoldierRedIcon());

        generalBlack = new GeneralBlack(Player.BLACK, gui.getGeneralBlackIcon());
        advisorBlack1 = new AdvisorBlack(Player.BLACK, gui.getAdvisorBlackIcon());
        advisorBlack2 = new AdvisorBlack(Player.BLACK, gui.getAdvisorBlackIcon());
        elephantBlack1 = new ElephantBlack(Player.BLACK, gui.getElephantBlackIcon());
        elephantBlack2 = new ElephantBlack(Player.BLACK, gui.getElephantBlackIcon());
        horseBlack1 = new Horse(Player.BLACK, gui.getHorseBlackIcon());
        horseBlack2 = new Horse(Player.BLACK, gui.getHorseBlackIcon());
        chariotBlack1 = new Chariot(Player.BLACK, gui.getChariotBlackIcon());
        chariotBlack2 = new Chariot(Player.BLACK, gui.getChariotBlackIcon());
        cannonBlack1 = new Cannon(Player.BLACK, gui.getCannonBlackIcon());
        cannonBlack2 = new Cannon(Player.BLACK, gui.getCannonBlackIcon());
        soldierBlack1 = new SoldierBlack(Player.BLACK, gui.getSoldierBlackIcon());
        soldierBlack2 = new SoldierBlack(Player.BLACK, gui.getSoldierBlackIcon());
        soldierBlack3 = new SoldierBlack(Player.BLACK, gui.getSoldierBlackIcon());
        soldierBlack4 = new SoldierBlack(Player.BLACK, gui.getSoldierBlackIcon());
        soldierBlack5 = new SoldierBlack(Player.BLACK, gui.getSoldierBlackIcon());
    }
    private void initializeGrid()
    {
        grid = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <=8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, null);
                grid.put(location, tile);
            }
        }
    }
    public void start()
    {
        resetGrid();
        initializeGameState();
    }
    private void resetGrid()
    {
        //Clear all figures and selections.
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setFigure(null);
            gridEntry.getValue().setSelection(null);
        }

        //Put red figures.
        grid.get(new Location(0,6)).setFigure(soldierRed1);
        grid.get(new Location(2,6)).setFigure(soldierRed2);
        grid.get(new Location(4,6)).setFigure(soldierRed3);
        grid.get(new Location(6,6)).setFigure(soldierRed4);
        grid.get(new Location(8,6)).setFigure(soldierRed5);
        grid.get(new Location(1,7)).setFigure(cannonRed1);
        grid.get(new Location(7,7)).setFigure(cannonRed2);
        grid.get(new Location(0,9)).setFigure(chariotRed1);
        grid.get(new Location(1,9)).setFigure(horseRed1);
        grid.get(new Location(2,9)).setFigure(elephantRed1);
        grid.get(new Location(3,9)).setFigure(advisorRed1);
        grid.get(new Location(4,9)).setFigure(generalRed);
        grid.get(new Location(5,9)).setFigure(advisorRed2);
        grid.get(new Location(6,9)).setFigure(elephantRed2);
        grid.get(new Location(7,9)).setFigure(horseRed2);
        grid.get(new Location(8,9)).setFigure(chariotRed2);

        //Put black figures.
        grid.get(new Location(0,0)).setFigure(chariotBlack1);
        grid.get(new Location(1,0)).setFigure(horseBlack1);
        grid.get(new Location(2,0)).setFigure(elephantBlack1);
        grid.get(new Location(3,0)).setFigure(advisorBlack1);
        grid.get(new Location(4,0)).setFigure(generalBlack);
        grid.get(new Location(5,0)).setFigure(advisorBlack2);
        grid.get(new Location(6,0)).setFigure(elephantBlack2);
        grid.get(new Location(7,0)).setFigure(horseBlack2);
        grid.get(new Location(8,0)).setFigure(chariotBlack2);
        grid.get(new Location(1,2)).setFigure(cannonBlack1);
        grid.get(new Location(7,2)).setFigure(cannonBlack2);
        grid.get(new Location(0,3)).setFigure(soldierBlack1);
        grid.get(new Location(2,3)).setFigure(soldierBlack2);
        grid.get(new Location(4,3)).setFigure(soldierBlack3);
        grid.get(new Location(6,3)).setFigure(soldierBlack4);
        grid.get(new Location(8,3)).setFigure(soldierBlack5);

        panelBoard.repaint();
    }
    private void initializeGameState()
    {
        turn = Player.RED;
        phase = Phase.CHOOSE_FIGURE;
        statusBar.setText(text.getRedPlayer() + ", " + text.getChooseFigure());

        getAllAllowedMoves();
    }
    public void handleSelectedLocation(Location selectedLocation)
    {
        TileType tileType;
        tileType = Game.checkTileType(selectedLocation, grid, turn);

        switch(phase)
        {
            case CHOOSE_FIGURE->
            {
                if(tileType == TileType.FRIENDLY)
                {
                    saveAndHighlightSelectedFigure(selectedLocation);

                    phase = Phase.CHOOSE_DESTINATION;
                    switch(turn)
                    {
                        case RED -> statusBar.setText(text.getRedPlayer()+", "+text.getChooseDestination());
                        case BLACK -> statusBar.setText(text.getBlackPlayer()+", "+text.getChooseDestination());
                    }
                }
            }

            case CHOOSE_DESTINATION->
            {
                switch(tileType)
                {
                    case FRIENDLY ->
                    {
                        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet(); //Unhighlight everything.
                        for(Map.Entry<Location, Tile> gridEntry : gridSet)
                        {
                            gridEntry.getValue().setSelection(null);
                        }

                        saveAndHighlightSelectedFigure(selectedLocation);
                    }
                    case ENEMY, EMPTY ->
                    {
                        if(allAllowedMoves.containsKey(prevSelectedLocation)) //If chosen figure has possible moves.
                        {
                            if(allAllowedMoves.get(prevSelectedLocation).contains(selectedLocation)) //If this move is possible for this figure.
                            {
                                moveFigure(selectedLocation);
                            }
                        }
                    }
                }
            }
        }
    }
    private void getAllAllowedMoves()
    {
        allAllowedMoves.clear();

        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            Figure figure = gridEntry.getValue().getFigure();
            if(figure!=null)
            {
                Player player = figure.getPlayer();
                if(player == turn)
                {
                    Location origin = gridEntry.getKey();
                    HashSet<Location> allowedMoves = figure.getAllowedMoves(grid, turn, generalRed, generalBlack);
                    if(allowedMoves.size()>0)
                    {
                        allAllowedMoves.put(origin, allowedMoves);
                    }
                }
            }
        }

        if(allAllowedMoves.size()==0)
        {
            statusBar.setText("Endgame"); //TODO: если мувов нет, то эндгейм.
        }
    }
    public static Location findLocationOfFigure(Figure figure, HashMap<Location, Tile> grid)
    {
        Location location = null;
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            Figure figureTemp = gridEntry.getValue().getFigure();
            if(figureTemp!=null)
            {
                if(figureTemp.equals(figure))
                {
                    location = gridEntry.getKey();
                    break;
                }
            }
        }
        return location;
    }
    public static TileType checkTileType(Location destination, HashMap<Location, Tile> grid, Player turn)
    {
        Figure figureAtDestination = grid.get(destination).getFigure();
        if(figureAtDestination == null)
        {
            return TileType.EMPTY;
        }
        else
        {
            Player playerOfFigureAtDestination = figureAtDestination.getPlayer();
            if(playerOfFigureAtDestination==turn)
            {
                return TileType.FRIENDLY;
            }
            else
            {
                return TileType.ENEMY;
            }
        }
    }
    private void saveAndHighlightSelectedFigure(Location locationSelected)
    {
        Figure figureSelected = grid.get(locationSelected).getFigure();
        prevSelectedLocation = locationSelected;                                        //Save selected location.
        prevSelectedFigure = figureSelected;                                                    //Save selected figure.

        grid.get(locationSelected).setSelection(selection);                           //Highlight selected figure.
        HashSet<Location> possibleMoves = allAllowedMoves.get(locationSelected); //Highlight possible moves.
        if(possibleMoves!=null)
        {
            for(Location possibleMove : possibleMoves)
            {
                grid.get(possibleMove).setSelection(selection);
            }
        }

        panelBoard.repaint();
    }
    private void moveFigure(Location locationSelected)
    {
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet(); //Unhighlight everything.
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setSelection(null);
        }

        grid.get(prevSelectedLocation).setFigure(null);           //Move figure from previous location...
        grid.get(locationSelected).setFigure(prevSelectedFigure); //...to new location.

        panelBoard.repaint();

        switch(turn)
        {
            case RED ->
            {
                turn = Player.BLACK;
                phase = Phase.CHOOSE_FIGURE;
                statusBar.setText(text.getBlackPlayer() + ", " + text.getChooseFigure());
            }
            case BLACK ->
            {
                turn = Player.RED;
                phase = Phase.CHOOSE_FIGURE;
                statusBar.setText(text.getRedPlayer() + ", " + text.getChooseFigure());
            }
        }
        getAllAllowedMoves();  //check for endgame. if not.
    }
    public void refreshText(Text text)
    {
        this.text = text;
        statusBar.setText(" ");
    }
    public HashMap<Location, Tile> getGrid()
    {
        return grid;
    }
}