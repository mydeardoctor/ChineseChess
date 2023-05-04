package com.github.mydeardoctor.chinesechess;

abstract class Text
{
    abstract String getTitle();

    //Menu Bar.
    abstract String getHelp();
    abstract String getAbout();
    abstract String getAboutVerbose();

    //Frame Main Menu.
    abstract String getPlay();
    abstract String getLoad();
    abstract String getRules();
    abstract String getSettings();

    //Frame Game Mode.
    abstract String getSinglePlayer();
    abstract String getLocalMultiplayer();
    abstract String getOnlineMultiplayer();
    abstract String getBack();

    //Frame Settings.
    abstract String getLanguage();
    String getEnglish()
    {
        return "English";
    }
    String getRussian()
    {
        return "Русский";
    }
    abstract String getApply();

    //Errors.
    abstract String getError();
    abstract String getErrorIcon();
    abstract String getErrorFont();
    abstract String getErrorBackground();
    abstract String getErrorAdvisorBlack();
    abstract String getErrorAdvisorRed();
    abstract String getErrorCannonBlack();
    abstract String getErrorCannonRed();
    abstract String getErrorChariotBlack();
    abstract String getErrorChariotRed();
    abstract String getErrorElephantBlack();
    abstract String getErrorElephantRed();
    abstract String getErrorGeneralBlack();
    abstract String getErrorGeneralRed();
    abstract String getErrorHorseBlack();
    abstract String getErrorHorseRed();
    abstract String getErrorSoldierBlack();
    abstract String getErrorSoldierRed();
}