package com.github.mydeardoctor.chinesechess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client //TODO
{
    //Client attributes.
    private Socket clientSocket;
    private ThreadPoolExecutor clientThreadPool;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    //GUI attributes.
    private GUI gui;

    public Client()
    {
        super();
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void reconnect(String ipAddress, int portNumber)
    {
        disconnect();
        connect(ipAddress, portNumber);
    }
    public void disconnect()
    {

    }
    private void connect(String ipAddress, int portNumber)
    {
        try
        {
            clientSocket = new Socket(ipAddress, portNumber);
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            clientThreadPool.execute(this::run);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            closeResources();
            gui.showDialogCouldNotConnectToServer();
        }
    }
    private void run()
    {
        while(true)
        {
            try
            {
                String message = "message from client";
                objectOutputStream.writeObject(message);
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    private void closeResources()
    {
        if(objectInputStream != null)
        {
            try
            {
                objectInputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(objectOutputStream != null)
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
