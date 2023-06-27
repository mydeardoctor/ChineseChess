package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.*;
import com.github.mydeardoctor.chinesechess.Action;

import javax.swing.*;
import java.util.HashMap;

public class Protocol
{
    //Game attributes.
    private GameOnlineMultiplayer gameOnlineMultiplayer;

    //Client attributes.
    private Client client;

    //GUI attributes.
    private GUI gui;

    public Protocol()
    {
        super();
    }
    public void setGameOnlineMultiplayer(GameOnlineMultiplayer gameOnlineMultiplayer)
    {
        this.gameOnlineMultiplayer = gameOnlineMultiplayer;
    }
    public void setClient(Client client)
    {
        this.client = client;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void processInput(Message message)
    {
        Action action = message.action();
        Object data = message.data();

        switch(action)
        {
            case UPDATE_TABLE_OF_CLIENTS -> updateTableOfClients((HashMap<Integer, String>)data);
            case OPPONENT_UNAVAILABLE -> opponentUnavailable();
            case START_GAME -> startGame((Object[])data);
            case MOVE -> makeMove((Object[])data);
            case PLAYER_QUIT -> playerQuit();
            case PlAYER_DISCONNECTED -> playerDisconnected();
        }
    }
    public void sendRegisterNickname()
    {
        Message message = new Message(Action.REGISTER_NICKNAME, client.getNickname());
        client.writeToServer(message);
    }
    private void updateTableOfClients(HashMap<Integer, String> mapOfNicknames)
    {
        gui.refreshTableOfClients(mapOfNicknames);
    }
    public void sendInvite(Integer opponentHashcode)
    {
        Message message = new Message(Action.INVITE, opponentHashcode);
        client.writeToServer(message);
    }
    private void opponentUnavailable()
    {
        gui.setButtonInviteIgnoresSelections(false);
        gui.showDialogOpponentUnavailable();
    }
    private void startGame(Object[] data)
    {
        SwingUtilities.invokeLater(()->
        {
            gui.startOnlineMultiplayerGame((String)data[0], (Player)data[1], (Player)data[2]);
        });
    }
    public void sendMove(Location origin, Location destination)
    {
        Message message = new Message(Action.MOVE, new Object[]{origin, destination});
        client.writeToServer(message);
    }
    private void makeMove(Object[] moves)
    {
        SwingUtilities.invokeLater(()->
        {
            Location origin = (Location)moves[0];
            Location destination = (Location)moves[1];
            gameOnlineMultiplayer.receiveMoveFromServer(origin, destination);
        });
    }
    public void sendEndGame()
    {
        Message message = new Message(Action.END_GAME, null);
        client.writeToServer(message);
    }
    public void sendPlayerQuit()
    {
        Message message = new Message(Action.PLAYER_QUIT, null);
        client.writeToServer(message);
    }
    private void playerQuit()
    {
        SwingUtilities.invokeLater(()->
        {
            gameOnlineMultiplayer.unhighlightEverything();
            gameOnlineMultiplayer.setTurn(gameOnlineMultiplayer.getOpponentSide()); //TODO для правильного сообщения
            gameOnlineMultiplayer.over();
            gui.showDialogOpponentQuit();
        });
    }
    private void playerDisconnected()
    {
        SwingUtilities.invokeLater(()->
        {
            gameOnlineMultiplayer.unhighlightEverything();
            gameOnlineMultiplayer.setTurn(gameOnlineMultiplayer.getOpponentSide()); //TODO для правильного сообщения
            gameOnlineMultiplayer.over();
            gui.showDialogOpponentDisconnected();
        });
    }
}
