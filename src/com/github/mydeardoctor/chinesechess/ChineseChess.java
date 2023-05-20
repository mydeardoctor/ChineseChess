package com.github.mydeardoctor.chinesechess;

//TODO:
//Сделать гейм клас основным. Проинициализировать первым.

//Concurrency
//Synchronised methods use objects lock. But with frequent polling, starvation may occur.
//Atomic variables.
//Guarded blocks. Wait, notifyAll
//Thread pool. 2 threads. 1 thread for main theme. play -> create, loop. stop -> delete. 2 thread for sfx. play->create->delete. No need to keep a thread that waits for a condition. thread can be created.

//Swing concurrency
//Shouldnt main thread data and EDT data be synchronised??? synchronize gri by making a copy and sync methods. cuz repaint can be invoke after resizong window and a worker thread is running in background.
//SwingUtilities.invokeLater for EDT
//SwingUtilities.isEventDispatchThread.
//SwingWorker for background worker threads

//GUI design patterns
//MVC

public class ChineseChess
{
    public static void main(String[] args)
    {
        Game game = new Game();
        GUI gui = new GUI();

        game.setGui(gui);
        gui.setGame(game);

        gui.showFrame();
    }
}