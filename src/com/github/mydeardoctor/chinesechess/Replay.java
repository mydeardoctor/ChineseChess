package com.github.mydeardoctor.chinesechess;

import java.util.*;
import java.io.*;

public class Replay
{
    //Replay attributes.
    public static final int FAILURE = -1;
    public static final int SUCCESS = 0;
    private State state;
    private final ArrayList<HashMap<Location, Tile>> replayOutput;
    private ArrayList<HashMap<Location, Tile>> replayInput;
    private int movesIndex;

    //GUI attributes.
    private GUI gui;

    public Replay()
    {
        state = State.OVER;
        replayOutput = new ArrayList<>();
        replayInput = new ArrayList<>();
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void resetReplayOutput()
    {
        replayOutput.clear();
    }
    public void addToReplayOutput(HashMap<Location, Tile> grid)
    {
        HashMap<Location, Tile> gridCopy = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, false);
                gridCopy.put(location, tile);
            }
        }

        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            Location location = gridEntry.getKey();
            Figure figure = gridEntry.getValue().getFigure();
            boolean selected = gridEntry.getValue().getSelected();
            gridCopy.get(location).setFigure(figure);
            gridCopy.get(location).setSelected(selected);
        }

        replayOutput.add(gridCopy);
    }
    public boolean getIsReplayOutputEmpty()
    {
        return (replayOutput.size() <= 1);
    }
    public int save(File selectedFile)
    {
        File fileWithExtension;
        try
        {
            String path = selectedFile.getCanonicalPath();
            if(!path.endsWith(".ccrpl"))
            {
                path = path.concat(".ccrpl"); //Add extension if there is none.
            }
            fileWithExtension = new File(path);
        }
        catch (Exception e)
        {
            return FAILURE;
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
        try
        {
            String path = selectedFile.getCanonicalPath();
            if(!path.endsWith(".ccrpl"))
            {
                return FAILURE;
            }
        }
        catch (Exception e)
        {
            return FAILURE;
        }

        try(FileInputStream fileInputStream = new FileInputStream(selectedFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            //noinspection unchecked
            replayInput = (ArrayList<HashMap<Location, Tile>>)objectInputStream.readObject();
        }
        catch(Exception e)
        {
            return FAILURE;
        }

        return SUCCESS;
    }
    public void start()
    {
        state = State.RUNNING;

        movesIndex = 0;
        HashMap<Location, Tile> grid = replayInput.get(movesIndex);
        gui.setPanelBoardReplayGrid(grid);
        gui.repaint();

        gui.disableButtonPreviousMove();
        if((movesIndex + 1) > (replayInput.size()-1))
        {
            gui.disableButtonNextMove();
            gui.disableButtonPlayPause();
        }
        else
        {
            gui.enableButtonNextMove();
            gui.enableButtonPlayPause();
        }
    }
    public void previousMove()
    {
        movesIndex--;

        HashMap<Location, Tile> grid = replayInput.get(movesIndex);
        gui.setPanelBoardReplayGrid(grid);
        gui.repaint();

        if((movesIndex - 1) < 0)
        {
            gui.disableButtonPreviousMove();
        }

        gui.enableButtonNextMove();
        gui.enableButtonPlayPause();
    }
    public void nextMove()
    {
        movesIndex++;

        HashMap<Location, Tile> grid = replayInput.get(movesIndex);
        gui.setPanelBoardReplayGrid(grid);
        gui.repaint();

        if((movesIndex + 1) > (replayInput.size()-1))
        {
            gui.stopTimer();
            gui.disableButtonNextMove();
            gui.disableButtonPlayPause();
            gui.setButtonPlayPauseUnselected();
        }

        gui.enableButtonPreviousMove();
    }
    public void stop()
    {
        state = State.OVER;
    }
    public State getState()
    {
        return state;
    }
}