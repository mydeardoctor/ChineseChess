package com.github.mydeardoctor.chinesechess;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public class Game
{
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
    private State state;
    private Player turn;
    private Phase phase;
    private HashMap<Location, HashSet<Location>> allAllowedMoves;
    private Location prevSelectedLocation;
    private Figure prevSelectedFigure;

    //GUI attributes.
    private GUI gui;
    private Text text;
    private BufferedImage selection;

    //Music player attributes.
    private MusicPlayer musicPlayer;

    public Game()
    {
        initializeFigures();
        initializeGrid();
        initializeGameState();
        allAllowedMoves = new HashMap<>();
    }
    private void initializeFigures()
    {
        generalRed = new GeneralRed(Player.RED, null);
        advisorRed1 = new AdvisorRed(Player.RED, null);
        advisorRed2 = new AdvisorRed(Player.RED, null);
        elephantRed1 = new ElephantRed(Player.RED, null);
        elephantRed2 = new ElephantRed(Player.RED, null);
        horseRed1 = new Horse(Player.RED, null);
        horseRed2 = new Horse(Player.RED, null);
        chariotRed1 = new Chariot(Player.RED, null);
        chariotRed2 = new Chariot(Player.RED, null);
        cannonRed1 = new Cannon(Player.RED, null);
        cannonRed2 = new Cannon(Player.RED, null);
        soldierRed1 = new SoldierRed(Player.RED, null);
        soldierRed2 = new SoldierRed(Player.RED, null);
        soldierRed3 = new SoldierRed(Player.RED, null);
        soldierRed4 = new SoldierRed(Player.RED, null);
        soldierRed5 = new SoldierRed(Player.RED, null);

        generalBlack = new GeneralBlack(Player.BLACK, null);
        advisorBlack1 = new AdvisorBlack(Player.BLACK, null);
        advisorBlack2 = new AdvisorBlack(Player.BLACK, null);
        elephantBlack1 = new ElephantBlack(Player.BLACK, null);
        elephantBlack2 = new ElephantBlack(Player.BLACK, null);
        horseBlack1 = new Horse(Player.BLACK, null);
        horseBlack2 = new Horse(Player.BLACK, null);
        chariotBlack1 = new Chariot(Player.BLACK, null);
        chariotBlack2 = new Chariot(Player.BLACK, null);
        cannonBlack1 = new Cannon(Player.BLACK, null);
        cannonBlack2 = new Cannon(Player.BLACK, null);
        soldierBlack1 = new SoldierBlack(Player.BLACK, null);
        soldierBlack2 = new SoldierBlack(Player.BLACK, null);
        soldierBlack3 = new SoldierBlack(Player.BLACK, null);
        soldierBlack4 = new SoldierBlack(Player.BLACK, null);
        soldierBlack5 = new SoldierBlack(Player.BLACK, null);
    }
    private void initializeGrid()
    {
        grid = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, null);
                grid.put(location, tile);
            }
        }
    }
    private void initializeGameState()
    {
        setState(State.OVER);
        setTurn(Player.RED);
        setPhase(Phase.CHOOSE_FIGURE);
    }
    private void setIconsToFigures(GUI gui)
    {
        generalRed.setIcon(gui.getGeneralRedIcon());
        advisorRed1.setIcon(gui.getAdvisorRedIcon());
        advisorRed2.setIcon(gui.getAdvisorRedIcon());
        elephantRed1.setIcon(gui.getElephantRedIcon());
        elephantRed2.setIcon(gui.getElephantRedIcon());
        horseRed1.setIcon(gui.getHorseRedIcon());
        horseRed2.setIcon(gui.getHorseRedIcon());
        chariotRed1.setIcon(gui.getChariotRedIcon());
        chariotRed2.setIcon(gui.getChariotRedIcon());
        cannonRed1.setIcon(gui.getCannonRedIcon());
        cannonRed2.setIcon(gui.getCannonRedIcon());
        soldierRed1.setIcon(gui.getSoldierRedIcon());
        soldierRed2.setIcon(gui.getSoldierRedIcon());
        soldierRed3.setIcon(gui.getSoldierRedIcon());
        soldierRed4.setIcon(gui.getSoldierRedIcon());
        soldierRed5.setIcon(gui.getSoldierRedIcon());

        generalBlack.setIcon(gui.getGeneralBlackIcon());
        advisorBlack1.setIcon(gui.getAdvisorBlackIcon());
        advisorBlack2.setIcon(gui.getAdvisorBlackIcon());
        elephantBlack1.setIcon(gui.getElephantBlackIcon());
        elephantBlack2.setIcon(gui.getElephantBlackIcon());
        horseBlack1.setIcon(gui.getHorseBlackIcon());
        horseBlack2.setIcon(gui.getHorseBlackIcon());
        chariotBlack1.setIcon(gui.getChariotBlackIcon());
        chariotBlack2.setIcon(gui.getChariotBlackIcon());
        cannonBlack1.setIcon(gui.getCannonBlackIcon());
        cannonBlack2.setIcon(gui.getCannonBlackIcon());
        soldierBlack1.setIcon(gui.getSoldierBlackIcon());
        soldierBlack2.setIcon(gui.getSoldierBlackIcon());
        soldierBlack3.setIcon(gui.getSoldierBlackIcon());
        soldierBlack4.setIcon(gui.getSoldierBlackIcon());
        soldierBlack5.setIcon(gui.getSoldierBlackIcon());
    }
    public void start()
    {
        resetGrid();
        resetGameState();
        gui.repaint();
        musicPlayer.playMainTheme();
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
    }
    private void resetGameState()
    {
        setState(State.RUNNING);
        setTurn(Player.RED);
        setPhase(Phase.CHOOSE_FIGURE);

        gui.setStatusBarText(text.getRedPlayer() + ", " + text.getChooseFigure());

        getAllAllowedMoves();
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
                if(player == getTurn()) //For every friendly figure.
                {
                    HashSet<Location> allowedMoves = figure.getAllowedMoves(this);
                    if(allowedMoves.size()>0)
                    {
                        Location origin = gridEntry.getKey();
                        allAllowedMoves.put(origin, allowedMoves);
                    }
                }
            }
        }

        if(allAllowedMoves.size()==0) //If there are no allowed moves, the game is over.
        {
            gameOver();
        }
    }
    public void handleSelectedLocation(Location selectedLocation)
    {
        if(state==State.OVER)
        {
            return;
        }

        TileType tileType = getTileType(selectedLocation, grid, turn);

        switch(phase)
        {
            case CHOOSE_FIGURE->
            {
                if(tileType == TileType.FRIENDLY)
                {
                    saveSelectedFigure(selectedLocation);
                    highlightSelectedFigureAndAllowedMoves(selectedLocation);
                    nextPhase();
                }
            }

            case CHOOSE_DESTINATION->
            {
                switch(tileType)
                {
                    case FRIENDLY ->
                    {
                        unhighlightEverything();
                        saveSelectedFigure(selectedLocation);
                        highlightSelectedFigureAndAllowedMoves(selectedLocation);
                    }
                    case ENEMY, EMPTY ->
                    {
                        if(allAllowedMoves.containsKey(prevSelectedLocation)) //If selected figure has allowed moves.
                        {
                            //If this is one of the allowed moves for selected figure.
                            if(allAllowedMoves.get(prevSelectedLocation).contains(selectedLocation))
                            {
                                unhighlightEverything();
                                moveFigure(selectedLocation);
                                nextTurn();
                            }
                        }
                    }
                }
            }
        }
    }
    public TileType getTileType(Location destination, HashMap<Location, Tile> grid, Player turn)
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
    private void saveSelectedFigure(Location locationSelected)
    {
        Figure figureSelected = grid.get(locationSelected).getFigure();
        prevSelectedLocation = locationSelected;
        prevSelectedFigure = figureSelected;
    }
    private void highlightSelectedFigureAndAllowedMoves(Location locationSelected)
    {
        grid.get(locationSelected).setSelection(selection);
        HashSet<Location> allowedMoves = allAllowedMoves.get(locationSelected);
        if(allowedMoves!=null)
        {
            for(Location allowedMove : allowedMoves)
            {
                grid.get(allowedMove).setSelection(selection);
            }
        }

        gui.repaint();
    }
    private void unhighlightEverything()
    {
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setSelection(null);
        }
    }
    private void moveFigure(Location locationSelected)
    {
        grid.get(prevSelectedLocation).setFigure(null);           //Move figure from previous location...
        grid.get(locationSelected).setFigure(prevSelectedFigure); //...to a new location.

        gui.repaint();
        musicPlayer.playSfx();
    }
    private void nextPhase()
    {
        setPhase(Phase.CHOOSE_DESTINATION);
        switch(getTurn())
        {
            case RED -> gui.setStatusBarText(text.getRedPlayer() + ", " + text.getChooseAnotherFigureOrDestination());
            case BLACK -> gui.setStatusBarText(text.getBlackPlayer() + ", " + text.getChooseAnotherFigureOrDestination());
        }
    }
    private void nextTurn()
    {
        switch(getTurn())
        {
            case RED ->
            {
                setTurn(Player.BLACK);
                setPhase(Phase.CHOOSE_FIGURE);
                gui.setStatusBarText(text.getBlackPlayer() + ", " + text.getChooseFigure());
            }
            case BLACK ->
            {
                setTurn(Player.RED);
                setPhase(Phase.CHOOSE_FIGURE);
                gui.setStatusBarText(text.getRedPlayer() + ", " + text.getChooseFigure());
            }
        }
        getAllAllowedMoves();
    }
    private void gameOver()
    {
        setState(State.OVER);
        switch(getTurn())
        {
            case RED -> gui.setStatusBarText(text.getGameOver() + " " + text.getBlackPlayer() + " " + text.getWon());
            case BLACK -> gui.setStatusBarText(text.getGameOver() + " " + text.getRedPlayer() + " " + text.getWon());
        }
        musicPlayer.stopMainTheme();
    }
    public void refreshText(Text text)
    {
        this.text = text;

        switch (getState())
        {
            case RUNNING ->
            {
                String message = "";
                switch(getTurn())
                {
                    case RED -> message = text.getRedPlayer();
                    case BLACK -> message = text.getBlackPlayer();
                }
                switch(getPhase())
                {
                    case CHOOSE_FIGURE -> message = message + ", " + text.getChooseFigure();
                    case CHOOSE_DESTINATION -> message = message + ", " + text.getChooseAnotherFigureOrDestination();
                }
                gui.setStatusBarText(message);
            }
            case OVER ->
            {
                switch(getTurn())
                {
                    case RED -> gui.setStatusBarText(text.getGameOver() + " " + text.getBlackPlayer() + " " + text.getWon());
                    case BLACK -> gui.setStatusBarText(text.getGameOver() + " " + text.getRedPlayer() + " " + text.getWon());
                }
            }
        }
    }
    public Figure getGeneralRed()
    {
        return generalRed;
    }
    public Figure getGeneralBlack()
    {
        return generalBlack;
    }
    synchronized public HashMap<Location, Tile> getGrid()
    {
        return grid;
    }
    public State getState()
    {
        return state;
    }
    public void setState(State state)
    {
        this.state = state;
    }
    public Player getTurn()
    {
        return turn;
    }
    public void setTurn(Player turn)
    {
        this.turn = turn;
    }
    public Phase getPhase()
    {
        return phase;
    }
    public void setPhase(Phase phase)
    {
        this.phase = phase;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
        text = gui.getText();
        selection = gui.getSelection();

        setIconsToFigures(gui);
    }
    public void setMusicPlayer(MusicPlayer musicPlayer)
    {
        this.musicPlayer = musicPlayer;
    }
}