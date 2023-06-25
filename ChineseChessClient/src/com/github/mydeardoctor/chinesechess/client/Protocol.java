package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.Action;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;
import java.util.HashMap;

public class Protocol
{
    //Client attributes.
    private Client client;

    //GUI attributes.
    private GUI gui;

    public Protocol()
    {
        super();
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
        State state = message.state();
        Player turn = message.turn();

        switch(action)
        {
            case UPDATE_TABLE_OF_CLIENTS -> updateTableOfClients((HashMap<Integer, String>)data);
        }
    }
    public void sendRegisterNickname()
    {
        Message message = new Message(Action.REGISTER_NICKNAME, client.getNickname(), null, null);
        client.writeToServer(message);
    }
    private void updateTableOfClients(HashMap<Integer, String> mapOfNicknames)
    {
        gui.refreshTableOfClients(mapOfNicknames);
    }
}
