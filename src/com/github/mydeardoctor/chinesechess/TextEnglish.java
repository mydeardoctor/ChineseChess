package com.github.mydeardoctor.chinesechess;

class TextEnglish extends Text
{
    @Override
    String getTitle()
    {
        return "Chinese Chess";
    }

    //Menu Bar.
    @Override
    String getHelp()
    {
        return "Help";
    }
    @Override
    String getAbout()
    {
        return "About";
    }
    @Override
    String getAboutVerbose()
    {
        return String.format("Chinese Chess\nProgrammer:%16s\nComposer:%16s", "my_dear_doctor", "eskimolly");
    }

    //Frame Main Menu.
    @Override
    String getPlay()
    {
        return "Play";
    }
    @Override
    String getLoad()
    {
        return "Load";
    }
    @Override
    String getRules()
    {
        return "Rules";
    }
    @Override
    String getSettings()
    {
        return "Settings";
    }

    //Frame Game Mode.
    @Override
    String getSinglePlayer()
    {
        return "Single player";
    }
    @Override
    String getLocalMultiplayer()
    {
        return "Local multiplayer";
    }
    @Override
    String getOnlineMultiplayer()
    {
        return "Online multiplayer";
    }
    @Override
    String getBack()
    {
        return "Back";
    }

    //Frame Settings.
    @Override
    String getLanguage()
    {
        return "Language";
    }
    @Override
    String getApply()
    {
        return "Apply";
    }

    //Errors.
    @Override
    String getError()
    {
        return "Error";
    }
    @Override
    String getErrorIcon()
    {
        return "Could not load \"icon.jpg\"";
    }
    @Override
    String getErrorFont()
    {
        return "Could not load \"kashimarusbycop.otf\"";
    }
    @Override
    String getErrorBackground()
    {
        return "Could not load \"background.jpg\"";
    }
    @Override
    String getErrorAdvisorBlack()
    {
        return "Could not load \"advisorBlack.png\"";
    }
    @Override
    String getErrorAdvisorRed()
    {
        return "Could not load \"advisorRed.png\"";
    }
    @Override
    String getErrorCannonBlack()
    {
        return "Could not load \"cannonBlack.png\"";
    }
    @Override
    String getErrorCannonRed()
    {
        return "Could not load \"cannonRed.png\"";
    }
    @Override
    String getErrorChariotBlack()
    {
        return "Could not load \"chariotBlack.png\"";
    }
    @Override
    String getErrorChariotRed()
    {
        return "Could not load \"chariotRed.png\"";
    }
    @Override
    String getErrorElephantBlack()
    {
        return "Could not load \"elephantBlack.png\"";
    }
    @Override
    String getErrorElephantRed()
    {
        return "Could not load \"elephantRed.png\"";
    }
    @Override
    String getErrorGeneralBlack()
    {
        return "Could not load \"generalBlack.png\"";
    }
    @Override
    String getErrorGeneralRed()
    {
        return "Could not load \"generalRed.png\"";
    }
    @Override
    String getErrorHorseBlack()
    {
        return "Could not load \"horseBlack.png\"";
    }
    @Override
    String getErrorHorseRed()
    {
        return "Could not load \"horseRed.png\"";
    }
    @Override
    String getErrorSoldierBlack()
    {
        return "Could not load \"soldierBlack.png\"";
    }
    @Override
    String getErrorSoldierRed()
    {
        return "Could not load \"soldierRed.png\"";
    }
}
