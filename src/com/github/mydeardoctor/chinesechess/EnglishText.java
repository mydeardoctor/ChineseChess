package com.github.mydeardoctor.chinesechess;

public class EnglishText extends Text
{
    //GUI.

    //Resources.
    @Override
    public String getWarning()
    {
        return "Warning";
    }
    @Override
    public String getSomeResourcesAreMissing()
    {
        return "Some resources are missing!";
    }

    //Title.
    @Override
    public String getTitle()
    {
        return "Chinese Chess";
    }

    //Menu Bar.
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
    public String getPlay()
    {
        return "Play";
    }
    @Override
    public String getLoad()
    {
        return "Load";
    }
    @Override
    public String getRules()
    {
        return "Rules";
    }
    @Override
    public String getSettings()
    {
        return "Settings";
    }

    //Frame Game Mode.
    @Override
    public String getSinglePlayer()
    {
        return "Single player";
    }
    @Override
    public String getLocalMultiplayer()
    {
        return "Local multiplayer";
    }
    @Override
    public String getOnlineMultiplayer()
    {
        return "Online multiplayer";
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

    //Game.
    @Override
    public String getRedPlayer()
    {
        return "Red player";
    }
    @Override
    public String getBlackPlayer()
    {
        return "Black player";
    }
    @Override
    public String getChooseFigure()
    {
        return "choose figure.";
    }
    @Override
    public String getChooseDestination()
    {
        return "choose destination.";
    }
}
