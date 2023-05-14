package com.github.mydeardoctor.chinesechess;

public abstract class Text
{
    //GUI.

    //Resources.
    public abstract String getWarning();
    public abstract String getSomeResourcesAreMissing();

    //Common frame features.
    public abstract String getTitle();
    public abstract String getHelp();
    public abstract String getAbout();
    public abstract String getAboutVerbose();

    //Frame Main Menu.
    public abstract String getPlay();
    public abstract String getLoad();
    public abstract String getRules();
    public abstract String getSettings();

    //Frame Game Mode.
    public abstract String getSinglePlayer();
    public abstract String getLocalMultiplayer();
    public abstract String getOnlineMultiplayer();
    public abstract String getBack();

    //Frame Settings.
    public abstract String getLanguage();
    public abstract String getApply();

    //Game.
    public abstract String getRedPlayer();
    public abstract String getBlackPlayer();
    public abstract String getChooseFigure();
    public abstract String getChooseAnotherFigureOrDestination();
    public abstract String getGameOver();
    public abstract String getWon();
}