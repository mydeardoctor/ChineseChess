package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;
import java.util.*;

public class GameSinglePlayer extends Game
{
    private final Random randomNumberGenerator;
    public GameSinglePlayer()
    {
        super();
        randomNumberGenerator = new Random();
    }
    @Override
    protected void initializeSides(Player playerSide, Player opponentSide)
    {
        String message = null;

        int randomNumber = randomNumberGenerator.nextInt(2);
        switch(randomNumber)
        {
            case 0 ->
            {
                this.playerSide = Player.RED;
                this.opponentSide = Player.BLACK;
                message = gui.getText().getYouPlay().concat(gui.getText().getRed());
            }
            case 1 ->
            {
                this.playerSide = Player.BLACK;
                this.opponentSide = Player.RED;
                message = gui.getText().getYouPlay().concat(gui.getText().getBlack());
            }
        }

        gui.showDialogYouPlay(message);
    }
    @Override
    protected void opponentTurn()
    {
        HashMap<Location, HashSet<Location>> allAttackingMoves = getAllAttackingMoves(allAllowedMoves);

        HashMap<Location, Location> randomMove;
        if(allAttackingMoves.size() != 0)
        {
            randomMove = getRandomMove(allAttackingMoves);
        }
        else
        {
            randomMove = getRandomMove(allAllowedMoves);
        }

        Location origin = null;
        Location destination = null;
        Set<Map.Entry<Location, Location>> randomMoveSet = randomMove.entrySet();
        for(Map.Entry<Location, Location> randomMoveEntry : randomMoveSet)
        {
            origin = randomMoveEntry.getKey();
            destination = randomMoveEntry.getValue();
        }

        moveFigure(origin, destination);
        replay.addToReplayOutput(grid);
        nextTurn();
    }
    private HashMap<Location, HashSet<Location>> getAllAttackingMoves(
            HashMap<Location, HashSet<Location>> allAllowedMoves)
    {
        HashMap<Location, HashSet<Location>> allAttackingMoves = new HashMap<>();

        Set<Map.Entry<Location, HashSet<Location>>> allAllowedMovesSet = allAllowedMoves.entrySet();
        for(Map.Entry<Location, HashSet<Location>> allAllowedMovesEntry : allAllowedMovesSet)
        {
            HashSet<Location> attackingMoves = new HashSet<>();

            Location origin = allAllowedMovesEntry.getKey();
            HashSet<Location> allowedMoves = allAllowedMovesEntry.getValue();
            for(Location allowedMove : allowedMoves)
            {
                TileType tileType = getTileType(allowedMove, grid, turn);
                if(tileType.equals(TileType.ENEMY))
                {
                    attackingMoves.add(allowedMove);
                }
            }

            if(attackingMoves.size() != 0)
            {
                allAttackingMoves.put(origin, attackingMoves);
            }
        }

        return allAttackingMoves;
    }
    private HashMap<Location, Location> getRandomMove(HashMap<Location, HashSet<Location>> allMoves)
    {
        Location origin = null;
        Location destination = null;

        int numberOfOrigins = allMoves.size();
        int indexOfOrigin = randomNumberGenerator.nextInt(numberOfOrigins);
        int i = 0;

        Set<Map.Entry<Location, HashSet<Location>>> allMovesSet = allMoves.entrySet();
        for(Map.Entry<Location, HashSet<Location>> allMovesEntry : allMovesSet)
        {
            if(i == indexOfOrigin)
            {
                origin = allMovesEntry.getKey();
                HashSet<Location> moves = allMovesEntry.getValue();

                int numberOfDestinations = moves.size();
                int indexOfDestination = randomNumberGenerator.nextInt(numberOfDestinations);
                int j = 0;

                for(Location move : moves)
                {
                    if(j == indexOfDestination)
                    {
                        destination = move;
                        break;
                    }
                    j++;
                }

                break;
            }
            i++;
        }

        HashMap<Location, Location> randomMove = new HashMap<>();
        randomMove.put(origin, destination);
        return randomMove;
    }
}