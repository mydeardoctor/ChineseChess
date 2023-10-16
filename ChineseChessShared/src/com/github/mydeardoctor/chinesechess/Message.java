package com.github.mydeardoctor.chinesechess;

import java.io.Serializable;

public record Message(Action action, Object data) implements Serializable
{
}
