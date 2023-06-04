package com.github.mydeardoctor.chinesechess.server;

public abstract class Text
{
    //GUI.

    //Resources.
    public abstract String getGuiWarning();
    public abstract String getSomeResourcesAreMissing();

    //Common frame features.
    public abstract String getTitle();
    public abstract String getHelp();
    public abstract String getSettings();
    public abstract String getAbout();
    public abstract String getAboutVerbose();

    //Frame Main Menu.
    public abstract String getPort();
    public abstract String getSetUp();

    //Frame Settings.
    public abstract String getLanguage();
    public abstract String getApply();
    public abstract String getBack();
}