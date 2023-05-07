package com.github.mydeardoctor.chinesechess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

class GUI
{
    //Text.
    private Text text;

    //Frame Common.
    private JFrame frame;
    private Font fontChinese;
    private JMenu menu;
    private JMenuItem menuItemRules;
    private JMenuItem menuItemSettings;
    private JMenuItem menuItemAbout;
    private PanelBackground panelBackground;
    private GridBagLayout layoutGridBag;

    //Frame Main Menu.
    private JButton buttonPlay;
    private JButton buttonLoad;
    private JButton buttonRules;
    private JButton buttonSettings;
    private GridBagConstraints constraintsForButtonPlay;
    private GridBagConstraints constraintsForButtonLoad;
    private GridBagConstraints constraintsForButtonRules;
    private GridBagConstraints constraintsForButtonSettings;

    //Frame Game Mode.
    private JButton buttonSinglePlayer;
    private JButton buttonLocalMultiplayer;
    private JButton buttonOnlineMultiplayer;
    private JButton buttonBackGameMode;
    private GridBagConstraints constraintsForButtonSinglePlayer;
    private GridBagConstraints constraintsForButtonLocalMultiplayer;
    private GridBagConstraints constraintsForButtonOnlineMultiplayer;
    private GridBagConstraints constraintsForButtonBackGameMode;

    //Frame Board.
    private JPanel panelBackgroundEmpty;
    private PanelBoard panelBoard;
    private JLabel statusBar;
    private BorderLayout layoutBorder;

    //Frame Settings.
    private JLabel labelLanguage;
    private JComboBox<String> comboBoxLanguage;
    private JButton buttonBackSettings;
    private JButton buttonApply;
    private GridBagConstraints constraintsForLabelLanguage;
    private GridBagConstraints constraintsForComboBoxLanguage;
    private GridBagConstraints constraintsForButtonBackSettings;
    private GridBagConstraints constraintsForButtonApply;

    //Game.
    private Game game;

    GUI()
    {
        textInit();
        frameCommonInit();
        frameMainMenuInit();
        frameGameModeInit();
        frameBoardInit();
        frameSettingsInit();
        showFrameMainMenu();
    }
    private void textInit()
    {
        text = new TextEnglish();
    }
    private void frameCommonInit()
    {
        frame = new JFrame(text.getTitle());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        layoutGridBag = new GridBagLayout();

        //Icon.
        URL urlIcon = getClass().getResource("/icon.jpg");
        try
        {
            BufferedImage icon = ImageIO.read(urlIcon);
            frame.setIconImage(icon);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorIcon(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }

        //Font.
        URL urlFont = getClass().getResource("/kashimarusbycop.otf");
        try (InputStream inputStream = urlFont.openStream())
        {
            fontChinese = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        }
        catch (Exception e)
        {
            fontChinese = new Font(Font.DIALOG, Font.PLAIN, 1);
            JOptionPane.showMessageDialog(null, text.getErrorFont(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }

        //Menu Bar.
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu(text.getHelp());
        menuItemRules = new JMenuItem(text.getRules());
        menuItemSettings = new JMenuItem(text.getSettings());
        menuItemAbout = new JMenuItem(text.getAbout());
        menuItemSettings.addActionListener(e->showFrameSettings());
        menuItemAbout.addActionListener(e->
                JOptionPane.showMessageDialog(frame, text.getAboutVerbose(), text.getAbout(),
                        JOptionPane.INFORMATION_MESSAGE));
        menu.add(menuItemRules);
        menu.add(menuItemSettings);
        menu.add(menuItemAbout);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //ContentPane with background image.
        panelBackground = new PanelBackground(text);
        frame.setContentPane(panelBackground);

        //Bounds.
        frame.setMinimumSize(new Dimension(800,800));
        frame.setBounds((int)frame.getGraphicsConfiguration().getBounds().getCenterX() -
                        (int)frame.getBounds().getCenterX(),
                (int)frame.getGraphicsConfiguration().getBounds().getCenterY() -
                        (int)frame.getBounds().getCenterY(),
                800,800);

        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    private void frameMainMenuInit()
    {
        //Buttons.
        buttonPlay = new JButton(text.getPlay());
        buttonLoad = new JButton(text.getLoad());
        buttonRules = new JButton(text.getRules());
        buttonSettings = new JButton(text.getSettings());

        //Action listener.
        buttonPlay.addActionListener(e->showFrameGameMode());
        buttonSettings.addActionListener(e->showFrameSettings());

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

        //Layout manager constraints.
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
    private void frameGameModeInit()
    {
        //Buttons.
        buttonSinglePlayer = new JButton(text.getSinglePlayer());
        buttonLocalMultiplayer = new JButton(text.getLocalMultiplayer());
        buttonOnlineMultiplayer = new JButton(text.getOnlineMultiplayer());
        buttonBackGameMode = new JButton(text.getBack());

        //Action listener.
        buttonSinglePlayer.addActionListener(e->showFrameBoard());
        buttonBackGameMode.addActionListener(e->showFrameMainMenu());

        //Background Color.
        buttonSinglePlayer.setBackground(Color.WHITE);
        buttonLocalMultiplayer.setBackground(Color.WHITE);
        buttonOnlineMultiplayer.setBackground(Color.WHITE);
        buttonBackGameMode.setBackground(Color.WHITE);

        //Border.
        BasicBorders.ButtonBorder border = new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK,
                Color.BLACK, Color.BLACK);
        buttonSinglePlayer.setBorder(border);
        buttonLocalMultiplayer.setBorder(border);
        buttonOnlineMultiplayer.setBorder(border);
        buttonBackGameMode.setBorder(border);

        //Font.
        Font font = fontChinese.deriveFont(Font.BOLD, 46.f);
        buttonSinglePlayer.setFont(font);
        buttonLocalMultiplayer.setFont(font);
        buttonOnlineMultiplayer.setFont(font);
        buttonBackGameMode.setFont(font);

        //Preferred Size.
        buttonSinglePlayer.setPreferredSize(new Dimension(500,100));
        buttonLocalMultiplayer.setPreferredSize(new Dimension(500,100));
        buttonOnlineMultiplayer.setPreferredSize(new Dimension(500,100));
        buttonBackGameMode.setPreferredSize(new Dimension(200,100));

        //Layout manager constraints.
        Insets insets = new Insets(30,0,30,0);
        constraintsForButtonSinglePlayer = new GridBagConstraints
                (0, 0,1,1,0,0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        insets, 0,0);
        constraintsForButtonLocalMultiplayer = new GridBagConstraints
                (0, 1,1,1,0,0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        insets, 0,0);
        constraintsForButtonOnlineMultiplayer = new GridBagConstraints
                (0, 2,1,1,0,0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        insets, 0,0);
        constraintsForButtonBackGameMode = new GridBagConstraints
                (0, 3,1,1,0,0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        insets, 0,0);
    }
    private void frameBoardInit()
    {
        panelBackgroundEmpty = new JPanel();
        panelBoard = new PanelBoard(text);

        statusBar = new JLabel(" ");
        statusBar.setOpaque(true);
        statusBar.setBorder(new LineBorder(Color.BLACK, 1));
        statusBar.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));

        layoutBorder = new BorderLayout();
    }
    private void frameSettingsInit()
    {
        //Label.
        labelLanguage = new JLabel(text.getLanguage());

        //ComboBox.
        comboBoxLanguage = new JComboBox<>();
        comboBoxLanguage.addItem(text.getEnglish());
        comboBoxLanguage.addItem(text.getRussian());

        //Buttons.
        buttonBackSettings = new JButton(text.getBack());
        buttonApply = new JButton(text.getApply());

        //Action listener.
        buttonBackSettings.addActionListener(e->showFrameMainMenu());
        buttonApply.addActionListener(e-> refreshText());

        //Background Color.
        buttonBackSettings.setBackground(Color.WHITE);
        buttonApply.setBackground(Color.WHITE);

        //Border.
        BasicBorders.ButtonBorder border = new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK,
                Color.BLACK, Color.BLACK);
        buttonBackSettings.setBorder(border);
        buttonApply.setBorder(border);

        //Font.
        Font font = fontChinese.deriveFont(Font.BOLD, 46.f);
        labelLanguage.setFont(font);
        comboBoxLanguage.setFont(font);
        buttonBackSettings.setFont(font);
        buttonApply.setFont(font);

        //Preferred Size.
        labelLanguage.setPreferredSize(new Dimension(200,100));
        comboBoxLanguage.setPreferredSize(new Dimension(300,100));
        buttonBackSettings.setPreferredSize(new Dimension(200,100));
        buttonApply.setPreferredSize(new Dimension(230,100));

        //Layout manager constraints.
        constraintsForLabelLanguage = new GridBagConstraints
                (0, 0,1,1,0,0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(0,30,0,30), 0,0);
        constraintsForComboBoxLanguage = new GridBagConstraints
                (1, 0,1,1,0,0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0,30,0,30), 0,0);
        constraintsForButtonBackSettings = new GridBagConstraints
                (0, 1,1,1,0,0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(80,30,0,30), 0,0);
        constraintsForButtonApply = new GridBagConstraints
                (1, 1,1,1,0,0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(80,30,0,30), 0,0);

    }
    private void showFrameMainMenu()
    {
        frame.getContentPane().removeAll();
        frame.setContentPane(panelBackground);
        frame.getContentPane().setLayout(layoutGridBag);
        frame.getContentPane().add(buttonPlay, constraintsForButtonPlay);
        frame.getContentPane().add(buttonLoad, constraintsForButtonLoad);
        frame.getContentPane().add(buttonRules, constraintsForButtonRules);
        frame.getContentPane().add(buttonSettings, constraintsForButtonSettings);
        frame.validate();
        frame.repaint();
    }
    private void showFrameGameMode()
    {
        frame.getContentPane().removeAll();
        frame.setContentPane(panelBackground);
        frame.getContentPane().setLayout(layoutGridBag);
        frame.getContentPane().add(buttonSinglePlayer, constraintsForButtonSinglePlayer);
        frame.getContentPane().add(buttonLocalMultiplayer, constraintsForButtonLocalMultiplayer);
        frame.getContentPane().add(buttonOnlineMultiplayer, constraintsForButtonOnlineMultiplayer);
        frame.getContentPane().add(buttonBackGameMode, constraintsForButtonBackGameMode);
        frame.validate();
        frame.repaint();
    }
    private void showFrameBoard()
    {
        frame.getContentPane().removeAll();
        frame.setContentPane(panelBackgroundEmpty);
        frame.getContentPane().setLayout(layoutBorder);
        frame.getContentPane().add(panelBoard, BorderLayout.CENTER);
        frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
        frame.validate();
        frame.repaint();
        game = new Game(text, panelBoard, statusBar);
    }
    private void showFrameSettings()
    {
        frame.getContentPane().removeAll();
        frame.setContentPane(panelBackground);
        frame.getContentPane().setLayout(layoutGridBag);
        frame.getContentPane().add(labelLanguage, constraintsForLabelLanguage);
        frame.getContentPane().add(comboBoxLanguage, constraintsForComboBoxLanguage);
        frame.getContentPane().add(buttonBackSettings, constraintsForButtonBackSettings);
        frame.getContentPane().add(buttonApply, constraintsForButtonApply);
        frame.validate();
        frame.repaint();
    }
    private void refreshText()
    {
        String language = (String)(comboBoxLanguage.getSelectedItem());
        switch (language)
        {
            case "English" -> text = new TextEnglish();
            case "Русский" -> text = new TextRussian();
        }

        //Frame Common.
        frame.setTitle(text.getTitle());
        menu.setText(text.getHelp());
        menuItemRules.setText(text.getRules());
        menuItemSettings.setText(text.getSettings());
        menuItemAbout.setText(text.getAbout());

        //Frame Main Menu.
        buttonPlay.setText(text.getPlay());
        buttonLoad.setText(text.getLoad());
        buttonRules.setText(text.getRules());
        buttonSettings.setText(text.getSettings());

        //Frame Game Mode.
        buttonSinglePlayer.setText(text.getSinglePlayer());
        buttonLocalMultiplayer.setText(text.getLocalMultiplayer());
        buttonOnlineMultiplayer.setText(text.getOnlineMultiplayer());
        buttonBackGameMode.setText(text.getBack());

        //Frame Settings.
        labelLanguage.setText(text.getLanguage());
        buttonBackSettings.setText(text.getBack());
        buttonApply.setText(text.getApply());

        //Game.
        game.refreshText(text);
    }
}