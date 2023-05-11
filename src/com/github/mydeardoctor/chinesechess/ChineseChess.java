package com.github.mydeardoctor.chinesechess;

//TODO: тщательный рефакторинг
//TODO: Сделать static функции. Передавать в них аргументы. Чтобы не передавать указатели на объекты для взаимодействия с полями.
//TODO: Static and final variables.
//TODO: загрузка ресурсов в одном месте. Обработка ситуаций, когда они null
public class ChineseChess
{
    public static void main(String[] args)
    {
        TextEnglish textEnglish = new TextEnglish();
        TextRussian textRussian = new TextRussian();

        GUI gui = new GUI(textEnglish, textRussian);

        Game game = new Game(textEnglish, gui.getPanelBoard(), gui.getStatusBar());

        gui.setGameReference(game);
        gui.getPanelBoard().setGameReference(game);

        gui.showFrameMainMenu();
    }
}