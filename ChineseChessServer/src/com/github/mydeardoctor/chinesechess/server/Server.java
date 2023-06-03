package com.github.mydeardoctor.chinesechess.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ThreadPoolExecutor threadPoolExecutor;
    private ServerSocket serverSocket;
    public Server()
    {
        initializeThreadPool();
        initializeServerSocket();
    }
    public void initializeThreadPool()
    {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
    }
    public void initializeServerSocket()
    {
        try
        {
            serverSocket = new ServerSocket(4242);
            System.out.println("Server initialized.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void run()
    {

        while (true)
        {
            Socket clientSocket = null;
            try
            {
                clientSocket = serverSocket.accept();
                System.out.println("Got clientSocket");
                //Check if client socket is null
                Client client = new Client(clientSocket);

                //check if thread pool is full
                threadPoolExecutor.execute(client::communicate);
                //create a thread to deal with the client;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }




        }
    }
}
