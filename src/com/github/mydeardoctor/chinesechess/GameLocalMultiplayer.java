package com.github.mydeardoctor.chinesechess;

public class GameLocalMultiplayer extends Game
{
    public GameLocalMultiplayer()
    {
        super();
    }
    @Override
    protected void initializeSidesForPlayers()
    {
    }
    @Override
    protected void nextPlayerTurn()
    {
        humanTurn();
    }
    @Override
    public boolean getIsCpuTurn()
    {
        return false;
    }
}
