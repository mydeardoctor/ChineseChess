package com.github.mydeardoctor.chinesechess;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

public class Replay
{
    //Replay attributes.
    static final int FAILURE = -1;
    static final int SUCCESS = 0;
    private State state;
    private ArrayList<Move> replayOutput;
    private ArrayList<Move> replayInput;
    private int movesIndex;
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

    //GUI attributes.
    private GUI gui;

    public Replay()
    {
        setState(State.OVER);
        initializeReplayOutput();
        initializeReplayInput();
        resetMovesIndex();
        initializeFigures();
        initializeGrid();
    }
    private void initializeReplayOutput()
    {
        replayOutput = new ArrayList<>();
    }
    private void initializeReplayInput()
    {
        replayInput = new ArrayList<>();
    }
    private void resetMovesIndex()
    {
        movesIndex = -1;
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
    public void setGui(GUI gui)
    {
        this.gui = gui;
        setIconsToFigures(gui);
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
    public void clearReplayOutput()
    {
        replayOutput.clear();
    }
    public void addToReplayOutput(Location origin, Location destination)
    {
        replayOutput.add(new Move(origin, destination));
    }
    public boolean getIsReplayOutputEmpty()
    {
        if(replayOutput.size() <= 0)
        {
            return true;
        }
        else //replayOutput.size() > 0
        {
            return false;
        }
    }
    public int save(File selectedFile)
    {
        String path = null;
        try
        {
            path = selectedFile.getCanonicalPath();
            if(path.endsWith(".ccrpl") == false)
            {
                path = path.concat(".ccrpl"); //Add extension if there is none.
            }
        }
        catch (Exception e)
        {
            return FAILURE;
        }

        File fileWithExtension = null;
        if(path != null)
        {
            fileWithExtension = new File(path);
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(fileWithExtension);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(replayOutput);
        }
        catch(Exception e)
        {
            return FAILURE;
        }
        return SUCCESS;
    }
    public int load(File selectedFile)
    {
        try(FileInputStream fileInputStream = new FileInputStream(selectedFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            replayInput = (ArrayList<Move>)objectInputStream.readObject();
        }
        catch(Exception e)
        {
            return FAILURE;
        }
        return SUCCESS;
    }
    public void start()
    {
        setState(State.RUNNING);
        resetMovesIndex();
        resetGrid();
        gui.enableButtonNextMove();
        gui.repaint();
    }
    private void resetGrid()
    {
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
    public void nextMove()
    {
        incrementMovesIndex();

        if(getMovesIndex() <= (replayInput.size()-1))
        {
            Move move = replayInput.get(movesIndex);
            Location origin = move.getOrigin();
            Location destination = move.getDestination();

            Figure figure = grid.get(origin).getFigure();
            grid.get(origin).setFigure(null);
            grid.get(destination).setFigure(figure);

            gui.repaint();

            if((getMovesIndex()+1) > (replayInput.size()-1))
            {
                stop();
            }
        }
        else
        {
            stop();
        }
    }
    public void stop()
    {
        setState(State.OVER);
        gui.disableButtonNextMove();
    }
    public State getState()
    {
        return state;
    }
    public void setState(State state)
    {
        this.state = state;
    }
    public int getMovesIndex()
    {
        return movesIndex;
    }
    private void incrementMovesIndex()
    {
        movesIndex++;
    }
    public HashMap<Location, Tile> getGrid()
    {
        return grid;
    }
}