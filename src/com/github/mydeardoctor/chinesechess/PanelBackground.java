package com.github.mydeardoctor.chinesechess;

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

class PanelBackground extends JPanel
{
    private BufferedImage background = null;
    PanelBackground(Text text)
    {
        URL url = getClass().getResource("/background.jpg");

        try
        {
            background = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorBackground(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),this);
    }
}