package com.github.mydeardoctor.chinesechess.server;

public abstract class Text
{
    //GUI.

    //Resources.
    public abstract String getGuiWarning();
    public abstract String getSomeResourcesAreMissing();

    //Common frame features.
    public abstract String getTitle();
    public abstract String getPort();
    public abstract String getSetUp();
}