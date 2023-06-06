package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

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
    private GridBagLayout gridBagLayout;
    private PanelBackground panelBackground;
    private JPanel panelEmpty;
    private JTextField statusBar;
    private GridBagConstraints constraintsForPanelEmpty;
    private GridBagConstraints constraintsForStatusBar;
    private JMenuBar menuBar;
    private JMenu menuHelp;
    private JMenuItem menuItemSettings;
    private JMenuItem menuItemAbout;

    //Frame Main Menu.
    private JButton buttonStartMainMenu;
    private JButton buttonLobby;
    private JButton buttonSettings;
    private GridBagConstraints constraintsForButtonStartMainMenu;
    private GridBagConstraints constraintsForButtonLobby;
    private GridBagConstraints constraintsForButtonSettings;

    //Frame Start.
    private boolean portCorrect;
    private JLabel labelPort;
    private JTextField textFieldPort;
    private JLabel labelPortIcon;
    private JTextArea textAreaPortWarning;
    private JButton buttonStart;
    private JButton buttonBackStart;
    private GridBagConstraints constraintsForLabelPort;
    private GridBagConstraints constraintsForTextFieldPort;
    private GridBagConstraints constraintsForLabelPortIcon;
    private GridBagConstraints constraintsForTextAreaPortWarning;
    private GridBagConstraints constraintsForButtonStart;
    private GridBagConstraints constraintsForButtonBackStart;

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
        try //TODO status bar
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

                //GridBag layout manager.
                gridBagLayout = new GridBagLayout();

                //Panel Background.
                panelBackground = new PanelBackground(background);
                panelBackground.setLayout(gridBagLayout);
                frame.setContentPane(panelBackground);

                //Panel Empty.
                panelEmpty = new JPanel();
                panelEmpty.setOpaque(false);
                panelEmpty.setLayout(gridBagLayout);
                constraintsForPanelEmpty = new GridBagConstraints(
                        0, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0);
                frame.getContentPane().add(panelEmpty, constraintsForPanelEmpty);

                //Status Bar.
                statusBar = new JTextField(text.getServerIsOff(), 1);
                statusBar.setEnabled(false);
                statusBar.setDisabledTextColor(Color.BLACK);
                statusBar.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(5, 5, 5, 5)));
                statusBar.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                constraintsForStatusBar = new GridBagConstraints(
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
                buttonStartMainMenu = new JButton(text.getStart());
                buttonStartMainMenu.setPreferredSize(new Dimension(300, 100));
                buttonStartMainMenu.setBackground(Color.WHITE);
                buttonStartMainMenu.setBorder(new LineBorder(Color.BLACK, 2));
                buttonStartMainMenu.setFont(fontChinese.deriveFont(Font.BOLD, 50.f));
                constraintsForButtonStartMainMenu = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(30, 0, 30, 0), 0, 0);
                buttonStartMainMenu.addActionListener(e->showFrameStart());

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
                //buttonLobby.addActionListener(e->); //TODO Button Lobby

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
    private void initializeFrameStart()
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            {
                portCorrect = true;

                //Label Port.
                labelPort = new JLabel(text.getPort());
                labelPort.setPreferredSize(new Dimension(250, 100));
                labelPort.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForLabelPort = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 550), 0, 0);

                //Text Field Port.
                PlainDocument plainDocumentForTextFieldPort = new PlainDocument();
                DocumentFilterForTextFieldPort documentFilterForTextFieldPort =
                        new DocumentFilterForTextFieldPort();
                plainDocumentForTextFieldPort.setDocumentFilter(documentFilterForTextFieldPort);
                DocumentListenerForTextFieldPort documentListenerForTextFieldPort =
                        new DocumentListenerForTextFieldPort(this);
                plainDocumentForTextFieldPort.addDocumentListener(documentListenerForTextFieldPort);

                textFieldPort = new JTextField(plainDocumentForTextFieldPort, "4242", 5);
                labelPort.setPreferredSize(new Dimension(100, 100));
                textFieldPort.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
                        new EmptyBorder(15, 5, 15, 5)));
                textFieldPort.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForTextFieldPort = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 250), 0, 0);

                //Label Port Icon.
                labelPortIcon = new JLabel(iconCorrect);
                constraintsForLabelPortIcon = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Label Port Status.
                textAreaPortWarning = new JTextArea(2, 10);
                textAreaPortWarning.setPreferredSize(new Dimension(250, 80));
                textAreaPortWarning.setOpaque(false);
                textAreaPortWarning.setEnabled(false);
                textAreaPortWarning.setDisabledTextColor(Color.BLACK);
                textAreaPortWarning.setLineWrap(true);
                textAreaPortWarning.setWrapStyleWord(true);
                textAreaPortWarning.setFont(fontChinese.deriveFont(Font.BOLD, 33.f));
                textAreaPortWarning.setCaretPosition(0);
                constraintsForTextAreaPortWarning = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 350, 0, 0), 0, 0);

                //Button Start.
                buttonStart = new JButton(text.getStart());
                buttonStart.setPreferredSize(new Dimension(250, 100));
                buttonStart.setBackground(Color.WHITE);
                buttonStart.setBorder(new LineBorder(Color.BLACK, 2));
                buttonStart.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonStart = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 0, 0, 400), 0, 0);
                buttonStart.addActionListener(e->startServer());

                //Button Back.
                buttonBackStart = new JButton(text.getBack());
                buttonBackStart.setPreferredSize(new Dimension(200, 100));
                buttonBackStart.setBackground(Color.WHITE);
                buttonBackStart.setBorder(new LineBorder(Color.BLACK, 2));
                buttonBackStart.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonBackStart = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(80, 400, 0, 0), 0, 0);
                buttonBackStart.addActionListener(e->showPreviousFrame());
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
            showFrameMainMenu();

            frame.setVisible(true);

            if(resourcesMissing)
            {
                JOptionPane.showMessageDialog(frame,
                        text.getSomeResourcesAreMissing(), text.getGuiWarning(),
                        JOptionPane.WARNING_MESSAGE);
            }
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
            panelEmpty.removeAll();
            panelEmpty.add(buttonStartMainMenu, constraintsForButtonStartMainMenu);
            panelEmpty.add(buttonLobby, constraintsForButtonLobby);
            panelEmpty.add(buttonSettings, constraintsForButtonSettings);
            repaint();
        });
    }
    private void showFrameStart()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.START);
            panelEmpty.removeAll();
            panelEmpty.add(labelPort, constraintsForLabelPort);
            panelEmpty.add(textFieldPort, constraintsForTextFieldPort);
            panelEmpty.add(labelPortIcon, constraintsForLabelPortIcon);
            panelEmpty.add(textAreaPortWarning, constraintsForTextAreaPortWarning);
            panelEmpty.add(buttonStart, constraintsForButtonStart);
            panelEmpty.add(buttonBackStart, constraintsForButtonBackStart);
            repaint();
        });
    }
    private void showFrameSettings()
    {
        SwingUtilities.invokeLater(()->
        {
            addToPreviousFrames(FrameType.SETTINGS);
            panelEmpty.removeAll();
            panelEmpty.add(labelLanguage, constraintsForLabelLanguage);
            panelEmpty.add(comboBoxLanguage, constraintsForComboBoxLanguage);
            panelEmpty.add(buttonApply, constraintsForButtonApply);
            panelEmpty.add(buttonBackSettings, constraintsForButtonBackSettings);
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
            if(portCorrect)
            {
                boolean portNumberParsed = false;
                int portNumber = 0;
                try
                {
                    portNumber = Integer.parseInt(textFieldPort.getText());
                    portNumberParsed = true;
                }
                catch (Exception e)
                {
                    showDialogCouldNotStartServer();
                }

                if(portNumberParsed)
                {
                    if((portNumber >= 1024) && (portNumber <= 65535))
                    {
                        server.initializeServerSocket(portNumber);
                    }
                    else
                    {
                        showDialogCouldNotStartServer();
                    }
                }
            }
            else
            {
                showDialogCouldNotStartServer();
            }
        });
    }
    private void showDialogCouldNotStartServer()
    {
        SwingUtilities.invokeLater(()->
        {
            JOptionPane.showMessageDialog(frame,
                    text.getCouldNotStartServer(), text.getServerError(), JOptionPane.ERROR_MESSAGE);
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
                    case START -> showFrameStart();
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
            if(server.getServerOn())
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
            buttonStartMainMenu.setText(text.getStart());
            buttonLobby.setText(text.getLobby());
            buttonSettings.setText(text.getSettings());

            //Frame Start.
            labelPort.setText(text.getPort());
            if(portCorrect)
            {
                textAreaPortWarning.setText("");
            }
            else
            {
                textAreaPortWarning.setText(text.getMustBeBetween());
            }
            buttonStart.setText(text.getStart());

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
            labelPortIcon.setIcon(iconIncorrect);
            textAreaPortWarning.setText(text.getMustBeBetween());
            buttonStart.setEnabled(false);
        });
    }
    public void setPortCorrect()
    {
        SwingUtilities.invokeLater(()->
        {
            portCorrect = true;
            labelPortIcon.setIcon(iconCorrect);
            textAreaPortWarning.setText("");
            buttonStart.setEnabled(true);
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