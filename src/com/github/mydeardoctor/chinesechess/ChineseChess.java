package com.github.mydeardoctor.chinesechess;

//TODO: тщательный рефакторинг
//TODO: нормальные названия без префиксов
//TODO: Static and final variables.
//TODO: Сделать static функции. Передавать в них аргументы. Чтобы не передавать указатели на объекты для взаимодействия с полями.
//TODO: добавить в frame board в menu settings (там и громкость)
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