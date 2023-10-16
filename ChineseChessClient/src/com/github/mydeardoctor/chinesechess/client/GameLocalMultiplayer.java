package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Side;

public class GameLocalMultiplayer extends Game
{
    public GameLocalMultiplayer()
    {
        super();
    }
    @Override
    protected void initializeSides(String opponentNickname, Side playerSide, Side opponentSide)
    {
        this.playerSide = Side.RED;
        this.opponentSide = Side.BLACK;
    }
    @Override
    protected void opponentTurn()
    {
        //Handle user mouse clicks.
    }
}
