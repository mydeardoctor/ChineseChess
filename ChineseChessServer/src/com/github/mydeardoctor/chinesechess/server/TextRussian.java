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
    public String getPort()
    {
        return "Порт";
    }
    @Override
    public String getSetUp()
    {
        return "Запустить";
    }

}