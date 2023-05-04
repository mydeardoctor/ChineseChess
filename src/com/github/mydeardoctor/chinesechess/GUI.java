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
    //Text.
    private Text text = null;
    private TextEnglish textEnglish = null;
    private TextRussian textRussian = null;

    //Frame Common.
    private JFrame frame = null;
    private Font fontChinese = null;
    JMenu menu = null;
    JMenuItem menuItemRules = null;
    JMenuItem menuItemSettings = null;
    JMenuItem menuItemAbout = null;
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

    GUI()
    {
        textInit();
        frameCommonInit();
        frameMainMenuInit();
        frameGameModeInit();
        frameSettingsInit();
        //showFrameMainMenu();
        showFrameBoard();
    }
    private void textInit()
    {
        textEnglish = new TextEnglish();
        textRussian = new TextRussian();
        text = textEnglish;
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
    private void showFrameBoard()
    { //TODO:
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new PanelBoard());
        frame.getContentPane().validate();
        frame.repaint();
    }
    private void refreshText()
    {
        String language = (String)comboBoxLanguage.getSelectedItem();
        switch(language)
        {
            case "English":
                text = textEnglish;
                break;
            case "Русский":
                text = textRussian;
                break;
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
    }
    private class PanelBackground extends JPanel
    {
        private BufferedImage background = null;
        private PanelBackground()
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
    class PanelBoard extends JPanel
    {
        private Graphics2D g2d = null;
        private int x = 0;
        private int y = 0;
        private int side = 0;
        private int tile = 0;
        private int figureDiameter = 0;
        private int figureRadius = 0;
        private BufferedImage advisorBlack = null;
        private BufferedImage advisorRed = null;
        private BufferedImage cannonBlack = null;
        private BufferedImage cannonRed = null;
        private BufferedImage chariotBlack = null;
        private BufferedImage chariotRed = null;
        private BufferedImage elephantBlack = null;
        private BufferedImage elephantRed = null;
        private BufferedImage generalBlack = null;
        private BufferedImage generalRed = null;
        private BufferedImage horseBlack = null;
        private BufferedImage horseRed = null;
        private BufferedImage soldierBlack = null;
        private BufferedImage soldierRed = null;
        PanelBoard()
        {
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
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g2d = (Graphics2D)g;

            //Calculation of dimensions.
            side = Math.min(this.getWidth(), this.getHeight());
            tile = side/11;
            x = (int)(this.getBounds().getCenterX()-tile*10/2);
            y = (int)(this.getBounds().getCenterY()-tile*11/2);
            figureDiameter = (int)(tile*0.85);
            figureRadius = figureDiameter/2;

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
            g2d.drawLine(x+tile*1,y+tile*1,x+tile*1,y+tile*10);
            for(int i = 2; i <= 8; i++)
            {
                g2d.drawLine(x+tile*i,y+tile*1,x+tile*i,y+tile*5);
                g2d.drawLine(x+tile*i,y+tile*6,x+tile*i,y+tile*10);
            }
            g2d.drawLine(x+tile*9,y+tile*1,x+tile*9,y+tile*10);
            g2d.drawLine(x+tile*10,y,x+tile*10,y+tile*11);

            //Figures.
            //Black figures.
            g2d.drawImage(chariotBlack, x+tile*1-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(horseBlack, x+tile*2-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(elephantBlack, x+tile*3-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(advisorBlack, x+tile*4-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(generalBlack, x+tile*5-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(advisorBlack, x+tile*6-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(elephantBlack, x+tile*7-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(horseBlack, x+tile*8-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(chariotBlack, x+tile*9-figureRadius,y+tile*1-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(cannonBlack, x+tile*2-figureRadius,y+tile*3-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(cannonBlack, x+tile*8-figureRadius,y+tile*3-figureRadius,
                    figureDiameter, figureDiameter, this);
            g2d.drawImage(soldierBlack, x+tile*1-figureRadius,y+tile*4-figureRadius,
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
            g2d.drawImage(chariotRed, x+tile*1-figureRadius,y+tile*10-figureRadius,
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
            g2d.drawImage(soldierRed, x+tile*1-figureRadius,y+tile*7-figureRadius,
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
}