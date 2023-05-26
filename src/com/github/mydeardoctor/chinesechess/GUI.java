package com.github.mydeardoctor.chinesechess;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
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
    boolean resourcesMissing;
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
    private BufferedImage selectionPalace;
    private BufferedImage selectionRiver;
    private ImageIcon iconUnmuted;
    private ImageIcon iconMuted;

    //Common frame features.
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menuHelp;
    private JMenuItem menuItemRules;
    private JMenuItem menuItemSettings;
    private JMenuItem menuItemAbout;
    private PanelBackground panelBackground;
    private GridBagLayout gridBagLayout;
    private ArrayList<FrameType> previousFrames;

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
    private JMenu menuNavigation;
    private JMenuItem menuItemMainMenu;
    private PanelBoardInteractive panelBoardInteractive;
    private JTextField statusBar;
    private GridBagConstraints constraintsForPanelBoardInteractive;
    private GridBagConstraints constraintsForStatusBar;

    //Frame Rules.
    private PanelBoardRules panelBoardRules;
    private JComboBox<String> comboBoxRules;
    private JButton buttonBackRules;
    private JTextArea textAreaRules;
    private JScrollPane scrollPaneRules;
    private GridBagConstraints constraintsForPanelBoardRules;
    private GridBagConstraints constraintsForComboBoxRules;
    private GridBagConstraints constraintsForButtonBackRules;
    private GridBagConstraints constraintsForScrollPaneRules;

    //Frame Settings.
    private JLabel labelLanguage;
    private JComboBox<String> comboBoxLanguage;
    private JLabel labelMusic;
    private JToggleButton buttonMuteMusic;
    private JSlider sliderGainMusic;
    private JLabel labelSfx;
    private JToggleButton buttonMuteSfx;
    private JSlider sliderGainSfx;
    private JButton buttonApply;
    private JButton buttonBackSettings;
    private GridBagConstraints constraintsForLabelLanguage;
    private GridBagConstraints constraintsForComboBoxLanguage;
    private GridBagConstraints constraintsForLabelMusic;
    private GridBagConstraints constraintsForButtonMuteMusic;
    private GridBagConstraints constraintsForSliderGainMusic;
    private GridBagConstraints constraintsForLabelSfx;
    private GridBagConstraints constraintsForButtonMuteSfx;
    private GridBagConstraints constraintsForSliderGainSfx;
    private GridBagConstraints constraintsForButtonApply;
    private GridBagConstraints constraintsForButtonBackSettings;

    //Game.
    private Game game;

    //Rules.
    private Rules rules;

    //Music player.
    private MusicPlayer musicPlayer;

    public GUI()
    {
        initializeText();
        initializeResources();
        initializeCommonFrameFeatures();
        initializeFrameMainMenu();
        initializeFrameGameMode();
        initializeFrameBoard();
        initializeFrameRules();
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
        resourcesMissing = false;

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
        URL url = getClass().getResource("/icon.jpg");
        try
        {
            icon = ImageIO.read(url);
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
        url = getClass().getResource("/background.jpg");
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

        //Selection Palace.
        url = getClass().getResource("/selectionPalace.png");
        try
        {
            selectionPalace = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            selectionPalace = new BufferedImage(200,200,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = selectionPalace.createGraphics();
            g2d.setColor(new Color(117, 240, 131));
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            g2d.drawRect(5,5,190,190);
        }

        //Selection River.
        url = getClass().getResource("/selectionRiver.png");
        try
        {
            selectionRiver = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            selectionRiver = new BufferedImage(800,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = selectionRiver.createGraphics();
            g2d.setColor(new Color(117, 240, 131));
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            g2d.drawRect(5,5,790,90);
        }

        //Icon unmuted.
        BufferedImage imageUnmuted = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageUnmuted.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawPolygon(new Polygon(new int[]{10,23,45,45,23,10,10}, new int[] {23,23,10,50,37,37,23}, 7));
        g2d.drawLine(23, 23, 23, 37);
        iconUnmuted = new ImageIcon(imageUnmuted);

        //Icon muted.
        BufferedImage imageMuted = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = imageMuted.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawPolygon(new Polygon(new int[]{10,23,45,45,23,10,10}, new int[] {23,23,10,50,37,37,23}, 7));
        g2d.drawLine(23, 23, 23, 37);
        g2d.setColor(Color.RED);
        g2d.drawLine(5, 5, 55, 55);
        g2d.drawLine(5, 55, 55, 5);
        iconMuted = new ImageIcon(imageMuted);
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
        try
        {
            SwingUtilities.invokeAndWait(()->
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
                menuBar = new JMenuBar();

                menuNavigation = new JMenu(text.getNavigation());
                menuItemMainMenu = new JMenuItem(text.getMainMenu());
                menuItemMainMenu.addActionListener(e->
                {
                    if(game.getState()==State.OVER)
                    {
                        showFrameMainMenu();
                    }
                    else //game.getState()==State.RUNNING
                    {
                        int selectedOption = JOptionPane.showOptionDialog(frame,
                                text.getAreYouSure(), text.getExitToMainMenu(),
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                new String[] {text.getYes(), text.getNo()}, text.getNo());
                        if(selectedOption==JOptionPane.YES_OPTION)
                        {
                            musicPlayer.stopMusic();
                            showFrameMainMenu();
                        }
                    }
                });
                menuNavigation.add(menuItemMainMenu);
                menuBar.add(menuNavigation);

                menuHelp = new JMenu(text.getHelp());
                menuItemRules = new JMenuItem(text.getRules());
                menuItemSettings = new JMenuItem(text.getSettings());
                menuItemAbout = new JMenuItem(text.getAbout());
                menuItemRules.addActionListener(e->showFrameRules());
                menuItemSettings.addActionListener(e->showFrameSettings());
                menuItemAbout.addActionListener(e->
                    JOptionPane.showMessageDialog(frame, text.getAboutVerbose(), text.getAbout(),
                            JOptionPane.INFORMATION_MESSAGE));
                menuHelp.add(menuItemRules);
                menuHelp.add(menuItemSettings);
                menuHelp.add(menuItemAbout);
                menuBar.add(menuHelp);

                frame.setJMenuBar(menuBar);

                //Content Pane with background image.
                panelBackground = new PanelBackground(background);
                frame.setContentPane(panelBackground);

                //Layout manager.
                gridBagLayout = new GridBagLayout();
                frame.getContentPane().setLayout(gridBagLayout);

                //Previous frames.
                previousFrames = new ArrayList<>();
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
                LineBorder border = new LineBorder(Color.BLACK, 2);
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
                buttonRules.addActionListener(e->showFrameRules());
                buttonSettings.addActionListener(e->showFrameSettings());
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void initializeFrameGameMode()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
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
                LineBorder border = new LineBorder(Color.BLACK, 2);
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
                    game.start();
                    showFrameBoard();
                });
                buttonBackGameMode.addActionListener(e->showFrameMainMenu());
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void initializeFrameBoard()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Panel Board Interactive.
                panelBoardInteractive = new PanelBoardInteractive();
                panelBoardInteractive.setOpaque(false);
                panelBoardInteractive.addMouseListener(panelBoardInteractive);

                //Status Bar.
                statusBar = new JTextField(1);
                statusBar.setDisabledTextColor(Color.BLACK);
                statusBar.setEnabled(false);
                statusBar.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                                                       new EmptyBorder(5,5,5,5)));
                statusBar.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));

                //Layout manager constraints.
                constraintsForPanelBoardInteractive = new GridBagConstraints
                        (0, 0,1,1,1,1,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0,0,0,0), 0,0);
                constraintsForStatusBar = new GridBagConstraints
                        (0, 1,1,1,0,0,
                                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                                new Insets(0,0,0,0), 0,0);
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void initializeFrameRules()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Panel Board Rules.
                panelBoardRules = new PanelBoardRules(selectionPalace, selectionRiver);
                panelBoardRules.setOpaque(false);

                //Combo box.
                comboBoxRules = new JComboBox<>();
                comboBoxRules.addItem(text.getGoal());
                comboBoxRules.addItem(text.getPalace());
                comboBoxRules.addItem(text.getRiver());
                comboBoxRules.addItem(text.getGeneral());
                comboBoxRules.addItem(text.getAdvisor());
                comboBoxRules.addItem(text.getElephant());
                comboBoxRules.addItem(text.getHorse());
                comboBoxRules.addItem(text.getChariot());
                comboBoxRules.addItem(text.getCannon());
                comboBoxRules.addItem(text.getSoldier());

                //Button back.
                buttonBackRules = new JButton(text.getBack());

                //Text area and Scroll pane.
                textAreaRules = new JTextArea(2, 1);
                textAreaRules.setMargin(new Insets(5,5,5,5));
                textAreaRules.setDisabledTextColor(Color.BLACK);
                textAreaRules.setEnabled(false);
                textAreaRules.setLineWrap(true);
                textAreaRules.setWrapStyleWord(true);
                textAreaRules.setText(text.getGoalRule());
                textAreaRules.setCaretPosition(0);
                scrollPaneRules = new JScrollPane(textAreaRules,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                //Preferred Size.
                comboBoxRules.setPreferredSize(new Dimension(120,40));
                buttonBackRules.setPreferredSize(new Dimension(80, 40));
                scrollPaneRules.setPreferredSize(new Dimension(1, 65));

                //Layout manager constraints.
                constraintsForPanelBoardRules = new GridBagConstraints
                        (0, 0,2,1,1,1,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0,0,0,0), 0,0);
                constraintsForComboBoxRules = new GridBagConstraints
                        (0, 1,1,1,0,0,
                                GridBagConstraints.WEST, GridBagConstraints.NONE,
                                new Insets(10,10,10,10), 0,0);
                constraintsForButtonBackRules = new GridBagConstraints
                        (1, 1,1,1,0,0,
                                GridBagConstraints.WEST, GridBagConstraints.NONE,
                                new Insets(10,0,10,0), 0,0);
                constraintsForScrollPaneRules = new GridBagConstraints
                        (0, 2,2,1,0,0,
                                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                                new Insets(0,0,0,0), 0,0);

                //Background Color.
                buttonBackRules.setBackground(Color.WHITE);

                //Border.
                LineBorder border = new LineBorder(Color.BLACK, 1);
                buttonBackRules.setBorder(border);
                scrollPaneRules.setBorder(border);

                //Font.
                Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
                comboBoxRules.setFont(font);
                buttonBackRules.setFont(font);
                textAreaRules.setFont(font);

                //Action listeners.
                comboBoxRules.addActionListener(e->
                {
                    int index = comboBoxRules.getSelectedIndex();
                    switch(index)
                    {
                        case 0 ->
                        {
                            rules.setGridForGoalRule();
                            textAreaRules.setText(text.getGoalRule());
                        }
                        case 1 ->
                        {
                            rules.setGridForPalaceRule();
                            textAreaRules.setText(text.getPalaceRule());
                        }
                        case 2 ->
                        {
                            rules.setGridForRiverRule();
                            textAreaRules.setText(text.getRiverRule());
                        }
                        case 3 ->
                        {
                            rules.setGridForGeneralRule();
                            textAreaRules.setText(text.getGeneralRule());
                        }
                        case 4 ->
                        {
                            rules.setGridForAdvisorRule();
                            textAreaRules.setText(text.getAdvisorRule());
                        }
                        case 5 ->
                        {
                            rules.setGridForElephantRule();
                            textAreaRules.setText(text.getElephantRule());
                        }
                        case 6 ->
                        {
                            rules.setGridForHorseRule();
                            textAreaRules.setText(text.getHorseRule());
                        }
                        case 7 ->
                        {
                            rules.setGridForChariotRule();
                            textAreaRules.setText(text.getChariotRule());
                        }
                        case 8 ->
                        {
                            rules.setGridForCannonRule();
                            textAreaRules.setText(text.getCannonRule());
                        }
                        case 9 ->
                        {
                            rules.setGridForSoldierRule();
                            textAreaRules.setText(text.getSoldierRule());
                        }
                    }
                    textAreaRules.setCaretPosition(0);
                    repaint();
                });
                buttonBackRules.addActionListener(e->showPreviousFrame());
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void initializeFrameSettings()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Language.
                labelLanguage = new JLabel(text.getLanguage());
                comboBoxLanguage = new JComboBox<>();
                comboBoxLanguage.addItem("English");
                comboBoxLanguage.addItem("Русский");

                //Music.
                labelMusic = new JLabel(text.getMusic());
                buttonMuteMusic = new JToggleButton(iconUnmuted, false);
                buttonMuteMusic.setSelectedIcon(iconMuted);
                buttonMuteMusic.setOpaque(false);
                sliderGainMusic = new JSlider();
                sliderGainMusic.setOpaque(false);

                //Sfx.
                labelSfx = new JLabel(text.getSfx());
                buttonMuteSfx = new JToggleButton(iconUnmuted, false);
                buttonMuteSfx.setSelectedIcon(iconMuted);
                buttonMuteSfx.setOpaque(false);
                sliderGainSfx = new JSlider();
                sliderGainSfx.setOpaque(false);

                //Buttons.
                buttonApply = new JButton(text.getApply());
                buttonBackSettings = new JButton(text.getBack());

                //Preferred Size.
                labelLanguage.setPreferredSize(new Dimension(300,100));
                comboBoxLanguage.setPreferredSize(new Dimension(300,80));
                labelMusic.setPreferredSize(new Dimension(300,100));
                buttonMuteMusic.setPreferredSize(new Dimension(60,60));
                sliderGainMusic.setPreferredSize(new Dimension(270,20));
                labelSfx.setPreferredSize(new Dimension(300,100));
                buttonMuteSfx.setPreferredSize(new Dimension(60,60));
                sliderGainSfx.setPreferredSize(new Dimension(270,20));
                buttonApply.setPreferredSize(new Dimension(230,100));
                buttonBackSettings.setPreferredSize(new Dimension(200,100));

                //Layout manager constraints.
                constraintsForLabelLanguage = new GridBagConstraints
                        (0, 0,1,1,0,0,
                                GridBagConstraints.EAST, GridBagConstraints.NONE,
                                new Insets(0,30,0,30), 0,0);
                constraintsForComboBoxLanguage = new GridBagConstraints
                        (1, 0,2,1,0,0,
                                GridBagConstraints.WEST, GridBagConstraints.NONE,
                                new Insets(0,30,10,30), 0,0);
                constraintsForLabelMusic = new GridBagConstraints
                        (0, 1,1,1,0,0,
                                GridBagConstraints.EAST, GridBagConstraints.NONE,
                                new Insets(0,30,0,30), 0,0);
                constraintsForButtonMuteMusic = new GridBagConstraints
                        (1, 1,1,1,0,0,
                                GridBagConstraints.WEST, GridBagConstraints.NONE,
                                new Insets(0,30,20,30), 0,0);
                constraintsForSliderGainMusic = new GridBagConstraints
                        (2, 1,1,1,0,0,
                                GridBagConstraints.EAST, GridBagConstraints.NONE,
                                new Insets(0,0,0,30), 0,0);
                constraintsForLabelSfx = new GridBagConstraints
                        (0, 2,1,1,0,0,
                                GridBagConstraints.EAST, GridBagConstraints.NONE,
                                new Insets(0,30,0,30), 0,0);
                constraintsForButtonMuteSfx = new GridBagConstraints
                        (1, 2,1,1,0,0,
                                GridBagConstraints.WEST, GridBagConstraints.NONE,
                                new Insets(0,30,20,30), 0,0);
                constraintsForSliderGainSfx = new GridBagConstraints
                        (2, 2,1,1,0,0,
                                GridBagConstraints.EAST, GridBagConstraints.NONE,
                                new Insets(0,0,0,30), 0,0);
                constraintsForButtonApply = new GridBagConstraints
                        (0, 3,1,1,0,0,
                                GridBagConstraints.WEST, GridBagConstraints.NONE,
                                new Insets(80,30,0,30), 0,0);
                constraintsForButtonBackSettings = new GridBagConstraints
                        (2, 3,1,1,0,0,
                                GridBagConstraints.EAST, GridBagConstraints.NONE,
                                new Insets(80,30,0,30), 0,0);

                //Background Color.
                buttonMuteMusic.setBackground(Color.WHITE);
                buttonMuteSfx.setBackground(Color.WHITE);
                buttonApply.setBackground(Color.WHITE);
                buttonBackSettings.setBackground(Color.WHITE);

                //Border.
                LineBorder border = new LineBorder(Color.BLACK, 2);
                LineBorder borderSmall = new LineBorder(Color.BLACK, 1);
                buttonMuteMusic.setBorder(borderSmall);
                buttonMuteSfx.setBorder(borderSmall);
                buttonApply.setBorder(border);
                buttonBackSettings.setBorder(border);

                //Font.
                Font font = fontChinese.deriveFont(Font.BOLD, 46.f);
                Font fontSmall = fontChinese.deriveFont(Font.BOLD, 37.f);
                labelLanguage.setFont(fontSmall);
                comboBoxLanguage.setFont(fontSmall);
                labelMusic.setFont(fontSmall);
                labelSfx.setFont(fontSmall);
                buttonApply.setFont(font);
                buttonBackSettings.setFont(font);

                //Action listeners.
                comboBoxLanguage.addActionListener(e->refreshText());
                buttonMuteMusic.addActionListener(e->
                {
                    JToggleButton buttonSource = (JToggleButton)e.getSource();
                    boolean selected = buttonSource.isSelected();
                    if(selected == true)
                    {
                        musicPlayer.muteMusic();
                    }
                    else //selected == false
                    {
                        musicPlayer.unmuteMusic();
                    }
                });
                sliderGainMusic.addChangeListener(e->
                {
                    JSlider sliderSource = (JSlider)e.getSource();
                    if(sliderSource.getValueIsAdjusting()==false)
                    {
                        int sliderValue = sliderSource.getValue();
                        musicPlayer.setGainMusicDb(sliderValue);
                    }
                });
                buttonMuteSfx.addActionListener(e->
                {
                    JToggleButton buttonSource = (JToggleButton)e.getSource();
                    boolean selected = buttonSource.isSelected();
                    if(selected == true)
                    {
                        musicPlayer.muteSfx();
                    }
                    else //selected == false
                    {
                        musicPlayer.unmuteSfx();
                    }
                });
                sliderGainSfx.addChangeListener(e->
                {
                    JSlider sliderSource = (JSlider)e.getSource();
                    if(sliderSource.getValueIsAdjusting()==false)
                    {
                        int sliderValue = sliderSource.getValue();
                        musicPlayer.setGainSfxDb(sliderValue);
                    }
                });
                //buttonApply.addActionListener(e->); //TODO: Implement. Возможно, кнопка понадобится для сервера.
                buttonBackSettings.addActionListener(e->showPreviousFrame());
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setGame(Game game)
    {
        this.game = game;
        panelBoardInteractive.setGame(game);
    }
    public void setRules(Rules rules)
    {
        this.rules = rules;
        panelBoardRules.setRules(rules);

        try
        {
            SwingUtilities.invokeAndWait(()->comboBoxRules.setSelectedIndex(0));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setMusicPlayer(MusicPlayer musicPlayer)
    {
        this.musicPlayer = musicPlayer;

        //Slider gain music.
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                sliderGainMusic.setMinimum(musicPlayer.getGainMusicPercentMinimum());
                sliderGainMusic.setMaximum(musicPlayer.getGainMusicPercentMaximum());
                sliderGainMusic.setValue(musicPlayer.getGainMusicPercentCurrent());
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Slider gain sfx.
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                sliderGainSfx.setMinimum(musicPlayer.getGainSfxPercentMinimum());
                sliderGainSfx.setMaximum(musicPlayer.getGainSfxPercentMaximum());
                sliderGainSfx.setValue(musicPlayer.getGainSfxPercentCurrent());
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void showFrameAndWarnings()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                showFrameMainMenu();

                frame.setVisible(true);

                if(resourcesMissing==true)
                {
                    JOptionPane.showMessageDialog(frame,
                            text.getSomeResourcesAreMissing(), text.getGuiWarning(), JOptionPane.WARNING_MESSAGE);
                }

                if(musicPlayer.getResourcesMissing()==true)
                {
                    JOptionPane.showMessageDialog(frame,
                            text.getSomeResourcesAreMissing(), text.getMusicPlayerWarning(), JOptionPane.WARNING_MESSAGE);
                }

                if((musicPlayer.getLineMusicAvailable()==false) ||
                        (musicPlayer.getMuteMusicAvailable()==false) ||
                        (musicPlayer.getGainMusicAvailable()==false) ||
                        (musicPlayer.getLineSfxAvailable()==false)   ||
                        (musicPlayer.getMuteSfxAvailable()==false)   ||
                        (musicPlayer.getGainSfxAvailable()==false))
                {
                    JOptionPane.showMessageDialog(frame,
                            text.getSomeFeaturesAreNotAvailable(), text.getMusicPlayerWarning(), JOptionPane.WARNING_MESSAGE);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void showFrameMainMenu()
    {
        addToPreviousFrames(FrameType.MAIN_MENU);

        SwingUtilities.invokeLater(()->
        {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(buttonPlay, constraintsForButtonPlay);
            frame.getContentPane().add(buttonLoad, constraintsForButtonLoad);
            frame.getContentPane().add(buttonRules, constraintsForButtonRules);
            frame.getContentPane().add(buttonSettings, constraintsForButtonSettings);
            repaint();
        });
    }
    private void showFrameGameMode()
    {
        addToPreviousFrames(FrameType.GAME_MODE);

        SwingUtilities.invokeLater(()->
        {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(buttonSinglePlayer, constraintsForButtonSinglePlayer);
            frame.getContentPane().add(buttonLocalMultiplayer, constraintsForButtonLocalMultiplayer);
            frame.getContentPane().add(buttonOnlineMultiplayer, constraintsForButtonOnlineMultiplayer);
            frame.getContentPane().add(buttonBackGameMode, constraintsForButtonBackGameMode);
            repaint();
        });
    }
    private void showFrameBoard()
    {
        addToPreviousFrames(FrameType.BOARD);

        SwingUtilities.invokeLater(()->
        {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelBoardInteractive, constraintsForPanelBoardInteractive);
            frame.getContentPane().add(statusBar, constraintsForStatusBar);
            repaint();
        });
    }
    private void showFrameRules()
    {
        addToPreviousFrames(FrameType.RULES);

        SwingUtilities.invokeLater(()->
        {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelBoardRules, constraintsForPanelBoardRules);
            frame.getContentPane().add(comboBoxRules, constraintsForComboBoxRules);
            frame.getContentPane().add(buttonBackRules, constraintsForButtonBackRules);
            frame.getContentPane().add(scrollPaneRules, constraintsForScrollPaneRules);
            repaint();
        });
    }
    private void showFrameSettings()
    {
        addToPreviousFrames(FrameType.SETTINGS);

        SwingUtilities.invokeLater(()->
        {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(labelLanguage, constraintsForLabelLanguage);
            frame.getContentPane().add(comboBoxLanguage, constraintsForComboBoxLanguage);
            frame.getContentPane().add(labelMusic, constraintsForLabelMusic);
            frame.getContentPane().add(buttonMuteMusic, constraintsForButtonMuteMusic);
            frame.getContentPane().add(sliderGainMusic, constraintsForSliderGainMusic);
            frame.getContentPane().add(labelSfx, constraintsForLabelSfx);
            frame.getContentPane().add(buttonMuteSfx, constraintsForButtonMuteSfx);
            frame.getContentPane().add(sliderGainSfx, constraintsForSliderGainSfx);
            frame.getContentPane().add(buttonBackSettings, constraintsForButtonBackSettings);
            frame.getContentPane().add(buttonApply, constraintsForButtonApply);
            repaint();
        });
    }
    public void repaint()
    {
        SwingUtilities.invokeLater(()->
        {
            frame.revalidate();
            frame.repaint();
        });
    }
    private void addToPreviousFrames(FrameType currentFrame)
    {
        int size = previousFrames.size();
        if(size==0)
        {
            previousFrames.add(currentFrame);
        }
        else if(size>0)
        {
            FrameType previousFrame = previousFrames.get(size-1);
            if(currentFrame != previousFrame)
            {
                previousFrames.add(currentFrame);
            }
        }
    }
    private void showPreviousFrame()
    {
        int size;

        size = previousFrames.size();
        if(size>0)
        {
            previousFrames.remove(size-1); //Skip current frame.
            size = previousFrames.size();
            if(size>0)
            {
                FrameType previousFrame = previousFrames.get(size-1);
                previousFrames.remove(size-1);
                switch(previousFrame)
                {
                    case MAIN_MENU -> showFrameMainMenu();
                    case GAME_MODE -> showFrameGameMode();
                    case BOARD -> showFrameBoard();
                    case RULES -> showFrameRules();
                    case SETTINGS -> showFrameSettings();
                }
            }
        }
    }
    private void refreshText()
    {
        SwingUtilities.invokeLater(()->
        {
            int index = comboBoxLanguage.getSelectedIndex();
            switch (index)
            {
                case 0 -> text = textEnglish;
                case 1 -> text = textRussian;
            }

            //Common frame features.
            frame.setTitle(text.getTitle());
            menuNavigation.setText(text.getNavigation());
            menuItemMainMenu.setText(text.getMainMenu());
            menuHelp.setText(text.getHelp());
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

            //Frame Rules.
            index = comboBoxRules.getSelectedIndex();
            comboBoxRules.removeAllItems();
            comboBoxRules.addItem(text.getGoal());
            comboBoxRules.addItem(text.getPalace());
            comboBoxRules.addItem(text.getRiver());
            comboBoxRules.addItem(text.getGeneral());
            comboBoxRules.addItem(text.getAdvisor());
            comboBoxRules.addItem(text.getElephant());
            comboBoxRules.addItem(text.getHorse());
            comboBoxRules.addItem(text.getChariot());
            comboBoxRules.addItem(text.getCannon());
            comboBoxRules.addItem(text.getSoldier());
            comboBoxRules.setSelectedIndex(index);

            //Frame Settings.
            labelLanguage.setText(text.getLanguage());
            labelMusic.setText(text.getMusic());
            labelSfx.setText(text.getSfx());
            buttonBackSettings.setText(text.getBack());
            buttonApply.setText(text.getApply());

            //Game.
            game.refreshText(text);
        });
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
    public void setStatusBarText(String message)
    {
        SwingUtilities.invokeLater(() -> statusBar.setText(message));
    }
}