package com.github.mydeardoctor.chinesechess.client;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client
{
    //Client attributes.
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
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void connect(String ipAddress, int portNumber) //TODO exceptions unwrapping
    {
        Client client = this;
        try
        {
            clientSocket = new Socket();
//            clientSocket.connect(new InetSocketAddress(ipAddress, portNumber), 3000); //TODO new thread
            //TODO block buttons
            SwingWorker<Boolean, Void> sw = new SwingWorker<Boolean, Void>()
            {
                @Override
                protected Boolean doInBackground() throws Exception
                {
                    boolean result;
                    try
                    {
                        clientSocket.connect(new InetSocketAddress(ipAddress, portNumber), 5000);
                        result = true;
                    }
                    catch (Exception e)
                    {
                        result = false;
                    }

                    return result;
                }

                @Override
                protected void done()
                {
                    try
                    {
                        boolean result = get();

                        if(result)
                        {
                            if(tryToOpenStreams())
                            {
                                gui.setConnected();

                                clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
                                clientThreadPool.execute(client::run);
                            }
                            else
                            {
                                gui.setDisconnected();
                                gui.showDialogCouldNotConnectToServer();
                            }
                        }
                        else
                        {
                            gui.setDisconnected();
                            gui.showDialogCouldNotConnectToServer();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();

                        gui.setDisconnected();
                        gui.showDialogCouldNotConnectToServer();
                    }


                }
            };
            sw.execute();
//
//            if(tryToOpenStreams())
//            {
//                gui.setConnectionOn();
//
//                clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
//                clientThreadPool.execute(this::run);
//            }
//            else
//            {
//                gui.setConnectionOff();
//                gui.showDialogCouldNotConnectToServer();
//            }
        }
        catch (Exception e)
        {
//            e.printStackTrace(); //TODO commented out code

            gui.setDisconnected();
            gui.showDialogCouldNotConnectToServer();
        }
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
                objectOutputStream.writeObject(message);
                Thread.sleep(1000);
            }
            //TODO Убрать InterruptedException
            catch (IOException | InterruptedException e) //SocketException is a subclass of IOException.
            {
                closeResources();
                gui.setDisconnected(); //TODO dialog disconnected

                System.out.println("Client Socket closed.");

                break;
            }
        }
        System.out.println("Client Thread stopped.");
    }
    public void disconnect() //TODO dialog disconnected
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
}
