package com.github.mydeardoctor.chinesechess;

import java.io.Serializable;

public class Move implements Serializable
{
    private Location origin;
    private Location destination;
    public Move(Location origin, Location destination)
    {
        this.origin = origin;
        this.destination = destination;
    }
    public Location getOrigin()
    {
        return origin;
    }
    public Location getDestination()
    {
        return destination;
    }
}
