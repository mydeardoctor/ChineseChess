package com.github.mydeardoctor.chinesechess;

class TextRussian extends Text
{
    //GUI.

    //Resources.
    @Override
    public String getWarning()
    {
        return "Предупреждение";
    }
    @Override
    public String getSomeResourcesAreMissing()
    {
        return "Не удалось обнаружить некоторые ресурсы!";
    }

    //Common frame features.
    @Override
    public String getTitle()
    {
        return "Китайские шахматы";
    }
    @Override
    public String getHelp()
    {
        return "Помощь";
    }
    @Override
    public String getAbout()
    {
        return "О программе";
    }
    @Override
    public String getAboutVerbose()
    {
        return String.format("Китайские шахматы\nПрограммист:%16s\nКомпозитор:%14s", "my_dear_doctor", "eskimolly");
    }

    //Frame Main Menu.
    @Override
    public String getPlay()
    {
        return "Играть";
    }
    @Override
    public String getLoad()
    {
        return "Загрузить";
    }
    @Override
    public String getRules()
    {
        return "Правила";
    }
    @Override
    public String getSettings()
    {
        return "Настройки";
    }

    //Frame Game Mode.
    @Override
    public String getSinglePlayer()
    {
        return "Одиночная игра";
    }
    @Override
    public String getLocalMultiplayer()
    {
        return "Локальный мультиплеер";
    }
    @Override
    public String getOnlineMultiplayer()
    {
        return "Онлайн мультиплеер";
    }
    @Override
    public String getBack()
    {
        return "Назад";
    }

    //Frame Settings.
    @Override
    public String getLanguage()
    {
        return "Язык";
    }
    @Override
    public String getApply()
    {
        return "Применить";
    }

    //Game.
    @Override
    public String getRedPlayer()
    {
        return "Красный игрок";
    }
    @Override
    public String getBlackPlayer()
    {
        return "Чёрный игрок";
    }
    @Override
    public String getChooseFigure()
    {
        return "выберите фигуру.";
    }
    @Override
    public String getChooseDestination()
    {
        return "выберите цель.";
    }
}
