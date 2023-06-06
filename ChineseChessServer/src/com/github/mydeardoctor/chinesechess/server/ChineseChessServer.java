package com.github.mydeardoctor.chinesechess.server;

public class ChineseChessServer
{
    public static void main(String[] args)
    {
        Server server = new Server();
        GUI gui = new GUI();

        server.setGui(gui);
        gui.setServer(server);

        gui.showFrameAndWarnings();
    }
}
