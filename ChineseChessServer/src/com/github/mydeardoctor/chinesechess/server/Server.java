package com.github.mydeardoctor.chinesechess.server;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    //Server attributes.
    private boolean serverIsOn;
    private ThreadPoolExecutor serverThreadPool;
    private ThreadPoolExecutor clientThreadPool;
    private ServerSocket serverSocket;
    private final ArrayList<Client> clients;
    private int maximumNumberOfPlayers;

    //GUI attributes.
    private GUI gui;

    public Server()
    {
        serverIsOn = false;
        clients = new ArrayList<>();
        maximumNumberOfPlayers = 2;
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
        try
        {
            //Stop Server Thread.
            if(serverThreadPool != null)
            {
                serverThreadPool.shutdownNow();
            }
            serverThreadPool = null;

            //Close Server Socket.
            if(serverSocket != null)
            {
                serverSocket.close();
            }
            serverSocket = null;

            //Stop Client Threads.
            if(clientThreadPool != null)
            {
                clientThreadPool.shutdownNow();
            }
            clientThreadPool = null;

            //Close Client Resources and Sockets.
            for(Client client : clients)
            {
                //close resources //TODO
                //delete resources
                //close sockets
                client = null; //delete sockets
            }

            //Clear ArrayList of Clients.
            clients.clear();

            serverIsOn = false;
            gui.setStatusBarText(gui.getText().getServerIsOff());
            gui.enableButtonStartServer();
            gui.disableButtonStopServer();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            gui.showDialogCouldNotStopServer();
        }
    }
    private void start(int portNumber, int maximumNumberOfPlayers)
    {
        try
        {
            serverThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            serverSocket = new ServerSocket(portNumber);
            this.maximumNumberOfPlayers = maximumNumberOfPlayers;
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
            if((serverSocket == null) || (clientThreadPool == null))
            {
                break;
            }

            if(clientThreadPool.getPoolSize() >= maximumNumberOfPlayers)
            {
                try
                {
                    Thread.sleep(100); //TODO заменить на нотифай
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try
                {
                    Socket clientSocket = serverSocket.accept();
                    Client client = new Client(clientSocket);
                    clients.add(client);
                    clientThreadPool.execute(client::run);
                }
                catch (Exception e)
                {
                    System.out.println("Server Socket closed.");
                    break;
                }
            }
        }
        System.out.println("Server Thread stopped.");
    }
    public boolean getIsServerOn()
    {
        return serverIsOn;
    }
}