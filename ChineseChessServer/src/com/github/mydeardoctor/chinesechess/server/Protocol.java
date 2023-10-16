package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

//TODO null args opponenthashcode, opponent
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
            case MOVE -> resendMove(sourceClient, (Location[])data);
            case END_GAME -> endGame(sourceClient);
            case PLAYER_QUIT -> resendPlayerQuit(sourceClient);
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

            String playerNickname = sourceClient.getNickname();
            String opponentNickname = opponent.getNickname();

            Side playerSide = null;
            Side opponentSide = null;
            Random randomNumberGenerator = new Random();
            int randomNumber = randomNumberGenerator.nextInt(2);
            switch(randomNumber)
            {
                case 0 ->
                {
                    playerSide = Side.RED;
                    opponentSide = Side.BLACK;
                }
                case 1 ->
                {
                    playerSide = Side.BLACK;
                    opponentSide = Side.RED;
                }
            }

            Message messageToPlayer = new Message(Action.START_GAME,
                    new Object[]{opponentNickname, playerSide, opponentSide});
            sourceClient.writeToClient(messageToPlayer);
            Message messageToOpponent = new Message(Action.START_GAME,
                    new Object[]{playerNickname, opponentSide, playerSide});
            opponent.writeToClient(messageToOpponent);
        }
        else
        {
            Message message = new Message(Action.OPPONENT_UNAVAILABLE, null);
            sourceClient.writeToClient(message);
        }
    }
    private void resendMove(Client sourceClient, Location[] move)
    {
        Integer opponentHashcode = sourceClient.getOpponentHashcode();
        Client opponent = mapOfClients.get(opponentHashcode);
        if(opponent != null)
        {
            Location origin = move[0];
            Location destination = move[1];

            Message message = new Message(Action.MOVE, new Location[]{origin, destination});
            opponent.writeToClient(message);
        }
    }
    private void endGame(Client sourceClient)
    {
        sourceClient.setState(State.OVER);
        sourceClient.setOpponentHashcode(null);
    }
    private void resendPlayerQuit(Client sourceClient)
    {
        Integer opponentHashcode = sourceClient.getOpponentHashcode();
        Client opponent = mapOfClients.get(opponentHashcode);
        if(opponent != null)
        {
            Message message = new Message(Action.PLAYER_QUIT, null);
            opponent.writeToClient(message);
        }

        sourceClient.setState(State.OVER);
        sourceClient.setOpponentHashcode(null);
    }
    public void sendPlayerDisconnected(Client sourceClient)
    {
        Integer opponentHashcode = sourceClient.getOpponentHashcode();
        Client opponent = mapOfClients.get(opponentHashcode);
        if(opponent != null)
        {
            Message message = new Message(Action.PlAYER_DISCONNECTED, null);
            opponent.writeToClient(message);
        }
    }
}
