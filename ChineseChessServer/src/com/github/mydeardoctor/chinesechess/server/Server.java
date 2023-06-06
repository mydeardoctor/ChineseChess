package com.github.mydeardoctor.chinesechess.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    //Server attributes.
    private boolean serverOn;
    private ThreadPoolExecutor threadPoolExecutor;
    private ServerSocket serverSocket;

    //GUI attributes.
    private GUI gui;
    public Server()
    {
        serverOn = false;
        initializeThreadPool();
    }
    private void initializeThreadPool()
    {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void initializeServerSocket(int portNumber) //TODO переподключение должно удалять старое подключение. сделать отдельный ексекьютор для сервера, чтобы остальной можно было очищать.
    {
        try
        {
            serverSocket = new ServerSocket(portNumber);
            serverOn = true;
            gui.setStatusBarText(gui.getText().getServerIsOn());
            threadPoolExecutor.execute(this::run);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void run()
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
    public boolean getServerOn()
    {
        return serverOn;
    }
}
