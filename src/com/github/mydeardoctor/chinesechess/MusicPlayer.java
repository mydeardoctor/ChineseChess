package com.github.mydeardoctor.chinesechess;

import javax.sound.sampled.*;
import java.net.URL;

//TODO: Main theme громче.
//TODO: Main theme убрать паузу в конце.
//TODO: Сделать sfx перемещения фигур.
//TODO: добавить в settings два мута и два регулятора громкости
//isActive = playingSound. isRunning

//TODO: Concurrency info.
//Synchronised methods use objects lock. But with frequent polling, starvation may occur.
//Atomic variables.
//Guarded blocks. wait(), notifyAll().

public class MusicPlayer
{
    private boolean resourcesMissing;

    private Clip lineMainTheme;
    private BooleanControl muteMainTheme;
    private FloatControl gainMainTheme;
    private float gainMainThemeMinimum;
    private float gainMainThemeMaximum;

    private boolean lineMainThemeAvailable;
    private boolean muteMainThemeAvailable;
    private boolean gainMainThemeAvailable;

    private Clip lineSfx;
    private BooleanControl muteSfx;
    private FloatControl gainSfx;
    private float gainSfxMinimum;
    private float gainSfxMaximum;

    private boolean lineSfxAvailable;
    private boolean muteSfxAvailable;
    private boolean gainSfxAvailable;

    public MusicPlayer()
    {
        resourcesMissing = false;
        initializeMainTheme();
        initializeSfx();
    }
    public void initializeMainTheme()
    {
        URL url = getClass().getResource("/mainTheme.wav");
        if(url == null)
        {
            resourcesMissing = true;
        }
        try
        (
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url)
        )
        {
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            lineMainTheme = (Clip)AudioSystem.getLine(info);
            lineMainTheme.open(audioInputStream);
            lineMainTheme.setLoopPoints(0, -1);

            lineMainThemeAvailable = true;
        }
        catch(Exception e)
        {
            lineMainThemeAvailable = false;
            if(lineMainTheme !=null)
            {
                lineMainTheme.close();
            }
        }

        try
        {
            muteMainTheme = (BooleanControl) lineMainTheme.getControl(BooleanControl.Type.MUTE);
            muteMainTheme.setValue(false);

            muteMainThemeAvailable = true;
        }
        catch(Exception e)
        {
            muteMainThemeAvailable = false;
        }

        try
        {
            gainMainTheme = (FloatControl) lineMainTheme.getControl(FloatControl.Type.MASTER_GAIN);
            gainMainThemeMinimum = gainMainTheme.getMinimum();
            gainMainThemeMaximum = gainMainTheme.getMaximum();
            //gainMainTheme.setValue(0);
            gainMainTheme.setValue(6);

            gainMainThemeAvailable = true;
        }
        catch(Exception e)
        {
            gainMainThemeAvailable = false;
        }
    }
    public void initializeSfx()
    {
        URL url = getClass().getResource("/coin2.wav");
        if(url == null)
        {
            resourcesMissing = true;
        }
        try
        (
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url)
        )
        {
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            lineSfx = (Clip)AudioSystem.getLine(info);
            lineSfx.open(audioInputStream);

            lineSfxAvailable = true;
        }
        catch(Exception e)
        {
            lineSfxAvailable = false;
            if(lineSfx !=null)
            {
                lineSfx.close();
            }
        }

        try
        {
            muteSfx = (BooleanControl) lineSfx.getControl(BooleanControl.Type.MUTE);
            muteSfx.setValue(false);

            muteSfxAvailable = true;
        }
        catch(Exception e)
        {
            muteSfxAvailable = false;
        }

        try
        {
            gainSfx = (FloatControl) lineSfx.getControl(FloatControl.Type.MASTER_GAIN);
            gainSfxMinimum = gainSfx.getMinimum();
            gainSfxMaximum = gainSfx.getMaximum();
            //gainSfx.setValue(0);
            gainSfx.setValue(-20.0f);

            gainSfxAvailable = true;
        }
        catch(Exception e)
        {
            gainSfxAvailable = false;
        }
    }
    void playMainTheme()
    {
        if(lineMainThemeAvailable==true)
        {
            lineMainTheme.stop();
            lineMainTheme.flush();
            lineMainTheme.setFramePosition(0);
            lineMainTheme.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    void stopMainTheme()
    {
        if(lineMainThemeAvailable==true)
        {
            lineMainTheme.stop();
            lineMainTheme.flush();
            lineMainTheme.setFramePosition(0);
        }
    }
    void playSfx()
    {
        if(lineSfxAvailable==true)
        {
            lineSfx.stop();
            lineSfx.flush();
            lineSfx.setFramePosition(0);
            lineSfx.start();
        }
    }
    boolean getResourcesMissing()
    {
        return resourcesMissing;
    }
    boolean getLineMainThemeAvailable()
    {
        return lineMainThemeAvailable;
    }
    boolean getMuteMainThemeAvailable()
    {
        return muteMainThemeAvailable;
    }
    boolean getGainMainThemeAvailable()
    {
        return gainMainThemeAvailable;
    }
    boolean getLineSfxAvailable()
    {
        return lineSfxAvailable;
    }
    boolean getMuteSfxAvailable()
    {
        return muteSfxAvailable;
    }
    boolean getGainSfxAvailable()
    {
        return gainSfxAvailable;
    }
}
