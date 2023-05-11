package com.github.mydeardoctor.chinesechess;

class GridLocation
{
    private int xGrid;
    private int yGrid;
    GridLocation(int xGrid, int yGrid)
    {
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }
    @Override
    public int hashCode()
    {
        return Integer.hashCode(9*yGrid+xGrid);
    }
    @Override
    public boolean equals(Object obj)
    {
        Integer xGridInteger = Integer.valueOf(xGrid);
        Integer yGridInteger = Integer.valueOf(yGrid);
        GridLocation gridLocationPassed = (GridLocation) obj;
        Integer xGridIntegerPassed = Integer.valueOf(gridLocationPassed.getXgrid());
        Integer yGridIntegerPassed = Integer.valueOf(gridLocationPassed.getYgrid());
        return xGridInteger.equals(xGridIntegerPassed)&&yGridInteger.equals(yGridIntegerPassed);
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
