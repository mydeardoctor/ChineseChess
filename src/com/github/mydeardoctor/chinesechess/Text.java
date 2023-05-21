package com.github.mydeardoctor.chinesechess;

public abstract class Text
{
    //Game.

    public abstract String getRedPlayer();
    public abstract String getBlackPlayer();
    public abstract String getChooseFigure();
    public abstract String getChooseAnotherFigureOrDestination();
    public abstract String getGameOver();
    public abstract String getWon();

    //GUI.

    //Resources.
    public abstract String getGuiWarning();
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
    public abstract String getMusic();
    public abstract String getSfx();
    public abstract String getApply();

    //Music player.

    public abstract String getMusicPlayerWarning();
    public abstract String getSomeFeaturesAreNotAvailable();
}