package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.Action;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;
import com.github.mydeardoctor.chinesechess.Phase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Protocol
{
    private MapOfClients mapOfClients;
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
        //Register nickname.
        client.setNickname(nickname);

        //Refresh table of clients.
        gui.refreshTableOfClients(mapOfClients.getAllNicknames());

        //Send nicknames to clients.
        HashMap<Integer, String> mapOfNicknames = mapOfClients.getNicknamesNotInGame();
        HashMap<Integer, Client> mapOfClientsCopy = mapOfClients.getCopy();
        Set<Map.Entry<Integer, Client>> setOfClientsCopy = mapOfClientsCopy.entrySet();
        for(Map.Entry<Integer, Client> entryOfClientsCopy : setOfClientsCopy)
        {
            Integer hashCode = entryOfClientsCopy.getKey();
            Client currentClient = entryOfClientsCopy.getValue();
            String currentNickname = mapOfNicknames.get(hashCode);

            //Don't send client's own nickname to client.
            if(currentNickname != null)
            {
                mapOfNicknames.remove(hashCode);
            }

            Message message = new Message(Action.UPDATE_TABLE_OF_CLIENTS, mapOfNicknames,
                    null, null, null);
            currentClient.writeToClient(message);

            //Return nickname to map of nicknames.
            if(currentNickname != null)
            {
                mapOfNicknames.put(hashCode, currentNickname);
            }
        }
    }
}
