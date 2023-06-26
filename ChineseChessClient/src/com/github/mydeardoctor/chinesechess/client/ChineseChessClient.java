package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.ParentLogger;

public class ChineseChessClient
{
    public static void main(String[] args)
    {
        ParentLogger.initialize("ChineseChessClientLog%u.txt");
        GameSinglePlayer gameSinglePlayer = new GameSinglePlayer();
        GameLocalMultiplayer gameLocalMultiplayer = new GameLocalMultiplayer();
        GameOnlineMultiplayer gameOnlineMultiplayer = new GameOnlineMultiplayer();
        Client client = new Client();
        Protocol protocol = new Protocol();
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
        gameOnlineMultiplayer.setReplay(replay);
        gameOnlineMultiplayer.setGui(gui);
        gameOnlineMultiplayer.setMusicPlayer(musicPlayer);
        client.setProtocol(protocol);
        client.setGui(gui);
        protocol.setClient(client);
        protocol.setGui(gui);
        replay.setGui(gui);
        gui.setGameSinglePlayer(gameSinglePlayer);
        gui.setGameLocalMultiplayer(gameLocalMultiplayer);
        gui.setGameOnlineMultiplayer(gameOnlineMultiplayer);
        gui.setClient(client);
        gui.setReplay(replay);
        gui.setRules(rules);
        gui.setMusicPlayer(musicPlayer);

        gui.showFrameAndWarnings();
    }
}