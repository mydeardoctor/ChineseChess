package com.github.mydeardoctor.chinesechess.client;

//TODO
/*
    Поля классов. Сверху общие, снизу частные.
    Final.
    Поля внутри класса использовать напрямую, геттеры и сеттеры в таком случае лишние.
    Equals вместо ==.
    Лямбды выносить в отдельные функции.

    GUI GridBag нормально, а не вручную

    Game Online Multiplayer.
 */
public class ChineseChessClient
{
    public static void main(String[] args)
    {
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