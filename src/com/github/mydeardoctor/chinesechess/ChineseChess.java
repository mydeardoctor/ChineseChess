package com.github.mydeardoctor.chinesechess;

public class ChineseChess
{
    public static void main(String[] args)
    {
        Game game = new Game();
        GUI gui = new GUI();
        MusicPlayer musicPlayer = new MusicPlayer();

        game.setGui(gui);
        game.setMusicPlayer(musicPlayer);

        gui.setGame(game);
        gui.setMusicPlayer(musicPlayer);

        gui.showFrame();
    }
}