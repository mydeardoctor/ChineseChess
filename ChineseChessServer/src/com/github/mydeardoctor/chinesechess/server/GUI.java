package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.PlainDocument;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.net.URL;
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
    private final ImageIcon iconIncorrect;
    private final ImageIcon iconCorrect;

    //Previous frames.
    private final ArrayList<FrameType> previousFrames;

    //Common frame features.
    private JFrame frame;
    private GridBagLayout gridBagLayout;
    private PanelBackground panelBackground;
    private JPanel panelTransparent;
    private JTextField statusBar;
    private GridBagConstraints constraintsForPanelTransparent;
    private GridBagConstraints constraintsForStatusBar;
    private JMenuBar menuBar;
    private JMenu menuHelp;
    private JMenuItem menuItemSettings;
    private JMenuItem menuItemAbout;


    //Frame Main Menu.
    private JButton buttonStartOnFrameMainMenu;
    private JButton buttonLobby;
    private JButton buttonSettings;
    private GridBagConstraints constraintsForButtonStartOnFrameMainMenu;
    private GridBagConstraints constraintsForButtonLobby;
    private GridBagConstraints constraintsForButtonSettings;

    //Frame Start.
    private JLabel labelPort;
    private DocumentListenerForTextFieldPort documentListenerForTextFieldPort;
    private JTextField textFieldPort;
    private JLabel labelWithPortIcon;
    private JTextArea textAreaPortTip;
    private JTextArea textAreaPlayers;
    private DocumentListenerForTextFieldPlayers documentListenerForTextFieldPlayers;
    private JTextField textFieldPlayers;
    private JLabel labelWithPlayersIcon;
    private JTextArea textAreaPlayersTip;
    private JPanel panelTransparentOnFrameStart;
    private JButton buttonStartOnFrameStart;
    private JButton buttonStop;
    private JButton buttonBackOnFrameStart;
    private GridBagConstraints constraintsForLabelPort;
    private GridBagConstraints constraintsForTextFieldPort;
    private GridBagConstraints constraintsForLabelWithPortIcon;
    private GridBagConstraints constraintsForTextAreaPortTip;
    private GridBagConstraints constraintsForTextAreaPlayers;
    private GridBagConstraints constraintsForTextFieldPlayers;
    private GridBagConstraints constraintsForLabelWithPlayersIcon;
    private GridBagConstraints constraintsForTextAreaPlayersTip;
    private GridBagConstraints constraintsForPanelTransparentOnFrameStart;

    //Frame Settings.
    private JLabel labelLanguage;
    private JComboBox<String> comboBoxLanguage;
    private JButton buttonBackOnFrameSettings;
    private GridBagConstraints constraintsForLabelLanguage;
    private GridBagConstraints constraintsForComboBoxLanguage;
    private GridBagConstraints constraintsForButtonBackOnFrameSettings;

    //Server attributes.
    private Server server;

    //Logger.
    private static final Logger logger = Logger.getLogger(GUI.class.getName());

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
        backgroundImage = initializeBackgroundImage();
        iconIncorrect = initializeIconIncorrect();
        iconCorrect = initializeIconCorrect();

        previousFrames = new ArrayList<>();

        initializeCommonFrameFeatures();
        initializeFrameMainMenu();
        initializeFrameStart();
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
    private void initializeCommonFrameFeatures()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Frame.
                frame = new JFrame(text.getChineseChessServer());
                frame.setMinimumSize(new Dimension(800,800));
                frame.setBounds((int)frame.getGraphicsConfiguration().getBounds().getCenterX() -
                                        (int)frame.getBounds().getCenterX(),
                                (int)frame.getGraphicsConfiguration().getBounds().getCenterY() -
                                        (int)frame.getBounds().getCenterY(),
                               800,800);
                frame.setIconImage(iconFrame);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //GridBag layout manager.
                gridBagLayout = new GridBagLayout();

                //Panel Background.
                panelBackground = new PanelBackground(backgroundImage);
                panelBackground.setLayout(gridBagLayout);
                frame.setContentPane(panelBackground);

                //Panel Transparent.
                panelTransparent = new JPanel();
                panelTransparent.setOpaque(false);
                panelTransparent.setLayout(gridBagLayout);
                constraintsForPanelTransparent = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Status Bar.
                statusBar = new JTextField(text.getServerIsStopped(), 1);
                statusBar.setEnabled(false);
                statusBar.setDisabledTextColor(Color.BLACK);
                statusBar.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(5, 5, 5, 5)));
                statusBar.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                constraintsForStatusBar = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Menu Bar.
                menuBar = new JMenuBar();
                menuHelp = new JMenu(text.getHelp());
                menuItemSettings = new JMenuItem(text.getSettings());
                menuItemSettings.addActionListener(e->showFrameSettings());
                menuItemAbout = new JMenuItem(text.getAbout());
                menuItemAbout.addActionListener(e-> showDialogAbout());
                menuHelp.add(menuItemSettings);
                menuHelp.add(menuItemAbout);
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
                //Button Start.
                buttonStartOnFrameMainMenu = new JButton(text.getStart());
                buttonStartOnFrameMainMenu.setPreferredSize(new Dimension(300, 100));
                buttonStartOnFrameMainMenu.setBackground(Color.WHITE);
                buttonStartOnFrameMainMenu.setBorder(new LineBorder(Color.BLACK, 2));
                buttonStartOnFrameMainMenu.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonStartOnFrameMainMenu = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonStartOnFrameMainMenu.addActionListener(e-> showFrameStart());

                //Button Lobby.
                buttonLobby = new JButton(text.getLobby());
                buttonLobby.setPreferredSize(new Dimension(300, 100));
                buttonLobby.setBackground(Color.WHITE);
                buttonLobby.setBorder(new LineBorder(Color.BLACK, 2));
                buttonLobby.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonLobby = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                //TODO Frame Lobby. Скопировать после создания в Client
                //buttonLobby.addActionListener(e->);

                //Button Settings.
                buttonSettings = new JButton(text.getSettings());
                buttonSettings.setPreferredSize(new Dimension(300, 100));
                buttonSettings.setBackground(Color.WHITE);
                buttonSettings.setBorder(new LineBorder(Color.BLACK, 2));
                buttonSettings.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonSettings = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
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
    private void initializeFrameStart()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                //Label Port.
                labelPort = new JLabel(text.getPort());
                labelPort.setPreferredSize(new Dimension(100, 100));
                labelPort.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForLabelPort = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 10, 0, 10), 0, 0);

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
                textFieldPort.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForTextFieldPort = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 10, 0, 10), 0, 0);

                //Label Port Correctness Icon.
                labelWithPortIcon = new JLabel(iconCorrect);
                constraintsForLabelWithPortIcon = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 10, 0, 10), 0, 0);

                //Text Area Port Tip.
                textAreaPortTip = new JTextArea(2, 10);
                textAreaPortTip.setPreferredSize(new Dimension(250, 80));
                textAreaPortTip.setOpaque(false);
                textAreaPortTip.setEnabled(false);
                textAreaPortTip.setDisabledTextColor(Color.BLACK);
                textAreaPortTip.setLineWrap(true);
                textAreaPortTip.setWrapStyleWord(true);
                textAreaPortTip.setFont(fontChinese.deriveFont(Font.BOLD, 33.f));
                textAreaPortTip.setCaretPosition(0);
                textAreaPortTip.setText("");
                constraintsForTextAreaPortTip = new GridBagConstraints(
                        3, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 20, 0, 10), 0, 0);

                //Text Area Players.
                textAreaPlayers = new JTextArea(2, 10);
                textAreaPlayers.setPreferredSize(new Dimension(200, 80));
                textAreaPlayers.setOpaque(false);
                textAreaPlayers.setEnabled(false);
                textAreaPlayers.setDisabledTextColor(Color.BLACK);
                textAreaPlayers.setLineWrap(true);
                textAreaPlayers.setWrapStyleWord(true);
                textAreaPlayers.setFont(fontChinese.deriveFont(Font.BOLD, 36.f));
                textAreaPlayers.setText(text.getMaximumNumberOfPlayers());
                textAreaPlayers.setCaretPosition(0);
                constraintsForTextAreaPlayers = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Text Field Players.
                PlainDocument documentForTextFieldPlayers = new PlainDocument();
                DocumentFilterForTextFieldPlayers documentFilterForTextFieldPlayers =
                        new DocumentFilterForTextFieldPlayers();
                documentForTextFieldPlayers.setDocumentFilter(documentFilterForTextFieldPlayers);
                documentListenerForTextFieldPlayers = new DocumentListenerForTextFieldPlayers(this);
                documentForTextFieldPlayers.addDocumentListener(documentListenerForTextFieldPlayers);

                textFieldPlayers = new JTextField(documentForTextFieldPlayers, "2", 5);
                textFieldPlayers.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(15, 5, 15, 5)));
                textFieldPlayers.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForTextFieldPlayers = new GridBagConstraints(
                        1, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Label Players Correctness Icon.
                labelWithPlayersIcon = new JLabel(iconCorrect);
                constraintsForLabelWithPlayersIcon = new GridBagConstraints(
                        2, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 10, 0, 10), 0, 0);

                //Text Area Players Tip.
                textAreaPlayersTip = new JTextArea(2, 10);
                textAreaPlayersTip.setPreferredSize(new Dimension(250, 80));
                textAreaPlayersTip.setOpaque(false);
                textAreaPlayersTip.setEnabled(false);
                textAreaPlayersTip.setDisabledTextColor(Color.BLACK);
                textAreaPlayersTip.setLineWrap(true);
                textAreaPlayersTip.setWrapStyleWord(true);
                textAreaPlayersTip.setFont(fontChinese.deriveFont(Font.BOLD, 33.f));
                textAreaPlayersTip.setCaretPosition(0);
                textAreaPlayersTip.setText("");
                constraintsForTextAreaPlayersTip = new GridBagConstraints(
                        3, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 20, 0, 10), 0, 0);

                //Panel Transparent.
                panelTransparentOnFrameStart = new JPanel();
                panelTransparentOnFrameStart.setOpaque(false);
                panelTransparentOnFrameStart.setLayout(gridBagLayout);
                constraintsForPanelTransparentOnFrameStart = new GridBagConstraints(
                        0, 2, 4, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Button Start.
                buttonStartOnFrameStart = new JButton(text.getStart());
                buttonStartOnFrameStart.setPreferredSize(new Dimension(220, 100));
                buttonStartOnFrameStart.setBackground(Color.WHITE);
                buttonStartOnFrameStart.setBorder(new LineBorder(Color.BLACK, 2));
                buttonStartOnFrameStart.setFont(fontChinese.deriveFont(Font.BOLD, 40.f));
                buttonStartOnFrameStart.setEnabled(true);
                GridBagConstraints constraintsForButtonStartServer = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 20, 0, 20), 0, 0);
                buttonStartOnFrameStart.addActionListener(e->startServer());
                panelTransparentOnFrameStart.add(buttonStartOnFrameStart, constraintsForButtonStartServer);

                //Button Stop.
                buttonStop = new JButton(text.getStop());
                buttonStop.setPreferredSize(new Dimension(220, 100));
                buttonStop.setBackground(Color.WHITE);
                buttonStop.setBorder(new LineBorder(Color.BLACK, 2));
                buttonStop.setFont(fontChinese.deriveFont(Font.BOLD, 40.f));
                buttonStop.setEnabled(false);
                GridBagConstraints constraintsForButtonStopServer = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 20, 0, 20), 0, 0);
                buttonStop.addActionListener(e->showDialogStopServer());
                panelTransparentOnFrameStart.add(buttonStop, constraintsForButtonStopServer);

                //Button Back.
                buttonBackOnFrameStart = new JButton(text.getBack());
                buttonBackOnFrameStart.setPreferredSize(new Dimension(150, 100));
                buttonBackOnFrameStart.setBackground(Color.WHITE);
                buttonBackOnFrameStart.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackOnFrameStart.setFont(fontChinese.deriveFont(Font.BOLD, 40.f));
                GridBagConstraints constraintsForButtonBackStartServer = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 20, 0, 20), 0, 0);
                buttonBackOnFrameStart.addActionListener(e->showPreviousFrame());
                panelTransparentOnFrameStart.add(buttonBackOnFrameStart, constraintsForButtonBackStartServer);
            });
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initializeFrameStart",
                    "Could not initialize frame Start.", e);
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
                labelLanguage.setPreferredSize(new Dimension(200, 100));
                labelLanguage.setFont(fontChinese.deriveFont(Font.BOLD, 37.f));
                constraintsForLabelLanguage = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 30, 0, 30), 0, 0);

                //ComboBox Language.
                comboBoxLanguage = new JComboBox<>();
                comboBoxLanguage.setPreferredSize(new Dimension(250, 80));
                comboBoxLanguage.setFont(fontChinese.deriveFont(Font.BOLD, 37.f));
                comboBoxLanguage.addItem("English");
                comboBoxLanguage.addItem("Русский");
                constraintsForComboBoxLanguage = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 30, 10, 30), 0, 0);
                comboBoxLanguage.addActionListener(e->refreshText());

                //Button Back.
                buttonBackOnFrameSettings = new JButton(text.getBack());
                buttonBackOnFrameSettings.setPreferredSize(new Dimension(200, 100));
                buttonBackOnFrameSettings.setBackground(Color.WHITE);
                buttonBackOnFrameSettings.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackOnFrameSettings.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonBackOnFrameSettings = new GridBagConstraints(
                        0, 1, 2, 1, 0, 0,
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
    public void setServer(Server server)
    {
        this.server = server;
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
            frame.getContentPane().add(panelTransparent, constraintsForPanelTransparent);
            frame.getContentPane().add(statusBar, constraintsForStatusBar);
            panelTransparent.removeAll();
            panelTransparent.add(buttonStartOnFrameMainMenu, constraintsForButtonStartOnFrameMainMenu);
            panelTransparent.add(buttonLobby, constraintsForButtonLobby);
            panelTransparent.add(buttonSettings, constraintsForButtonSettings);
            repaint();
        });
    }
    private void showFrameStart()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.START);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelTransparent, constraintsForPanelTransparent);
            frame.getContentPane().add(statusBar, constraintsForStatusBar);
            panelTransparent.removeAll();
            panelTransparent.add(labelPort, constraintsForLabelPort);
            panelTransparent.add(textFieldPort, constraintsForTextFieldPort);
            panelTransparent.add(labelWithPortIcon, constraintsForLabelWithPortIcon);
            panelTransparent.add(textAreaPortTip, constraintsForTextAreaPortTip);
            panelTransparent.add(textAreaPlayers, constraintsForTextAreaPlayers);
            panelTransparent.add(textFieldPlayers, constraintsForTextFieldPlayers);
            panelTransparent.add(labelWithPlayersIcon, constraintsForLabelWithPlayersIcon);
            panelTransparent.add(textAreaPlayersTip, constraintsForTextAreaPlayersTip);
            panelTransparent.add(panelTransparentOnFrameStart, constraintsForPanelTransparentOnFrameStart);
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
            frame.getContentPane().add(buttonBackOnFrameSettings, constraintsForButtonBackOnFrameSettings);
            repaint();
        });
    }
    private void showDialogAbout()
    {
        SwingUtilities.invokeLater(()->
                JOptionPane.showMessageDialog(frame,
                        text.getAboutVerbose(), text.getAbout(), JOptionPane.INFORMATION_MESSAGE)
        );
    }
    private void startServer()
    {
        SwingUtilities.invokeLater(()->
        {
            int portNumber = Integer.parseInt(textFieldPort.getText());
            int maximumNumberOfPlayers = Integer.parseInt(textFieldPlayers.getText());
            server.start(portNumber, maximumNumberOfPlayers);
        });
    }
    private void showDialogStopServer()
    {
        SwingUtilities.invokeLater(()->
        {
            int selectedOption = JOptionPane.showOptionDialog(frame,
                    text.getAreYouSure(), text.getServerStop(),
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[] {text.getYes(), text.getNo()}, text.getNo());
            if(selectedOption == JOptionPane.YES_OPTION)
            {
               server.stop();
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
                    case START -> showFrameStart();
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
            frame.setTitle(text.getChineseChessServer());
            if(server.getIsServerOn())
            {
                statusBar.setText(text.getServerIsRunning());
            }
            else
            {
                statusBar.setText(text.getServerIsStopped());
            }
            menuHelp.setText(text.getHelp());
            menuItemSettings.setText(text.getSettings());
            menuItemAbout.setText(text.getAbout());

            //Frame Main Menu.
            buttonStartOnFrameMainMenu.setText(text.getStart());
            buttonLobby.setText(text.getLobby());
            buttonSettings.setText(text.getSettings());

            //Frame Start Server.
            labelPort.setText(text.getPort());
            if(documentListenerForTextFieldPort.getIsPortCorrect())
            {
                textAreaPortTip.setText("");
            }
            else
            {
                textAreaPortTip.setText(text.getPortTip());
            }
            textAreaPlayers.setText(text.getMaximumNumberOfPlayers());
            if(documentListenerForTextFieldPlayers.getArePlayersCorrect())
            {
                textAreaPlayersTip.setText("");
            }
            else
            {
                textAreaPlayersTip.setText(text.getPlayersTip());
            }
            buttonStartOnFrameStart.setText(text.getStart());
            buttonStop.setText(text.getStop());
            buttonBackOnFrameStart.setText(text.getBack());

            //Frame Settings.
            labelLanguage.setText(text.getLanguage());
            buttonBackOnFrameSettings.setText(text.getBack());
        });
    }
    private void repaint()
    {
        SwingUtilities.invokeLater(()->
        {
            frame.revalidate();
            frame.repaint();
        });
    }
    public void setStatusBarText(String message)
    {
        SwingUtilities.invokeLater(()->statusBar.setText(message));
    }
    public void setPortIncorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithPortIcon.setIcon(iconIncorrect);
            textAreaPortTip.setText(text.getPortTip());
            buttonStartOnFrameStart.setEnabled(false);
        });
    }
    public void setPortCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithPortIcon.setIcon(iconCorrect);
            textAreaPortTip.setText("");
            if(documentListenerForTextFieldPlayers.getArePlayersCorrect())
            {
                buttonStartOnFrameStart.setEnabled(true);
            }
        });
    }
    public void setPlayersIncorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithPlayersIcon.setIcon(iconIncorrect);
            textAreaPlayersTip.setText(text.getPlayersTip());
            buttonStartOnFrameStart.setEnabled(false);
        });
    }
    public void setPlayersCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            labelWithPlayersIcon.setIcon(iconCorrect);
            textAreaPlayersTip.setText("");
            if(documentListenerForTextFieldPort.getIsPortCorrect())
            {
                buttonStartOnFrameStart.setEnabled(true);
            }
        });
    }
    public void setServerStopped()
    {
        setStatusBarText(text.getServerIsStopped());
        buttonStartOnFrameStart.setEnabled(
                documentListenerForTextFieldPort.getIsPortCorrect() &&
                documentListenerForTextFieldPlayers.getArePlayersCorrect());
        buttonStop.setEnabled(false);
    }
    public void setServerRunning()
    {
        setStatusBarText(text.getServerIsRunning());
        buttonStartOnFrameStart.setEnabled(false);
        buttonStop.setEnabled(true);
    }
    public void showDialogCouldNotStartServer()
    {
        SwingUtilities.invokeLater(()->
                JOptionPane.showMessageDialog(frame,
                        text.getCouldNotStartServer(), text.getServerError(), JOptionPane.ERROR_MESSAGE)
        );
    }
}