package com.github.mydeardoctor.chinesechess.client;

import javax.sound.sampled.*;
import java.net.URL;

public class MusicPlayer
{
    //Resources.
    private boolean resourcesMissing;

    //Music.
    private final Clip lineMusic;
    private final BooleanControl muteMusic;
    private final FloatControl gainMusic;
    private final float gainMusicDbMinimum;
    private final float gainMusicDbMaximum;
    private final int gainMusicPercentMinimum;
    private final int gainMusicPercentMaximum;
    private final int gainMusicPercentCurrent;

    private boolean lineMusicAvailable;
    private boolean muteMusicAvailable;
    private boolean gainMusicAvailable;

    //Sfx.
    private final Clip lineSfx;
    private final BooleanControl muteSfx;
    private final FloatControl gainSfx;
    private final float gainSfxDbMinimum;
    private final float gainSfxDbMaximum;
    private final int gainSfxPercentMinimum;
    private final int gainSfxPercentMaximum;
    private final int gainSfxPercentCurrent;

    private boolean lineSfxAvailable;
    private boolean muteSfxAvailable;
    private boolean gainSfxAvailable;

    public MusicPlayer()
    {
        //Resources.
        resourcesMissing = false;

        //Music.
        lineMusic = initializeLineMusic();
        muteMusic = initializeMuteMusic();
        gainMusic = initializeGainMusic();
        gainMusicDbMinimum = initializeGainMusicDbMinimum();
        gainMusicDbMaximum = initializeGainMusicDbMaximum();
        gainMusicPercentMinimum = initializeGainMusicPercentMinimum();
        gainMusicPercentMaximum = initializeGainMusicPercentMaximum();
        gainMusicPercentCurrent = initializeGainMusicPercentCurrent();

        //SFX.
        lineSfx = initializeLineSfx();
        muteSfx = initializeMuteSfx();
        gainSfx = initializeGainSfx();
        gainSfxDbMinimum = initializeGainSfxDbMinimum();
        gainSfxDbMaximum = initializeGainSfxDbMaximum();
        gainSfxPercentMinimum = initializeGainSfxPercentMinimum();
        gainSfxPercentMaximum = initializeGainSfxPercentMaximum();
        gainSfxPercentCurrent = initializeGainSfxPercentCurrent();
    }
    private Clip initializeLineMusic()
    {
        Clip lineMusic = null;

        URL url = getClass().getResource("/mainTheme.wav");
        if(url == null)
        {
            resourcesMissing = true;
        }
        //noinspection DataFlowIssue
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
            if(lineMusic != null)
            {
                lineMusic.close();
            }
        }

        return lineMusic;
    }
    private BooleanControl initializeMuteMusic()
    {
        BooleanControl muteMusic = null;

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

        return muteMusic;
    }
    private FloatControl initializeGainMusic()
    {
        FloatControl gainMusic = null;

        try
        {
            gainMusic = (FloatControl)lineMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainMusicAvailable = true;
        }
        catch(Exception e)
        {
            gainMusicAvailable = false;
        }

        return gainMusic;
    }
    private float initializeGainMusicDbMinimum()
    {
        float gainMusicDbMinimum = 0;
        if(gainMusicAvailable)
        {
            gainMusicDbMinimum = gainMusic.getMinimum();
        }
        return gainMusicDbMinimum;
    }
    private float initializeGainMusicDbMaximum()
    {
        float gainMusicDbMaximum = 0;
        if(gainMusicAvailable)
        {
            gainMusicDbMaximum = gainMusic.getMaximum();
        }
        return gainMusicDbMaximum;
    }
    private int initializeGainMusicPercentMinimum()
    {
        int gainMusicPercentMinimum = 0;
        if(gainMusicAvailable)
        {
            gainMusicPercentMinimum = getPercent(gainMusicDbMinimum);
        }
        return gainMusicPercentMinimum;
    }
    private int initializeGainMusicPercentMaximum()
    {
        int gainMusicPercentMaximum = 0;
        if(gainMusicAvailable)
        {
            gainMusicPercentMaximum = getPercent(gainMusicDbMaximum);
        }
        return gainMusicPercentMaximum;
    }
    private int initializeGainMusicPercentCurrent()
    {
        int gainMusicPercentCurrent = 0;
        if(gainMusicAvailable)
        {
            float gainMusicDbCurrent = gainMusic.getValue();
            gainMusicPercentCurrent = getPercent(gainMusicDbCurrent);
        }
        return gainMusicPercentCurrent;
    }
    private Clip initializeLineSfx()
    {
        Clip lineSfx = null;

        URL url = getClass().getResource("/sfx.wav");
        if(url == null)
        {
            resourcesMissing = true;
        }
        //noinspection DataFlowIssue
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

        return lineSfx;
    }
    private BooleanControl initializeMuteSfx()
    {
        BooleanControl muteSfx = null;

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

        return muteSfx;
    }
    private FloatControl initializeGainSfx()
    {
        FloatControl gainSfx = null;

        try
        {
            gainSfx = (FloatControl) lineSfx.getControl(FloatControl.Type.MASTER_GAIN);
            gainSfxAvailable = true;
        }
        catch(Exception e)
        {
            gainSfxAvailable = false;
        }

        return gainSfx;
    }
    private float initializeGainSfxDbMinimum()
    {
        float gainSfxDbMinimum = 0;

        if(gainSfxAvailable)
        {
            gainSfxDbMinimum = gainSfx.getMinimum();
        }

        return gainSfxDbMinimum;
    }
    private float initializeGainSfxDbMaximum()
    {
        float gainSfxDbMaximum = 0;

        if(gainSfxAvailable)
        {
            gainSfxDbMaximum = gainSfx.getMaximum();
        }

        return gainSfxDbMaximum;
    }
    private int initializeGainSfxPercentMinimum()
    {
        int gainSfxPercentMinimum = 0;

        if(gainSfxAvailable)
        {
            gainSfxPercentMinimum = getPercent(gainSfxDbMinimum);
        }

        return gainSfxPercentMinimum;
    }
    private int initializeGainSfxPercentMaximum()
    {
        int gainSfxPercentMaximum = 0;

        if(gainSfxAvailable)
        {
            gainSfxPercentMaximum = getPercent(gainSfxDbMaximum);
        }

        return gainSfxPercentMaximum;
    }
    private int initializeGainSfxPercentCurrent()
    {
        int gainSfxPercentCurrent = 0;

        if(gainSfxAvailable)
        {
            float gainSfxDbCurrent = gainSfx.getValue();
            gainSfxPercentCurrent = getPercent(gainSfxDbCurrent);
        }

        return gainSfxPercentCurrent;
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