package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;
import com.github.mydeardoctor.chinesechess.Phase;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client
{
    //Client attributes.
    private final Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private String nickname; //TODO
    private State state; //TODO
    private Player turn; //TODO
    private Phase phase; //TODO

    //Server attributes.
    private final Server server;

    //Map of Clients attributes.
    private final MapOfClients mapOfClients;

    //Protocol attributes.
    private final Protocol protocol;

    //GUI attributes.
    private final GUI gui;

    //Logger.
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public Client(Socket clientSocket, Server server, MapOfClients mapOfClients, Protocol protocol, GUI gui)
    {
        this.clientSocket = clientSocket;
        nickname = null;
        state = State.OVER;
        turn = Player.RED;
        phase = Phase.CHOOSE_FIGURE;
        this.server = server;
        this.mapOfClients = mapOfClients;
        this.protocol = protocol;
        this.gui = gui;
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
    public void run() //TODO отсылать клиентам никнеймы, если клиент зашёл в игру или вышел из неё
    {
        while(true)
        {
            try
            {
                //If clientSocket is closed,
                //any thread currently blocked in an I/O operation upon this socket will throw a SocketException.
                Message message = (Message)(objectInputStream.readObject());
                protocol.processInput(message, this);
            }
            catch (IOException | ClassNotFoundException e) //SocketException is a subclass of IOException.
            {
                System.out.println("Client Socket closed.\n");

                closeResources();
                mapOfClients.remove(this.hashCode());
                gui.refreshTableOfClients(mapOfClients.getAllNicknames());
                //TODO
//                server.sendListOfClientsToEveryClient();
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
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
    public State getState()
    {
        return state;
    }
}
