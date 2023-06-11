package com.github.mydeardoctor.chinesechess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Client
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
            clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(ipAddress, portNumber), 3000);

            if(tryToOpenStreams())
            {
                gui.setConnectionOn();

                clientThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
                clientThreadPool.execute(this::run);
            }
            else
            {
                gui.setConnectionOff();
                gui.showDialogCouldNotConnectToServer();
            }
        }
        catch (Exception e)
        {
//            e.printStackTrace(); //TODO commented out code

            gui.setConnectionOff();
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
            e.printStackTrace();

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
                String message = this.toString();
                objectOutputStream.writeObject(message);
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                closeResources();
                gui.setConnectionOff();
                System.out.println("Client Socket closed.");
                break;
            }
        }
        System.out.println("Client Thread stopped.");
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
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }

        //Refresh GUI.
        gui.setConnectionOff();
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
//                e.printStackTrace(); //TODO commented out code
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
//                e.printStackTrace(); //TODO commented out code
            }
        }
    }
}
