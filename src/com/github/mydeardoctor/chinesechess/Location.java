package com.github.mydeardoctor.chinesechess;

public class Location
{
    private int x;
    private int y;
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //hashCode() and equals() are overridden so that HashMap<Location, Tile>.get(new Location(x, y)) can be used.
    //HashMap<K, V>.get(key) compares argument key with all keys already present in HashMap<K, V>.
    //Overriding hashCode() and equals() means that a new instance of Location can be passed to HashMap<K, V>.get()
    //for convenience.
    @Override
    public int hashCode()
    {
        return Integer.hashCode(y*9 + x);
    }
    @Override
    public boolean equals(Object obj)
    {
        Integer integerX = Integer.valueOf(x);
        Integer integerY = Integer.valueOf(y);
        Integer comparedIntegerX = Integer.valueOf(((Location)obj).getX());
        Integer comparedIntegerY = Integer.valueOf(((Location)obj).getY());
        return integerX.equals(comparedIntegerX) && integerY.equals(comparedIntegerY);
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}