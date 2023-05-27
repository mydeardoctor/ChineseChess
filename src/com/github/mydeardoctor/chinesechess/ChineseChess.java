package com.github.mydeardoctor.chinesechess;

public class ChineseChess
{
    public static void main(String[] args)
    {
        Game game = new Game();
        Replay replay = new Replay();
        Rules rules = new Rules();
        GUI gui = new GUI();
        MusicPlayer musicPlayer = new MusicPlayer();

        game.setReplay(replay);
        game.setGui(gui);
        game.setMusicPlayer(musicPlayer);
        rules.setGui(gui);
        gui.setGame(game);
        gui.setReplay(replay);
        gui.setRules(rules);
        gui.setMusicPlayer(musicPlayer);

        gui.showFrameAndWarnings();
    }
}