package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.Message;
import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.Player;

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
    private String nickname;
    private State state; //TODO state
    private Player turn; //TODO turn
    //TODO Integer opponenthashcode

    //Map of Clients attributes.
    private final MapOfClients mapOfClients;

    //Protocol attributes.
    private final Protocol protocol;

    //GUI attributes.
    private final GUI gui;

    //Logger.
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public Client(Socket clientSocket, MapOfClients mapOfClients, Protocol protocol, GUI gui)
    {
        this.clientSocket = clientSocket;
        nickname = null;
        state = State.OVER;
        turn = Player.RED;
        //TODO opponentHashCode = null
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

            closeStreams();
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
                Message message = (Message)(objectInputStream.readObject());
                protocol.processInput(this, message);
            }
            catch (IOException | ClassNotFoundException e) //SocketException is a subclass of IOException.
            {
                System.out.println("Client Socket closed.\n");

                //Close resources and delete this client.
                closeStreams();
                mapOfClients.remove(this.hashCode());

                //Delete reference to this client from its opponent.
                //TODO if opponent!= null       opponent statesReset, opponentHashCode = null     sendWarningToOpponent(states reset, gameOver, guiReset, guiShowFrame, guiShowWarning)

                //Send every client an updated list of clients.
                protocol.sendUpdateTableOfClients();

                //Refresh server's GUI.
                gui.refreshTableOfClients(mapOfClients.getNicknames());

                break;
            }
        }
        System.out.println("Client Thread stopped.\n");
    }
    public synchronized void writeToClient(Object message)
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
    private void closeStreams()
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
