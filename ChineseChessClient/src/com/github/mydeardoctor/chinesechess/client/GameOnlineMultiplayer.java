package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;
import com.github.mydeardoctor.chinesechess.Player;
import com.github.mydeardoctor.chinesechess.State;

public class GameOnlineMultiplayer extends Game
{
    public GameOnlineMultiplayer()
    {
        super();
    }
    @Override
    protected void initializeSides(String opponentNickname, Player playerSide, Player opponentSide) //TODO не нравится
    {
        this.playerSide = playerSide;
        this.opponentSide = opponentSide;

        String messageVersus = gui.getText().getYouPlay().concat(gui.getText().getVersus()).concat(opponentNickname);

        String messageSide = null;
        switch(playerSide)
        {
            case RED -> messageSide = gui.getText().getYouPlay().concat(gui.getText().getRed());
            case BLACK -> messageSide = gui.getText().getYouPlay().concat(gui.getText().getBlack());
        }

        String message = String.format("%s.\n%s", messageVersus, messageSide);

        gui.showDialogYouPlay(message);
    }
    @Override
    protected void moveFigure(Location origin, Location destination)
    {
        super.moveFigure(origin, destination);
        protocol.sendMove(origin, destination);
    }
    //TODO не нравится
    @Override
    public void receiveMoveFromServer(Location origin, Location destination)
    {
        super.receiveMoveFromServer(origin, destination);
        super.moveFigure(origin, destination);
        musicPlayer.playSfx();
        replay.addToReplayOutput(grid);
        nextTurn();
    }
    @Override
    protected void opponentTurn()
    {
        //Wait for server message.
    }
    @Override
    public void over()
    {
        super.over();
        protocol.sendEndGame();
        gui.setButtonInviteIgnoresSelections(false);
    }
    @Override
    public void stop()
    {
        if(state.equals(State.RUNNING))
        {
            protocol.sendPlayerQuit();
            gui.setButtonInviteIgnoresSelections(false);
        }
        super.stop();
    }
}
