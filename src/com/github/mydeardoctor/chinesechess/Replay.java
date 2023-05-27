package com.github.mydeardoctor.chinesechess;

import java.io.*;
import java.util.ArrayList;

public class Replay //TODO refactoring
{
    static final int FAILURE = -1;
    static final int SUCCESS = 0;
    private ArrayList<Move> replayOutput;
    private ArrayList<Move> replayInput;
    public Replay()
    {
        initializeReplayOutput();
        initializeReplayInput();
    }
    private void initializeReplayOutput()
    {
        replayOutput = new ArrayList<>();
    }
    private void initializeReplayInput()
    {
        replayInput = new ArrayList<>();
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
        if(replayOutput.size()<=0)
        {
            return true;
        }
        else //replayOutput.size()>0
        {
            return false;
        }
    }
    public int save(File selectedFile)
    {
        String path=null;
        try
        {
            path = selectedFile.getCanonicalPath();
            if(path.endsWith(".ccrpl")==false)
            {
                path = path.concat(".ccrpl"); //Add extension if there is none.
            }
        }
        catch (Exception e)
        {
            return FAILURE;
        }

        File fileWithExtension = null;
        if(path!=null)
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
    public int open(File selectedFile)
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
}
