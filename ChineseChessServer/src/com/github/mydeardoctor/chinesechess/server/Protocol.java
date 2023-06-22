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
    public void processInput(Client client, Message message)
    {
        Action action = message.action();
        Object data = message.data();
        State state = message.state();
        Player turn = message.turn();
        Phase phase = message.phase();

        switch(action)
        {
            case REGISTER_NICKNAME -> registerNickname(client, (String)data);
        }
    }
    private void registerNickname(Client client, String nickname)
    {
        client.setNickname(nickname);
        gui.refreshTableOfClients(mapOfClients.getNicknamesOfAllClients());
        sendUpdateTableOfClients();
    }
    public void sendUpdateTableOfClients()
    {
        HashMap<Integer, Client> mapOfClientsCopy = mapOfClients.getCopy();
        HashMap<Integer, String> mapOfNicknamesOfAvailableClients = mapOfClients.getNicknamesOfAvailableClients();

        Set<Map.Entry<Integer, Client>> setOfClientsCopy = mapOfClientsCopy.entrySet();
        for(Map.Entry<Integer, Client> entryOfClientsCopy : setOfClientsCopy)
        {
            Integer hashCode = entryOfClientsCopy.getKey();
            Client client = entryOfClientsCopy.getValue();
            String nickname = mapOfNicknamesOfAvailableClients.get(hashCode);

            //Don't send client's own nickname.
            if(nickname != null)
            {
                mapOfNicknamesOfAvailableClients.remove(hashCode);
            }

            Message message = new Message(Action.UPDATE_TABLE_OF_CLIENTS, mapOfNicknamesOfAvailableClients,
                    null, null, null);
            client.writeToClient(message);

            //Put nickname back.
            if(nickname != null)
            {
                mapOfNicknamesOfAvailableClients.put(hashCode, nickname);
            }
        }
    }
}
