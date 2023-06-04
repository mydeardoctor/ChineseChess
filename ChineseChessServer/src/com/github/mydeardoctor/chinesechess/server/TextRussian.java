package com.github.mydeardoctor.chinesechess.server;

class TextRussian extends Text
{
    //GUI.

    //Resources.
    @Override
    public String getGuiWarning()
    {
        return "Предупреждение GUI";
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
        return "Китайские шахматы. Сервер";
    }
    @Override
    public String getHelp()
    {
        return "Помощь";
    }
    @Override
    public String getSettings()
    {
        return "Настройки";
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
    public String getPort()
    {
        return "Порт";
    }
    @Override
    public String getSetUp()
    {
        return "Запустить";
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
    @Override
    public String getBack()
    {
        return "Назад";
    }
}