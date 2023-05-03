package com.github.mydeardoctor.chinesechess;

class TextRussian extends Text
{
    @Override
    String getTitle()
    {
        return "Китайские шахматы";
    }

    //Menu Bar.
    @Override
    String getHelp()
    {
        return "Помощь";
    }
    @Override
    String getAbout()
    {
        return "О программе";
    }
    @Override
    String getAboutVerbose()
    {
        return String.format("Китайские шахматы\nПрограммист:%16s\nКомпозитор:%14s", "my_dear_doctor", "eskimolly");
    }

    //Frame Main Menu.
    @Override
    String getPlay()
    {
        return "Играть";
    }
    @Override
    String getLoad()
    {
        return "Загрузить";
    }
    @Override
    String getRules()
    {
        return "Правила";
    }
    @Override
    String getSettings()
    {
        return "Настройки";
    }

    //Frame Game Mode.
    @Override
    String getSinglePlayer()
    {
        return "Одиночная игра";
    }
    @Override
    String getLocalMultiplayer()
    {
        return "Локальный мультиплеер";
    }
    @Override
    String getOnlineMultiplayer()
    {
        return "Онлайн мультиплеер";
    }
    @Override
    String getBack()
    {
        return "Назад";
    }

    //Frame Settings.
    @Override
    String getLanguage()
    {
        return "Язык";
    }
    @Override
    String getApply()
    {
        return "Применить";
    }

    //Errors.
    @Override
    String getError()
    {
        return "Ошибка";
    }
    @Override
    String getErrorIcon()
    {
        return "Не удалось загрузить \"icon.jpg\"";
    }
    @Override
    String getErrorFont()
    {
        return "Не удалось загрузить \"kashimarusbycop.otf\"";
    }
    @Override
    String getErrorBackground()
    {
        return "Не удалось загрузить \"background.jpg\"";
    }
}
