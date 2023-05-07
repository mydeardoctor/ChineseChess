package com.github.mydeardoctor.chinesechess;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.net.URL;
import javax.imageio.ImageIO;

class PanelBackground extends JPanel
{
    private BufferedImage background;
    PanelBackground(Text text)
    {
        URL url = getClass().getResource("/background.jpg");

        try
        {
            //noinspection DataFlowIssue
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
