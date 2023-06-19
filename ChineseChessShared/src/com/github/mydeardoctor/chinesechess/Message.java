package com.github.mydeardoctor.chinesechess;

import java.io.Serializable;

public class Message implements Serializable
{
    private final Action action;
    private final Object data; //TODO must be serializable!
    private final State state;
    private final Player turn;
    private final Phase phase;
    public Message(Action action, Object data, State state, Player turn, Phase phase)
    {
        this.action = action;
        this.data = data;
        this.state = state;
        this.turn = turn;
        this.phase = phase;
    }
    public Action getAction()
    {
        return action;
    }
    public Object getData()
    {
        return data;
    }
    public State getState()
    {
        return state;
    }
    public Player getTurn()
    {
        return turn;
    }
    public Phase getPhase()
    {
        return phase;
    }
}
