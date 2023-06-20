package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.ParentLogger;

public class ChineseChessServer
{
    public static void main(String[] args)
    {
        ParentLogger parentLogger = new ParentLogger();
        parentLogger.initialize("ChineseChessServerLog%u.txt");
        Server server = new Server();
        GUI gui = new GUI();

        server.setGui(gui);
        gui.setServer(server);

        gui.showFrameAndWarnings();
    }
}
