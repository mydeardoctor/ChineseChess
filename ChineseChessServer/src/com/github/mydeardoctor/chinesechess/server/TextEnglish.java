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
    public String getHelp()
    {
        return "Help";
    }
    @Override
    public String getSettings()
    {
        return "Settings";
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
    public String getPort()
    {
        return "Port";
    }
    @Override
    public String getSetUp()
    {
        return "Set Up";
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
    @Override
    public String getBack()
    {
        return "Back";
    }
}