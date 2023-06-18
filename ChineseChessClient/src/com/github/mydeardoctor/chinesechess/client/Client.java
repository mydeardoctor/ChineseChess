package com.github.mydeardoctor.chinesechess.client;

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
    private ThreadPoolExecutor clientThreadPool;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    //GUI attributes.
    private GUI gui;

    //Logger.
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public Client()
    {
        super();
        connectedToServer = false;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void connect(String ipAddress, int portNumber)
    {
        gui.disableButtonConnect();
        gui.disableButtonDisconnect();

        clientSocket = new Socket();
        Client clientReference = this;

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
                            clientThreadPool.execute(clientReference::run);
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

            closeResources();
            result = false;
        }

        return result;
    }
    private void run()
    {
        while(true)
        {
            try
            {
                //If clientSocket is closed,
                //any thread currently blocked in an I/O operation upon this socket will throw a SocketException.
                String message = this.toString();
                objectOutputStream.writeObject(message); //TODO переделать
                Thread.sleep(1000);
            }
            //TODO Убрать InterruptedException
            //TODO add IOException
            catch (IOException | InterruptedException e) //SocketException is a subclass of IOException.
            {
                closeResources();
                connectedToServer = false;
                gui.setDisconnected();
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
    public synchronized void writeToServer(Object message) //TODO Нужно ли закрывать соединение? На данный момент неправильно, так как нет потока с чтением, поэтому поток не закроется. Не проверен exception.
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
}
