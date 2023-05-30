package com.github.mydeardoctor.chinesechess;

//TODO
/*
inspect code
если try with resources что будет если exception

final
Если в классе используется поле, то использовать его напрямую, а не через геттер и сеттер.
Оптимизировать типы
== заменить на equals
Сделать доступ к другим объектам черещ геттеры, а не новые функции
Переставить аттрибуты местами. Сверху общее, снизу конкретное.
lambdas or method references

Game. Переработать и сделать абстрактным классом.
PvE extends Game. Умный выбор ходов.
PvP extends Game.
Если класс Replay и Rules использует одинаковые функции инициализации grid, то сделать их статическими.

Replay.
load - проверить на ends with ccrpl
Сделать кнопку назад. Сделать real time воспроизведение.

GUI
Заменить icon на image
Selected Figure заменить на Selected
Вынести лямбды в отдельные функции.
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