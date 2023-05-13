package com.github.mydeardoctor.chinesechess;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PanelBackground extends JPanel
{
    private BufferedImage background;
    public PanelBackground(BufferedImage background)
    {
        this.background = background;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),this);
    }
}