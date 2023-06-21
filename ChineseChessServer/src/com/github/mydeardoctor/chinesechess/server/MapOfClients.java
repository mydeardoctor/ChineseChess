package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapOfClients
{
    private final HashMap<Integer, Client> mapOfClients;
    public MapOfClients()
    {
        mapOfClients = new HashMap<>();
    }
    public synchronized void put(Integer hashCode, Client client)
    {
        mapOfClients.put(hashCode, client);
    }
    public synchronized void remove(Integer hashCode)
    {
        mapOfClients.remove(hashCode);
    }
    public synchronized HashMap<Integer, Client> getCopy()
    {
        HashMap<Integer, Client> mapOfClientsCopy = new HashMap<>();

        Set<Map.Entry<Integer, Client>> setOfClients = mapOfClients.entrySet();
        for(Map.Entry<Integer, Client> entryOfClients : setOfClients)
        {
            Integer hashCode = entryOfClients.getKey();
            Client client = entryOfClients.getValue();
            mapOfClientsCopy.put(hashCode, client);
        }

        return mapOfClientsCopy;
    }
    public synchronized HashMap<Integer, String> getAllNicknames()
    {
        HashMap<Integer, String> mapOfAllNicknames = new HashMap<>();

        Set<Map.Entry<Integer, Client>> setOfClients = mapOfClients.entrySet();
        for(Map.Entry<Integer, Client> entryOfClients : setOfClients)
        {
            Integer hashCode = entryOfClients.getKey();
            String nickname = entryOfClients.getValue().getNickname();
            mapOfAllNicknames.put(hashCode, nickname);
        }

        return mapOfAllNicknames;
    }
    public synchronized HashMap<Integer, String> getNicknamesNotInGame()
    {
        HashMap<Integer, String> mapOfNicknamesNotInGame = new HashMap<>();

        Set<Map.Entry<Integer, Client>> setOfClients = mapOfClients.entrySet();
        for(Map.Entry<Integer, Client> entryOfClients : setOfClients)
        {
            Integer hashCode = entryOfClients.getKey();
            Client client = entryOfClients.getValue();
            String nickname = client.getNickname();

            if(client.getState().equals(State.OVER) && (nickname != null))
            {
                mapOfNicknamesNotInGame.put(hashCode, nickname);
            }
        }

        return mapOfNicknamesNotInGame;
    }
}
