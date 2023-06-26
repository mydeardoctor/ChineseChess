package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.Action;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

        switch(action)
        {
            case REGISTER_NICKNAME -> registerNickname(sourceClient, (String)data);
            case INVITE -> handleInvite(sourceClient, (Integer)data);
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

            Message message = new Message(Action.UPDATE_TABLE_OF_CLIENTS, mapOfNicknames);
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
    private void handleInvite(Client sourceClient, Integer opponentHashcode)
    {
        Client opponent = mapOfClients.get(opponentHashcode);
        if((opponent != null) && (opponent.getState() != State.RUNNING))
        {
            sourceClient.setState(State.RUNNING);
            opponent.setState(State.RUNNING);

            sourceClient.setOpponentHashcode(opponentHashcode);
            opponent.setOpponentHashcode(sourceClient.hashCode());

            Player playerSide = null;
            Player opponentSide = null;
            Random randomNumberGenerator = new Random();
            int randomNumber = randomNumberGenerator.nextInt(2);
            switch(randomNumber)
            {
                case 0 ->
                {
                    playerSide = Player.RED;
                    opponentSide = Player.BLACK;
                }
                case 1 ->
                {
                    playerSide = Player.BLACK;
                    opponentSide = Player.RED;
                }
            }

            Message messageToPlayer = new Message(Action.START_GAME, new Player[]{playerSide, opponentSide});
            sourceClient.writeToClient(messageToPlayer);
            Message messageToOpponent = new Message(Action.START_GAME, new Player[]{opponentSide, playerSide});
            opponent.writeToClient(messageToOpponent);
        }
        else
        {
            Message message = new Message(Action.OPPONENT_UNAVAILABLE, null);
            sourceClient.writeToClient(message);
        }
    }
}
