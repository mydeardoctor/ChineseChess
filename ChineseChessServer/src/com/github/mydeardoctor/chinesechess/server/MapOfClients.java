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
    public synchronized HashMap<Integer, String> getNicknamesOfAllClients()
    {
        HashMap<Integer, String> mapOfNicknamesOfAllClients = new HashMap<>();

        Set<Map.Entry<Integer, Client>> setOfClients = mapOfClients.entrySet();
        for(Map.Entry<Integer, Client> entryOfClients : setOfClients)
        {
            Integer hashCode = entryOfClients.getKey();
            Client client = entryOfClients.getValue();
            String nickname = client.getNickname();
            mapOfNicknamesOfAllClients.put(hashCode, nickname);
        }

        return mapOfNicknamesOfAllClients;
    }
    public synchronized HashMap<Integer, String> getNicknamesOfAvailableClients()
    {
        HashMap<Integer, String> mapOfNicknamesOfAvailableClients = new HashMap<>();

        Set<Map.Entry<Integer, Client>> setOfClients = mapOfClients.entrySet();
        for(Map.Entry<Integer, Client> entryOfClients : setOfClients)
        {
            Integer hashCode = entryOfClients.getKey();
            Client client = entryOfClients.getValue();
            String nickname = client.getNickname();

            if((nickname != null) && client.getState().equals(State.OVER))
            {
                mapOfNicknamesOfAvailableClients.put(hashCode, nickname);
            }
        }

        return mapOfNicknamesOfAvailableClients;
    }
}
