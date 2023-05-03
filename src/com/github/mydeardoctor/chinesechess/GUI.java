package com.github.mydeardoctor.chinesechess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

class GUI
{
    private Text text = null;
    private JFrame frame = null;
    private Font fontChinese = null;
    private GridBagLayout layoutGridBag = null;

    //Frame Main Menu.
    private JButton buttonPlay = null;
    private JButton buttonLoad = null;
    private JButton buttonRules = null;
    private JButton buttonSettings = null;
    private GridBagConstraints constraintsForButtonPlay = null;
    private GridBagConstraints constraintsForButtonLoad = null;
    private GridBagConstraints constraintsForButtonRules = null;
    private GridBagConstraints constraintsForButtonSettings = null;

    //Frame Game Mode.
    private JButton buttonSinglePlayer = null;
    private JButton buttonLocalMultiplayer = null;
    private JButton buttonOnlineMultiplayer = null;
    private JButton buttonBackGameMode = null;
    private GridBagConstraints constraintsForButtonSinglePlayer = null;
    private GridBagConstraints constraintsForButtonLocalMultiplayer = null;
    private GridBagConstraints constraintsForButtonOnlineMultiplayer = null;
    private GridBagConstraints constraintsForButtonBackGameMode = null;

    //Frame Settings.
    private JLabel labelLanguage = null;
    private JComboBox<String> comboBoxLanguage = null;
    private JButton buttonBackSettings = null;
    private JButton buttonApply = null;
    private GridBagConstraints constraintsForLabelLanguage = null;
    private GridBagConstraints constraintsForComboBoxLanguage = null;
    private GridBagConstraints constraintsForButtonBackSettings = null;
    private GridBagConstraints constraintsForButtonApply = null;

    GUI(Text text)
    {
        textInit(text);
        frameCommonInit();
        frameMainMenuInit();
        frameGameModeInit();
        frameSettingsInit();
        showFrameMainMenu();
    }
    private void textInit(Text text)
    {
        this.text = text;
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
        JMenu menu = new JMenu(text.getHelp());
        JMenuItem menuItemRules = new JMenuItem(text.getRules());
        JMenuItem menuItemSettings = new JMenuItem(text.getSettings());
        JMenuItem menuItemAbout = new JMenuItem(text.getAbout());
        menuItemAbout.addActionListener(e->
                JOptionPane.showMessageDialog(frame, text.getAboutVerbose(), text.getAbout(),
                        JOptionPane.INFORMATION_MESSAGE));
        menu.add(menuItemRules);
        menu.add(menuItemSettings);
        menu.add(menuItemAbout);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //ContentPane with background image.
        frame.setContentPane(new PanelBackground(text));

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
    private void frameSettingsInit()
    {
        //Label.
        labelLanguage = new JLabel(text.getLanguage());

        //ComboBox.
        comboBoxLanguage = new JComboBox<String>();
        comboBoxLanguage.addItem(text.getEnglish());
        comboBoxLanguage.addItem(text.getRussian());

        //Buttons.
        buttonBackSettings = new JButton(text.getBack());
        buttonApply = new JButton(text.getApply());

        //TODO: ActionListener Apply Language
        //Action listener.
        buttonBackSettings.addActionListener(e->showFrameMainMenu());

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
        frame.getContentPane().setLayout(layoutGridBag);
        frame.getContentPane().add(buttonPlay, constraintsForButtonPlay);
        frame.getContentPane().add(buttonLoad, constraintsForButtonLoad);
        frame.getContentPane().add(buttonRules, constraintsForButtonRules);
        frame.getContentPane().add(buttonSettings, constraintsForButtonSettings);
        frame.getContentPane().validate();
        frame.repaint();
    }
    private void showFrameGameMode()
    {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(layoutGridBag);
        frame.getContentPane().add(buttonSinglePlayer, constraintsForButtonSinglePlayer);
        frame.getContentPane().add(buttonLocalMultiplayer, constraintsForButtonLocalMultiplayer);
        frame.getContentPane().add(buttonOnlineMultiplayer, constraintsForButtonOnlineMultiplayer);
        frame.getContentPane().add(buttonBackGameMode, constraintsForButtonBackGameMode);
        frame.getContentPane().validate();
        frame.repaint();
    }
    private void showFrameLoad()
    {

    }
    private void showFrameRules()
    {

    }
    private void showFrameSettings()
    {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(layoutGridBag);
        frame.getContentPane().add(labelLanguage, constraintsForLabelLanguage);
        frame.getContentPane().add(comboBoxLanguage, constraintsForComboBoxLanguage);
        frame.getContentPane().add(buttonBackSettings, constraintsForButtonBackSettings);
        frame.getContentPane().add(buttonApply, constraintsForButtonApply);
        frame.getContentPane().validate();
        frame.repaint();
    }
}