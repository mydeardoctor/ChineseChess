package com.github.mydeardoctor.chinesechess.client;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client //TODO
{
    private Socket clientSocket;
    private ThreadPoolExecutor threadPoolExecutor;
    private ObjectOutputStream objectOutputStream;

    public Client()
    {
        clientSocket = new Socket();
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    }
    public void connectToServer(String ipAddress, int portNumber)
    {
        try
        {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ipAddress, portNumber);
            clientSocket.connect(inetSocketAddress, 3000); //TODO. maybe make a new thread to try

            OutputStream outputStream = clientSocket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

//            threadPoolExecutor.shutdownNow(); //TODO
            threadPoolExecutor.execute(this::run);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
}
