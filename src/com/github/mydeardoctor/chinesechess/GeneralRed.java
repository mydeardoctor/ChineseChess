package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public class GeneralRed extends General
{
    public GeneralRed(Player player, BufferedImage icon)
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

        if(y!=7) //If not top of the Palace.
        {
            //Check tile above.
            checkTile(x, y-1, game, grid, turn, possibleMoves);
        }
        if(y!=9) //If not bottom of the Palace.
        {
            //Check tile below.
            checkTile(x, y+1, game, grid, turn, possibleMoves);
        }
        if(x!=3) //If not left side of the Palace.
        {
            //Check tile on the left.
            checkTile(x-1, y, game, grid, turn, possibleMoves);
        }
        if(x!=5) //If not right side of the Palace.
        {
            //Check tile on the right.
            checkTile(x+1, y, game, grid, turn, possibleMoves);
        }

        return possibleMoves;
    }
}