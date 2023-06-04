package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.*;
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
    private final Font fontChinese;
    private final BufferedImage iconFrame;
    private final BufferedImage background;
    private final BufferedImage generalRedImage;
    private final BufferedImage advisorRedImage;
    private final BufferedImage elephantRedImage;
    private final BufferedImage horseRedImage;
    private final BufferedImage chariotRedImage;
    private final BufferedImage cannonRedImage;
    private final BufferedImage soldierRedImage;
    private final BufferedImage generalBlackImage;
    private final BufferedImage advisorBlackImage;
    private final BufferedImage elephantBlackImage;
    private final BufferedImage horseBlackImage;
    private final BufferedImage chariotBlackImage;
    private final BufferedImage cannonBlackImage;
    private final BufferedImage soldierBlackImage;
    private final BufferedImage selection;
    private final BufferedImage selectionPalace;
    private final BufferedImage selectionRiver;
    private final ImageIcon iconPreviousMove;
    private final ImageIcon iconSlower;
    private final ImageIcon iconPlay;
    private final ImageIcon iconPause;
    private final ImageIcon iconStop;
    private final ImageIcon iconFaster;
    private final ImageIcon iconNextMove;
    private final ImageIcon iconUnmuted;
    private final ImageIcon iconMuted;

    //Images of figures.
    private final HashMap<Class<? extends Figure>, BufferedImage> imagesOfFigures;
    //Previous frames.
    private final ArrayList<FrameType> previousFrames;

    //Common frame features.
    private JFrame frame;
    private PanelBackground panelBackground;
    private GridBagLayout gridBagLayout;
    private JFileChooser fileChooser;
    private JMenuBar menuBar;
    private JMenu menuNavigation;
    private JMenuItem menuItemMainMenu;
    private JMenu menuReplay;
    private JMenuItem menuItemSaveReplay;
    private JMenu menuHelp;
    private JMenuItem menuItemRules;
    private JMenuItem menuItemSettings;
    private JMenuItem menuItemAbout;

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
    private PanelBoardInteractive panelBoardInteractive;
    private JTextField statusBar;
    private GridBagConstraints constraintsForPanelBoardInteractive;
    private GridBagConstraints constraintsForStatusBar;

    //Frame Replay.
    private PanelBoard panelBoardReplay;
    private JButton buttonPreviousMove;
    private JButton buttonSlower;
    private JToggleButton buttonPlayPause;
    private JButton buttonStop;
    private JButton buttonFaster;
    private JButton buttonNextMove;
    private GridBagConstraints constraintsForPanelBoardReplay;
    private GridBagConstraints constraintsForButtonPreviousMove;
    private GridBagConstraints constraintsForButtonSlower;
    private GridBagConstraints constraintsForButtonPlayPause;
    private GridBagConstraints constraintsForButtonStop;
    private GridBagConstraints constraintsForButtonFaster;
    private GridBagConstraints constraintsForButtonNextMove;
    private Timer timer;

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
    private GameSinglePlayer gameSinglePlayer;
    private GameLocalMultiplayer gameLocalMultiplayer;

    //Replay.
    private Replay replay;

    //Rules.
    private Rules rules;

    //Music player.
    private MusicPlayer musicPlayer;

    public GUI()
    {
        //Text
        textEnglish = new TextEnglish();
        textRussian = new TextRussian();
        text = textEnglish;

        //Resources
        resourcesMissing = false;
        fontChinese = initializeFontChinese();
        iconFrame = initializeIconFrame();
        background = initializeBackground();
        generalRedImage = initializeGeneralRedImage();
        advisorRedImage = initializeAdvisorRedImage();
        elephantRedImage = initializeElephantRedImage();
        horseRedImage = initializeHorseRedImage();
        chariotRedImage = initializeChariotRedImage();
        cannonRedImage = initializeCannonRedImage();
        soldierRedImage = initializeSoldierRedImage();
        generalBlackImage = initializeGeneralBlackImage();
        advisorBlackImage = initializeAdvisorBlackImage();
        elephantBlackImage = initializeElephantBlackImage();
        horseBlackImage = initializeHorseBlackImage();
        chariotBlackImage = initializeChariotBlackImage();
        cannonBlackImage = initializeCannonBlackImage();
        soldierBlackImage = initializeSoldierBlackImage();
        selection = initializeSelection();
        selectionPalace = initializeSelectionPalace();
        selectionRiver = initializeSelectionRiver();
        iconPreviousMove = initializeIconPreviousMove();
        iconSlower = initializeIconSLower();
        iconPlay = initializeIconPlay();
        iconPause = initializeIconPause();
        iconStop = initializeIconStop();
        iconFaster = initializeIconFaster();
        iconNextMove = initializeIconNextMove();
        iconUnmuted = initializeIconUnmuted();
        iconMuted = initializeIconMuted();

        imagesOfFigures = initializeImagesOfFigures();
        previousFrames = new ArrayList<>();

        initializeCommonFrameFeatures();
        initializeFrameMainMenu();
        initializeFrameGameMode();
        initializeFrameBoard();
        initializeFrameReplay();
        initializeFrameRules();
        initializeFrameSettings();
    }
    private Font initializeFontChinese()
    {
        Font fontChinese;
        URL url = getClass().getResource("/kashimarusbycop.otf");
        //noinspection DataFlowIssue
        try (InputStream inputStream = url.openStream())
        {
            fontChinese = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            fontChinese = new Font(Font.DIALOG, Font.PLAIN, 1);
        }
        return fontChinese;
    }
    private BufferedImage initializeIconFrame()
    {
        BufferedImage iconFrame;
        URL url = getClass().getResource("/icon.jpg");
        try
        {
            //noinspection DataFlowIssue
            iconFrame = ImageIO.read(url);
        } catch (Exception e)
        {
            resourcesMissing = true;
            iconFrame = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = iconFrame.createGraphics();
            g2d.setColor(new Color(196, 185, 165));
            g2d.fillRect(0,0,100,100);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 42));
            g2d.drawString("象棋", 5, 65);
        }
        return iconFrame;
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
    private BufferedImage initializeGeneralRedImage()
    {
        BufferedImage generalRedImage;
        URL url = getClass().getResource("/generalRed.png");
        try
        {
            //noinspection DataFlowIssue
            generalRedImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            generalRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(generalRedImage, Color.RED, "帥");
        }
        return generalRedImage;
    }
    private BufferedImage initializeAdvisorRedImage()
    {
        BufferedImage advisorRedImage;
        URL url = getClass().getResource("/advisorRed.png");
        try
        {
            //noinspection DataFlowIssue
            advisorRedImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            advisorRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(advisorRedImage, Color.RED, "仕");
        }
        return advisorRedImage;
    }
    private BufferedImage initializeElephantRedImage()
    {
        BufferedImage elephantRedImage;
        URL url = getClass().getResource("/elephantRed.png");
        try
        {
            //noinspection DataFlowIssue
            elephantRedImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            elephantRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(elephantRedImage, Color.RED, "相");
        }
        return elephantRedImage;
    }
    private BufferedImage initializeHorseRedImage()
    {
        BufferedImage horseRedImage;
        URL url = getClass().getResource("/horseRed.png");
        try
        {
            //noinspection DataFlowIssue
            horseRedImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            horseRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(horseRedImage, Color.RED, "傌");
        }
        return horseRedImage;
    }
    private BufferedImage initializeChariotRedImage()
    {
        BufferedImage chariotRedImage;
        URL url = getClass().getResource("/chariotRed.png");
        try
        {
            //noinspection DataFlowIssue
            chariotRedImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            chariotRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(chariotRedImage, Color.RED, "俥");
        }
        return chariotRedImage;
    }
    private BufferedImage initializeCannonRedImage()
    {
        BufferedImage cannonRedImage;
        URL url = getClass().getResource("/cannonRed.png");
        try
        {
            //noinspection DataFlowIssue
            cannonRedImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            cannonRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(cannonRedImage, Color.RED, "炮");
        }
        return cannonRedImage;
    }
    private BufferedImage initializeSoldierRedImage()
    {
        BufferedImage soldierRedImage;
        URL url = getClass().getResource("/soldierRed.png");
        try
        {
            //noinspection DataFlowIssue
            soldierRedImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            soldierRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(soldierRedImage, Color.RED, "兵");
        }
        return soldierRedImage;
    }
    private BufferedImage initializeGeneralBlackImage()
    {
        BufferedImage generalBlackImage;
        URL url = getClass().getResource("/generalBlack.png");
        try
        {
            //noinspection DataFlowIssue
            generalBlackImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            generalBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(generalBlackImage, Color.BLACK, "將");
        }
        return generalBlackImage;
    }
    private BufferedImage initializeAdvisorBlackImage()
    {
        BufferedImage advisorBlackImage;
        URL url = getClass().getResource("/advisorBlack.png");
        try
        {
            //noinspection DataFlowIssue
            advisorBlackImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            advisorBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(advisorBlackImage, Color.BLACK, "士");
        }
        return advisorBlackImage;
    }
    private BufferedImage initializeElephantBlackImage()
    {
        BufferedImage elephantBlackImage;
        URL url = getClass().getResource("/elephantBlack.png");
        try
        {
            //noinspection DataFlowIssue
            elephantBlackImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            elephantBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(elephantBlackImage, Color.BLACK, "象");
        }
        return elephantBlackImage;
    }
    private BufferedImage initializeHorseBlackImage()
    {
        BufferedImage horseBlackImage;
        URL url = getClass().getResource("/horseBlack.png");
        try
        {
            //noinspection DataFlowIssue
            horseBlackImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            horseBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(horseBlackImage, Color.BLACK, "馬");
        }
        return horseBlackImage;
    }
    private BufferedImage initializeChariotBlackImage()
    {
        BufferedImage chariotBlackImage;
        URL url = getClass().getResource("/chariotBlack.png");
        try
        {
            //noinspection DataFlowIssue
            chariotBlackImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            chariotBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(chariotBlackImage, Color.BLACK, "車");
        }
        return chariotBlackImage;
    }
    private BufferedImage initializeCannonBlackImage()
    {
        BufferedImage cannonBlackImage;
        URL url = getClass().getResource("/cannonBlack.png");
        try
        {
            //noinspection DataFlowIssue
            cannonBlackImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            cannonBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(cannonBlackImage, Color.BLACK, "砲");
        }
        return cannonBlackImage;
    }
    private BufferedImage initializeSoldierBlackImage()
    {
        BufferedImage soldierBlackImage;
        URL url = getClass().getResource("/soldierBlack.png");
        try
        {
            //noinspection DataFlowIssue
            soldierBlackImage = ImageIO.read(url);
        }
        catch (Exception e)
        {
            resourcesMissing = true;
            soldierBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(soldierBlackImage, Color.BLACK, "卒");
        }
        return soldierBlackImage;
    }
    private void initializeDefaultFigureImage(BufferedImage figureImage, Color color, String label)
    {
        Graphics2D g2d = figureImage.createGraphics();
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
    private BufferedImage initializeSelection()
    {
        BufferedImage selection;
        URL url = getClass().getResource("/selection.png");
        try
        {
            //noinspection DataFlowIssue
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
        return selection;
    }
    private BufferedImage initializeSelectionPalace()
    {
        BufferedImage selectionPalace;
        URL url = getClass().getResource("/selectionPalace.png");
        try
        {
            //noinspection DataFlowIssue
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
        return selectionPalace;
    }
    private BufferedImage initializeSelectionRiver()
    {
        BufferedImage selectionRiver;
        URL url = getClass().getResource("/selectionRiver.png");
        try
        {
            //noinspection DataFlowIssue
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
        return selectionRiver;
    }
    private ImageIcon initializeIconPreviousMove()
    {
        BufferedImage imagePreviousMove = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imagePreviousMove.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.fillPolygon(new Polygon(new int[]{15,50,50,15}, new int[] {30,10,50,30}, 4));
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(15, 50, 15, 10);
        return new ImageIcon(imagePreviousMove);
    }
    private ImageIcon initializeIconSLower()
    {
        BufferedImage imageSlower = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageSlower.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.fillPolygon(new Polygon(new int[]{7,32,32,47,47,32,32,7}, new int[] {30,10,20,10,50,40,50,30}, 8));
        return new ImageIcon(imageSlower);
    }
    private ImageIcon initializeIconPlay()
    {
        BufferedImage imagePlay = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imagePlay.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.fillPolygon(new Polygon(new int[]{13,48,13,13}, new int[] {10,30,50,10}, 4));
        return new ImageIcon(imagePlay);
    }
    private ImageIcon initializeIconPause()
    {
        BufferedImage imagePause = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imagePause.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(20, 13, 20, 47);
        g2d.drawLine(40, 13, 40, 47);
        return new ImageIcon(imagePause);
    }
    private ImageIcon initializeIconStop()
    {
        BufferedImage imageStop = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageStop.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.fillPolygon(new Polygon(new int[]{12,48,48,12,12}, new int[] {12,12,48,48,12}, 5));
        return new ImageIcon(imageStop);
    }
    private ImageIcon initializeIconFaster()
    {
        BufferedImage imageFaster = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageFaster.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.fillPolygon(new Polygon(new int[]{13,28,28,53,28,28,13,13}, new int[] {10,20,10,30,50,40,50,10}, 8));
        return new ImageIcon(imageFaster);
    }
    private ImageIcon initializeIconNextMove()
    {
        BufferedImage imageNextMove = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageNextMove.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.fillPolygon(new Polygon(new int[]{10,45,10,10}, new int[] {10,30,50,10}, 4));
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(45, 50, 45, 10);
        return new ImageIcon(imageNextMove);
    }
    private ImageIcon initializeIconUnmuted()
    {
        BufferedImage imageUnmuted = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageUnmuted.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawPolygon(new Polygon(new int[]{10,23,45,45,23,10,10}, new int[] {23,23,10,50,37,37,23}, 7));
        g2d.drawLine(23, 23, 23, 37);
        return new ImageIcon(imageUnmuted);
    }
    private ImageIcon initializeIconMuted()
    {
        BufferedImage imageMuted = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageMuted.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawPolygon(new Polygon(new int[]{10,23,45,45,23,10,10}, new int[] {23,23,10,50,37,37,23}, 7));
        g2d.drawLine(23, 23, 23, 37);
        g2d.setColor(Color.RED);
        g2d.drawLine(5, 5, 55, 55);
        g2d.drawLine(5, 55, 55, 5);
        return new ImageIcon(imageMuted);
    }
    private HashMap<Class<? extends Figure>, BufferedImage> initializeImagesOfFigures()
    {
        HashMap<Class<? extends Figure>, BufferedImage> imagesOfFigures = new HashMap<>();

        //Red figures.
        imagesOfFigures.put(GeneralRed.class, generalRedImage);
        imagesOfFigures.put(AdvisorRed.class, advisorRedImage);
        imagesOfFigures.put(ElephantRed.class, elephantRedImage);
        imagesOfFigures.put(HorseRed.class, horseRedImage);
        imagesOfFigures.put(ChariotRed.class, chariotRedImage);
        imagesOfFigures.put(CannonRed.class, cannonRedImage);
        imagesOfFigures.put(SoldierRed.class, soldierRedImage);

        //Black figures.
        imagesOfFigures.put(GeneralBlack.class, generalBlackImage);
        imagesOfFigures.put(AdvisorBlack.class, advisorBlackImage);
        imagesOfFigures.put(ElephantBlack.class, elephantBlackImage);
        imagesOfFigures.put(HorseBlack.class, horseBlackImage);
        imagesOfFigures.put(ChariotBlack.class, chariotBlackImage);
        imagesOfFigures.put(CannonBlack.class, cannonBlackImage);
        imagesOfFigures.put(SoldierBlack.class, soldierBlackImage);

        return imagesOfFigures;
    }
    private void initializeCommonFrameFeatures()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Frame.
                frame = new JFrame(text.getTitle());
                frame.setMinimumSize(new Dimension(800,800));
                frame.setBounds((int)frame.getGraphicsConfiguration().getBounds().getCenterX() -
                                  (int)frame.getBounds().getCenterX(),
                                (int)frame.getGraphicsConfiguration().getBounds().getCenterY() -
                                  (int)frame.getBounds().getCenterY(),
                             800,800);
                frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
                frame.setIconImage(iconFrame);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //Panel Background.
                panelBackground = new PanelBackground(background);
                frame.setContentPane(panelBackground);

                //GridBag layout manager.
                gridBagLayout = new GridBagLayout();
                frame.getContentPane().setLayout(gridBagLayout);

                //File Chooser.
                fileChooser = new JFileChooser();
                try
                {
                    //Set a directory where this program was invoked from as a default directory of a File Chooser.
                    String currentDirectoryPath = getClass().getProtectionDomain()
                                                            .getCodeSource()
                                                            .getLocation()
                                                            .toURI()
                                                            .getPath();
                    File currentDirectoryFile = new File(currentDirectoryPath);
                    fileChooser.setCurrentDirectory(currentDirectoryFile);
                }
                catch(Exception e)
                {
                    fileChooser.setCurrentDirectory(null);
                }
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Chinese Chess Replay .ccrpl",
                                                                      "ccrpl"));

                //Menu Bar.
                menuBar = new JMenuBar();
                //Menu Navigation.
                menuNavigation = new JMenu(text.getNavigation());
                menuItemMainMenu = new JMenuItem(text.getMainMenu());
                menuItemMainMenu.addActionListener(e-> showDialogExitToMainMenu());
                menuNavigation.add(menuItemMainMenu);
                //Menu Replay.
                menuReplay = new JMenu(text.getReplay());
                menuItemSaveReplay = new JMenuItem(text.getSaveReplay());
                menuItemSaveReplay.addActionListener(e-> showDialogSaveReplay());
                menuReplay.add(menuItemSaveReplay);
                //Menu Help.
                menuHelp = new JMenu(text.getHelp());
                menuItemRules = new JMenuItem(text.getRules());
                menuItemRules.addActionListener(e->showFrameRules());
                menuItemSettings = new JMenuItem(text.getSettings());
                menuItemSettings.addActionListener(e->showFrameSettings());
                menuItemAbout = new JMenuItem(text.getAbout());
                menuItemAbout.addActionListener(e-> showDialogAbout());
                menuHelp.add(menuItemRules);
                menuHelp.add(menuItemSettings);
                menuHelp.add(menuItemAbout);

                menuBar.add(menuNavigation);
                menuBar.add(menuReplay);
                menuBar.add(menuHelp);
                frame.setJMenuBar(menuBar);
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
                //Button Play.
                buttonPlay = new JButton(text.getPlay());
                buttonPlay.setPreferredSize(new Dimension(300, 100));
                buttonPlay.setBackground(Color.WHITE);
                buttonPlay.setBorder(new LineBorder(Color.BLACK, 2));
                buttonPlay.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonPlay = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonPlay.addActionListener(e->showFrameGameMode());

                //Button Load.
                buttonLoad = new JButton(text.getLoad());
                buttonLoad.setPreferredSize(new Dimension(300, 100));
                buttonLoad.setBackground(Color.WHITE);
                buttonLoad.setBorder(new LineBorder(Color.BLACK, 2));
                buttonLoad.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonLoad = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonLoad.addActionListener(e->showDialogLoadReplay());

                //Button Rules.
                buttonRules = new JButton(text.getRules());
                buttonRules.setPreferredSize(new Dimension(300, 100));
                buttonRules.setBackground(Color.WHITE);
                buttonRules.setBorder(new LineBorder(Color.BLACK, 2));
                buttonRules.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonRules = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonRules.addActionListener(e->showFrameRules());

                //Button Settings.
                buttonSettings = new JButton(text.getSettings());
                buttonSettings.setPreferredSize(new Dimension(300, 100));
                buttonSettings.setBackground(Color.WHITE);
                buttonSettings.setBorder(new LineBorder(Color.BLACK, 2));
                buttonSettings.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonSettings = new GridBagConstraints(
                        0, 3, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
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
                //Button Single Player.
                buttonSinglePlayer = new JButton(text.getSinglePlayer());
                buttonSinglePlayer.setPreferredSize(new Dimension(500, 100));
                buttonSinglePlayer.setBackground(Color.WHITE);
                buttonSinglePlayer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonSinglePlayer.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonSinglePlayer = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonSinglePlayer.addActionListener(e->startSinglePlayerGame());

                //Button Local Multiplayer.
                buttonLocalMultiplayer = new JButton(text.getLocalMultiplayer());
                buttonLocalMultiplayer.setPreferredSize(new Dimension(500, 100));
                buttonLocalMultiplayer.setBackground(Color.WHITE);
                buttonLocalMultiplayer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonLocalMultiplayer.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonLocalMultiplayer = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonLocalMultiplayer.addActionListener(e->startLocalMultiplayerGame());

                //Button Online Multiplayer.
                buttonOnlineMultiplayer = new JButton(text.getOnlineMultiplayer());
                buttonOnlineMultiplayer.setPreferredSize(new Dimension(500, 100));
                buttonOnlineMultiplayer.setBackground(Color.WHITE);
                buttonOnlineMultiplayer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonOnlineMultiplayer.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonOnlineMultiplayer = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);

                //Button Back.
                buttonBackGameMode = new JButton(text.getBack());
                buttonBackGameMode.setPreferredSize(new Dimension(200, 100));
                buttonBackGameMode.setBackground(Color.WHITE);
                buttonBackGameMode.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackGameMode.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonBackGameMode = new GridBagConstraints(
                        0, 3, 1, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
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
                //Panel Board.
                panelBoardInteractive = new PanelBoardInteractive(imagesOfFigures, selection);
                panelBoardInteractive.setOpaque(false);
                constraintsForPanelBoardInteractive = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);
                panelBoardInteractive.addMouseListener(panelBoardInteractive);

                //Status Bar.
                statusBar = new JTextField(1);
                statusBar.setEnabled(false);
                statusBar.setDisabledTextColor(Color.BLACK);
                statusBar.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                                                       new EmptyBorder(5, 5, 5, 5)));
                statusBar.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                constraintsForStatusBar = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0);
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void initializeFrameReplay()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Panel Board.
                panelBoardReplay = new PanelBoard(imagesOfFigures, selection);
                panelBoardReplay.setOpaque(false);
                constraintsForPanelBoardReplay = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Button Previous Move.
                buttonPreviousMove = new JButton(iconPreviousMove);
                buttonPreviousMove.setPreferredSize(new Dimension(60, 60));
                buttonPreviousMove.setOpaque(false);
                buttonPreviousMove.setBackground(Color.WHITE);
                buttonPreviousMove.setBorder(new LineBorder(Color.BLACK, 2));
                constraintsForButtonPreviousMove = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 0, 10, 450), 0, 0);
                buttonPreviousMove.addActionListener(e->replay.previousMove());

                //Button Slower.
                buttonSlower = new JButton(iconSlower);
                buttonSlower.setPreferredSize(new Dimension(60, 60));
                buttonSlower.setOpaque(false);
                buttonSlower.setBackground(Color.WHITE);
                buttonSlower.setBorder(new LineBorder(Color.BLACK, 2));
                constraintsForButtonSlower = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 0, 10, 270), 0, 0);
                buttonSlower.addActionListener(e-> replaySlower());

                //Button Play Pause.
                buttonPlayPause = new JToggleButton(iconPlay, false);
                buttonPlayPause.setPreferredSize(new Dimension(60, 60));
                buttonPlayPause.setOpaque(false);
                buttonPlayPause.setBackground(Color.WHITE);
                buttonPlayPause.setBorder(new LineBorder(Color.BLACK, 2));
                buttonPlayPause.setSelectedIcon(iconPause);
                constraintsForButtonPlayPause = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 0, 10, 90), 0, 0);
                buttonPlayPause.addActionListener(this::replayPlayPause);

                //Button Stop.
                buttonStop = new JButton(iconStop);
                buttonStop.setPreferredSize(new Dimension(60, 60));
                buttonStop.setOpaque(false);
                buttonStop.setBackground(Color.WHITE);
                buttonStop.setBorder(new LineBorder(Color.BLACK, 2));
                constraintsForButtonStop = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 90, 10, 0), 0, 0);
                buttonStop.addActionListener(e->replayStop());

                //Button Faster.
                buttonFaster = new JButton(iconFaster);
                buttonFaster.setPreferredSize(new Dimension(60, 60));
                buttonFaster.setOpaque(false);
                buttonFaster.setBackground(Color.WHITE);
                buttonFaster.setBorder(new LineBorder(Color.BLACK, 2));
                constraintsForButtonFaster = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 270, 10, 0), 0, 0);
                buttonFaster.addActionListener(e-> replayFaster());

                //Button Next Move.
                buttonNextMove = new JButton(iconNextMove);
                buttonNextMove.setPreferredSize(new Dimension(60, 60));
                buttonNextMove.setOpaque(false);
                buttonNextMove.setBackground(Color.WHITE);
                buttonNextMove.setBorder(new LineBorder(Color.BLACK, 2));
                constraintsForButtonNextMove = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 450, 10, 0), 0, 0);
                buttonNextMove.addActionListener(e->replay.nextMove());

                //Timer.
                timer = new Timer(500, e->replay.nextMove());
                timer.setInitialDelay(0);
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
                //Panel Board.
                panelBoardRules = new PanelBoardRules(imagesOfFigures, selection, selectionPalace, selectionRiver);
                panelBoardRules.setOpaque(false);
                constraintsForPanelBoardRules = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Combo Box.
                comboBoxRules = new JComboBox<>();
                comboBoxRules.setPreferredSize(new Dimension(135, 40));
                comboBoxRules.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
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
                constraintsForComboBoxRules = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 0, 10, 160), 0, 0);
                comboBoxRules.addActionListener(e->changeDisplayedRule());

                //Button Back.
                buttonBackRules = new JButton(text.getBack());
                buttonBackRules.setPreferredSize(new Dimension(80, 40));
                buttonBackRules.setBackground(Color.WHITE);
                buttonBackRules.setBorder(new LineBorder(Color.BLACK, 1));
                buttonBackRules.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                constraintsForButtonBackRules = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 120, 10, 0), 0, 0);
                buttonBackRules.addActionListener(e->showPreviousFrame());

                //Text Area.
                textAreaRules = new JTextArea(2, 1);
                textAreaRules.setMargin(new Insets(5, 5, 5, 5));
                textAreaRules.setEnabled(false);
                textAreaRules.setDisabledTextColor(Color.BLACK);
                textAreaRules.setLineWrap(true);
                textAreaRules.setWrapStyleWord(true);
                textAreaRules.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                textAreaRules.setText(text.getGoalRule());
                textAreaRules.setCaretPosition(0);

                //Scroll Pane.
                scrollPaneRules = new JScrollPane(textAreaRules,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPaneRules.setPreferredSize(new Dimension(1, 65));
                scrollPaneRules.setBorder(new LineBorder(Color.BLACK, 1));
                constraintsForScrollPaneRules = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0);


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
                //Label Language.
                labelLanguage = new JLabel(text.getLanguage());
                labelLanguage.setPreferredSize(new Dimension(300, 100));
                labelLanguage.setFont(fontChinese.deriveFont(Font.BOLD, 37.f));
                constraintsForLabelLanguage = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(0, 30, 0, 30), 0, 0);

                //ComboBox Language.
                comboBoxLanguage = new JComboBox<>();
                comboBoxLanguage.setPreferredSize(new Dimension(300, 80));
                comboBoxLanguage.setFont(fontChinese.deriveFont(Font.BOLD, 37.f));
                comboBoxLanguage.addItem("English");
                comboBoxLanguage.addItem("Русский");
                constraintsForComboBoxLanguage = new GridBagConstraints(
                        1, 0, 2, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 30, 10, 30), 0, 0);
                comboBoxLanguage.addActionListener(e->refreshText());

                //Label Music.
                labelMusic = new JLabel(text.getMusic());
                labelMusic.setPreferredSize(new Dimension(300, 100));
                labelMusic.setFont(fontChinese.deriveFont(Font.BOLD, 37.f));
                constraintsForLabelMusic = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(0, 30, 0, 30), 0, 0);

                //Button Mute Music.
                buttonMuteMusic = new JToggleButton(iconUnmuted, false);
                buttonMuteMusic.setPreferredSize(new Dimension(60, 60));
                buttonMuteMusic.setOpaque(false);
                buttonMuteMusic.setBackground(Color.WHITE);
                buttonMuteMusic.setBorder(new LineBorder(Color.BLACK, 1));
                buttonMuteMusic.setSelectedIcon(iconMuted);
                constraintsForButtonMuteMusic = new GridBagConstraints(
                        1, 1, 1, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 30, 20, 30), 0, 0);
                buttonMuteMusic.addActionListener(this::muteOrUnmuteMusic);

                //Slider Gain Music.
                sliderGainMusic = new JSlider();
                sliderGainMusic.setPreferredSize(new Dimension(270, 20));
                sliderGainMusic.setOpaque(false);
                constraintsForSliderGainMusic = new GridBagConstraints(
                        2, 1, 1, 1, 0, 0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 30), 0, 0);
                sliderGainMusic.addChangeListener(this::changeGainMusic);

                //Label SFX.
                labelSfx = new JLabel(text.getSfx());
                labelSfx.setPreferredSize(new Dimension(300, 100));
                labelSfx.setFont(fontChinese.deriveFont(Font.BOLD, 37.f));
                constraintsForLabelSfx = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(0, 30, 0, 30), 0, 0);

                //Button Mute SFX.
                buttonMuteSfx = new JToggleButton(iconUnmuted, false);
                buttonMuteSfx.setPreferredSize(new Dimension(60, 60));
                buttonMuteSfx.setOpaque(false);
                buttonMuteSfx.setBackground(Color.WHITE);
                buttonMuteSfx.setBorder(new LineBorder(Color.BLACK, 1));
                buttonMuteSfx.setSelectedIcon(iconMuted);
                constraintsForButtonMuteSfx = new GridBagConstraints(
                        1, 2, 1, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 30, 20, 30), 0, 0);
                buttonMuteSfx.addActionListener(this::muteOrUnmuteSfx);

                //Slider Gain SFX.
                sliderGainSfx = new JSlider();
                sliderGainSfx.setPreferredSize(new Dimension(270, 20));
                sliderGainSfx.setOpaque(false);
                constraintsForSliderGainSfx = new GridBagConstraints(
                        2, 2, 1, 1, 0, 0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 30), 0, 0);
                sliderGainSfx.addChangeListener(this::changeGainSfx);

                //Button Apply.
                buttonApply = new JButton(text.getApply());
                buttonApply.setPreferredSize(new Dimension(230, 100));
                buttonApply.setBackground(Color.WHITE);
                buttonApply.setBorder(new LineBorder(Color.BLACK, 2));
                buttonApply.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonApply = new GridBagConstraints(
                        0, 3, 1, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(80, 30, 0, 30), 0, 0);
                //TODO: Implement. Возможно, кнопка понадобится для сервера.
                //buttonApply.addActionListener(e->);

                //Button Back.
                buttonBackSettings = new JButton(text.getBack());
                buttonBackSettings.setPreferredSize(new Dimension(200, 100));
                buttonBackSettings.setBackground(Color.WHITE);
                buttonBackSettings.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackSettings.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonBackSettings = new GridBagConstraints(
                        2, 3, 1, 1, 0, 0,
                        GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(80, 30, 0, 30), 0, 0);
                buttonBackSettings.addActionListener(e->showPreviousFrame());
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setGameSinglePlayer(GameSinglePlayer gameSinglePlayer)
    {
        this.gameSinglePlayer = gameSinglePlayer;
    }
    public void setGameLocalMultiplayer(GameLocalMultiplayer gameLocalMultiplayer)
    {
        this.gameLocalMultiplayer = gameLocalMultiplayer;
    }
    public void setReplay(Replay replay)
    {
        this.replay = replay;
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

        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                sliderGainMusic.setMinimum(musicPlayer.getGainMusicPercentMinimum());
                sliderGainMusic.setMaximum(musicPlayer.getGainMusicPercentMaximum());
                sliderGainMusic.setValue(musicPlayer.getGainMusicPercentCurrent());

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

                if(resourcesMissing)
                {
                    JOptionPane.showMessageDialog(frame,
                            text.getSomeResourcesAreMissing(), text.getGuiWarning(),
                            JOptionPane.WARNING_MESSAGE);
                }

                if(musicPlayer.getResourcesMissing())
                {
                    JOptionPane.showMessageDialog(frame,
                            text.getSomeResourcesAreMissing(), text.getMusicPlayerWarning(),
                            JOptionPane.WARNING_MESSAGE);
                }

                if((!musicPlayer.getLineMusicAvailable()) ||
                   (!musicPlayer.getMuteMusicAvailable()) ||
                   (!musicPlayer.getGainMusicAvailable()) ||
                   (!musicPlayer.getLineSfxAvailable())   ||
                   (!musicPlayer.getMuteSfxAvailable())   ||
                   (!musicPlayer.getGainSfxAvailable()))
                {
                    JOptionPane.showMessageDialog(frame,
                            text.getSomeFeaturesAreNotAvailable(), text.getMusicPlayerWarning(),
                            JOptionPane.WARNING_MESSAGE);
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
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.MAIN_MENU);
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
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.GAME_MODE);
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
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.BOARD);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelBoardInteractive, constraintsForPanelBoardInteractive);
            frame.getContentPane().add(statusBar, constraintsForStatusBar);
            repaint();
        });
    }
    private void showFrameReplay()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.REPLAY);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelBoardReplay, constraintsForPanelBoardReplay);
            frame.getContentPane().add(buttonPreviousMove, constraintsForButtonPreviousMove);
            frame.getContentPane().add(buttonSlower, constraintsForButtonSlower);
            frame.getContentPane().add(buttonPlayPause, constraintsForButtonPlayPause);
            frame.getContentPane().add(buttonStop, constraintsForButtonStop);
            frame.getContentPane().add(buttonFaster, constraintsForButtonFaster);
            frame.getContentPane().add(buttonNextMove, constraintsForButtonNextMove);
            repaint();
        });
    }
    private void showFrameRules()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.RULES);
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
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.SETTINGS);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(labelLanguage, constraintsForLabelLanguage);
            frame.getContentPane().add(comboBoxLanguage, constraintsForComboBoxLanguage);
            frame.getContentPane().add(labelMusic, constraintsForLabelMusic);
            frame.getContentPane().add(buttonMuteMusic, constraintsForButtonMuteMusic);
            frame.getContentPane().add(sliderGainMusic, constraintsForSliderGainMusic);
            frame.getContentPane().add(labelSfx, constraintsForLabelSfx);
            frame.getContentPane().add(buttonMuteSfx, constraintsForButtonMuteSfx);
            frame.getContentPane().add(sliderGainSfx, constraintsForSliderGainSfx);
            frame.getContentPane().add(buttonApply, constraintsForButtonApply);
            frame.getContentPane().add(buttonBackSettings, constraintsForButtonBackSettings);
            repaint();
        });
    }
    private void showDialogExitToMainMenu()
    {
        SwingUtilities.invokeLater(()->
        {
            State gameSinglePlayerState = gameSinglePlayer.getState();
            State gameLocalMultiplayerState = gameLocalMultiplayer.getState();
            State replayState = replay.getState();
            if(gameSinglePlayerState.equals(State.RUNNING)     ||
               gameLocalMultiplayerState.equals(State.RUNNING) ||
               replayState.equals(State.RUNNING))
            {
                //Show warning.
                int selectedOption = JOptionPane.showOptionDialog(frame,
                        text.getAreYouSure(), text.getExitToMainMenu(),
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new String[] {text.getYes(), text.getNo()}, text.getNo());
                if(selectedOption == JOptionPane.YES_OPTION)
                {
                    if(gameSinglePlayerState.equals(State.RUNNING))
                    {
                        gameSinglePlayer.stop();
                    }
                    if(gameLocalMultiplayerState.equals(State.RUNNING))
                    {
                        gameLocalMultiplayer.stop();
                    }
                    if(replayState.equals(State.RUNNING))
                    {
                        replay.stop();
                    }
                    showFrameMainMenu();
                }
            }
            else
            {
                showFrameMainMenu();
            }
        });
    }
    private void showDialogSaveReplay()
    {
        SwingUtilities.invokeLater(()->
        {
            if(replay.getIsReplayOutputEmpty())
            {
                JOptionPane.showMessageDialog(frame,
                        text.getNothingToSave(), text.getReplayWarning(), JOptionPane.WARNING_MESSAGE);
            }
            else //replayOutput is not empty.
            {
                fileChooser.setDialogTitle(text.getSaveReplay());
                int selectedOption = fileChooser.showSaveDialog(frame);
                if(selectedOption == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    int result = replay.save(selectedFile);
                    if(result == Replay.FAILURE)
                    {
                        JOptionPane.showMessageDialog(frame,
                                text.getCouldNotSaveReplay(), text.getReplayError(), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    private void showDialogAbout()
    {
        SwingUtilities.invokeLater(()->
            JOptionPane.showMessageDialog(frame,
                    text.getAboutVerbose(), text.getAbout(), JOptionPane.INFORMATION_MESSAGE)
        );
    }
    private void showDialogLoadReplay()
    {
        SwingUtilities.invokeLater(()->
        {
            fileChooser.setDialogTitle(text.getLoadReplay());
            int selectedOption = fileChooser.showOpenDialog(frame);
            if(selectedOption == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                int result = replay.load(selectedFile);
                if(result == Replay.FAILURE)
                {
                    JOptionPane.showMessageDialog(frame,
                            text.getCouldNotLoadReplay(), text.getReplayError(), JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    replay.start();
                    showFrameReplay();
                }
            }
        });
    }
    private void startSinglePlayerGame()
    {
        SwingUtilities.invokeLater(()->
        {
            panelBoardInteractive.setGame(gameSinglePlayer);
            gameSinglePlayer.start();
            showFrameBoard();
        });
    }
    private void startLocalMultiplayerGame()
    {
        SwingUtilities.invokeLater(()->
        {
            panelBoardInteractive.setGame(gameLocalMultiplayer);
            gameLocalMultiplayer.start();
            showFrameBoard();
        });
    }
    private void replaySlower()
    {
        SwingUtilities.invokeLater(()->
        {
            int currentDelay = timer.getDelay();
            int delay = currentDelay + 100;

            timer.setDelay(delay);
            if(delay == 1000)
            {
                buttonSlower.setEnabled(false);
            }

            buttonFaster.setEnabled(true);
        });
    }
    private void replayPlayPause(ActionEvent e)
    {
        SwingUtilities.invokeLater(()->
        {
            JToggleButton buttonSource = (JToggleButton)e.getSource();
            boolean selected = buttonSource.isSelected();
            if(selected) //Play.
            {
                timer.restart();
            }
            else //Pause.
            {
                timer.stop();
            }
        });
    }
    private void replayStop()
    {
        SwingUtilities.invokeLater(()->
        {
            timer.stop();
            replay.start();
            buttonPlayPause.setSelected(false);
        });
    }
    private void replayFaster()
    {
        SwingUtilities.invokeLater(()->
        {
            int currentDelay = timer.getDelay();
            int delay = currentDelay - 100;

            timer.setDelay(delay);
            if(delay == 100)
            {
                buttonFaster.setEnabled(false);
            }

            buttonSlower.setEnabled(true);
        });
    }
    private void changeDisplayedRule()
    {
        SwingUtilities.invokeLater(()->
        {
            int selectedIndex = comboBoxRules.getSelectedIndex();
            switch(selectedIndex)
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
    }
    private void muteOrUnmuteMusic(ActionEvent e)
    {
        SwingUtilities.invokeLater(()->
        {
            JToggleButton buttonSource = (JToggleButton)e.getSource();
            boolean selected = buttonSource.isSelected();
            if(selected)
            {
                musicPlayer.muteMusic();
            }
            else
            {
                musicPlayer.unmuteMusic();
            }
        });
    }
    private void changeGainMusic(ChangeEvent e)
    {
        SwingUtilities.invokeLater(()->
        {
            JSlider sliderSource = (JSlider)e.getSource();
            if(!sliderSource.getValueIsAdjusting())
            {
                int sliderValue = sliderSource.getValue();
                musicPlayer.setGainMusicDb(sliderValue);
            }
        });
    }
    private void muteOrUnmuteSfx(ActionEvent e)
    {
        SwingUtilities.invokeLater(()->
        {
            JToggleButton buttonSource = (JToggleButton)e.getSource();
            boolean selected = buttonSource.isSelected();
            if(selected)
            {
                musicPlayer.muteSfx();
            }
            else
            {
                musicPlayer.unmuteSfx();
            }
        });
    }
    private void changeGainSfx(ChangeEvent e)
    {
        SwingUtilities.invokeLater(()->
        {
            JSlider sliderSource = (JSlider)e.getSource();
            if(!sliderSource.getValueIsAdjusting())
            {
                int sliderValue = sliderSource.getValue();
                musicPlayer.setGainSfxDb(sliderValue);
            }
        });
    }
    private void showPreviousFrame()
    {
        int size = previousFrames.size();
        if(size > 0)
        {
            previousFrames.remove(size - 1); //Delete current frame from array.
            size = previousFrames.size();
            if(size > 0)
            {
                FrameType previousFrame = previousFrames.get(size - 1);
                previousFrames.remove(size - 1); //Delete previous frame from array.
                switch(previousFrame)
                {
                    case MAIN_MENU -> showFrameMainMenu();
                    case GAME_MODE -> showFrameGameMode();
                    case BOARD -> showFrameBoard();
                    case REPLAY -> showFrameReplay();
                    case RULES -> showFrameRules();
                    case SETTINGS -> showFrameSettings();
                }
            }
        }
    }
    private void addToPreviousFrames(FrameType currentFrame)
    {
        int size = previousFrames.size();
        if(size == 0)
        {
            previousFrames.add(currentFrame);
        }
        else
        {
            FrameType previousFrame = previousFrames.get(size - 1);
            if(currentFrame != previousFrame)
            {
                previousFrames.add(currentFrame);
            }
        }
    }
    private void refreshText()
    {
        SwingUtilities.invokeLater(()->
        {
            int selectedIndex = comboBoxLanguage.getSelectedIndex();
            switch (selectedIndex)
            {
                case 0 -> text = textEnglish;
                case 1 -> text = textRussian;
            }

            //Common frame features.
            frame.setTitle(text.getTitle());
            menuNavigation.setText(text.getNavigation());
            menuItemMainMenu.setText(text.getMainMenu());
            menuReplay.setText(text.getReplay());
            menuItemSaveReplay.setText(text.getSaveReplay());
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
            selectedIndex = comboBoxRules.getSelectedIndex();
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
            comboBoxRules.setSelectedIndex(selectedIndex);
            buttonBackRules.setText(text.getBack());

            //Frame Settings.
            labelLanguage.setText(text.getLanguage());
            labelMusic.setText(text.getMusic());
            labelSfx.setText(text.getSfx());
            buttonApply.setText(text.getApply());
            buttonBackSettings.setText(text.getBack());

            //Game.
            gameSinglePlayer.refreshText();
            gameLocalMultiplayer.refreshText();
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
    public Text getText()
    {
        return text;
    }
    public void setStatusBarText(String message)
    {
        SwingUtilities.invokeLater(()->statusBar.setText(message));
    }
    public void showDialogYouPlay(String message)
    {
        SwingUtilities.invokeLater(()->
                JOptionPane.showMessageDialog(frame,
                        message, text.getSinglePlayer(), JOptionPane.INFORMATION_MESSAGE)
        );
    }
    public void setPanelBoardReplayGrid(HashMap<Location, Tile> grid)
    {
        panelBoardReplay.setGrid(grid);
    }
    public void enableButtonPreviousMove()
    {
        SwingUtilities.invokeLater(()->buttonPreviousMove.setEnabled(true));
    }
    public void disableButtonPreviousMove()
    {
        SwingUtilities.invokeLater(()->buttonPreviousMove.setEnabled(false));
    }
    public void enableButtonPlayPause()
    {
        SwingUtilities.invokeLater(()-> buttonPlayPause.setEnabled(true));
    }
    public void disableButtonPlayPause()
    {
        SwingUtilities.invokeLater(()-> buttonPlayPause.setEnabled(false));
    }
    public void setButtonPlayPauseUnselected()
    {
        SwingUtilities.invokeLater(()->buttonPlayPause.setSelected(false));
    }
    public void enableButtonNextMove()
    {
        SwingUtilities.invokeLater(()->buttonNextMove.setEnabled(true));
    }
    public void disableButtonNextMove()
    {
        SwingUtilities.invokeLater(()->buttonNextMove.setEnabled(false));
    }
    public void stopTimer()
    {
        SwingUtilities.invokeLater(()->timer.stop());
    }
}