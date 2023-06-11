package com.github.mydeardoctor.chinesechess.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client //TODO
{
    //Client attributes.
    private Socket clientSocket;
    private ThreadPoolExecutor clientThreadPool;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

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
    public void connect(String ipAddress, int portNumber)
    {
        try
        {
            clientSocket = new Socket(ipAddress, portNumber);
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            clientThreadPool.execute(this::run);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            closeResources();
            gui.showDialogCouldNotConnectToServer(); //TODO кнопки
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
    }
}
