package com.github.mydeardoctor.chinesechess;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.net.URL;

public class GUI
{
    //Text.
    private Text text;
    private TextEnglish textEnglish;
    private TextRussian textRussian;

    //Resources.
    private Font fontChinese;
    private BufferedImage icon;
    private BufferedImage background;
    private BufferedImage generalRedIcon;
    private BufferedImage advisorRedIcon;
    private BufferedImage elephantRedIcon;
    private BufferedImage horseRedIcon;
    private BufferedImage chariotRedIcon;
    private BufferedImage cannonRedIcon;
    private BufferedImage soldierRedIcon;
    private BufferedImage generalBlackIcon;
    private BufferedImage advisorBlackIcon;
    private BufferedImage elephantBlackIcon;
    private BufferedImage horseBlackIcon;
    private BufferedImage chariotBlackIcon;
    private BufferedImage cannonBlackIcon;
    private BufferedImage soldierBlackIcon;
    private BufferedImage selection;

    //Common frame features.
    private JFrame frame;
    private JMenu menu;
    private JMenuItem menuItemAbout;
    private PanelBackground panelBackground;
    private GridBagLayout gridBagLayout;
    private BorderLayout borderLayout;

    //Frame Main menu.
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
    private JPanel panelEmpty;
    private PanelBoard panelBoard;
    private JLabel statusBar;

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

    public GUI()
    {
        initializeText();
        initializeResources();
        initializeCommonFrameFeatures();
        initializeFrameMainMenu();
        initializeFrameGameMode();
        initializeFrameBoard();
        initializeFrameSettings();
    }
    private void initializeText()
    {
        textEnglish = new TextEnglish();
        textRussian = new TextRussian();
        text = textEnglish;
    }
    private void initializeResources()
    {
        boolean resourcesMissing = false;

        //Font.
        URL urlFont = getClass().getResource("/kashimarusbycop.otf");
        try (InputStream inputStream = urlFont.openStream())
        {
            fontChinese = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            fontChinese = new Font(Font.DIALOG, Font.PLAIN, 1);
        }

        //Icon.
        URL urlIcon = getClass().getResource("/icon.jpg");
        try
        {
            icon = ImageIO.read(urlIcon);
        } catch (Exception e)
        {
            resourcesMissing = true;
            icon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = icon.createGraphics();
            g2d.setColor(new Color(196, 185, 165));
            g2d.fillRect(0,0,100,100);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 42));
            g2d.drawString("象棋", 5, 65);
        }

        //Background.
        URL url = getClass().getResource("/background.jpg");
        try
        {
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

        //General Red.
        url = getClass().getResource("/generalRed.png");
        try
        {
            generalRedIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            generalRedIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(generalRedIcon, Color.RED, "帥");
        }

        //Advisor Red.
        url = getClass().getResource("/advisorRed.png");
        try
        {
            advisorRedIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            advisorRedIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(advisorRedIcon, Color.RED, "仕");
        }

        //Elephant Red.
        url = getClass().getResource("/elephantRed.png");
        try
        {
            elephantRedIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            elephantRedIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(elephantRedIcon, Color.RED, "相");
        }

        //Horse Red.
        url = getClass().getResource("/horseRed.png");
        try
        {
            horseRedIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            horseRedIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(horseRedIcon, Color.RED, "傌");
        }

        //Chariot Red.
        url = getClass().getResource("/chariotRed.png");
        try
        {
            chariotRedIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            chariotRedIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(chariotRedIcon, Color.RED, "俥");
        }

        //Cannon Red.
        url = getClass().getResource("/cannonRed.png");
        try
        {
            cannonRedIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            cannonRedIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(cannonRedIcon, Color.RED, "炮");
        }

        //Soldier Red.
        url = getClass().getResource("/soldierRed.png");
        try
        {
            soldierRedIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            soldierRedIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(soldierRedIcon, Color.RED, "兵");
        }

        //General Black.
        url = getClass().getResource("/generalBlack.png");
        try
        {
            generalBlackIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            generalBlackIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(generalBlackIcon, Color.BLACK, "將");
        }

        //Advisor Black.
        url = getClass().getResource("/advisorBlack.png");
        try
        {
            advisorBlackIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            advisorBlackIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(advisorBlackIcon, Color.BLACK, "士");
        }

        //Elephant Black.
        url = getClass().getResource("/elephantBlack.png");
        try
        {
            elephantBlackIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            elephantBlackIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(elephantBlackIcon, Color.BLACK, "象");
        }

        //Horse Black.
        url = getClass().getResource("/horseBlack.png");
        try
        {
            horseBlackIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            horseBlackIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(horseBlackIcon, Color.BLACK, "馬");
        }

        //Chariot Black.
        url = getClass().getResource("/chariotBlack.png");
        try
        {
            chariotBlackIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            chariotBlackIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(chariotBlackIcon, Color.BLACK, "車");
        }

        //Cannon Black.
        url = getClass().getResource("/cannonBlack.png");
        try
        {
            cannonBlackIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            cannonBlackIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(cannonBlackIcon, Color.BLACK, "砲");
        }

        //Soldier Black.
        url = getClass().getResource("/soldierBlack.png");
        try
        {
            soldierBlackIcon = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            soldierBlackIcon = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(soldierBlackIcon, Color.BLACK, "卒");
        }

        //Selection.
        url = getClass().getResource("/selection.png");
        try
        {
            selection = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            selection = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = selection.createGraphics();
            g2d.setColor(new Color(117, 240, 131));
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            g2d.drawArc(5,5,90,90,0,360);
        }

        if(resourcesMissing==true)
        {
            JOptionPane.showMessageDialog(null, text.getSomeResourcesAreMissing(), text.getWarning(),
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    private void initializeDefaultFigureImage(BufferedImage figure, Color color, String label)
    {
        Graphics2D g2d = figure.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillOval(0,0,100,100);
        g2d.setColor(new Color(255,221,170));
        g2d.fillOval(3,3,94,94);
        g2d.setColor(color);
        g2d.fillOval(6,6,88,88);
        g2d.setColor(new Color(255,221,170));
        g2d.fillOval(9,9,82,82);
        g2d.setColor(color);
        g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
        g2d.drawString(label,20,70);
    }
    private void initializeCommonFrameFeatures()
    {
        frame = new JFrame(text.getTitle());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Bounds.
        frame.setMinimumSize(new Dimension(800,800));
        frame.setBounds((int)frame.getGraphicsConfiguration().getBounds().getCenterX() -
                          (int)frame.getBounds().getCenterX(),
                        (int)frame.getGraphicsConfiguration().getBounds().getCenterY() -
                          (int)frame.getBounds().getCenterY(),
                     800,800);
        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);

        //Icon.
        frame.setIconImage(icon);

        //Menu Bar.
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu(text.getHelp());
        menuItemAbout = new JMenuItem(text.getAbout());
        menuItemAbout.addActionListener(e->
                JOptionPane.showMessageDialog(frame, text.getAboutVerbose(), text.getAbout(),
                        JOptionPane.INFORMATION_MESSAGE));
        menu.add(menuItemAbout);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //Content Pane with background image.
        panelBackground = new PanelBackground(background);

        //Layout managers.
        gridBagLayout = new GridBagLayout();
        borderLayout = new BorderLayout();

        frame.setVisible(true);
    }
    private void initializeFrameMainMenu()
    {
        //Buttons.
        buttonPlay = new JButton(text.getPlay());
        buttonLoad = new JButton(text.getLoad());
        buttonRules = new JButton(text.getRules());
        buttonSettings = new JButton(text.getSettings());

        //Preferred Size.
        Dimension dimension = new Dimension(300,100);
        buttonPlay.setPreferredSize(dimension);
        buttonLoad.setPreferredSize(dimension);
        buttonRules.setPreferredSize(dimension);
        buttonSettings.setPreferredSize(dimension);

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

        //Background Color.
        buttonPlay.setBackground(Color.WHITE);
        buttonLoad.setBackground(Color.WHITE);
        buttonRules.setBackground(Color.WHITE);
        buttonSettings.setBackground(Color.WHITE);

        //Border.
        BasicBorders.ButtonBorder border = new BasicBorders.ButtonBorder(
                Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);
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

        //Action listeners.
        buttonPlay.addActionListener(e->showFrameGameMode());
        buttonSettings.addActionListener(e->showFrameSettings());
    }
    private void initializeFrameGameMode()
    {
        //Buttons.
        buttonSinglePlayer = new JButton(text.getSinglePlayer());
        buttonLocalMultiplayer = new JButton(text.getLocalMultiplayer());
        buttonOnlineMultiplayer = new JButton(text.getOnlineMultiplayer());
        buttonBackGameMode = new JButton(text.getBack());

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

        //Background Color.
        buttonSinglePlayer.setBackground(Color.WHITE);
        buttonLocalMultiplayer.setBackground(Color.WHITE);
        buttonOnlineMultiplayer.setBackground(Color.WHITE);
        buttonBackGameMode.setBackground(Color.WHITE);

        //Border.
        BasicBorders.ButtonBorder border = new BasicBorders.ButtonBorder(
                Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);
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

        //Action listener.
        buttonLocalMultiplayer.addActionListener(e->
        {
            showFrameBoard();
            game.start();
        });
        buttonBackGameMode.addActionListener(e->showFrameMainMenu());
    }
    private void initializeFrameBoard()
    {
        //Empty content pane.
        panelEmpty = new JPanel();

        //Board Panel.
        panelBoard = new PanelBoard(background);
        panelBoard.addMouseListener(panelBoard);

        //Status Bar.
        statusBar = new JLabel(" ");
        statusBar.setOpaque(true);
        statusBar.setBorder(new LineBorder(Color.BLACK, 1));
        statusBar.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
    }
    private void initializeFrameSettings()
    {
        //Label.
        labelLanguage = new JLabel(text.getLanguage());

        //ComboBox.
        comboBoxLanguage = new JComboBox<>();
        comboBoxLanguage.addItem("English");
        comboBoxLanguage.addItem("Русский");

        //Buttons.
        buttonBackSettings = new JButton(text.getBack());
        buttonApply = new JButton(text.getApply());

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

        //Background Color.
        buttonBackSettings.setBackground(Color.WHITE);
        buttonApply.setBackground(Color.WHITE);

        //Border.
        BasicBorders.ButtonBorder border = new BasicBorders.ButtonBorder(
                Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);
        buttonBackSettings.setBorder(border);
        buttonApply.setBorder(border);

        //Font.
        Font font = fontChinese.deriveFont(Font.BOLD, 46.f);
        labelLanguage.setFont(font);
        comboBoxLanguage.setFont(font);
        buttonBackSettings.setFont(font);
        buttonApply.setFont(font);

        //Action listeners.
        buttonBackSettings.addActionListener(e->showFrameMainMenu());
        buttonApply.addActionListener(e-> refreshText());
    }
    public void showFrameMainMenu()
    {
        frame.getContentPane().removeAll();
        frame.setContentPane(panelBackground);
        frame.getContentPane().setLayout(gridBagLayout);
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
        frame.getContentPane().setLayout(gridBagLayout);
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
        frame.setContentPane(panelEmpty);
        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(panelBoard, BorderLayout.CENTER);
        frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
        frame.validate();
        frame.repaint();
    }
    private void showFrameSettings()
    {
        frame.getContentPane().removeAll();
        frame.setContentPane(panelBackground);
        frame.getContentPane().setLayout(gridBagLayout);
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
            case "English" -> text = textEnglish;
            case "Русский" -> text = textRussian;
        }

        //Common frame features.
        frame.setTitle(text.getTitle());
        menu.setText(text.getHelp());
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
    public Text getText()
    {
        return text;
    }
    public BufferedImage getGeneralRedIcon()
    {
        return generalRedIcon;
    }
    public BufferedImage getAdvisorRedIcon()
    {
        return advisorRedIcon;
    }
    public BufferedImage getElephantRedIcon()
    {
        return elephantRedIcon;
    }
    public BufferedImage getHorseRedIcon()
    {
        return horseRedIcon;
    }
    public BufferedImage getChariotRedIcon()
    {
        return chariotRedIcon;
    }
    public BufferedImage getCannonRedIcon()
    {
        return cannonRedIcon;
    }
    public BufferedImage getSoldierRedIcon()
    {
        return soldierRedIcon;
    }
    public BufferedImage getGeneralBlackIcon()
    {
        return generalBlackIcon;
    }
    public BufferedImage getAdvisorBlackIcon()
    {
        return advisorBlackIcon;
    }
    public BufferedImage getElephantBlackIcon()
    {
        return elephantBlackIcon;
    }
    public BufferedImage getHorseBlackIcon()
    {
        return horseBlackIcon;
    }
    public BufferedImage getChariotBlackIcon()
    {
        return chariotBlackIcon;
    }
    public BufferedImage getCannonBlackIcon()
    {
        return cannonBlackIcon;
    }
    public BufferedImage getSoldierBlackIcon()
    {
        return soldierBlackIcon;
    }
    public BufferedImage getSelection()
    {
        return selection;
    }
    public PanelBoard getPanelBoard()
    {
        return panelBoard;
    }
    public JLabel getStatusBar()
    {
        return statusBar;
    }
    public void setGame(Game game)
    {
        this.game = game;
        panelBoard.setGame(game);
    }
}