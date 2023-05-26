package com.github.mydeardoctor.chinesechess;

import java.util.HashSet;
import java.util.HashMap;
import java.awt.image.BufferedImage;

public class ElephantBlack extends Elephant
{
    public ElephantBlack(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game, HashMap<Location, Tile> grid, Player turn)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        Location origin = getLocation(this, grid);
        int x = origin.getX();
        int y = origin.getY();

        //Check tile above-left.
        if((x>=2)&&(y>=2))
        {
            checkTile(x-2,y-2,x-1,y-1, game, grid, turn, possibleMoves);
        }

        //Check tile above-right.
        if((x<=6)&&(y>=2))
        {
            checkTile(x+2,y-2,x+1,y-1, game, grid, turn, possibleMoves);
        }

        //Check tile below-left.
        if((x>=2)&&(y<=2))
        {
            checkTile(x-2,y+2,x-1,y+1, game, grid, turn, possibleMoves);
        }

        //Check tile below-right.
        if((x<=6)&&(y<=2))
        {
            checkTile(x+2,y+2,x+1,y+1, game, grid, turn, possibleMoves);
        }

        return possibleMoves;
    }
}