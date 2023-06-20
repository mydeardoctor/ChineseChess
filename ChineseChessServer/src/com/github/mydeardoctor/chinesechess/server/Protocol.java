package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.Action;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;
import com.github.mydeardoctor.chinesechess.Phase;

public class Protocol
{
    public Protocol()
    {
        super();
    }
    public void processInput(Message message, Client client)
    {
        Action action = message.getAction();
        Object data = message.getData();
        State state = message.getState();
        Player turn = message.getTurn();
        Phase phase = message.getPhase();

        switch(action)
        {
            case REGISTER_NICKNAME -> registerNickname(client, (String)data);
        }
    }
    private void registerNickname(Client client, String nickname)
    {
        client.setNickname(nickname);
        System.out.println(nickname); //TODO!!!!!!!!!!!!!!!!!!!
    }
}
