package com.github.mydeardoctor.chinesechess;

class Location
{
    private int xGrid;
    private int yGrid;
    Location(int xGrid, int yGrid)
    {
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }
    public int getXgrid()
    {
        return xGrid;
    }
    public int getYgrid()
    {
        return yGrid;
    }
}
