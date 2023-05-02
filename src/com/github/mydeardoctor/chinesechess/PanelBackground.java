package com.github.mydeardoctor.chinesechess;

import java.awt.*;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

class PanelBackground extends JPanel
{
    private BufferedImage background = null;
    PanelBackground()
    {
        //TODO: catch dialogue, logging
        try
        {
            URL url = getClass().getResource("/background.jpg");
            background = ImageIO.read(url);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),this);
    }
}