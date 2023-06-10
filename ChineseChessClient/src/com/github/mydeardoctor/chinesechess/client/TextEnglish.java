package com.github.mydeardoctor.chinesechess.client;

public class TextEnglish extends Text
{
    //Game.

    @Override
    public String getYouPlay()
    {
        return "You play ";
    }
    @Override
    public String getRed()
    {
        return "Red.";
    }
    @Override
    public String getBlack()
    {
        return "Black.";
    }
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
    public String getChooseAnotherFigureOrDestination()
    {
        return "choose another figure or destination.";
    }
    @Override
    public String getGameOver()
    {
        return "Game over.";
    }
    @Override
    public String getWon()
    {
        return "won.";
    }

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
        return "Chinese Chess";
    }
    @Override
    public String getNavigation()
    {
        return "Navigation";
    }
    @Override
    public String getMainMenu()
    {
        return "Main menu";
    }
    @Override
    public String getExitToMainMenu()
    {
        return "Exit to main menu";
    }
    @Override
    public String getAreYouSure()
    {
        return "All progress will be lost!\nAre you sure?";
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
    public String getReplay()
    {
        return "Replay";
    }
    @Override
    public String getSaveReplay()
    {
        return "Save replay";
    }
    @Override
    public String getReplayWarning()
    {
        return "Replay warning";
    }
    @Override
    public String getNothingToSave()
    {
        return "Nothing to save!";
    }
    @Override
    public String getReplayError()
    {
        return "Replay error";
    }
    @Override
    public String getCouldNotSaveReplay()
    {
        return "Could not save replay!";
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
    @Override
    public String getLoadReplay()
    {
        return "Load replay";
    }
    @Override
    public String getCouldNotLoadReplay()
    {
        return "Could not load replay!";
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

    //Frame Connect to Server.
    @Override
    public String getIpAddress()
    {
        return "IP address";
    }
    @Override
    public String getIpTip()
    {
        return "Must be in format XXX.XXX.XXX.XXX\nEach byte must be between 0 and 255";
    }
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
    public String getConnect()
    {
        return "Connect";
    }
    @Override
    public String getClientError()
    {
        return "Client error";
    }
    @Override
    public String getCouldNotConnectToServer()
    {
        return "Could not connect to server!";
    }

    //Frame Rules.
    @Override
    public String getGoal()
    {
        return "Goal";
    }
    @Override
    public String getGoalRule()
    {
        return "The goal of the game is to checkmate the enemy general.";
    }
    @Override
    public String getPalace()
    {
        return "Palace";
    }
    @Override
    public String getPalaceRule()
    {
        return "A general cannot leave the Palace. An advisor cannot leave the Palace.";
    }
    @Override
    public String getRiver()
    {
        return "River";
    }
    @Override
    public String getRiverRule()
    {
        return "After crossing the River a soldier can also move horizontally one point." +
                " An elephant cannot cross the River.";
    }
    @Override
    public String getGeneral()
    {
        return "General";
    }
    @Override
    public String getGeneralRule()
    {
        return "A general moves horizontally or vertically one point. A general cannot leave the Palace." +
                " Generals cannot face each other.";
    }
    @Override
    public String getAdvisor()
    {
        return "Advisor";
    }
    @Override
    public String getAdvisorRule()
    {
        return "An advisor moves diagonally one point. An advisor cannot leave the Palace.";
    }
    @Override
    public String getElephant()
    {
        return "Elephant";
    }
    @Override
    public String getElephantRule()
    {
        return "An elephant moves diagonally two points. An elephant cannot jump over other pieces." +
                " An elephant cannot cross the River.";
    }
    @Override
    public String getHorse()
    {
        return "Horse";
    }
    @Override
    public String getHorseRule()
    {
        return "A horse moves horizontally or vertically one point and then diagonally one point." +
                " A horse cannot jump over other pieces.";
    }
    @Override
    public String getChariot()
    {
        return "Chariot";
    }
    @Override
    public String getChariotRule()
    {
        return "A chariot moves horizontally or vertically any number of points." +
                " A chariot cannot jump over other pieces.";
    }
    @Override
    public String getCannon()
    {
        return "Cannon";
    }
    @Override
    public String getCannonRule()
    {
        return "A cannon moves horizontally or vertically any number of points." +
                " But to capture an enemy piece a cannon must jump over one other piece.";
    }
    @Override
    public String getSoldier()
    {
        return "Soldier";
    }
    @Override
    public String getSoldierRule()
    {
        return "A soldier moves forward vertically one point." +
                " After crossing the River a soldier can also move horizontally one point.";
    }

    //Frame Settings.
    @Override
    public String getLanguage()
    {
        return "Language";
    }
    @Override
    public String getMusic()
    {
        return "Music";
    }
    @Override
    public String getSfx()
    {
        return "Sound effects";
    }

    //Music player.

    @Override
    public String getMusicPlayerWarning()
    {
        return "Music player warning";
    }
    @Override
    public String getSomeFeaturesAreNotAvailable()
    {
        return "Some features are not available!";
    }
}