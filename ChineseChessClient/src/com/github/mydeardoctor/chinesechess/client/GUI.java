package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.State;
import com.github.mydeardoctor.chinesechess.PanelBackground;
import com.github.mydeardoctor.chinesechess.DocumentFilterForTextFieldPort;
import com.github.mydeardoctor.chinesechess.TableOfClientsModel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final BufferedImage backgroundImage;
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
    private final BufferedImage selectionImage;
    private final BufferedImage selectionPalaceImage;
    private final BufferedImage selectionRiverImage;
    private final ImageIcon iconIncorrect;
    private final ImageIcon iconCorrect;
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
    private JButton buttonBackOnFrameGameMode;
    private GridBagConstraints constraintsForButtonSinglePlayer;
    private GridBagConstraints constraintsForButtonLocalMultiplayer;
    private GridBagConstraints constraintsForButtonOnlineMultiplayer;
    private GridBagConstraints constraintsForButtonBackOnFrameGameMode;

    //Frame Board.
    private PanelBoardInteractive panelBoardInteractive;
    private JTextField statusBar;
    private GridBagConstraints constraintsForPanelBoardInteractive;
    private GridBagConstraints constraintsForStatusBar;

    //Frame Online Multiplayer.
    private JPanel panelTransparentOnFrameOnlineMultiplayer;
    private JTextField statusBarOfConnection;
    private JButton buttonConnectToServer;
    private JButton buttonLobby;
    private JButton buttonBackOnFrameOnlineMultiplayer;
    private GridBagConstraints constraintsForPanelTransparentOnFrameOnlineMultiplayer;
    private GridBagConstraints constraintsForStatusBarOfConnection;
    private GridBagConstraints constraintsForButtonConnectToServer;
    private GridBagConstraints constraintsForButtonLobby;
    private GridBagConstraints constraintsForButtonBackOnFrameOnlineMultiplayer;

    //Frame Connect to Server.
    private JLabel labelIp;
    private DocumentListenerForTextFieldIp documentListenerForTextFieldIp;
    private JTextField textFieldIp;
    private JLabel labelWithIpIcon;
    private JTextArea textAreaIpTip;
    private JLabel labelPort;
    private DocumentListenerForTextFieldPort documentListenerForTextFieldPort;
    private JTextField textFieldPort;
    private JLabel labelWithPortIcon;
    private JTextArea textAreaPortTip;
    private JLabel labelNickname;
    private DocumentListenerForTextFieldNickname documentListenerForTextFieldNickname;
    private JTextField textFieldNickname;
    private JLabel labelWithNicknameIcon;
    private JTextArea textAreaNicknameTip;
    private JPanel panelTransparentOnFrameConnectToServer;
    private JButton buttonConnect;
    private JButton buttonDisconnect;
    private JButton buttonBackOnFrameConnectToServer;
    private GridBagConstraints constraintsForLabelIp;
    private GridBagConstraints constraintsForTextFieldIp;
    private GridBagConstraints constraintsForLabelWithIpIcon;
    private GridBagConstraints constraintsForTextAreaIpTip;
    private GridBagConstraints constraintsForLabelPort;
    private GridBagConstraints constraintsForTextFieldPort;
    private GridBagConstraints constraintsForLabelWithPortIcon;
    private GridBagConstraints constraintsForTextAreaPortTip;
    private GridBagConstraints constraintsForLabelNickname;
    private GridBagConstraints constraintsForTextFieldNickname;
    private GridBagConstraints constraintsForLabelWithNicknameIcon;
    private GridBagConstraints constraintsForTextAreaNicknameTip;
    private GridBagConstraints constraintsForPanelTransparentOnFrameConnectToServer;

    //Frame Lobby.
    private JLabel labelListOfClients;
    private JScrollPane scrollPaneWithTableOfClients;
    private TableOfClientsModel tableOfClientsModel;
    private JButton buttonBackOnFrameLobby;
    private GridBagConstraints constraintsForLabelListOfClients;
    private GridBagConstraints constraintsForScrollPaneWithTableOfClients;
    private GridBagConstraints constraintsForButtonBackOnFrameLobby;

    //Frame Replay.
    private PanelBoard panelBoardOnFrameReplay;
    private JPanel panelTransparentOnFrameReplay;
    private JButton buttonPreviousMove;
    private JButton buttonSlower;
    private JToggleButton buttonPlayPause;
    private JButton buttonFaster;
    private JButton buttonNextMove;
    private GridBagConstraints constraintsForPanelBoardOnFrameReplay;
    private GridBagConstraints constraintsForPanelTransparentOnFrameReplay;
    private Timer timerForReplay;

    //Frame Rules.
    private PanelBoardRules panelBoardOnFrameRules;
    private JPanel panelTransparentOnFrameRules;
    private JComboBox<String> comboBoxRules;
    private JButton buttonBackOnFrameRules;
    private JTextArea textAreaRules;
    private JScrollPane scrollPaneRules;
    private GridBagConstraints constraintsForPanelBoardOnFrameRules;
    private GridBagConstraints constraintsForPanelTransparentOnFrameRules;
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
    private JButton buttonBackOnFrameSettings;
    private GridBagConstraints constraintsForLabelLanguage;
    private GridBagConstraints constraintsForComboBoxLanguage;
    private GridBagConstraints constraintsForLabelMusic;
    private GridBagConstraints constraintsForButtonMuteMusic;
    private GridBagConstraints constraintsForSliderGainMusic;
    private GridBagConstraints constraintsForLabelSfx;
    private GridBagConstraints constraintsForButtonMuteSfx;
    private GridBagConstraints constraintsForSliderGainSfx;
    private GridBagConstraints constraintsForButtonBackOnFrameSettings;

    //Game.
    private GameSinglePlayer gameSinglePlayer;
    private GameLocalMultiplayer gameLocalMultiplayer;

    //Client.
    private Client client;

    //Replay.
    private Replay replay;

    //Rules.
    private Rules rules;

    //Music player.
    private MusicPlayer musicPlayer;

    //Logger.
    private static final Logger logger = Logger.getLogger(GUI.class.getName());

    public GUI()
    {
        //Text.
        textEnglish = new TextEnglish();
        textRussian = new TextRussian();
        text = textEnglish;

        //Resources.
        resourcesMissing = false;
        fontChinese = initializeFontChinese();
        iconFrame = initializeIconFrame();
        backgroundImage = initializeBackgroundImage();
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
        selectionImage = initializeSelectionImage();
        selectionPalaceImage = initializeSelectionPalaceImage();
        selectionRiverImage = initializeSelectionRiverImage();
        iconIncorrect = initializeIconIncorrect();
        iconCorrect = initializeIconCorrect();
        iconPreviousMove = initializeIconPreviousMove();
        iconSlower = initializeIconSlower();
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
        initializeFrameOnlineMultiplayer();
        initializeFrameConnectToServer();
        initializeFrameLobby();
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
        catch (NullPointerException | IOException | FontFormatException e)
        {
            resourcesMissing = true;
            fontChinese = new Font(Font.DIALOG, Font.PLAIN, 1);

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFontChinese",
                    "Could not initialize fontChinese.", e);
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
        }
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            iconFrame = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = iconFrame.createGraphics();
            g2d.setColor(new Color(196, 185, 165));
            g2d.fillRect(0,0,100,100);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 42));
            g2d.drawString("象棋", 5, 65);

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeIconFrame",
                    "Could not initialize iconFrame.", e);
        }
        return iconFrame;
    }
    private BufferedImage initializeBackgroundImage()
    {
        BufferedImage backgroundImage;
        URL url = getClass().getResource("/background.jpg");
        try
        {
            //noinspection DataFlowIssue
            backgroundImage = ImageIO.read(url);
        }
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            backgroundImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = backgroundImage.createGraphics();
            g2d.setColor(new Color(196, 185, 165));
            g2d.fillRect(0,0,100,100);

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeBackgroundImage",
                    "Could not initialize backgroundImage.", e);
        }
        return backgroundImage;
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            generalRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(generalRedImage, Color.RED, "帥");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeGeneralRedImage",
                    "Could not initialize generalRedImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            advisorRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(advisorRedImage, Color.RED, "仕");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeAdvisorRedImage",
                    "Could not initialize advisorRedImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            elephantRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(elephantRedImage, Color.RED, "相");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeElephantRedImage",
                    "Could not initialize elephantRedImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            horseRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(horseRedImage, Color.RED, "傌");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeHorseRedImage",
                    "Could not initialize horseRedImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            chariotRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(chariotRedImage, Color.RED, "俥");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeChariotRedImage",
                    "Could not initialize chariotRedImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            cannonRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(cannonRedImage, Color.RED, "炮");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeCannonRedImage",
                    "Could not initialize cannonRedImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            soldierRedImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(soldierRedImage, Color.RED, "兵");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeSoldierRedImage",
                    "Could not initialize soldierRedImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            generalBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(generalBlackImage, Color.BLACK, "將");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeGeneralBlackImage",
                    "Could not initialize generalBlackImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            advisorBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(advisorBlackImage, Color.BLACK, "士");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeAdvisorBlackImage",
                    "Could not initialize advisorBlackImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            elephantBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(elephantBlackImage, Color.BLACK, "象");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeElephantBlackImage",
                    "Could not initialize elephantBlackImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            horseBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(horseBlackImage, Color.BLACK, "馬");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeHorseBlackImage",
                    "Could not initialize horseBlackImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            chariotBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(chariotBlackImage, Color.BLACK, "車");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeChariotBlackImage",
                    "Could not initialize chariotBlackImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            cannonBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(cannonBlackImage, Color.BLACK, "砲");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeCannonBlackImage",
                    "Could not initialize cannonBlackImage.", e);
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
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            soldierBlackImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            initializeDefaultFigureImage(soldierBlackImage, Color.BLACK, "卒");

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeSoldierBlackImage",
                    "Could not initialize soldierBlackImage.", e);
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
    private BufferedImage initializeSelectionImage()
    {
        BufferedImage selectionImage;
        URL url = getClass().getResource("/selection.png");
        try
        {
            //noinspection DataFlowIssue
            selectionImage = ImageIO.read(url);
        }
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            selectionImage = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = selectionImage.createGraphics();
            g2d.setColor(new Color(117, 240, 131));
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            g2d.drawArc(5,5,90,90,0,360);

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeSelectionImage",
                    "Could not initialize selectionImage.", e);
        }
        return selectionImage;
    }
    private BufferedImage initializeSelectionPalaceImage()
    {
        BufferedImage selectionPalaceImage;
        URL url = getClass().getResource("/selectionPalace.png");
        try
        {
            //noinspection DataFlowIssue
            selectionPalaceImage = ImageIO.read(url);
        }
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            selectionPalaceImage = new BufferedImage(200,200,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = selectionPalaceImage.createGraphics();
            g2d.setColor(new Color(117, 240, 131));
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            g2d.drawRect(5,5,190,190);

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeSelectionPalaceImage",
                    "Could not initialize selectionPalaceImage.", e);
        }
        return selectionPalaceImage;
    }
    private BufferedImage initializeSelectionRiverImage()
    {
        BufferedImage selectionRiverImage;
        URL url = getClass().getResource("/selectionRiver.png");
        try
        {
            //noinspection DataFlowIssue
            selectionRiverImage = ImageIO.read(url);
        }
        catch (IllegalArgumentException | IOException e)
        {
            resourcesMissing = true;
            selectionRiverImage = new BufferedImage(800,100,BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d = selectionRiverImage.createGraphics();
            g2d.setColor(new Color(117, 240, 131));
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            g2d.drawRect(5,5,790,90);

            logger.logp(Level.WARNING, this.getClass().getName(), "initializeSelectionRiverImage",
                    "Could not initialize selectionRiverImage.", e);
        }
        return selectionRiverImage;
    }
    private ImageIcon initializeIconIncorrect()
    {
        BufferedImage imageIncorrect = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageIncorrect.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillOval(0, 0, 60, 60);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(15, 15, 45, 45);
        g2d.drawLine(15, 45, 45, 15);
        return new ImageIcon(imageIncorrect);
    }
    private ImageIcon initializeIconCorrect()
    {
        BufferedImage imageCorrect = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2d = imageCorrect.createGraphics();
        g2d.setColor(new Color(117, 240, 131));
        g2d.fillOval(0, 0, 60, 60);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(10, 32, 25, 45);
        g2d.drawLine(25, 45, 45, 15);
        return new ImageIcon(imageCorrect);
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
    private ImageIcon initializeIconSlower()
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
                frame = new JFrame(text.getChineseChessClient());
                frame.setMinimumSize(new Dimension(800,800));
                frame.setBounds((int)frame.getGraphicsConfiguration().getBounds().getCenterX() -
                                  (int)frame.getBounds().getCenterX(),
                                (int)frame.getGraphicsConfiguration().getBounds().getCenterY() -
                                  (int)frame.getBounds().getCenterY(),
                             800,800);
                frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
                frame.setIconImage(iconFrame);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //GridBag layout manager.
                gridBagLayout = new GridBagLayout();

                //Panel Background.
                panelBackground = new PanelBackground(backgroundImage);
                panelBackground.setLayout(gridBagLayout);
                frame.setContentPane(panelBackground);

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
                catch(URISyntaxException e)
                {
                    fileChooser.setCurrentDirectory(null);

                    logger.logp(Level.WARNING, this.getClass().getName(), "initializeCommonFrameFeatures",
                            "Could not set a directory where this program was invoked to fileChooser.", e);
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
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeCommonFrameFeatures",
                    "Could not initialize common frame features.", e);
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
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameMainMenu",
                    "Could not initialize frame Main Menu.", e);
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
                buttonOnlineMultiplayer.addActionListener(e-> showFrameOnlineMultiplayer());

                //Button Back.
                buttonBackOnFrameGameMode = new JButton(text.getBack());
                buttonBackOnFrameGameMode.setPreferredSize(new Dimension(200, 100));
                buttonBackOnFrameGameMode.setBackground(Color.WHITE);
                buttonBackOnFrameGameMode.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackOnFrameGameMode.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonBackOnFrameGameMode = new GridBagConstraints(
                        0, 3, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonBackOnFrameGameMode.addActionListener(e->showPreviousFrame());
            });
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameGameMode",
                    "Could not initialize frame Game Mode.", e);
        }
    }
    private void initializeFrameBoard()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Panel Board.
                panelBoardInteractive = new PanelBoardInteractive(imagesOfFigures, selectionImage);
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
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameBoard",
                    "Could not initialize frame Board.", e);
        }
    }
    private void initializeFrameOnlineMultiplayer()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Panel Transparent.
                panelTransparentOnFrameOnlineMultiplayer = new JPanel();
                panelTransparentOnFrameOnlineMultiplayer.setOpaque(false);
                panelTransparentOnFrameOnlineMultiplayer.setLayout(gridBagLayout);
                constraintsForPanelTransparentOnFrameOnlineMultiplayer = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Status Bar.
                statusBarOfConnection = new JTextField(1);
                statusBarOfConnection.setEnabled(false);
                statusBarOfConnection.setDisabledTextColor(Color.BLACK);
                statusBarOfConnection.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(5, 5, 5, 5)));
                statusBarOfConnection.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                statusBarOfConnection.setText(text.getDisconnectedFromServer());
                constraintsForStatusBarOfConnection = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Button Connect to Server.
                buttonConnectToServer = new JButton(text.getConnectToServer());
                buttonConnectToServer.setPreferredSize(new Dimension(500, 100));
                buttonConnectToServer.setBackground(Color.WHITE);
                buttonConnectToServer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonConnectToServer.setFont(fontChinese.deriveFont(Font.BOLD, 43.f));
                constraintsForButtonConnectToServer = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonConnectToServer.addActionListener(e->showFrameConnectToServer());

                //Button Lobby.
                buttonLobby = new JButton(text.getLobby());
                buttonLobby.setPreferredSize(new Dimension(500, 100));
                buttonLobby.setBackground(Color.WHITE);
                buttonLobby.setBorder(new LineBorder(Color.BLACK, 2));
                buttonLobby.setFont(fontChinese.deriveFont(Font.BOLD, 43.f));
                constraintsForButtonLobby = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonLobby.addActionListener(e->showFrameLobby());

                //Button Back.
                buttonBackOnFrameOnlineMultiplayer = new JButton(text.getBack());
                buttonBackOnFrameOnlineMultiplayer.setPreferredSize(new Dimension(200, 100));
                buttonBackOnFrameOnlineMultiplayer.setBackground(Color.WHITE);
                buttonBackOnFrameOnlineMultiplayer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackOnFrameOnlineMultiplayer.setFont(fontChinese.deriveFont(Font.BOLD, 43.f));
                constraintsForButtonBackOnFrameOnlineMultiplayer = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonBackOnFrameOnlineMultiplayer.addActionListener(e->showPreviousFrame());
            });
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameOnlineMultiplayer",
                    "Could not initialize frame Online Multiplayer.", e);
        }
    }
    private void initializeFrameConnectToServer()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Label IP.
                labelIp = new JLabel(text.getIpAddress());
                labelIp.setPreferredSize(new Dimension(155, 100));
                labelIp.setFont(fontChinese.deriveFont(Font.BOLD, 35.f));
                constraintsForLabelIp = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 10, 0, 10), 0, 0);

                //Text Field IP.
                PlainDocument documentForTextFieldIp = new PlainDocument();
                DocumentFilterForTextFieldIp documentFilterForTextFieldIp =
                        new DocumentFilterForTextFieldIp();
                documentForTextFieldIp.setDocumentFilter(documentFilterForTextFieldIp);
                documentListenerForTextFieldIp = new DocumentListenerForTextFieldIp(this);
                documentForTextFieldIp.addDocumentListener(documentListenerForTextFieldIp);

                textFieldIp = new JTextField(documentForTextFieldIp, "", 10);
                textFieldIp.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(15, 5, 15, 5)));
                textFieldIp.setFont(fontChinese.deriveFont(Font.BOLD, 35.f));
                constraintsForTextFieldIp = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 10, 0, 10), 0, 0);

                //Label with IP Icon.
                labelWithIpIcon = new JLabel(iconIncorrect);
                constraintsForLabelWithIpIcon = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 10, 0, 10), 0, 0);

                //Text Area IP Tip.
                textAreaIpTip = new JTextArea(2, 10);
                textAreaIpTip.setPreferredSize(new Dimension(250, 140));
                textAreaIpTip.setOpaque(false);
                textAreaIpTip.setEnabled(false);
                textAreaIpTip.setDisabledTextColor(Color.BLACK);
                textAreaIpTip.setLineWrap(true);
                textAreaIpTip.setWrapStyleWord(true);
                textAreaIpTip.setFont(fontChinese.deriveFont(Font.BOLD, 30.f));
                textAreaIpTip.setCaretPosition(0);
                textAreaIpTip.setText(text.getIpTip());
                constraintsForTextAreaIpTip = new GridBagConstraints(
                        3, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 20, 0, 10), 0, 0);

                //Label Port.
                labelPort = new JLabel(text.getPort());
                labelPort.setPreferredSize(new Dimension(100, 100));
                labelPort.setFont(fontChinese.deriveFont(Font.BOLD, 35.f));
                constraintsForLabelPort = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Text Field Port.
                PlainDocument documentForTextFieldPort = new PlainDocument();
                DocumentFilterForTextFieldPort documentFilterForTextFieldPort =
                        new DocumentFilterForTextFieldPort();
                documentForTextFieldPort.setDocumentFilter(documentFilterForTextFieldPort);
                documentListenerForTextFieldPort = new DocumentListenerForTextFieldPort(this);
                documentForTextFieldPort.addDocumentListener(documentListenerForTextFieldPort);

                textFieldPort = new JTextField(documentForTextFieldPort, "4242", 5);
                textFieldPort.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(15, 5, 15, 5)));
                textFieldPort.setFont(fontChinese.deriveFont(Font.BOLD, 35.f));
                constraintsForTextFieldPort = new GridBagConstraints(
                        1, 1, 1, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Label with Port Icon.
                labelWithPortIcon = new JLabel(iconCorrect);
                constraintsForLabelWithPortIcon = new GridBagConstraints(
                        2, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Text Area Port Tip.
                textAreaPortTip = new JTextArea(2, 10);
                textAreaPortTip.setPreferredSize(new Dimension(250, 80));
                textAreaPortTip.setOpaque(false);
                textAreaPortTip.setEnabled(false);
                textAreaPortTip.setDisabledTextColor(Color.BLACK);
                textAreaPortTip.setLineWrap(true);
                textAreaPortTip.setWrapStyleWord(true);
                textAreaPortTip.setFont(fontChinese.deriveFont(Font.BOLD, 30.f));
                textAreaPortTip.setCaretPosition(0);
                textAreaPortTip.setText("");
                constraintsForTextAreaPortTip = new GridBagConstraints(
                        3, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 20, 0, 10), 0, 0);

                //Label Nickname.
                labelNickname = new JLabel(text.getNickname());
                labelNickname.setPreferredSize(new Dimension(155, 100));
                labelNickname.setFont(fontChinese.deriveFont(Font.BOLD, 35.f));
                constraintsForLabelNickname = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Text Field Nickname.
                PlainDocument documentForTextFieldNickname = new PlainDocument();
                DocumentFilterForTextFieldNickname documentFilterForTextFieldNickname =
                        new DocumentFilterForTextFieldNickname();
                documentForTextFieldNickname.setDocumentFilter(documentFilterForTextFieldNickname);
                documentListenerForTextFieldNickname = new DocumentListenerForTextFieldNickname(this);
                documentForTextFieldNickname.addDocumentListener(documentListenerForTextFieldNickname);

                textFieldNickname = new JTextField(documentForTextFieldNickname, "", 10);
                textFieldNickname.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(15, 5, 15, 5)));
                textFieldNickname.setFont(fontChinese.deriveFont(Font.BOLD, 35.f));
                constraintsForTextFieldNickname = new GridBagConstraints(
                        1, 2, 1, 1, 0, 0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Label with Nickname Icon.
                labelWithNicknameIcon = new JLabel(iconIncorrect);
                constraintsForLabelWithNicknameIcon = new GridBagConstraints(
                        2, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Text Area Nickname Tip.
                textAreaNicknameTip = new JTextArea(2, 10);
                textAreaNicknameTip.setPreferredSize(new Dimension(250, 40));
                textAreaNicknameTip.setOpaque(false);
                textAreaNicknameTip.setEnabled(false);
                textAreaNicknameTip.setDisabledTextColor(Color.BLACK);
                textAreaNicknameTip.setLineWrap(true);
                textAreaNicknameTip.setWrapStyleWord(true);
                textAreaNicknameTip.setFont(fontChinese.deriveFont(Font.BOLD, 30.f));
                textAreaNicknameTip.setCaretPosition(0);
                textAreaNicknameTip.setText(text.getNicknameTip());
                constraintsForTextAreaNicknameTip = new GridBagConstraints(
                        3, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 20, 0, 10), 0, 0);

                //Panel Transparent.
                panelTransparentOnFrameConnectToServer = new JPanel();
                panelTransparentOnFrameConnectToServer.setOpaque(false);
                panelTransparentOnFrameConnectToServer.setLayout(gridBagLayout);
                constraintsForPanelTransparentOnFrameConnectToServer = new GridBagConstraints(
                        0, 3, 4, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 0, 0), 0, 0);

                //Button Connect.
                buttonConnect = new JButton(text.getConnect());
                buttonConnect.setPreferredSize(new Dimension(230, 100));
                buttonConnect.setEnabled(false);
                buttonConnect.setBackground(Color.WHITE);
                buttonConnect.setBorder(new LineBorder(Color.BLACK, 2));
                buttonConnect.setFont(fontChinese.deriveFont(Font.BOLD, 30.f));
                GridBagConstraints constraintsForButtonConnect = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(50, 20, 20, 20), 0, 0);
                buttonConnect.addActionListener(e->connectToServer());
                panelTransparentOnFrameConnectToServer.add(buttonConnect, constraintsForButtonConnect);

                //Button Disconnect.
                buttonDisconnect = new JButton(text.getDisconnect());
                buttonDisconnect.setPreferredSize(new Dimension(230, 100));
                buttonDisconnect.setEnabled(false);
                buttonDisconnect.setBackground(Color.WHITE);
                buttonDisconnect.setBorder(new LineBorder(Color.BLACK, 2));
                buttonDisconnect.setFont(fontChinese.deriveFont(Font.BOLD, 30.f));
                GridBagConstraints constraintsForButtonDisconnect = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(50, 20, 20, 20), 0, 0);
                buttonDisconnect.addActionListener(e->showDialogDisconnectFromServer());
                panelTransparentOnFrameConnectToServer.add(buttonDisconnect, constraintsForButtonDisconnect);

                //Button Back.
                buttonBackOnFrameConnectToServer = new JButton(text.getBack());
                buttonBackOnFrameConnectToServer.setPreferredSize(new Dimension(180, 100));
                buttonBackOnFrameConnectToServer.setBackground(Color.WHITE);
                buttonBackOnFrameConnectToServer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackOnFrameConnectToServer.setFont(fontChinese.deriveFont(Font.BOLD, 30.f));
                GridBagConstraints constraintsForButtonBackConnectToServer = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(50, 20, 20, 20), 0, 0);
                buttonBackOnFrameConnectToServer.addActionListener(e->showPreviousFrame());
                panelTransparentOnFrameConnectToServer.add(
                        buttonBackOnFrameConnectToServer, constraintsForButtonBackConnectToServer);
            });
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameConnect",
                    "Could not initialize frame Connect.", e);
        }
    }
    private void initializeFrameLobby() //TODO cellselectable?
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Label List of Clients.
                labelListOfClients = new JLabel(text.getListOfClients());
                labelListOfClients.setPreferredSize(new Dimension(300, 60));
                labelListOfClients.setFont(fontChinese.deriveFont(Font.BOLD, 40.f));
                constraintsForLabelListOfClients = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Table of Clients.
                tableOfClientsModel = new TableOfClientsModel(0, 2); //TODO ширина колонок
                JTable tableOfClients = new JTable(tableOfClientsModel);
                tableOfClients.setPreferredScrollableViewportSize(new Dimension(500, 400));
                tableOfClients.setRowHeight(50);
                tableOfClients.setTableHeader(null);
                tableOfClients.setCellSelectionEnabled(true);
                tableOfClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tableOfClients.setDragEnabled(false);
                tableOfClients.setGridColor(Color.BLACK);
                tableOfClients.setFont(fontChinese.deriveFont(Font.BOLD, 40.f));

                //Scroll Pane.
                scrollPaneWithTableOfClients = new JScrollPane(tableOfClients,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPaneWithTableOfClients.setBorder(new LineBorder(Color.BLACK, 2));
                constraintsForScrollPaneWithTableOfClients = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 0, 0, 0), 0, 0);

                //Button Back.
                buttonBackOnFrameLobby = new JButton(text.getBack());
                buttonBackOnFrameLobby.setPreferredSize(new Dimension(200, 100));
                buttonBackOnFrameLobby.setBackground(Color.WHITE);
                buttonBackOnFrameLobby.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackOnFrameLobby.setFont(fontChinese.deriveFont(Font.BOLD, 40.f));
                constraintsForButtonBackOnFrameLobby= new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 0, 0), 0, 0);
                buttonBackOnFrameLobby.addActionListener(e->showPreviousFrame());
            });
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameLobby",
                    "Could not initialize frame Lobby.", e);
        }
    }
    private void initializeFrameReplay()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Panel Board.
                panelBoardOnFrameReplay = new PanelBoard(imagesOfFigures, selectionImage);
                panelBoardOnFrameReplay.setOpaque(false);
                constraintsForPanelBoardOnFrameReplay = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Panel Transparent.
                panelTransparentOnFrameReplay = new JPanel();
                panelTransparentOnFrameReplay.setOpaque(false);
                panelTransparentOnFrameReplay.setLayout(gridBagLayout);
                constraintsForPanelTransparentOnFrameReplay = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Button Previous Move.
                buttonPreviousMove = new JButton(iconPreviousMove);
                buttonPreviousMove.setPreferredSize(new Dimension(60, 60));
                buttonPreviousMove.setOpaque(false);
                buttonPreviousMove.setBackground(Color.WHITE);
                buttonPreviousMove.setBorder(new LineBorder(Color.BLACK, 2));
                GridBagConstraints constraintsForButtonPreviousMove = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                buttonPreviousMove.addActionListener(e->replay.previousMove());
                panelTransparentOnFrameReplay.add(buttonPreviousMove, constraintsForButtonPreviousMove);

                //Button Slower.
                buttonSlower = new JButton(iconSlower);
                buttonSlower.setPreferredSize(new Dimension(60, 60));
                buttonSlower.setOpaque(false);
                buttonSlower.setBackground(Color.WHITE);
                buttonSlower.setBorder(new LineBorder(Color.BLACK, 2));
                GridBagConstraints constraintsForButtonSlower = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                buttonSlower.addActionListener(e-> replaySlower());
                panelTransparentOnFrameReplay.add(buttonSlower, constraintsForButtonSlower);

                //Button Play Pause.
                buttonPlayPause = new JToggleButton(iconPlay, false);
                buttonPlayPause.setPreferredSize(new Dimension(60, 60));
                buttonPlayPause.setOpaque(false);
                buttonPlayPause.setBackground(Color.WHITE);
                buttonPlayPause.setBorder(new LineBorder(Color.BLACK, 2));
                buttonPlayPause.setSelectedIcon(iconPause);
                GridBagConstraints constraintsForButtonPlayPause = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                buttonPlayPause.addActionListener(this::replayPlayPause);
                panelTransparentOnFrameReplay.add(buttonPlayPause, constraintsForButtonPlayPause);

                //Button Stop.
                JButton buttonStop = new JButton(iconStop);
                buttonStop.setPreferredSize(new Dimension(60, 60));
                buttonStop.setOpaque(false);
                buttonStop.setBackground(Color.WHITE);
                buttonStop.setBorder(new LineBorder(Color.BLACK, 2));
                GridBagConstraints constraintsForButtonStop = new GridBagConstraints(
                        3, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                buttonStop.addActionListener(e->replayStop());
                panelTransparentOnFrameReplay.add(buttonStop, constraintsForButtonStop);

                //Button Faster.
                buttonFaster = new JButton(iconFaster);
                buttonFaster.setPreferredSize(new Dimension(60, 60));
                buttonFaster.setOpaque(false);
                buttonFaster.setBackground(Color.WHITE);
                buttonFaster.setBorder(new LineBorder(Color.BLACK, 2));
                GridBagConstraints constraintsForButtonFaster = new GridBagConstraints(
                        4, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                buttonFaster.addActionListener(e-> replayFaster());
                panelTransparentOnFrameReplay.add(buttonFaster, constraintsForButtonFaster);

                //Button Next Move.
                buttonNextMove = new JButton(iconNextMove);
                buttonNextMove.setPreferredSize(new Dimension(60, 60));
                buttonNextMove.setOpaque(false);
                buttonNextMove.setBackground(Color.WHITE);
                buttonNextMove.setBorder(new LineBorder(Color.BLACK, 2));
                GridBagConstraints constraintsForButtonNextMove = new GridBagConstraints(
                        5, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                buttonNextMove.addActionListener(e->replay.nextMove());
                panelTransparentOnFrameReplay.add(buttonNextMove, constraintsForButtonNextMove);

                //Timer.
                timerForReplay = new Timer(500, e->replay.nextMove());
                timerForReplay.setInitialDelay(0);
            });
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameReplay",
                    "Could not initialize frame Replay.", e);
        }
    }
    private void initializeFrameRules()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Panel Board.
                panelBoardOnFrameRules = new PanelBoardRules(
                        imagesOfFigures, selectionImage, selectionPalaceImage, selectionRiverImage);
                panelBoardOnFrameRules.setOpaque(false);
                constraintsForPanelBoardOnFrameRules = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Panel Transparent.
                panelTransparentOnFrameRules = new JPanel();
                panelTransparentOnFrameRules.setOpaque(false);
                panelTransparentOnFrameRules.setLayout(gridBagLayout);
                constraintsForPanelTransparentOnFrameRules = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
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
                GridBagConstraints constraintsForComboBoxRules = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                comboBoxRules.addActionListener(e->changeDisplayedRule());
                panelTransparentOnFrameRules.add(comboBoxRules, constraintsForComboBoxRules);

                //Button Back.
                buttonBackOnFrameRules = new JButton(text.getBack());
                buttonBackOnFrameRules.setPreferredSize(new Dimension(80, 40));
                buttonBackOnFrameRules.setBackground(Color.WHITE);
                buttonBackOnFrameRules.setBorder(new LineBorder(Color.BLACK, 1));
                buttonBackOnFrameRules.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                GridBagConstraints constraintsForButtonBackRules = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);
                buttonBackOnFrameRules.addActionListener(e->showPreviousFrame());
                panelTransparentOnFrameRules.add(buttonBackOnFrameRules, constraintsForButtonBackRules);

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
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameRules",
                    "Could not initialize frame Rules.", e);
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
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 30), 0, 0);
                sliderGainMusic.addChangeListener(this::changeGainOfMusic);

                //Label SFX.
                labelSfx = new JLabel(text.getSoundEffects());
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
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 30), 0, 0);
                sliderGainSfx.addChangeListener(this::changeGainOfSfx);

                //Button Back.
                buttonBackOnFrameSettings = new JButton(text.getBack());
                buttonBackOnFrameSettings.setPreferredSize(new Dimension(200, 100));
                buttonBackOnFrameSettings.setBackground(Color.WHITE);
                buttonBackOnFrameSettings.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackOnFrameSettings.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonBackOnFrameSettings = new GridBagConstraints(
                        0, 3, 3, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 30, 0, 30), 0, 0);
                buttonBackOnFrameSettings.addActionListener(e->showPreviousFrame());
            });
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameSettings",
                    "Could not initialize frame Settings.", e);
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
    public void setClient(Client client)
    {
        this.client = client;
    }
    public void setReplay(Replay replay)
    {
        this.replay = replay;
    }
    public void setRules(Rules rules)
    {
        this.rules = rules;
        panelBoardOnFrameRules.setRules(rules);

        try
        {
            SwingUtilities.invokeAndWait(()->comboBoxRules.setSelectedIndex(0));
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "setRules",
                    "Could not set selected index to comboBoxRules.", e);
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
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "setMusicPlayer",
                    "Could not set musicPlayer.", e);
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
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "showFrameAndWarnings",
                    "Could not show frame and warnings.", e);
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
            frame.getContentPane().add(buttonBackOnFrameGameMode, constraintsForButtonBackOnFrameGameMode);
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
    private void showFrameOnlineMultiplayer()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.ONLINE_MULTIPLAYER);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelTransparentOnFrameOnlineMultiplayer,
                    constraintsForPanelTransparentOnFrameOnlineMultiplayer);
            frame.getContentPane().add(statusBarOfConnection, constraintsForStatusBarOfConnection);
            panelTransparentOnFrameOnlineMultiplayer.removeAll();
            panelTransparentOnFrameOnlineMultiplayer.add(buttonConnectToServer, constraintsForButtonConnectToServer);
            panelTransparentOnFrameOnlineMultiplayer.add(buttonLobby, constraintsForButtonLobby);
            panelTransparentOnFrameOnlineMultiplayer.add(
                    buttonBackOnFrameOnlineMultiplayer, constraintsForButtonBackOnFrameOnlineMultiplayer);
            repaint();
        });
    }
    private void showFrameConnectToServer()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.CONNECT_TO_SERVER);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelTransparentOnFrameOnlineMultiplayer,
                    constraintsForPanelTransparentOnFrameOnlineMultiplayer);
            frame.getContentPane().add(statusBarOfConnection, constraintsForStatusBarOfConnection);
            panelTransparentOnFrameOnlineMultiplayer.removeAll();
            panelTransparentOnFrameOnlineMultiplayer.add(labelIp, constraintsForLabelIp);
            panelTransparentOnFrameOnlineMultiplayer.add(textFieldIp, constraintsForTextFieldIp);
            panelTransparentOnFrameOnlineMultiplayer.add(labelWithIpIcon, constraintsForLabelWithIpIcon);
            panelTransparentOnFrameOnlineMultiplayer.add(textAreaIpTip, constraintsForTextAreaIpTip);
            panelTransparentOnFrameOnlineMultiplayer.add(labelPort, constraintsForLabelPort);
            panelTransparentOnFrameOnlineMultiplayer.add(textFieldPort, constraintsForTextFieldPort);
            panelTransparentOnFrameOnlineMultiplayer.add(labelWithPortIcon, constraintsForLabelWithPortIcon);
            panelTransparentOnFrameOnlineMultiplayer.add(textAreaPortTip, constraintsForTextAreaPortTip);
            panelTransparentOnFrameOnlineMultiplayer.add(labelNickname, constraintsForLabelNickname);
            panelTransparentOnFrameOnlineMultiplayer.add(textFieldNickname, constraintsForTextFieldNickname);
            panelTransparentOnFrameOnlineMultiplayer.add(labelWithNicknameIcon, constraintsForLabelWithNicknameIcon);
            panelTransparentOnFrameOnlineMultiplayer.add(textAreaNicknameTip, constraintsForTextAreaNicknameTip);
            panelTransparentOnFrameOnlineMultiplayer.add(
                    panelTransparentOnFrameConnectToServer, constraintsForPanelTransparentOnFrameConnectToServer);
            repaint();
        });
    }
    private void showFrameLobby()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.LOBBY);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelTransparentOnFrameOnlineMultiplayer,
                    constraintsForPanelTransparentOnFrameOnlineMultiplayer);
            frame.getContentPane().add(statusBarOfConnection, constraintsForStatusBarOfConnection);
            panelTransparentOnFrameOnlineMultiplayer.removeAll();
            panelTransparentOnFrameOnlineMultiplayer.add(labelListOfClients, constraintsForLabelListOfClients);
            panelTransparentOnFrameOnlineMultiplayer.add(scrollPaneWithTableOfClients,
                    constraintsForScrollPaneWithTableOfClients);
            panelTransparentOnFrameOnlineMultiplayer.add(buttonBackOnFrameLobby, constraintsForButtonBackOnFrameLobby);
            repaint();
        });
    }
    private void showFrameReplay()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.REPLAY);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelBoardOnFrameReplay, constraintsForPanelBoardOnFrameReplay);
            frame.getContentPane().add(panelTransparentOnFrameReplay, constraintsForPanelTransparentOnFrameReplay);
            repaint();
        });
    }
    private void showFrameRules()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.RULES);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelBoardOnFrameRules, constraintsForPanelBoardOnFrameRules);
            frame.getContentPane().add(panelTransparentOnFrameRules, constraintsForPanelTransparentOnFrameRules);
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
            frame.getContentPane().add(buttonBackOnFrameSettings, constraintsForButtonBackOnFrameSettings);
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
                        text.getAllProgressWillBeLost(), text.getExitToMainMenu(),
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
    private void connectToServer()
    {
        SwingUtilities.invokeLater(()->
        {
            String ipAddress = documentListenerForTextFieldIp.getIpAddressWithoutLeadingZeros();
            int portNumber = Integer.parseInt(textFieldPort.getText());
            String nickname = textFieldNickname.getText();
            client.connect(ipAddress, portNumber, nickname);
        });
    }
    private void showDialogDisconnectFromServer()
    {
        SwingUtilities.invokeLater(()->
        {
            int selectedOption = JOptionPane.showOptionDialog(frame,
                    text.getConnectionWillBeLost(), text.getDisconnectFromServer(),
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[] {text.getYes(), text.getNo()}, text.getNo());
            if(selectedOption == JOptionPane.YES_OPTION)
            {
                client.disconnect();
            }
        });
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
    private void replaySlower()
    {
        SwingUtilities.invokeLater(()->
        {
            int currentDelay = timerForReplay.getDelay();
            int delay = currentDelay + 100;

            timerForReplay.setDelay(delay);
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
                timerForReplay.restart();
            }
            else //Pause.
            {
                timerForReplay.stop();
            }
        });
    }
    private void replayStop()
    {
        SwingUtilities.invokeLater(()->
        {
            timerForReplay.stop();
            replay.start();
            buttonPlayPause.setSelected(false);
        });
    }
    private void replayFaster()
    {
        SwingUtilities.invokeLater(()->
        {
            int currentDelay = timerForReplay.getDelay();
            int delay = currentDelay - 100;

            timerForReplay.setDelay(delay);
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
    private void changeGainOfMusic(ChangeEvent e)
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
    private void changeGainOfSfx(ChangeEvent e)
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
                //Delete previous frame from array, because it is going to be added later anyway in showXXXFrame().
                previousFrames.remove(size - 1);
                switch(previousFrame)
                {
                    case MAIN_MENU -> showFrameMainMenu();
                    case GAME_MODE -> showFrameGameMode();
                    case BOARD -> showFrameBoard();
                    case ONLINE_MULTIPLAYER -> showFrameOnlineMultiplayer();
                    case CONNECT_TO_SERVER -> showFrameConnectToServer();
                    case LOBBY -> showFrameLobby();
                    case REPLAY -> showFrameReplay();
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
            int selectedIndex = comboBoxLanguage.getSelectedIndex();
            switch (selectedIndex)
            {
                case 0 -> text = textEnglish;
                case 1 -> text = textRussian;
            }

            //Common frame features.
            frame.setTitle(text.getChineseChessClient());
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
            buttonBackOnFrameGameMode.setText(text.getBack());

            //Frame Online Multiplayer.
            buttonConnectToServer.setText(text.getConnectToServer());
            buttonLobby.setText(text.getLobby());
            buttonBackOnFrameOnlineMultiplayer.setText(text.getBack());
            if(client.getIsConnectedToServer())
            {
                statusBarOfConnection.setText(text.getConnectedToServer());
            }
            else
            {
                statusBarOfConnection.setText(text.getDisconnectedFromServer());
            }

            //Frame Connect to Server.
            labelIp.setText(text.getIpAddress());
            if(documentListenerForTextFieldIp.getIsIpCorrect())
            {
                textAreaIpTip.setText("");
            }
            else
            {
                textAreaIpTip.setText(text.getIpTip());
            }
            labelPort.setText(text.getPort());
            if(documentListenerForTextFieldPort.getIsPortCorrect())
            {
                textAreaPortTip.setText("");
            }
            else
            {
                textAreaPortTip.setText(text.getPortTip());
            }
            labelNickname.setText(text.getNickname());
            if(documentListenerForTextFieldNickname.getIsNicknameCorrect())
            {
                textAreaNicknameTip.setText("");
            }
            else
            {
                textAreaNicknameTip.setText(text.getNicknameTip());
            }
            buttonConnect.setText(text.getConnect());
            buttonDisconnect.setText(text.getDisconnect());
            buttonBackOnFrameConnectToServer.setText(text.getBack());

            //Frame Lobby.
            buttonBackOnFrameLobby.setText(text.getBack());

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
            buttonBackOnFrameRules.setText(text.getBack());

            //Frame Settings.
            labelLanguage.setText(text.getLanguage());
            labelMusic.setText(text.getMusic());
            labelSfx.setText(text.getSoundEffects());
            buttonBackOnFrameSettings.setText(text.getBack());

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
    public void setIpIncorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithIpIcon.setIcon(iconIncorrect);
            textAreaIpTip.setText(text.getIpTip());
            buttonConnect.setEnabled(false);
        });
    }
    public void setIpCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithIpIcon.setIcon(iconCorrect);
            textAreaIpTip.setText("");
            if(documentListenerForTextFieldPort.getIsPortCorrect() &&
               documentListenerForTextFieldNickname.getIsNicknameCorrect())
            {
                buttonConnect.setEnabled(true);
            }
        });
    }
    public void setPortIncorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithPortIcon.setIcon(iconIncorrect);
            textAreaPortTip.setText(text.getPortTip());
            buttonConnect.setEnabled(false);
        });
    }
    public void setPortCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithPortIcon.setIcon(iconCorrect);
            textAreaPortTip.setText("");
            if(documentListenerForTextFieldIp.getIsIpCorrect() &&
               documentListenerForTextFieldNickname.getIsNicknameCorrect())
            {
                buttonConnect.setEnabled(true);
            }
        });
    }
    public void setNicknameIncorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithNicknameIcon.setIcon(iconIncorrect);
            textAreaNicknameTip.setText(text.getNicknameTip());
            buttonConnect.setEnabled(false);
        });
    }
    public void setNicknameCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithNicknameIcon.setIcon(iconCorrect);
            textAreaNicknameTip.setText("");
            if(documentListenerForTextFieldIp.getIsIpCorrect() &&
               documentListenerForTextFieldPort.getIsPortCorrect())
            {
                buttonConnect.setEnabled(true);
            }
        });
    }
    public void disableButtonConnect()
    {
        buttonConnect.setEnabled(false);
    }
    public void disableButtonDisconnect()
    {
        buttonDisconnect.setEnabled(false);
    }
    public void setDisconnected()
    {
        SwingUtilities.invokeLater(()->
        {
            buttonConnect.setEnabled(
                documentListenerForTextFieldIp.getIsIpCorrect() &&
                documentListenerForTextFieldPort.getIsPortCorrect());
            buttonDisconnect.setEnabled(false);
            statusBarOfConnection.setText(text.getDisconnectedFromServer());
            for(int rowIndex = tableOfClientsModel.getRowCount() - 1; rowIndex >= 0; rowIndex--)
            {
                tableOfClientsModel.removeRow(rowIndex);
            }
        });
    }
    public void setConnected()
    {
        SwingUtilities.invokeLater(()->
        {
            buttonConnect.setEnabled(false);
            buttonDisconnect.setEnabled(true);
            statusBarOfConnection.setText(text.getConnectedToServer());
        });
    }
    public void showDialogCouldNotConnectToServer()
    {
        SwingUtilities.invokeLater(()->
                JOptionPane.showMessageDialog(frame,
                        text.getCouldNotConnectToServer(), text.getClientError(), JOptionPane.ERROR_MESSAGE)
        );
    }
    public void showDialogDisconnectedFromServer()
    {
        SwingUtilities.invokeLater(()->
                JOptionPane.showMessageDialog(frame,
                        text.getDisconnectedFromServer(), text.getClientInfo(), JOptionPane.INFORMATION_MESSAGE)
        );
    }
    public void refreshTableOfClients(HashMap<Integer, String> mapOfNicknames)
    {
        SwingUtilities.invokeLater(()->
        {
            for(int rowIndex = tableOfClientsModel.getRowCount() - 1; rowIndex >= 0; rowIndex--)
            {
                tableOfClientsModel.removeRow(rowIndex);
            }

            Set<Map.Entry<Integer, String>> setOfNicknames = mapOfNicknames.entrySet();
            for(Map.Entry<Integer, String> entryOfNicknames : setOfNicknames)
            {
                Integer hashCode = entryOfNicknames.getKey();
                String nickname = entryOfNicknames.getValue();
                tableOfClientsModel.addRow(new Object[]{hashCode.toString(), nickname.toString()});
            }
        });
    }
    public void setPanelBoardReplayGrid(HashMap<Location, Tile> grid)
    {
        panelBoardOnFrameReplay.setGrid(grid);
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
    public void stopTimerForReplay()
    {
        SwingUtilities.invokeLater(()-> timerForReplay.stop());
    }
}