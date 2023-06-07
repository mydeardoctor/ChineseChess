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
    public String getServerIsOff()
    {
        return "Server is off.";
    }
    @Override
    public String getServerIsOn()
    {
        return "Server is on.";
    }
    @Override
    public String getHelp()
    {
        return "Help";
    }
    @Override
    public String getAbout()
    {
        return "About";
    }
    @Override
    public String getAboutVerbose()
    {
        return String.format("Chinese Chess\nProgrammer:%16s\nComposer:%16s", "my_dear_doctor", "eskimolly");
    }

    //Frame Main Menu.
    @Override
    public String getStart()
    {
        return "Start";
    }
    @Override
    public String getLobby()
    {
        return "Lobby";
    }
    @Override
    public String getSettings()
    {
        return "Settings";
    }

    //Frame Start Server.
    @Override
    public String getPort()
    {
        return "Port";
    }
    @Override
    public String getMustBeBetween()
    {
        return String.format("Must be between\n%17s", "1024 and 65535");
    }
    @Override
    public String getServerError()
    {
        return "Server error";
    }
    @Override
    public String getCouldNotStartServer()
    {
        return "Could not start server!";
    }
    @Override
    public String getBack()
    {
        return "Back";
    }

    //Frame Settings.
    @Override
    public String getLanguage()
    {
        return "Language";
    }
    @Override
    public String getApply()
    {
        return "Apply";
    }
}