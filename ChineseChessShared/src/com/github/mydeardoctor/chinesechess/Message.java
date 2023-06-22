package com.github.mydeardoctor.chinesechess;

import java.io.Serializable;

//TODO data must be serializable
public record Message(Action action, Object data, State state, Player turn, Phase phase) implements Serializable
{
}
