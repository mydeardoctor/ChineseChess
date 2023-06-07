package com.github.mydeardoctor.chinesechess.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.net.ServerSocket;
import java.net.Socket;

public class Server //TODO
{
    //Server attributes.
    private boolean serverIsOn;
    private ThreadPoolExecutor threadPoolExecutor;
    private ServerSocket serverSocket;

    //GUI attributes.
    private GUI gui;

    public Server()
    {
        serverIsOn = false;
    }
    public void setGui(GUI gui)
    {
        this.gui = gui;
    }
    public void start(int portNumber, int maximumNumberOfPlayers) //TODO переподключение должно удалять старое подключение. сделать отдельный ексекьютор для сервера, чтобы остальной можно было очищать.
    {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maximumNumberOfPlayers+1);
        try
        {
            serverSocket = new ServerSocket(portNumber);
            serverIsOn = true;
            gui.setStatusBarText(gui.getText().getServerIsOn());
            threadPoolExecutor.execute(this::run);
        }
        catch (Exception e)
        {
            e.printStackTrace(); //TODO сообщение об ошибке
        }
    }
    private void run()
    {

        while (true)
        {
            try
            {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Got clientSocket");
                Client client = new Client(clientSocket);
                //check if thread pool is full
                threadPoolExecutor.execute(client::communicate);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public boolean getIsServerOn()
    {
        return serverIsOn;
    }
}
