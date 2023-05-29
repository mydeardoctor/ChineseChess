package com.github.mydeardoctor.chinesechess;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class Game
{//TODO переставить местами. Сверху состояния. Внихзу конкретика
    //Game attributes.
    private final Figure generalRed;
    private final Figure advisorRed1;
    private final Figure advisorRed2;
    private final Figure elephantRed1;
    private final Figure elephantRed2;
    private final Figure horseRed1;
    private final Figure horseRed2;
    private final Figure chariotRed1;
    private final Figure chariotRed2;
    private final Figure cannonRed1;
    private final Figure cannonRed2;
    private final Figure soldierRed1;
    private final Figure soldierRed2;
    private final Figure soldierRed3;
    private final Figure soldierRed4;
    private final Figure soldierRed5;
    private final Figure generalBlack;
    private final Figure advisorBlack1;
    private final Figure advisorBlack2;
    private final Figure elephantBlack1;
    private final Figure elephantBlack2;
    private final Figure horseBlack1;
    private final Figure horseBlack2;
    private final Figure chariotBlack1;
    private final Figure chariotBlack2;
    private final Figure cannonBlack1;
    private final Figure cannonBlack2;
    private final Figure soldierBlack1;
    private final Figure soldierBlack2;
    private final Figure soldierBlack3;
    private final Figure soldierBlack4;
    private final Figure soldierBlack5;
    private HashMap<Location, Tile> grid;
    private State state;
    private Player turn;
    private Phase phase;
    private HashMap<Location, HashSet<Location>> allAllowedMoves;
    private Location prevSelectedLocation;
    private Figure prevSelectedFigure;

    //Replay attributes.
    private Replay replay;

    //GUI attributes.
    private GUI gui;

    //Music player attributes.
    private MusicPlayer musicPlayer;

    public Game()
    {
        //Initialize figures.
        //Red figures.
        generalRed = new GeneralRed();
        advisorRed1 = new AdvisorRed();
        advisorRed2 = new AdvisorRed();
        elephantRed1 = new ElephantRed();
        elephantRed2 = new ElephantRed();
        horseRed1 = new HorseRed();
        horseRed2 = new HorseRed();
        chariotRed1 = new ChariotRed();
        chariotRed2 = new ChariotRed();
        cannonRed1 = new CannonRed();
        cannonRed2 = new CannonRed();
        soldierRed1 = new SoldierRed();
        soldierRed2 = new SoldierRed();
        soldierRed3 = new SoldierRed();
        soldierRed4 = new SoldierRed();
        soldierRed5 = new SoldierRed();
        //Black figures.
        generalBlack = new GeneralBlack();
        advisorBlack1 = new AdvisorBlack();
        advisorBlack2 = new AdvisorBlack();
        elephantBlack1 = new ElephantBlack();
        elephantBlack2 = new ElephantBlack();
        horseBlack1 = new HorseBlack();
        horseBlack2 = new HorseBlack();
        chariotBlack1 = new ChariotBlack();
        chariotBlack2 = new ChariotBlack();
        cannonBlack1 = new CannonBlack();
        cannonBlack2 = new CannonBlack();
        soldierBlack1 = new SoldierBlack();
        soldierBlack2 = new SoldierBlack();
        soldierBlack3 = new SoldierBlack();
        soldierBlack4 = new SoldierBlack();
        soldierBlack5 = new SoldierBlack();

        initializeGrid();
        initializeGameState();
        allAllowedMoves = new HashMap<>();
    }
    private void initializeGrid()
    {
        grid = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, false);
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
    public void setReplay(Replay replay)
    {
        this.replay = replay;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui; //TODO убрать ссылку на текст
    }
    public void setMusicPlayer(MusicPlayer musicPlayer)
    {
        this.musicPlayer = musicPlayer;
    }
    public void start()
    {
        resetGrid();
        resetGameState();
        replay.addToReplayOutput(grid);
        gui.repaint();
        musicPlayer.playMusic();
    }
    private void resetGrid()
    {
        //Clear all figures and selections.
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setFigure(null);
            gridEntry.getValue().setSelected(false);
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

        gui.setStatusBarText(gui.getText().getRedPlayer() + ", " + gui.getText().getChooseFigure());

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
                    if(allowedMoves.size() > 0)
                    {
                        Location origin = gridEntry.getKey();
                        allAllowedMoves.put(origin, allowedMoves);
                    }
                }
            }
        }

        if(allAllowedMoves.size() == 0) //If there are no allowed moves, the game is over.
        {
            gameOver();
        }
    }
    public void handleSelectedLocation(Location selectedLocation)
    {
        if(state == State.OVER)
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
                                replay.addToReplayOutput(grid);
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
        grid.get(locationSelected).setSelected(true);
        HashSet<Location> allowedMoves = allAllowedMoves.get(locationSelected);
        if(allowedMoves != null)
        {
            for(Location allowedMove : allowedMoves)
            {
                grid.get(allowedMove).setSelected(true);
            }
        }

        gui.repaint();
    }
    private void unhighlightEverything()
    {
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setSelected(false);
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
            case RED -> gui.setStatusBarText(gui.getText().getRedPlayer() + ", " +
                        gui.getText().getChooseAnotherFigureOrDestination());
            case BLACK -> gui.setStatusBarText(gui.getText().getBlackPlayer() + ", " +
                          gui.getText().getChooseAnotherFigureOrDestination());
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
                gui.setStatusBarText(gui.getText().getBlackPlayer() + ", " + gui.getText().getChooseFigure());
            }
            case BLACK ->
            {
                setTurn(Player.RED);
                setPhase(Phase.CHOOSE_FIGURE);
                gui.setStatusBarText(gui.getText().getRedPlayer() + ", " + gui.getText().getChooseFigure());
            }
        }
        getAllAllowedMoves();
    }
    private void gameOver()
    {
        setState(State.OVER);
        switch(getTurn())
        {
            case RED -> gui.setStatusBarText(gui.getText().getGameOver() + " " +
                                             gui.getText().getBlackPlayer() + " " +
                                             gui.getText().getWon());
            case BLACK -> gui.setStatusBarText(gui.getText().getGameOver() + " " +
                                               gui.getText().getRedPlayer() + " " +
                                               gui.getText().getWon());
        }
        musicPlayer.stopMusic();
    }
    public void stop()
    {
        clearGrid(); //TODO?
        setState(State.OVER);
        replay.resetReplayOutput();
        musicPlayer.stopMusic();
    }
    private void clearGrid()
    {
        //Clear all figures and selections.
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setFigure(null);
            gridEntry.getValue().setSelected(false);
        }
    }
    public void refreshText()
    {
        switch (getState())
        {
            case RUNNING ->
            {
                String message = "";
                switch(getTurn())
                {
                    case RED -> message = gui.getText().getRedPlayer();
                    case BLACK -> message = gui.getText().getBlackPlayer();
                }
                switch(getPhase())
                {
                    case CHOOSE_FIGURE -> message = message + ", " +
                                                    gui.getText().getChooseFigure();
                    case CHOOSE_DESTINATION -> message = message + ", " +
                                                         gui.getText().getChooseAnotherFigureOrDestination();
                }
                gui.setStatusBarText(message);
            }
            case OVER ->
            {
                switch(getTurn())
                {
                    case RED -> gui.setStatusBarText(gui.getText().getGameOver() + " " +
                                                     gui.getText().getBlackPlayer() + " " +
                                                     gui.getText().getWon());
                    case BLACK -> gui.setStatusBarText(gui.getText().getGameOver() + " " +
                                                       gui.getText().getRedPlayer() + " " +
                                                       gui.getText().getWon());
                }
            }
        }
    }
    public Figure getGeneralRed()
    {
        return generalRed;
    }
    public Figure getAdvisorRed1()
    {
        return advisorRed1;
    }
    public Figure getAdvisorRed2()
    {
        return advisorRed2;
    }
    public Figure getElephantRed1()
    {
        return elephantRed1;
    }
    public Figure getElephantRed2()
    {
        return elephantRed2;
    }
    public Figure getHorseRed1()
    {
        return horseRed1;
    }
    public Figure getHorseRed2()
    {
        return horseRed2;
    }
    public Figure getChariotRed1()
    {
        return chariotRed1;
    }
    public Figure getChariotRed2()
    {
        return chariotRed2;
    }
    public Figure getCannonRed1()
    {
        return cannonRed1;
    }
    public Figure getCannonRed2()
    {
        return cannonRed2;
    }
    public Figure getSoldierRed1()
    {
        return soldierRed1;
    }
    public Figure getSoldierRed2()
    {
        return soldierRed2;
    }
    public Figure getSoldierRed3()
    {
        return soldierRed3;
    }
    public Figure getSoldierRed4()
    {
        return soldierRed4;
    }
    public Figure getSoldierRed5()
    {
        return soldierRed5;
    }
    public Figure getGeneralBlack()
    {
        return generalBlack;
    }
    public Figure getAdvisorBlack1()
    {
        return advisorBlack1;
    }
    public Figure getAdvisorBlack2()
    {
        return advisorBlack2;
    }
    public Figure getElephantBlack1()
    {
        return elephantBlack1;
    }
    public Figure getElephantBlack2()
    {
        return elephantBlack2;
    }
    public Figure getHorseBlack1()
    {
        return horseBlack1;
    }
    public Figure getHorseBlack2()
    {
        return horseBlack2;
    }
    public Figure getChariotBlack1()
    {
        return chariotBlack1;
    }
    public Figure getChariotBlack2()
    {
        return chariotBlack2;
    }
    public Figure getCannonBlack1()
    {
        return cannonBlack1;
    }
    public Figure getCannonBlack2()
    {
        return cannonBlack2;
    }
    public Figure getSoldierBlack1()
    {
        return soldierBlack1;
    }
    public Figure getSoldierBlack2()
    {
        return soldierBlack2;
    }
    public Figure getSoldierBlack3()
    {
        return soldierBlack3;
    }
    public Figure getSoldierBlack4()
    {
        return soldierBlack4;
    }
    public Figure getSoldierBlack5()
    {
        return soldierBlack5;
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
}