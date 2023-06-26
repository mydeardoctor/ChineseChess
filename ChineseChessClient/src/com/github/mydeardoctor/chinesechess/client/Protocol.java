package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.Action;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;

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
            case START_GAME -> startGame((Player[])data);
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
    private void startGame(Player[] sides)
    {
        SwingUtilities.invokeLater(()->
        {
            gui.startOnlineMultiplayerGame(sides[0], sides[1]);
        });
    }
}
