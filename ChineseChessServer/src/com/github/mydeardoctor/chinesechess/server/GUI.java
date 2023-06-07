package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.PlainDocument;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.net.URL;

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
    private final ImageIcon iconIncorrect;
    private final ImageIcon iconCorrect;

    //Previous frames.
    private final ArrayList<FrameType> previousFrames;

    //Common frame features.
    private JFrame frame;
    private JPanel panelTransparent;
    private JTextField statusBar;
    private JMenuBar menuBar;
    private JMenu menuHelp;
    private JMenuItem menuItemSettings;
    private JMenuItem menuItemAbout;

    //Frame Main Menu.
    private JButton buttonStart;
    private JButton buttonLobby;
    private JButton buttonSettings;
    private GridBagConstraints constraintsForButtonStart;
    private GridBagConstraints constraintsForButtonLobby;
    private GridBagConstraints constraintsForButtonSettings;

    //Frame Start Server.
    private boolean portCorrect;
    private boolean playersCorrect;
    private JLabel labelPort;
    private JTextField textFieldPort;
    private JLabel labelPortCorrectnessIcon;
    private JTextArea textAreaPortTip;
    private JTextArea textAreaPlayers;
    private JTextField textFieldPlayers;
    private JLabel labelPlayersCorrectnessIcon;
    private JTextArea textAreaPlayersTip;
    private JButton buttonStartServer;
    private JButton buttonBackStartServer;
    private GridBagConstraints constraintsForLabelPort;
    private GridBagConstraints constraintsForTextFieldPort;
    private GridBagConstraints constraintsForLabelPortCorrectnessIcon;
    private GridBagConstraints constraintsForTextAreaPortTip;
    private GridBagConstraints constraintsForTextAreaPlayers;
    private GridBagConstraints constraintsForTextFieldPlayers;
    private GridBagConstraints constraintsForLabelPlayersCorrectnessIcon;
    private GridBagConstraints constraintsForTextAreaPlayersTip;
    private GridBagConstraints constraintsForButtonStartServer;
    private GridBagConstraints constraintsForButtonBackStartServer;

    //Frame Settings.
    private JLabel labelLanguage;
    private JComboBox<String> comboBoxLanguage;
    private JButton buttonApply;
    private JButton buttonBackSettings;
    private GridBagConstraints constraintsForLabelLanguage;
    private GridBagConstraints constraintsForComboBoxLanguage;
    private GridBagConstraints constraintsForButtonApply;
    private GridBagConstraints constraintsForButtonBackSettings;

    //Server attributes.
    private Server server;

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
        iconIncorrect = initializeIconIncorrect();
        iconCorrect = initializeIconCorrect();

        previousFrames = new ArrayList<>();

        initializeCommonFrameFeatures();
        initializeFrameMainMenu();
        initializeFrameStartServer();
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
        }
        catch (Exception e)
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
        try //TODO status bar следить за выключением
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
                frame.setIconImage(iconFrame);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //Panel Background.
                PanelBackground panelBackground = new PanelBackground(background);
                panelBackground.setLayout(new GridBagLayout());
                frame.setContentPane(panelBackground);

                //Panel Transparent.
                panelTransparent = new JPanel();
                panelTransparent.setOpaque(false);
                panelTransparent.setLayout(new GridBagLayout());
                GridBagConstraints constraintsForPanelEmpty = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);
                frame.getContentPane().add(panelTransparent, constraintsForPanelEmpty);

                //Status Bar.
                statusBar = new JTextField(text.getServerIsOff(), 1);
                statusBar.setEnabled(false);
                statusBar.setDisabledTextColor(Color.BLACK);
                statusBar.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(5, 5, 5, 5)));
                statusBar.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                GridBagConstraints constraintsForStatusBar = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0);
                frame.getContentPane().add(statusBar, constraintsForStatusBar);

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
                //Button Start.
                buttonStart = new JButton(text.getStart());
                buttonStart.setPreferredSize(new Dimension(300, 100));
                buttonStart.setBackground(Color.WHITE);
                buttonStart.setBorder(new LineBorder(Color.BLACK, 2));
                buttonStart.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonStart = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonStart.addActionListener(e-> showFrameStartServer());

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
                //buttonLobby.addActionListener(e->); //TODO Button Lobby Action Listener

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
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void initializeFrameStartServer() //TODO stop button, when start show dialog warning
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
                        new Insets(0, 0, 0, 530), 0, 0);

                //Text Field Port.
                PlainDocument documentForTextFieldPort = new PlainDocument();
                DocumentFilterForTextFieldPort documentFilterForTextFieldPort = new DocumentFilterForTextFieldPort();
                documentForTextFieldPort.setDocumentFilter(documentFilterForTextFieldPort);
                DocumentListenerForTextFieldPort documentListenerForTextFieldPort =
                        new DocumentListenerForTextFieldPort(this);
                documentForTextFieldPort.addDocumentListener(documentListenerForTextFieldPort);

                textFieldPort = new JTextField(documentForTextFieldPort, "4242", 5);
                textFieldPort.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(15, 5, 15, 5)));
                textFieldPort.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForTextFieldPort = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 180), 0, 0);

                //Label Port Correctness Icon.
                labelPortCorrectnessIcon = new JLabel(iconCorrect);
                constraintsForLabelPortCorrectnessIcon = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 70, 0, 0), 0, 0);

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
                constraintsForTextAreaPortTip = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 440, 0, 0), 0, 0);

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
                        new Insets(0, 0, 0, 530), 0, 0);

                //Text Field Players.
                PlainDocument documentForTextFieldPlayers = new PlainDocument();
                DocumentFilterForTextFieldPlayers documentFilterForTextFieldPlayers =
                        new DocumentFilterForTextFieldPlayers();
                documentForTextFieldPlayers.setDocumentFilter(documentFilterForTextFieldPlayers);
                DocumentListenerForTextFieldPlayers documentListenerForTextFieldPlayers =
                        new DocumentListenerForTextFieldPlayers(this);
                documentForTextFieldPlayers.addDocumentListener(documentListenerForTextFieldPlayers);

                textFieldPlayers = new JTextField(documentForTextFieldPlayers, "6", 5);
                textFieldPlayers.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(15, 5, 15, 5)));
                textFieldPlayers.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForTextFieldPlayers = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 180), 0, 0);

                //Label Players Correctness Icon.
                labelPlayersCorrectnessIcon = new JLabel(iconCorrect);
                constraintsForLabelPlayersCorrectnessIcon = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 70, 0, 0), 0, 0);

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
                constraintsForTextAreaPlayersTip = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 440, 0, 0), 0, 0);

                //Button Start.
                buttonStartServer = new JButton(text.getStart());
                buttonStartServer.setPreferredSize(new Dimension(250, 100));
                buttonStartServer.setBackground(Color.WHITE);
                buttonStartServer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonStartServer.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonStartServer = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 0, 0, 400), 0, 0);
                buttonStartServer.addActionListener(e->startServer());

                //Button Back.
                buttonBackStartServer = new JButton(text.getBack());
                buttonBackStartServer.setPreferredSize(new Dimension(200, 100));
                buttonBackStartServer.setBackground(Color.WHITE);
                buttonBackStartServer.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackStartServer.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonBackStartServer = new GridBagConstraints(
                        0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 400, 0, 0), 0, 0);
                buttonBackStartServer.addActionListener(e->showPreviousFrame());

                setPortCorrect();
                setPlayersCorrect();
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
            panelTransparent.removeAll();
            panelTransparent.add(buttonStart, constraintsForButtonStart);
            panelTransparent.add(buttonLobby, constraintsForButtonLobby);
            panelTransparent.add(buttonSettings, constraintsForButtonSettings);
            repaint();
        });
    }
    private void showFrameStartServer()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.START_SERVER);
            panelTransparent.removeAll();
            panelTransparent.add(labelPort, constraintsForLabelPort);
            panelTransparent.add(textFieldPort, constraintsForTextFieldPort);
            panelTransparent.add(labelPortCorrectnessIcon, constraintsForLabelPortCorrectnessIcon);
            panelTransparent.add(textAreaPortTip, constraintsForTextAreaPortTip);
            panelTransparent.add(textAreaPlayers, constraintsForTextAreaPlayers);
            panelTransparent.add(textFieldPlayers, constraintsForTextFieldPlayers);
            panelTransparent.add(labelPlayersCorrectnessIcon, constraintsForLabelPlayersCorrectnessIcon);
            panelTransparent.add(textAreaPlayersTip, constraintsForTextAreaPlayersTip);
            panelTransparent.add(buttonStartServer, constraintsForButtonStartServer);
            panelTransparent.add(buttonBackStartServer, constraintsForButtonBackStartServer);
            repaint();
        });
    }
    private void showFrameSettings()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.SETTINGS);
            panelTransparent.removeAll();
            panelTransparent.add(labelLanguage, constraintsForLabelLanguage);
            panelTransparent.add(comboBoxLanguage, constraintsForComboBoxLanguage);
            panelTransparent.add(buttonApply, constraintsForButtonApply);
            panelTransparent.add(buttonBackSettings, constraintsForButtonBackSettings);
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
            try
            {
                int portNumber = Integer.parseInt(textFieldPort.getText());
                int maximumNumberOfPlayers = Integer.parseInt(textFieldPlayers.getText());
                server.start(portNumber, maximumNumberOfPlayers);
            }
            catch (Exception e)
            {
                showDialogCouldNotStartServer();
            }
        });
    }
    private void showDialogCouldNotStartServer()
    {
        SwingUtilities.invokeLater(()->
                JOptionPane.showMessageDialog(frame,
                        text.getCouldNotStartServer(), text.getServerError(), JOptionPane.ERROR_MESSAGE)
        );
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
                    case START_SERVER -> showFrameStartServer();
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
            if(server.getIsServerOn())
            {
                statusBar.setText(text.getServerIsOn());
            }
            else
            {
                statusBar.setText(text.getServerIsOff());
            }
            menuHelp.setText(text.getHelp());
            menuItemSettings.setText(text.getSettings());
            menuItemAbout.setText(text.getAbout());

            //Frame Main Menu.
            buttonStart.setText(text.getStart());
            buttonLobby.setText(text.getLobby());
            buttonSettings.setText(text.getSettings());

            //Frame Start Server.
            labelPort.setText(text.getPort());
            if(portCorrect)
            {
                textAreaPortTip.setText("");
            }
            else
            {
                textAreaPortTip.setText(text.getPortMustBeBetween());
            }
            textAreaPlayers.setText(text.getMaximumNumberOfPlayers());
            if(playersCorrect)
            {
                textAreaPlayersTip.setText("");
            }
            else
            {
                textAreaPlayersTip.setText(text.getPlayersMustBeBetween());
            }
            buttonStartServer.setText(text.getStart());
            buttonBackStartServer.setText(text.getBack());

            //Frame Settings.
            labelLanguage.setText(text.getLanguage());
            buttonApply.setText(text.getApply());
            buttonBackSettings.setText(text.getBack());
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
    public void setPortIncorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            portCorrect = false;
            labelPortCorrectnessIcon.setIcon(iconIncorrect);
            textAreaPortTip.setText(text.getPortMustBeBetween());
            buttonStartServer.setEnabled(false);
        });
    }
    public void setPortCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            portCorrect = true;
            labelPortCorrectnessIcon.setIcon(iconCorrect);
            textAreaPortTip.setText("");
            if(playersCorrect)
            {
                buttonStartServer.setEnabled(true);
            }
        });
    }
    public void setPlayersIncorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            playersCorrect = false;
            labelPlayersCorrectnessIcon.setIcon(iconIncorrect);
            textAreaPlayersTip.setText(text.getPlayersMustBeBetween());
            buttonStartServer.setEnabled(false);
        });
    }
    public void setPlayersCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            playersCorrect = true;
            labelPlayersCorrectnessIcon.setIcon(iconCorrect);
            textAreaPlayersTip.setText("");
            if(portCorrect)
            {
                buttonStartServer.setEnabled(true);
            }
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
}