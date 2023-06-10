package com.github.mydeardoctor.chinesechess.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Server
{
    //Server attributes.
    private boolean serverIsOn;
    private ThreadPoolExecutor serverThreadPool;
    private ThreadPoolExecutor clientThreadPool;
    private ServerSocket serverSocket;
    private final ArrayList<Client> clients;

    //GUI attributes.
    private GUI gui;

    public Server()
    {
        serverIsOn = false;
        clients = new ArrayList<>();
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void restart(int portNumber, int maximumNumberOfPlayers)
    {
        stop();
        start(portNumber, maximumNumberOfPlayers);
    }
    public void stop()
    {
        //Close Server Socket. Causes an Exception in Server Thread. Server Thread begins to stop.
        if(serverSocket != null)
        {
            try
            {
                serverSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //Wait for Server Thread to stop.
        if(serverThreadPool != null)
        {
            serverThreadPool.shutdown();
            try
            {
                serverThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        //Close Client Sockets. Causes an Exception in Client Threads. Client Threads begin to stop.
        for(Client client : clients)
        {
            try
            {
                client.getClientSocket().close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //Wait for Client Threads to stop.
        if(clientThreadPool != null)
        {
            clientThreadPool.shutdown();
            try
            {
                clientThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        //Clear ArrayList of Clients.
        clients.clear();

        //Refresh GUI.
        serverIsOn = false;
        gui.setStatusBarText(gui.getText().getServerIsOff());
        gui.enableButtonStartServer();
        gui.disableButtonStopServer();
    }
    private void start(int portNumber, int maximumNumberOfPlayers)
    {
        try
        {
            serverSocket = new ServerSocket(portNumber);
            serverThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(maximumNumberOfPlayers);

            serverIsOn = true;
            gui.setStatusBarText(gui.getText().getServerIsOn());
            gui.disableButtonStartServer();
            gui.enableButtonStopServer();

            serverThreadPool.execute(this::run);
        }
        catch(Exception e)
        {
            e.printStackTrace();

            serverIsOn = false;
            gui.setStatusBarText(gui.getText().getServerIsOff());
            gui.enableButtonStartServer();
            gui.disableButtonStopServer();
            gui.showDialogCouldNotStartServer();
        }
    }
    private void run()
    {
        while (true)
        {
            try
            {
                Socket clientSocket = serverSocket.accept();
                if(clientThreadPool.getActiveCount() < clientThreadPool.getCorePoolSize())
                {
                    Client client = new Client(clientSocket);
                    if(client.tryToOpenStreams())
                    {
                        clients.add(client);
                        clientThreadPool.execute(client::run);
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Server Socket closed.");
                break;
            }
        }
        System.out.println("Server Thread stopped.");
    }
    public boolean getIsServerOn()
    {
        return serverIsOn;
    }
}