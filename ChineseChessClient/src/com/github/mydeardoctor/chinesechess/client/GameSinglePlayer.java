package com.github.mydeardoctor.chinesechess.client;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GameSinglePlayer extends Game
{
    private Player cpu;
    private final Random randomNumberGenerator;
    public GameSinglePlayer()
    {
        super();
        randomNumberGenerator = new Random();
    }
    @Override
    protected void initializeSidesForPlayers()
    {
        String message = null;

        int randomNumber = randomNumberGenerator.nextInt(2);
        switch(randomNumber)
        {
            case 0 ->
            {
                cpu = Player.RED;
                message = gui.getText().getYouPlay().concat(gui.getText().getBlack());
            }
            case 1 ->
            {
                cpu = Player.BLACK;
                message = gui.getText().getYouPlay().concat(gui.getText().getRed());
            }
        }

        gui.showDialogYouPlay(message);
    }
    @Override
    protected void nextPlayerTurn()
    {
        if(turn.equals(cpu))
        {
            cpuTurn();
        }
        else
        {
            humanTurn();
        }
    }
    private void cpuTurn()
    {
        getAllAllowedMoves();
        if(state.equals(State.OVER))
        {
            return;
        }

        //Choose random move.
        Location origin = null;
        Location destination = null;

        int numberOfOrigins = allAllowedMoves.size();
        int indexOfOrigin = randomNumberGenerator.nextInt(numberOfOrigins);
        int i = 0;

        Set<Map.Entry<Location, HashSet<Location>>> allAllowedMovesSet = allAllowedMoves.entrySet();
        for(Map.Entry<Location, HashSet<Location>> allAllowedMovesEntry : allAllowedMovesSet)
        {
            if(i == indexOfOrigin)
            {
                origin = allAllowedMovesEntry.getKey();
                HashSet<Location> allowedMoves = allAllowedMovesEntry.getValue();

                int numberOfDestinations = allowedMoves.size();
                int indexOfDestination = randomNumberGenerator.nextInt(numberOfDestinations);
                int j = 0;

                for(Location allowedDestination : allowedMoves)
                {
                    if(j == indexOfDestination)
                    {
                        destination = allowedDestination;
                        break;
                    }
                    j++;
                }

                break;
            }
            i++;
        }

        moveFigure(origin, destination);
        replay.addToReplayOutput(grid);
        nextTurn();
    }
    @Override
    public boolean getIsCpuTurn()
    {
        return turn.equals(cpu);
    }
}