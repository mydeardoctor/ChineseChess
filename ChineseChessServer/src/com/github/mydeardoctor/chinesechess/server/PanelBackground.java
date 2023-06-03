package com.github.mydeardoctor.chinesechess.server;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelBackground extends JPanel
{
    private final BufferedImage background;
    public PanelBackground(BufferedImage background)
    {
        this.background = background;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(), this.getHeight(), this);
    }
}