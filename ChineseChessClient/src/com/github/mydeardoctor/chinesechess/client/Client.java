package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Message;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client
{
    //Client attributes.
    private boolean connectedToServer;
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ThreadPoolExecutor clientThreadPool;
    private String nickname;

    //Protocol attributes.
    private Protocol protocol;

    //GUI attributes.
    private GUI gui;

    //Logger.
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public Client()
    {
        connectedToServer = false;
    }
    public void setProtocol(Protocol protocol)
    {
        this.protocol = protocol;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void connect(String ipAddress, int portNumber, String nickname)
    {
        gui.disableButtonConnect();
        gui.disableButtonDisconnect();

        Client thisClient = this;
        clientSocket = new Socket();
        this.nickname = nickname;

        //Establishing a connection with a server requires time.
        //SwingWorker is used so that EDT will not be blocked during establishing of connection.
        SwingWorker<Boolean, Void> connectorToServer = new SwingWorker<>()
        {
            @Override
            protected Boolean doInBackground() //Invoked on a separate thread.
            {
                boolean result;
                try
                {
                    clientSocket.connect(new InetSocketAddress(ipAddress, portNumber), 5000);
                    result = true;
                }
                catch (IOException e)
                {
                    result = false;

                    logger.logp(Level.WARNING,
                            this.getClass().getName() + " SwingWorker<Boolean, Void> connectorToServer",
                            "doInBackground",
                            "Could not connect to server.", e);
                }
                return result;
            }
            @Override
            protected void done() //Invoked on EDT.
            {
                try
                {
                    boolean result = get();

                    if (result)
                    {
                        if (tryToOpenStreams())
                        {
                            connectedToServer = true;
                            gui.setConnected();

                            clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
                            clientThreadPool.execute(thisClient::run);
                        }
                        else
                        {
                            connectedToServer = false;
                            gui.setDisconnected();
                            gui.showDialogCouldNotConnectToServer();
                        }
                    }
                    else
                    {
                        connectedToServer = false;
                        gui.setDisconnected();
                        gui.showDialogCouldNotConnectToServer();
                    }
                }
                catch (InterruptedException | ExecutionException e)
                {
                    connectedToServer = false;
                    gui.setDisconnected();
                    gui.showDialogCouldNotConnectToServer();

                    logger.logp(Level.WARNING,
                            this.getClass().getName() + " SwingWorker<Boolean, Void> connectorToServer",
                            "done",
                            "Could not get results of doInBackground().", e);
                }
            }
        };
        connectorToServer.execute();
    }
    private boolean tryToOpenStreams()
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
    private void run()
    {
        protocol.sendRegisterNickname();
        while(true)
        {
            try
            {
                //If clientSocket is closed,
                //any thread currently blocked in an I/O operation upon this socket will throw a SocketException.
                Message message = (Message)(objectInputStream.readObject());
                protocol.processInput(message);
            }
            catch (IOException | ClassNotFoundException e) //SocketException is a subclass of IOException.
            {
                closeStreams();
                connectedToServer = false;
                gui.setDisconnected(); //TODO мб showFrameOnlineMultiPlayer. Если игрок в игре, то он находится на Frame Board. GameOver. State=over.
                gui.showDialogDisconnectedFromServer();

                System.out.println("Client Socket closed.\n");

                break;
            }
        }
        System.out.println("Client Thread stopped.\n");
    }
    public void disconnect()
    {
        //Close Client Socket. Causes an Exception in Client Thread. Client Thread begins to stop.
        if(clientSocket != null)
        {
            try
            {
                clientSocket.close();
            }
            catch (IOException e)
            {
                logger.logp(Level.WARNING, this.getClass().getName(), "disconnect",
                        "Could not close clientSocket.", e);
            }
        }

        //Wait for Client Thread to stop.
        if(clientThreadPool != null)
        {
            clientThreadPool.shutdown();
            try
            {
                clientThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            }
            catch (InterruptedException e)
            {
                logger.logp(Level.WARNING, this.getClass().getName(), "disconnect",
                        "Could not stop threads of clientThreadPool.", e);
            }
        }

        //Refresh GUI.
        connectedToServer = false;
        gui.setDisconnected();
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
    public synchronized void writeToServer(Object message)
    {
        try
        {
            objectOutputStream.writeObject(message);
        }
        catch(IOException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "writeToServer",
                    "Could not write to objectOutputStream of clientSocket.", e);
        }
    }
    public boolean getIsConnectedToServer()
    {
        return connectedToServer;
    }
    public String getNickname()
    {
        return nickname;
    }
}
