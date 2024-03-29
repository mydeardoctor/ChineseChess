package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Side;
import com.github.mydeardoctor.chinesechess.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Game
{
    //Game attributes.
    protected State state;
    protected Side turn;
    private Phase phase;
    protected Side playerSide;
    protected Side opponentSide;
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
    protected final HashMap<Location, Tile> grid;
    protected final HashMap<Location, HashSet<Location>> allAllowedMoves;
    private Location previouslySelectedLocation;

    //Protocol attributes.
    protected Protocol protocol;

    //Replay attributes.
    protected Replay replay;

    //GUI attributes.
    protected GUI gui;

    //Music player attributes.
    protected MusicPlayer musicPlayer;

    public Game()
    {
        initializeGameState();

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

        grid = initializeGrid();
        allAllowedMoves = new HashMap<>();
    }
    private void initializeGameState()
    {
        state = State.OVER;
        turn = Side.RED;
        phase = Phase.CHOOSE_FIGURE;
    }
    private HashMap<Location, Tile> initializeGrid()
    {
        HashMap<Location, Tile> grid = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, false);
                grid.put(location, tile);
            }
        }
        return grid;
    }
    public void setProtocol(Protocol protocol)
    {
        this.protocol = protocol;
    }
    public void setReplay(Replay replay)
    {
        this.replay = replay;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void setMusicPlayer(MusicPlayer musicPlayer)
    {
        this.musicPlayer = musicPlayer;
    }
    public void start(String opponentNickname, Side playerSide, Side opponentSide)
    {
        resetGameState();
        resetGrid();

        replay.resetReplayOutput();
        replay.addToReplayOutput(grid);
        gui.setStatusBarText(gui.getText().getRedPlayer() + ", " + gui.getText().getChooseFigure());
        musicPlayer.playMusic();

        initializeSides(opponentNickname, playerSide, opponentSide);
        decideWhoseNextTurnIs();
    }
    private void resetGameState()
    {
        state = State.RUNNING;
        turn = Side.RED;
        phase = Phase.CHOOSE_FIGURE;
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
    protected abstract void initializeSides(String opponentNickname, Side playerSide, Side opponentSide);
    private void decideWhoseNextTurnIs()
    {
        if(getAllAllowedMoves())
        {
            if(turn.equals(playerSide))
            {
                playerTurn();
            }
            else
            {
                opponentTurn();
            }
        }
        else
        {
            over(turn);
        }
    };
    private void playerTurn()
    {
        //Handle user mouse clicks.
    }
    protected abstract void opponentTurn();
    private boolean getAllAllowedMoves()
    {
        allAllowedMoves.clear();

        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            Figure figure = gridEntry.getValue().getFigure();
            if(figure != null)
            {
                Side side = figure.getSide();
                if(side.equals(turn)) //For every friendly figure.
                {
                    HashSet<Location> allowedMoves = figure.getAllowedMoves(this, turn);
                    if(allowedMoves.size() != 0)
                    {
                        Location origin = gridEntry.getKey();
                        allAllowedMoves.put(origin, allowedMoves);
                    }
                }
            }
        }

        if(allAllowedMoves.size() != 0)
        {
            return true;
        }
        else //If there are no allowed moves, the game is over.
        {
            return false;
        }
    }
    public void handleSelectedLocation(Location selectedLocation)
    {
        TileType tileType = getTileType(selectedLocation, grid, turn);

        switch(phase)
        {
            case CHOOSE_FIGURE->
            {
                if(tileType.equals(TileType.FRIENDLY))
                {
                    saveSelectedLocation(selectedLocation);
                    unhighlightEverything();
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
                        saveSelectedLocation(selectedLocation);
                        unhighlightEverything();
                        highlightSelectedFigureAndAllowedMoves(selectedLocation);
                    }
                    case ENEMY, EMPTY ->
                    {
                        if(allAllowedMoves.containsKey(previouslySelectedLocation)) //If selected figure has allowed moves.
                        {
                            //If this is one of the allowed moves for selected figure.
                            if(allAllowedMoves.get(previouslySelectedLocation).contains(selectedLocation))
                            {
                                unhighlightEverything();
                                moveFigure(previouslySelectedLocation, selectedLocation);
                                musicPlayer.playSfx();
                                replay.addToReplayOutput(grid);
                                nextTurn();
                            }
                        }
                    }
                }
            }
        }
    }
    public TileType getTileType(Location destination, HashMap<Location, Tile> grid, Side turn)
    {
        Figure figureAtDestination = grid.get(destination).getFigure();
        if(figureAtDestination == null)
        {
            return TileType.EMPTY;
        }
        else
        {
            Side sideOfFigureAtDestination = figureAtDestination.getSide();
            if(sideOfFigureAtDestination.equals(turn))
            {
                return TileType.FRIENDLY;
            }
            else
            {
                return TileType.ENEMY;
            }
        }
    }
    private void saveSelectedLocation(Location locationSelected)
    {
        previouslySelectedLocation = locationSelected;
    }
    private void unhighlightEverything()
    {
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setSelected(false);
        }

        gui.repaint();
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
    protected void moveFigure(Location origin, Location destination)
    {
        Figure selectedFigure = grid.get(origin).getFigure();
        grid.get(origin).setFigure(null);                       //Move figure from previous location...
        grid.get(destination).setFigure(selectedFigure);        //...to a new location.

        gui.repaint();
    }
    private void nextPhase()
    {
        phase = Phase.CHOOSE_DESTINATION;
        switch(turn)
        {
            case RED -> gui.setStatusBarText(gui.getText().getRedPlayer() + ", " +
                        gui.getText().getChooseAnotherFigureOrDestination());
            case BLACK -> gui.setStatusBarText(gui.getText().getBlackPlayer() + ", " +
                          gui.getText().getChooseAnotherFigureOrDestination());
        }
    }
    protected void nextTurn()
    {
        switch(turn)
        {
            case RED ->
            {
                turn = Side.BLACK;
                phase = Phase.CHOOSE_FIGURE;
                gui.setStatusBarText(gui.getText().getBlackPlayer() + ", " + gui.getText().getChooseFigure());
            }
            case BLACK ->
            {
                turn = Side.RED;
                phase = Phase.CHOOSE_FIGURE;
                gui.setStatusBarText(gui.getText().getRedPlayer() + ", " + gui.getText().getChooseFigure());
            }
        }

        decideWhoseNextTurnIs();
    }
    public void over(Side turn)
    {
        state = State.OVER;

        switch(turn)
        {
            case RED -> gui.setStatusBarText(gui.getText().getGameOver() + " " +
                                             gui.getText().getBlackPlayer() + " " +
                                             gui.getText().getWon());
            case BLACK -> gui.setStatusBarText(gui.getText().getGameOver() + " " +
                                               gui.getText().getRedPlayer() + " " +
                                               gui.getText().getWon());
        }

        unhighlightEverything();
        musicPlayer.stopMusic();
    }
    public void stop()
    {
        state = State.OVER;
        replay.resetReplayOutput();
        musicPlayer.stopMusic();
    }
    public void refreshText()
    {
        switch (state)
        {
            case RUNNING ->
            {
                String message = "";
                switch(turn)
                {
                    case RED -> message = gui.getText().getRedPlayer();
                    case BLACK -> message = gui.getText().getBlackPlayer();
                }
                switch(phase)
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
                gui.setStatusBarText(gui.getText().getGameOver());
            }
        }
    }
    public State getState()
    {
        return state;
    }
    public Side getTurn()
    {
        return turn;
    }
    public Side getOpponentSide()
    {
        return opponentSide;
    }
    public Figure getGeneralRed()
    {
        return generalRed;
    }
    public Figure getGeneralBlack()
    {
        return generalBlack;
    }
    public HashMap<Location, Tile> getGrid()
    {
        return grid;
    }
}