package com.github.mydeardoctor.chinesechess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

//TODO: git
class GUI
{
    private Text text = null;
    private TextEnglish textEnglish = null;
    private TextRussian textRussian = null;
    private JFrame frame = null;
    private Font fontChinese = null;
    private JButton buttonPlay = null;
    private JButton buttonLoad = null;
    private JButton buttonRules = null;
    private JButton buttonSettings = null;
    private GridBagLayout layoutForFrameMainMenu = null;
    private GridBagConstraints constraintsForButtonPlay = null;
    private GridBagConstraints constraintsForButtonLoad = null;
    private GridBagConstraints constraintsForButtonRules = null;
    private GridBagConstraints constraintsForButtonSettings = null;
    GUI()
    {
        textInit();
        frameCommonInit();
        frameMainMenuInit();
        showFrameMainMenu();
    }
    private void textInit()
    {
        textEnglish = new TextEnglish();
        textRussian = new TextRussian();
        text = textRussian;
    }
    private void frameCommonInit()
    {
        frame = new JFrame(text.getTitle());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Icon.
        //TODO: catch dialogue, logging
        try {
            URL url = getClass().getResource("/icon.jpg");
            BufferedImage icon = ImageIO.read(url);
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Font.
        //TODO: catch dialogue, logging
        try {
            URL url = getClass().getResource("/kashimarusbycop.otf");
            InputStream inputStream = url.openStream();
            fontChinese = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (IOException | FontFormatException e) {
            fontChinese = new Font(Font.DIALOG, Font.PLAIN, 1);
            e.printStackTrace();
        }

        //Menu Bar.
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(text.getHelp());
        JMenuItem menuItemRules = new JMenuItem(text.getRules());
        JMenuItem menuItemSettings = new JMenuItem(text.getSettings());
        JMenuItem menuItemAbout = new JMenuItem(text.getAbout());
        menuItemAbout.addActionListener(new MenuItemAboutActionListener());
        menu.add(menuItemRules);
        menu.add(menuItemSettings);
        menu.add(menuItemAbout);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //ContentPane with background image.
        frame.setContentPane(new PanelBackground());

        //Bounds.
        frame.setMinimumSize(new Dimension(800,800));
        frame.setBounds((int)frame.getGraphicsConfiguration().getBounds().getCenterX() -
                        (int)frame.getBounds().getCenterX(),
                (int)frame.getGraphicsConfiguration().getBounds().getCenterY() -
                        (int)frame.getBounds().getCenterY(),
                800,800);


        frame.setVisible(true);
    }
    private void frameMainMenuInit()
    {
        //Buttons.
        buttonPlay = new JButton(text.getPlay());
        buttonLoad = new JButton(text.getLoad());
        buttonRules = new JButton(text.getRules());
        buttonSettings = new JButton(text.getSettings());

        //Background Color.
        buttonPlay.setBackground(Color.WHITE);
        buttonLoad.setBackground(Color.WHITE);
        buttonRules.setBackground(Color.WHITE);
        buttonSettings.setBackground(Color.WHITE);

        //Border.
        BasicBorders.ButtonBorder border = new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK,
                                                                         Color.BLACK, Color.BLACK);
        buttonPlay.setBorder(border);
        buttonLoad.setBorder(border);
        buttonRules.setBorder(border);
        buttonSettings.setBorder(border);

        //Font.
        Font font = fontChinese.deriveFont(Font.BOLD, 50.f);
        buttonPlay.setFont(font);
        buttonLoad.setFont(font);
        buttonRules.setFont(font);
        buttonSettings.setFont(font);

        //Preferred Size.
        buttonPlay.setPreferredSize(new Dimension(300,100));
        buttonLoad.setPreferredSize(new Dimension(300,100));
        buttonRules.setPreferredSize(new Dimension(300,100));
        buttonSettings.setPreferredSize(new Dimension(300,100));

        //Layout manager.
        layoutForFrameMainMenu = new GridBagLayout();
        Insets insets = new Insets(30,0,30,0);
        constraintsForButtonPlay = new GridBagConstraints
                (0, 0,1,1,0,0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        insets, 0,0);
        constraintsForButtonLoad = new GridBagConstraints
                (0, 1,1,1,0,0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        insets, 0,0);
        constraintsForButtonRules = new GridBagConstraints
                (0, 2,1,1,0,0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        insets, 0,0);
        constraintsForButtonSettings = new GridBagConstraints
                (0, 3,1,1,0,0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        insets, 0,0);
    }
    private void showFrameMainMenu()
    {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(layoutForFrameMainMenu);
        frame.getContentPane().add(buttonPlay, constraintsForButtonPlay);
        frame.getContentPane().add(buttonLoad, constraintsForButtonLoad);
        frame.getContentPane().add(buttonRules, constraintsForButtonRules);
        frame.getContentPane().add(buttonSettings, constraintsForButtonSettings);
        frame.getContentPane().validate();
    }
    private void showFramePlay()
    {

    }
    private void showFrameLoadGame()
    {

    }
    private void showFrameRules()
    {

    }
    private void showFrameSettings()
    {

    }
    private class MenuItemAboutActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(frame, text.getAboutVerbose(), text.getAbout(),
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}