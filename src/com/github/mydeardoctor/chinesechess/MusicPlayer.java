package com.github.mydeardoctor.chinesechess;

import javax.sound.sampled.*;
import java.net.URL;

public class MusicPlayer
{
    //Resources.
    private boolean resourcesMissing;

    //Music.
    private Clip lineMusic;
    private BooleanControl muteMusic;
    private FloatControl gainMusic;
    private float gainMusicDbMinimum;
    private float gainMusicDbMaximum;
    private int gainMusicPercentMinimum;
    private int gainMusicPercentMaximum;
    private int gainMusicPercentCurrent;

    private boolean lineMusicAvailable;
    private boolean muteMusicAvailable;
    private boolean gainMusicAvailable;

    //Sfx.
    private Clip lineSfx;
    private BooleanControl muteSfx;
    private FloatControl gainSfx;
    private float gainSfxDbMinimum;
    private float gainSfxDbMaximum;
    private int gainSfxPercentMinimum;
    private int gainSfxPercentMaximum;
    private int gainSfxPercentCurrent;

    private boolean lineSfxAvailable;
    private boolean muteSfxAvailable;
    private boolean gainSfxAvailable;

    public MusicPlayer()
    {
        resourcesMissing = false;
        initializeLineMusic();
        initializeLineSfx();
    }
    private void initializeLineMusic()
    {
        //Line.
        URL url = getClass().getResource("/mainTheme.wav");
        if(url == null)
        {
            resourcesMissing = true;
        }
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url))
        {
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            lineMusic = (Clip)AudioSystem.getLine(info);
            lineMusic.open(audioInputStream);
            lineMusic.setLoopPoints(0, -1);
            lineMusicAvailable = true;
        }
        catch(Exception e)
        {
            lineMusicAvailable = false;
            if(lineMusic!=null)
            {
                lineMusic.close();
            }
        }

        //Mute control.
        try
        {
            muteMusic = (BooleanControl)lineMusic.getControl(BooleanControl.Type.MUTE);
            muteMusic.setValue(false);
            muteMusicAvailable = true;
        }
        catch(Exception e)
        {
            muteMusicAvailable = false;
        }

        //Gain control.
        try
        {
            gainMusic = (FloatControl)lineMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainMusicDbMinimum = gainMusic.getMinimum();
            gainMusicDbMaximum = gainMusic.getMaximum();
            float gainMusicDbCurrent = gainMusic.getValue();
            gainMusicPercentMinimum = getPercent(gainMusicDbMinimum);
            gainMusicPercentMaximum = getPercent(gainMusicDbMaximum);
            gainMusicPercentCurrent = getPercent(gainMusicDbCurrent);
            gainMusicAvailable = true;
        }
        catch(Exception e)
        {
            gainMusicAvailable = false;
        }
    }
    private void initializeLineSfx()
    {
        //Line.
        URL url = getClass().getResource("/sfx.wav");
        if(url == null)
        {
            resourcesMissing = true;
        }
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url))
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

        //Mute control.
        try
        {
            muteSfx = (BooleanControl)lineSfx.getControl(BooleanControl.Type.MUTE);
            muteSfx.setValue(false);
            muteSfxAvailable = true;
        }
        catch(Exception e)
        {
            muteSfxAvailable = false;
        }

        //Gain control.
        try
        {
            gainSfx = (FloatControl) lineSfx.getControl(FloatControl.Type.MASTER_GAIN);
            gainSfxDbMinimum = gainSfx.getMinimum();
            gainSfxDbMaximum = gainSfx.getMaximum();
            float gainSfxDbCurrent = gainSfx.getValue();
            gainSfxPercentMinimum = getPercent(gainSfxDbMinimum);
            gainSfxPercentMaximum = getPercent(gainSfxDbMaximum);
            gainSfxPercentCurrent = getPercent(gainSfxDbCurrent);
            gainSfxAvailable = true;
        }
        catch(Exception e)
        {
            gainSfxAvailable = false;
        }
    }
    private int getPercent(float db)
    {
        int percent = (int)(100*Math.pow(10.0, db/20.0));
        if(percent <= 0)
        {
            percent = 1;
        }
        return percent;
    }
    private float getDb(int percent, float dbMinimum, float dbMaximum)
    {
        if(percent <= 1)
        {
            return dbMinimum;
        }

        float db = (float)(20*Math.log10(percent/100.0));
        if(db > dbMaximum)
        {
            db = dbMaximum;
        }
        else if(db < dbMinimum)
        {
            db = dbMinimum;
        }
        return db;
    }
    public void playMusic()
    {
        if(lineMusicAvailable)
        {
            lineMusic.stop();
            lineMusic.flush();
            lineMusic.setFramePosition(0);
            lineMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stopMusic()
    {
        if(lineMusicAvailable)
        {
            lineMusic.stop();
            lineMusic.flush();
            lineMusic.setFramePosition(0);
        }
    }
    public void playSfx()
    {
        if(lineSfxAvailable)
        {
            lineSfx.stop();
            lineSfx.flush();
            lineSfx.setFramePosition(0);
            lineSfx.start();
        }
    }
    public void muteMusic()
    {
        if(muteMusicAvailable)
        {
            muteMusic.setValue(true);
        }
    }
    public void unmuteMusic()
    {
        if(muteMusicAvailable)
        {
            muteMusic.setValue(false);
        }
    }
    public void muteSfx()
    {
        if(muteSfxAvailable)
        {
            muteSfx.setValue(true);
        }
    }
    public void unmuteSfx()
    {
        if(muteSfxAvailable)
        {
            muteSfx.setValue(false);
        }
    }
    public void setGainMusicDb(int percent)
    {
        if(gainMusicAvailable)
        {
            float db = getDb(percent, gainMusicDbMinimum, gainMusicDbMaximum);
            gainMusic.setValue(db);
        }
    }
    public void setGainSfxDb(int percent)
    {
        if(gainSfxAvailable)
        {
            float db = getDb(percent, gainSfxDbMinimum, gainSfxDbMaximum);
            gainSfx.setValue(db);
        }
    }
    public boolean getResourcesMissing()
    {
        return resourcesMissing;
    }
    public int getGainMusicPercentMinimum()
    {
        return gainMusicPercentMinimum;
    }
    public int getGainMusicPercentMaximum()
    {
        return gainMusicPercentMaximum;
    }
    public int getGainMusicPercentCurrent()
    {
        return gainMusicPercentCurrent;
    }
    public boolean getLineMusicAvailable()
    {
        return lineMusicAvailable;
    }
    public boolean getMuteMusicAvailable()
    {
        return muteMusicAvailable;
    }
    public boolean getGainMusicAvailable()
    {
        return gainMusicAvailable;
    }
    public int getGainSfxPercentMinimum()
    {
        return gainSfxPercentMinimum;
    }
    public int getGainSfxPercentMaximum()
    {
        return gainSfxPercentMaximum;
    }
    public int getGainSfxPercentCurrent()
    {
        return gainSfxPercentCurrent;
    }
    public boolean getLineSfxAvailable()
    {
        return lineSfxAvailable;
    }
    public boolean getMuteSfxAvailable()
    {
        return muteSfxAvailable;
    }
    public boolean getGainSfxAvailable()
    {
        return gainSfxAvailable;
    }
}