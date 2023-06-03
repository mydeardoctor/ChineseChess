package com.github.mydeardoctor.chinesechess.server;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class GUI
{
    //Text.
    private Text text;
    private final TextEnglish textEnglish;
    private final TextRussian textRussian;

    //Resources.
    boolean resourcesMissing;
    private final BufferedImage background;

    //Common frame features.
    private JFrame frame;
    private PanelBackground panelBackground;
    private GridBagLayout gridBagLayout;

    //Frame Main menu.

    public GUI()
    {
        //Text
        textEnglish = new TextEnglish();
        textRussian = new TextRussian();
        text = textEnglish;

        //Resources
        background = initializeBackground();

        initializeCommonFrameFeatures();
        initializeFrameMainMenu();
    }
    private BufferedImage initializeBackground()
    {
        BufferedImage background;
        URL url = getClass().getResource("/background.jpg");
        try
        {
            //noinspection DataFlowIssue
            background = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            background = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = background.createGraphics();
            g2d.setColor(new Color(196, 185, 165));
            g2d.fillRect(0,0,100,100);
        }
        return background;
    }
    private void initializeCommonFrameFeatures()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Frame.
                frame = new JFrame("server");
                frame.setMinimumSize(new Dimension(800,800));
                frame.setBounds((int)frame.getGraphicsConfiguration().getBounds().getCenterX() -
                                (int)frame.getBounds().getCenterX(),
                        (int)frame.getGraphicsConfiguration().getBounds().getCenterY() -
                                (int)frame.getBounds().getCenterY(),
                        800,800);
                //frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
                //frame.setIconImage(iconFrame);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //Panel Background.
                panelBackground = new PanelBackground(background);
                frame.setContentPane(panelBackground);

                //GridBag layout manager.
                gridBagLayout = new GridBagLayout();
                frame.getContentPane().setLayout(gridBagLayout);
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void initializeFrameMainMenu()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //addToPreviousFrames(FrameType.MAIN_MENU);
                frame.getContentPane().removeAll();
                repaint();
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void showFrameMainMenu()
    {
        SwingUtilities.invokeLater(()->
        {

        });
    }
    public void showFrameAndWarnings()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                showFrameMainMenu();

                frame.setVisible(true);


            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void repaint()
    {
        SwingUtilities.invokeLater(()->
        {
            frame.revalidate();
            frame.repaint();
        });
    }

}