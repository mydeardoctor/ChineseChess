package com.github.mydeardoctor.chinesechess;

//TODO
/*
    Переставить аттрибуты местами. Сверху общее, снизу конкретное.
    final
    equals вместо ==
    Использовать поле в классе напрямую, а не через геттеры и сеттеры.
    Лямбды выносить в отдельные функции.

    Game.
    Переработать и сделать абстрактным классом.
    PvE extends Game.
    PvE умный выбор ходов.
    PvP extends Game.

    Replay.
    Сделать кнопку prevMove.
    Сделать real time воспроизведение.
 */
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
        replay.setGui(gui);
        rules.setGame(game);
        gui.setGame(game);
        gui.setReplay(replay);
        gui.setRules(rules);
        gui.setMusicPlayer(musicPlayer);

        gui.showFrameAndWarnings();
    }
}