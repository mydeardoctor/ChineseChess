package com.github.mydeardoctor.chinesechess.server;

import java.util.ArrayList;

public class ListOfClients
{
    private final ArrayList<Client> clients;
    public ListOfClients()
    {
        clients = new ArrayList<>();
    }
    public synchronized void add(Client client)
    {
        clients.add(client);
    }
    public synchronized void remove(Client client)
    {
        clients.remove(client);
    }
    public synchronized ArrayList<Client> getClients()
    {
        ArrayList<Client> clientsCopy = new ArrayList<>();
        for(Client client : clients)
        {
            clientsCopy.add(client);
        }
        return clientsCopy;
    }
}
