package com.github.mydeardoctor.chinesechess;

import java.io.Serializable;

public enum Action implements Serializable
{
    REGISTER_NICKNAME,
    UPDATE_TABLE_OF_CLIENTS,
    INVITE,
    OPPONENT_UNAVAILABLE,
    START_GAME,
    MOVE,
    END_GAME,
    PLAYER_QUIT,
    PlAYER_DISCONNECTED
}
