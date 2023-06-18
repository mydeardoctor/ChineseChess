package com.github.mydeardoctor.chinesechess.client;

public abstract class Text
{
    //Game.

    public abstract String getYouPlay();
    public abstract String getRed();
    public abstract String getBlack();
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
    public abstract String getChineseChessClient();
    public abstract String getNavigation();
    public abstract String getMainMenu();
    public abstract String getExitToMainMenu();
    public abstract String getAllProgressWillBeLost();
    public abstract String getYes();
    public abstract String getNo();
    public abstract String getReplay();
    public abstract String getSaveReplay();
    public abstract String getReplayWarning();
    public abstract String getNothingToSave();
    public abstract String getReplayError();
    public abstract String getCouldNotSaveReplay();
    public abstract String getHelp();
    public abstract String getAbout();
    public abstract String getAboutVerbose();

    //Frame Main Menu.
    public abstract String getPlay();
    public abstract String getLoad();
    public abstract String getRules();
    public abstract String getSettings();
    public abstract String getLoadReplay();
    public abstract String getCouldNotLoadReplay();

    //Frame Game Mode.
    public abstract String getSinglePlayer();
    public abstract String getLocalMultiplayer();
    public abstract String getOnlineMultiplayer();
    public abstract String getBack();

    //Frame Online Multiplayer.
    public abstract String getConnectToServer();
    public abstract String getLobby();
    public abstract String getDisconnectedFromServer();
    public abstract String getConnectedToServer();

    //Frame Connect.
    public abstract String getIpAddress();
    public abstract String getIpTip();
    public abstract String getPort();
    public abstract String getPortTip();
    public abstract String getConnect();
    public abstract String getDisconnect();
    public abstract String getClientError();
    public abstract String getCouldNotConnectToServer();
    public abstract String getDisconnectFromServer();
    public abstract String getConnectionWillBeLost();
    public abstract String getClientInfo();

    //Frame Rules.
    public abstract String getGoal();
    public abstract String getGoalRule();
    public abstract String getPalace();
    public abstract String getPalaceRule();
    public abstract String getRiver();
    public abstract String getRiverRule();
    public abstract String getGeneral();
    public abstract String getGeneralRule();
    public abstract String getAdvisor();
    public abstract String getAdvisorRule();
    public abstract String getElephant();
    public abstract String getElephantRule();
    public abstract String getHorse();
    public abstract String getHorseRule();
    public abstract String getChariot();
    public abstract String getChariotRule();
    public abstract String getCannon();
    public abstract String getCannonRule();
    public abstract String getSoldier();
    public abstract String getSoldierRule();

    //Frame Settings.
    public abstract String getLanguage();
    public abstract String getMusic();
    public abstract String getSoundEffects();

    //Music player.

    public abstract String getMusicPlayerWarning();
    public abstract String getSomeFeaturesAreNotAvailable();
}