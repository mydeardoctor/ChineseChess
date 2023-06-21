package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.Action;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;
import com.github.mydeardoctor.chinesechess.Phase;
import java.util.HashMap;

public class Protocol
{
    //GUI attributes.
    private GUI gui;
    public Protocol()
    {
        super();
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void processInput(Message message)
    {
        Action action = message.getAction();
        Object data = message.getData();
        State state = message.getState();
        Player turn = message.getTurn();
        Phase phase = message.getPhase();

        switch(action)
        {
            case UPDATE_TABLE_OF_CLIENTS -> gui.refreshTableOfClients((HashMap<Integer, String>)data);
        }
    }
}
