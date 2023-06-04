package com.github.mydeardoctor.chinesechess.server;

public class TextEnglish extends Text
{
    //GUI.

    //Resources.
    @Override
    public String getGuiWarning()
    {
        return "GUI warning";
    }
    @Override
    public String getSomeResourcesAreMissing()
    {
        return "Some resources are missing!";
    }

    //Common frame features.
    @Override
    public String getTitle()
    {
        return "Chinese Chess Server";
    }
    @Override
    public String getPort()
    {
        return "Port";
    }
    @Override
    public String getSetUp()
    {
        return "Set Up";
    }
}