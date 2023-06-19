package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client
{
    private final Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private State state; //TODO
    private Player turn; //TODO
    private Phase phase; //TODO
    private final Server server;
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public Client(Socket clientSocket, Server server)
    {
        this.clientSocket = clientSocket;
        state = State.OVER;
        turn = Player.RED;
        phase = Phase.CHOOSE_FIGURE;
        this.server = server;
    }
    public boolean tryToOpenStreams()
    {
        boolean result;

        try
        {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            result = true;
        }
        catch (IOException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "tryToOpenStreams",
                    "Could not open objectOutputStream and objectInputStream of clientSocket.", e);

            closeResources();
            result = false;
        }

        return result;
    }
    public void run()
    {
        while(true)
        {
            try
            {
                //If clientSocket is closed,
                //any thread currently blocked in an I/O operation upon this socket will throw a SocketException.
                String message = (String)(objectInputStream.readObject());
                System.out.println(message + "\n");
            }
            catch (IOException | ClassNotFoundException e) //SocketException is a subclass of IOException.
            {
                System.out.println("Client Socket closed.\n");

                closeResources();
                server.getListOfClients().remove(this);
                server.getGui().refreshTableOfClients(server.getListOfClients().getClients());
                server.sendListOfClientsToEveryClient();
                break;
            }
        }
        System.out.println("Client Thread stopped.\n");
    }
    private void closeResources()
    {
        if(objectOutputStream != null)
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException e)
            {
                logger.logp(Level.WARNING, this.getClass().getName(), "closeResources",
                        "Could not close objectOutputStream of clientSocket.", e);
            }
        }
        if(objectInputStream != null)
        {
            try
            {
                objectInputStream.close();
            }
            catch (IOException e)
            {
                logger.logp(Level.WARNING, this.getClass().getName(), "closeResources",
                        "Could not close objectInputStream of clientSocket.", e);
            }
        }
    }
    public synchronized void writeToClient(Object message) //TODO Нужно ли закрывать соединение? Не проверен exception
    {
        try
        {
            objectOutputStream.writeObject(message);
        }
        catch (IOException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "writeToClient",
                    "Could not write to objectOutputStream of clientSocket.", e);
        }
    }
    public Socket getClientSocket()
    {
        return clientSocket;
    }
    public State getState()
    {
        return state;
    }
}
