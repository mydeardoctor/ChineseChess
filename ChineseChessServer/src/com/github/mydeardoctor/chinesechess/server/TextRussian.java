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
    public String getServerIsOff()
    {
        return "Сервер выключен.";
    }
    @Override
    public String getServerIsOn()
    {
        return "Сервер запущен.";
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
    public String getStart()
    {
        return "Запустить";
    }
    @Override
    public String getLobby()
    {
        return "Лобби";
    }
    @Override
    public String getSettings()
    {
        return "Настройки";
    }

    //Frame Start.
    @Override
    public String getPort()
    {
        return "Порт";
    }
    @Override
    public String getMustBeBetween()
    {
        return String.format("%13s\nот 1024 до 65535", "Должен быть");
    }
    @Override
    public String getServerError()
    {
        return "Ошибка Server";
    }
    @Override
    public String getCouldNotStartServer()
    {
        return "Не удалось запустить сервер!";
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
}