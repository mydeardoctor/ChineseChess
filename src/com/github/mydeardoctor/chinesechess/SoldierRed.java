package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
import java.awt.image.BufferedImage;

public class SoldierRed extends Soldier
{
    public SoldierRed(Player player, BufferedImage icon)
    {
        super(player, icon);
    }
    @Override
    public HashSet<Location> getPossibleMoves(Game game)
    {
        HashSet<Location> possibleMoves = new HashSet<>();

        HashMap<Location, Tile> grid = game.getGrid();
        Location origin = getLocation(this, grid);
        int x = origin.getX();
        int y = origin.getY();

        if((y>=5)&&(y<=9)) //If below River.
        {
            //Check tile above.
            checkTile(x, y-1, possibleMoves, game);
        }
        else //If above River.
        {
            if(y!=0) //If not top of the board.
            {
                //Check tile above.
                checkTile(x, y-1, possibleMoves, game);
            }
            if(x!=0) //If not left side of the board.
            {
                //Check tile on the left.
                checkTile(x-1, y, possibleMoves, game);
            }
            if(x!=8) //If not right side of the board.
            {
                //Check tile on the right.
                checkTile(x+1, y, possibleMoves, game);
            }
        }

        return possibleMoves;
    }
}