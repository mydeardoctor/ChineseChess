package com.github.mydeardoctor.chinesechess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

class PanelBoard extends PanelBackground
{
    private BufferedImage advisorBlack;
    private BufferedImage advisorRed;
    private BufferedImage cannonBlack;
    private BufferedImage cannonRed;
    private BufferedImage chariotBlack;
    private BufferedImage chariotRed;
    private BufferedImage elephantBlack;
    private BufferedImage elephantRed;
    private BufferedImage generalBlack;
    private BufferedImage generalRed;
    private BufferedImage horseBlack;
    private BufferedImage horseRed;
    private BufferedImage soldierBlack;
    private BufferedImage soldierRed;
    PanelBoard(Text text)
    {
        super(text);

        URL url = getClass().getResource("/advisorBlack.png");
        try
        {
            advisorBlack = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorAdvisorBlack(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/advisorRed.png");
        try
        {
            advisorRed = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorAdvisorRed(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/cannonBlack.png");
        try
        {
            cannonBlack = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorCannonBlack(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/cannonRed.png");
        try
        {
            cannonRed = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorCannonRed(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/chariotBlack.png");
        try
        {
            chariotBlack = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorChariotBlack(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/chariotRed.png");
        try
        {
            chariotRed = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorChariotRed(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/elephantBlack.png");
        try
        {
            elephantBlack = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorElephantBlack(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/elephantRed.png");
        try
        {
            elephantRed = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorElephantRed(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/generalBlack.png");
        try
        {
            generalBlack = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorGeneralBlack(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/generalRed.png");
        try
        {
            generalRed = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorGeneralRed(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/horseBlack.png");
        try
        {
            horseBlack = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorHorseBlack(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/horseRed.png");
        try
        {
            horseRed = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorHorseRed(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/soldierBlack.png");
        try
        {
            soldierBlack = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorSoldierBlack(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
        url = getClass().getResource("/soldierRed.png");
        try
        {
            soldierRed = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorSoldierRed(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        //Calculation of dimensions.
        int side = Math.min(this.getWidth(), this.getHeight());
        int tile = side/11;
        int x = (this.getWidth()-tile*10)/2;
        int y = (this.getHeight()-tile*11)/2;
        int figureDiameter = (int)(tile*0.85);
        int figureRadius = figureDiameter/2;

        //Rectangles.
        g2d.setColor(new Color(207, 92, 1));
        g2d.fillRect(x, y, tile*10, tile*11);
        g2d.setColor(new Color(252, 174, 63));
        g2d.fillRect(x+tile, y+tile, tile*8, tile*9);

        //Lines.
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        //Horizontal lines.
        g2d.drawLine(x,y,x+tile*10,y);
        for(int i = 1; i <= 10; i++)
        {
            g2d.drawLine(x+tile,y+tile*i,x+tile*9,y+tile*i);
        }
        g2d.drawLine(x,y+tile*11,x+tile*10,y+tile*11);
        //Vertical lines.
        g2d.drawLine(x,y,x,y+tile*11);
        g2d.drawLine(x+tile,y+tile,x+tile,y+tile*10);
        for(int i = 2; i <= 8; i++)
        {
            g2d.drawLine(x+tile*i,y+tile,x+tile*i,y+tile*5);
            g2d.drawLine(x+tile*i,y+tile*6,x+tile*i,y+tile*10);
        }
        g2d.drawLine(x+tile*9,y+tile,x+tile*9,y+tile*10);
        g2d.drawLine(x+tile*10,y,x+tile*10,y+tile*11);
        //Diagonal lines.
        g2d.drawLine(x+tile*4,y+tile*3,x+tile*6,y+tile);
        g2d.drawLine(x+tile*4,y+tile,x+tile*6,y+tile*3);
        g2d.drawLine(x+tile*4,y+tile*10,x+tile*6,y+tile*8);
        g2d.drawLine(x+tile*4,y+tile*8,x+tile*6,y+tile*10);

        //Figures.
        //Black figures.
        g2d.drawImage(chariotBlack, x+tile-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(horseBlack, x+tile*2-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(elephantBlack, x+tile*3-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(advisorBlack, x+tile*4-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(generalBlack, x+tile*5-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(advisorBlack, x+tile*6-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(elephantBlack, x+tile*7-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(horseBlack, x+tile*8-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(chariotBlack, x+tile*9-figureRadius,y+tile-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(cannonBlack, x+tile*2-figureRadius,y+tile*3-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(cannonBlack, x+tile*8-figureRadius,y+tile*3-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierBlack, x+tile-figureRadius,y+tile*4-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierBlack, x+tile*3-figureRadius,y+tile*4-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierBlack, x+tile*5-figureRadius,y+tile*4-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierBlack, x+tile*7-figureRadius,y+tile*4-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierBlack, x+tile*9-figureRadius,y+tile*4-figureRadius,
                figureDiameter, figureDiameter, this);
        //Red figures.
        g2d.drawImage(chariotRed, x+tile-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(horseRed, x+tile*2-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(elephantRed, x+tile*3-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(advisorRed, x+tile*4-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(generalRed, x+tile*5-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(advisorRed, x+tile*6-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(elephantRed, x+tile*7-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(horseRed, x+tile*8-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(chariotRed, x+tile*9-figureRadius,y+tile*10-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(cannonRed, x+tile*2-figureRadius,y+tile*8-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(cannonRed, x+tile*8-figureRadius,y+tile*8-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierRed, x+tile-figureRadius,y+tile*7-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierRed, x+tile*3-figureRadius,y+tile*7-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierRed, x+tile*5-figureRadius,y+tile*7-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierRed, x+tile*7-figureRadius,y+tile*7-figureRadius,
                figureDiameter, figureDiameter, this);
        g2d.drawImage(soldierRed, x+tile*9-figureRadius,y+tile*7-figureRadius,
                figureDiameter, figureDiameter, this);
    }
}
