package com.github.mydeardoctor.chinesechess.server;

import java.net.InetAddress;
import java.net.Socket;
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
    public synchronized ArrayList<Socket> getClientSockets()
    {
        ArrayList<Socket> clientSockets = new ArrayList<>();
        for(Client client : clients)
        {
            clientSockets.add(client.getClientSocket());
        }
        return clientSockets;
    }
    public synchronized ArrayList<InetAddress> getClientInetAddresses()
    {
        ArrayList<InetAddress> clientInetAddresses = new ArrayList<>();
        for(Client client : clients)
        {
            clientInetAddresses.add(client.getClientSocket().getInetAddress());
        }
        return clientInetAddresses;
    }
}
