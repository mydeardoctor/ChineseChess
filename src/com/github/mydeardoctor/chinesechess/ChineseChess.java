package com.github.mydeardoctor.chinesechess;

//TODO: Сделать static функции. Передавать в них аргументы. Чтобы не передавать указатели на объекты для взаимодействия с полями.
//TODO: Static and final variables.
public class ChineseChess
{
    public static void main(String[] args)
    {
        TextEnglish textEnglish = new TextEnglish();
        TextRussian textRussian = new TextRussian();

        GUI gui = new GUI(textEnglish, textEnglish, textRussian);

        Game game = new Game(textEnglish, gui.getPanelBoard(), gui.getStatusBar());
        gui.setGameReference(game);
        gui.getPanelBoard().setGameReference(game);

        gui.showFrameMainMenu();
    }
}