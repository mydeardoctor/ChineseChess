package com.github.mydeardoctor.chinesechess;

class TextRussian extends Text
{
    @Override
    String getTitle()
    {
        return "Китайские шахматы";
    }
    @Override
    String getHelp()
    {
        return "Помощь";
    }
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
}
