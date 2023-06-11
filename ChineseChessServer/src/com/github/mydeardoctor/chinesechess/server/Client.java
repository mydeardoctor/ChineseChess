package com.github.mydeardoctor.chinesechess.server;

import java.io.*;
import java.net.Socket;

public class Client
{
    private final Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Client(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
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
            e.printStackTrace();

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
                String message = (String)(objectInputStream.readObject());
                System.out.println(message);
            }
            catch (Exception e)
            {
                closeResources();
                System.out.println("Client Socket closed.");
                break;
            }
        }
        System.out.println("Client Thread stopped.");
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
    public Socket getClientSocket()
    {
        return clientSocket;
    }
}
