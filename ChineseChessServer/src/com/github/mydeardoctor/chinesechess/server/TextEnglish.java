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
    public String getChineseChessServer()
    {
        return "Chinese Chess Server";
    }
    @Override
    public String getServerIsStopped()
    {
        return "Server is stopped.";
    }
    @Override
    public String getServerIsRunning()
    {
        return "Server is running.";
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

    //Frame Start.
    @Override
    public String getPort()
    {
        return "Port";
    }
    @Override
    public String getPortTip()
    {
        return String.format("Must be between\n%17s", "1024 and 65535");
    }
    @Override
    public String getMaximumNumberOfPlayers()
    {
        return "Max number\nof players";
    }
    @Override
    public String getPlayersTip()
    {
        return String.format("Must be between\n%15s", "2 and 100");
    }
    @Override
    public String getStop()
    {
        return "Stop";
    }
    @Override
    public String getServerStop()
    {
        return "Server Stop";
    }
    @Override
    public String getAreYouSure()
    {
        return "All connections will be lost!\nAre you sure?";
    }
    @Override
    public String getYes()
    {
        return "Yes";
    }
    @Override
    public String getNo()
    {
        return "No";
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
}