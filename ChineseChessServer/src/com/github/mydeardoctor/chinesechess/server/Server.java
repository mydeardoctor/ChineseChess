package com.github.mydeardoctor.chinesechess.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server
{
    //Server attributes.
    private boolean serverRunning;
    private ServerSocket serverSocket;
    private ThreadPoolExecutor serverThreadPool;
    private ThreadPoolExecutor clientThreadPool;

    //Map of Clients attributes.
    private MapOfClients mapOfClients;

    //Protocol attributes.
    private Protocol protocol;

    //GUI attributes.
    private GUI gui;

    //Logger.
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public Server()
    {
        serverRunning = false;
    }
    public void setMapOfClients(MapOfClients mapOfClients)
    {
        this.mapOfClients = mapOfClients;
    }
    public void setProtocol(Protocol protocol)
    {
        this.protocol = protocol;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void start(int portNumber, int maximumNumberOfPlayers)
    {
        try
        {
            serverSocket = new ServerSocket(portNumber, 1);
            serverThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(maximumNumberOfPlayers);
            serverThreadPool.execute(this::run);

            serverRunning = true;
            gui.setServerRunning();
        }
        catch(IOException e)
        {
            serverRunning = false;
            gui.setServerStopped();
            gui.showDialogCouldNotStartServer();

            logger.logp(Level.WARNING, this.getClass().getName(), "start",
                    "Could not create serverSocket.", e);
        }
    }
    private void run()
    {
        while (true)
        {
            try
            {
                //If serverSocket is closed, any thread currently blocked in accept() will throw a SocketException.
                Socket clientSocket = serverSocket.accept();
                if(clientThreadPool.getActiveCount() < clientThreadPool.getCorePoolSize())
                {
                    Client client = new Client(clientSocket, mapOfClients, protocol, gui);
                    if(client.tryToOpenStreams())
                    {
                        mapOfClients.put(client.hashCode(), client);
                        clientThreadPool.execute(client::run);
                    }
                    else
                    {
                        try
                        {
                            clientSocket.close();
                        }
                        catch (IOException e)
                        {
                            logger.logp(Level.WARNING, this.getClass().getName(), "run",
                                    "Could not close clientSocket.", e);
                        }
                    }
                }
                else
                {
                    try
                    {
                        clientSocket.close();
                    }
                    catch (IOException e)
                    {
                        logger.logp(Level.WARNING, this.getClass().getName(), "run",
                                "Could not close clientSocket.", e);
                    }
                }
            }
            catch (IOException e) //SocketException is a subclass of IOException.
            {
                serverRunning = false;
                gui.setServerStopped();

                System.out.println("Server Socket closed.\n");

                break;
            }
        }
        System.out.println("Server Thread stopped.\n");
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
                logger.logp(Level.WARNING, this.getClass().getName(), "stop",
                        "Could not close serverSocket.", e);
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
                logger.logp(Level.WARNING, this.getClass().getName(), "stop",
                        "Could not stop threads of serverThreadPool.", e);
            }
        }

        //Close Client Sockets. Causes an Exception in Client Threads. Client Threads begin to stop.
        HashMap<Integer, Client> clients = mapOfClients.getCopy();
        Set<Map.Entry<Integer, Client>> setOfClients = clients.entrySet();
        for(Map.Entry<Integer, Client> entryOfClients : setOfClients)
        {
            try
            {
                entryOfClients.getValue().getClientSocket().close();
            }
            catch (IOException e)
            {
                logger.logp(Level.WARNING, this.getClass().getName(), "stop",
                        "Could not close client sockets.", e);
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
                logger.logp(Level.WARNING, this.getClass().getName(), "stop",
                        "Could not stop threads of clientThreadPool.", e);
            }
        }

        //Refresh GUI.
        serverRunning = false;
        gui.setServerStopped();
    }
    public boolean getIsServerRunning()
    {
        return serverRunning;
    }
}