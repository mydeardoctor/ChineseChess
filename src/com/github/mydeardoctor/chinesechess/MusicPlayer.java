package com.github.mydeardoctor.chinesechess;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//TODO: main theme громче. Сделать sfx перемещения фигур.
//TODO: main theme в конце есть пауза?
//TODO: Separate thread
//TODO: добавить в settings два мута и два регулятора громкости
//TODO: getLine с регулятором громкости (set info)


//TODO:
//Concurrency
//Synchronised methods use objects lock. But with frequent polling, starvation may occur.
//Atomic variables.
//Guarded blocks. Wait, notifyAll
//Thread pool. 2 threads. 1 thread for main theme. play -> create, loop. stop -> delete. 2 thread for sfx. play->create->delete. No need to keep a thread that waits for a condition. thread can be created.

public class MusicPlayer
{
    private static final int BUFFER_SIZE = 4096;
    private ThreadPoolExecutor threadPoolExecutor;
    public MusicPlayer()
    {
        //createThreadPool();

    }
    private void createThreadPool()
    {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    }
    private void playTest()
    {
        //Separate thread. Check if user wants to stop. boolean variable.
        URL url3 = getClass().getResource("/mainTheme.wav");
        try
        (
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(url3)
        )
        {
            AudioFormat audioFormat = audioInputStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, audioFormat);
            if (!AudioSystem.isLineSupported(info2))
            {
            }
            SourceDataLine sourceDataLine = (SourceDataLine)AudioSystem.getLine(info2);
            sourceDataLine.open(audioFormat);

            FloatControl floatControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            float minimum = floatControl.getMinimum();
            float current = floatControl.getValue();
            float maximum = floatControl.getMaximum();
            floatControl.setValue(maximum);

            BooleanControl booleanControl = (BooleanControl) sourceDataLine.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(false);


            int bufferSize = sourceDataLine.getBufferSize();
            byte[] buffer = new byte[bufferSize];

            sourceDataLine.start();
            int bytesRead;

            while(    (bytesRead = audioInputStream2.read(buffer, 0, bufferSize))   !=   -1    )
            {
                sourceDataLine.write(buffer, 0, bytesRead);
            }
            sourceDataLine.drain();
            sourceDataLine.stop();
            sourceDataLine.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//        clip.stop();
//        clip.close()



        //isActive
        //isRunning
        //Flush
        //mute
        //one slider for master volume. two buttons for mute

/*
        URL url1 = getClass().getResource("/coin2.wav");
        try
        (
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url1);
        )
        {
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            if (!AudioSystem.isLineSupported(info))
            {
            }
            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(audioInputStream);

            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minimum = floatControl.getMinimum();
            float current = floatControl.getValue();
            float maximum = floatControl.getMaximum();
            floatControl.setValue(-30.0f);

            clip.setLoopPoints(0, -1);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            //clip.setFramePosition(0); //Перемотка
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        URL url2 = getClass().getResource("/mainTheme.wav");
        try
        (
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(url2);
        )
        {
            AudioFormat audioFormat = audioInputStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, audioFormat);
            if (!AudioSystem.isLineSupported(info2))
            {
            }
            Clip clip2 = (Clip)AudioSystem.getLine(info2);
            clip2.open(audioInputStream2);

            FloatControl floatControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
            float miminun = floatControl.getMinimum();
            float current = floatControl.getValue();
            float maximum = floatControl.getMaximum();
            floatControl.setValue(6.0f);


            clip2.setLoopPoints(0, -1);
            clip2.loop(Clip.LOOP_CONTINUOUSLY);
            clip2.start();
            //clip.setFramePosition(0); //Перемотка
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//        clip.stop();
//        clip.close();
*/


        //Не закрывать лайны. подумать как юзать их без новых задач.
        //Events?
//        AudioFileFormat;
        //mixer input is clip or sourceDataLine. Has start and stop, pause, resume. flush.
    }
    public void playMainTheme()
    {

    }
    public void playMove()
    {

    }
}
