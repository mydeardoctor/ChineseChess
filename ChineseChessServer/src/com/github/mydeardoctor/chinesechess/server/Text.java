package com.github.mydeardoctor.chinesechess.server;

public abstract class Text
{
    //GUI.

    //Resources.
    public abstract String getGuiWarning();
    public abstract String getSomeResourcesAreMissing();

    //Common frame features.
    public abstract String getTitle();
    public abstract String getServerIsOff();
    public abstract String getServerIsOn();
    public abstract String getHelp();
    public abstract String getAbout();
    public abstract String getAboutVerbose();

    //Frame Main Menu.
    public abstract String getStart();
    public abstract String getLobby();
    public abstract String getSettings();

    //Frame Start Server.
    public abstract String getPort();
    public abstract String getPortTip();
    public abstract String getMaximumNumberOfPlayers();
    public abstract String getPlayersMustBeBetween();
    public abstract String getServerError();
    public abstract String getCouldNotStartServer();
    public abstract String getBack();

    //Frame Settings.
    public abstract String getLanguage();
    public abstract String getApply();
}