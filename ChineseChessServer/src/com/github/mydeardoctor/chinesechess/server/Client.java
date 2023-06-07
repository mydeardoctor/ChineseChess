package com.github.mydeardoctor.chinesechess.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client //TODO перенести в Server
{
    private final Socket clientSocket;
    public Client(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }
    public void communicate()
    {
        try
        {
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("input stream started");
            String message;
            while((message = bufferedReader.readLine())!=null)
            {
                System.out.println("from client: "+ message);
            }
            System.out.println("input stream ended");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
