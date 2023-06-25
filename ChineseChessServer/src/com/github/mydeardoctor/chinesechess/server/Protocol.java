package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.Action;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Protocol
{
    //Map of Clients attributes.
    private MapOfClients mapOfClients;

    //GUI attributes.
    private GUI gui;

    public Protocol()
    {
        super();
    }
    public void setMapOfClients(MapOfClients mapOfClients)
    {
        this.mapOfClients = mapOfClients;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void processInput(Client sourceClient, Message message)
    {
        Action action = message.action();
        Object data = message.data();
        State state = message.state(); //TODO если серверу без разницы на state и turn, которые присылает клиент, то эти строчки не нужны.
        Player turn = message.turn();

        switch(action)
        {
            case REGISTER_NICKNAME -> registerNickname(sourceClient, (String)data);
        }
    }
    private void registerNickname(Client client, String nickname)
    {
        client.setNickname(nickname);
        sendUpdateTableOfClients();
        gui.refreshTableOfClients(getNicknames());
    }
    public void sendUpdateTableOfClients()
    {
        HashMap<Integer, Client> mapOfClientsCopy = mapOfClients.getCopy();
        HashMap<Integer, String> mapOfNicknames = getNicknames();

        Set<Map.Entry<Integer, Client>> setOfClientsCopy = mapOfClientsCopy.entrySet();
        for(Map.Entry<Integer, Client> entryOfClientsCopy : setOfClientsCopy)
        {
            Integer hashCode = entryOfClientsCopy.getKey();
            Client client = entryOfClientsCopy.getValue();
            String nickname = mapOfNicknames.get(hashCode);

            //Don't send client's own nickname.
            if(nickname != null)
            {
                mapOfNicknames.remove(hashCode);
            }

            Message message = new Message(Action.UPDATE_TABLE_OF_CLIENTS, mapOfNicknames, null, null);
            client.writeToClient(message);

            //Put nickname back.
            if(nickname != null)
            {
                mapOfNicknames.put(hashCode, nickname);
            }
        }
    }
    public HashMap<Integer, String> getNicknames()
    {
        HashMap<Integer, String> mapOfNicknames = new HashMap<>();

        HashMap<Integer, Client> mapOfClientsCopy = mapOfClients.getCopy();
        Set<Map.Entry<Integer, Client>> setOfClientsCopy = mapOfClientsCopy.entrySet();
        for(Map.Entry<Integer, Client> entryOfClientsCopy : setOfClientsCopy)
        {
            Integer hashCode = entryOfClientsCopy.getKey();
            Client client = entryOfClientsCopy.getValue();
            String nickname = client.getNickname();

            if(nickname != null)
            {
                mapOfNicknames.put(hashCode, nickname);
            }
        }

        return mapOfNicknames;
    }
}
