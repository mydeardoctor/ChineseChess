package com.github.mydeardoctor.chinesechess.client;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
    public Client()
    {

    }
    public void connectToServer()
    {
        try
        {
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket clientSocket = new Socket(inetAddress, 4242);
            System.out.println("Client initialized");

            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            while(true)
            {
                Thread.sleep(1000);
                String message = "I send a message";
                printWriter.println(message);
                printWriter.flush();

                System.out.println("sent message");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}