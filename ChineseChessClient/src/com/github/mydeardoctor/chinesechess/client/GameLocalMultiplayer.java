package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Player;

public class GameLocalMultiplayer extends Game
{
    public GameLocalMultiplayer()
    {
        super();
    }
    @Override
    protected void initializeSides(Player playerSide, Player opponentSide)
    {
        this.playerSide = Player.RED;
        this.opponentSide = Player.BLACK;
    }
    @Override
    protected void opponentTurn()
    {
        //Handle user mouse clicks.
    }
}
