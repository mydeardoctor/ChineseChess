package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.ParentLogger;

public class ChineseChessServer
{
    public static void main(String[] args)
    {
        ParentLogger.initialize("ChineseChessServerLog%u.txt");
        Server server = new Server();
        MapOfClients mapOfClients = new MapOfClients();
        Protocol protocol = new Protocol();
        GUI gui = new GUI();

        server.setMapOfClients(mapOfClients);
        server.setProtocol(protocol);
        server.setGui(gui);
        protocol.setMapOfClients(mapOfClients);
        protocol.setGui(gui);
        gui.setServer(server);

        gui.showFrameAndWarnings();
    }
}
