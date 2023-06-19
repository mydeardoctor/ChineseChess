package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.*;

import java.net.InetAddress;
import java.util.ArrayList;

public class Protocol
{
    private GUI gui;
    public Protocol()
    {
        super();
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void handleInput(Message message)
    {
        Action action = message.getAction();
        Object data = message.getData();
        State state = message.getState();
        Player turn = message.getTurn();
        Phase phase = message.getPhase();

        switch(action)
        {
            case UPDATE_TABLE_OF_CLIENTS -> updateTableOfClients((ArrayList<InetAddress>)data);
        }
    }
    private void updateTableOfClients(ArrayList<InetAddress> ipAddressesOfClientsNotInGame)
    {
        gui.refreshTableOfClients(ipAddressesOfClientsNotInGame);
    }
}
