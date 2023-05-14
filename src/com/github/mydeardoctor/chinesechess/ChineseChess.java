package com.github.mydeardoctor.chinesechess;

public class ChineseChess
{
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        MusicPlayer musicPlayer = new MusicPlayer(); //TODO:
        Game game = new Game(gui, musicPlayer);
        gui.setGame(game);

        gui.showFrameMainMenu();
    }
}