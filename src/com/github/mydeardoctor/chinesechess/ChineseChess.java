package com.github.mydeardoctor.chinesechess;

public class ChineseChess
{
    public static void main(String[] args)
    {
        Game game = new Game();
        GUI gui = new GUI();

        game.setGui(gui);
        gui.setGame(game);

        gui.showFrame();
    }
}