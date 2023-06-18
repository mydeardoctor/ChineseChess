package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.ParentLogger;

//TODO
//Exceptions unwrapping.
//Multithreading analisys. В Сервере надо проверить классы Server и Client.

public class ChineseChessClient
{
    public static void main(String[] args)
    {
        ParentLogger parentLogger = new ParentLogger();
        parentLogger.initialize("ChineseChessClientLog.txt");
        GameSinglePlayer gameSinglePlayer = new GameSinglePlayer();
        GameLocalMultiplayer gameLocalMultiplayer = new GameLocalMultiplayer();
        Client client = new Client();
        Replay replay = new Replay();
        Rules rules = new Rules();
        GUI gui = new GUI();
        MusicPlayer musicPlayer = new MusicPlayer();

        gameSinglePlayer.setReplay(replay);
        gameSinglePlayer.setGui(gui);
        gameSinglePlayer.setMusicPlayer(musicPlayer);
        gameLocalMultiplayer.setReplay(replay);
        gameLocalMultiplayer.setGui(gui);
        gameLocalMultiplayer.setMusicPlayer(musicPlayer);
        client.setGui(gui);
        replay.setGui(gui);
        gui.setGameSinglePlayer(gameSinglePlayer);
        gui.setGameLocalMultiplayer(gameLocalMultiplayer);
        gui.setClient(client);
        gui.setReplay(replay);
        gui.setRules(rules);
        gui.setMusicPlayer(musicPlayer);

        gui.showFrameAndWarnings();
    }
}