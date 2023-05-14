package com.github.mydeardoctor.chinesechess;

public class ChineseChess
{
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        Game game = new Game(gui);
        gui.setGame(game);

        gui.showFrameMainMenu();
    }
}