package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Player;

public class GameOnlineMultiplayer extends Game
{
    public GameOnlineMultiplayer()
    {
        super();
    }
    @Override
    protected void initializeSides(Player playerSide, Player opponentSide)
    {
        this.playerSide = playerSide;
        this.opponentSide = opponentSide;

        String message = null;
        switch(playerSide)
        {
            case RED -> message = gui.getText().getYouPlay().concat(gui.getText().getRed());
            case BLACK -> message = gui.getText().getYouPlay().concat(gui.getText().getBlack());
        }
        gui.showDialogYouPlay(message);
    }
    @Override
    protected void moveFigure(Location origin, Location destination)
    {
        super.moveFigure(origin, destination);
        //TODO send to server
    }
    @Override
    protected void opponentTurn()
    {
        //Wait for server message.
        //TODO movefigure, replay, nextTurn
    }
}
