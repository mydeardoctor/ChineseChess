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
    private PanelBackground panelBackground;
    private GridBagLayout gridBagLayout;
    private JMenuBar menuBar;
    private JMenu menuHelp;
    private JMenuItem menuItemSettings;
    private JMenuItem menuItemAbout;

    //Frame Main menu.
    private JLabel labelPort;
    private JTextField textFieldPort;
    private JLabel labelPortCheck;
    private JLabel labelPortStatus;
    private JButton buttonSetUp;
    private GridBagConstraints constraintsForLabelPort;
    private GridBagConstraints constraintsForTextFieldPort;
    private GridBagConstraints constraintsForLabelPortCheck;
    private GridBagConstraints constraintsForLabelPortStatus;
    private GridBagConstraints constraintsForButtonSetUp;

    //Frame Settings.
    private JLabel labelLanguage;
    private JComboBox<String> comboBoxLanguage;
    private JButton buttonApply;
    private JButton buttonBackSettings;
    private GridBagConstraints constraintsForLabelLanguage;
    private GridBagConstraints constraintsForComboBoxLanguage;
    private GridBagConstraints constraintsForButtonApply;
    private GridBagConstraints constraintsForButtonBackSettings;

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
                frame.setIconImage(iconFrame);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //Panel Background.
                panelBackground = new PanelBackground(background);
                frame.setContentPane(panelBackground);

                //GridBag layout manager.
                gridBagLayout = new GridBagLayout();
                frame.getContentPane().setLayout(gridBagLayout);

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
                //textFieldPort.setFont(new Font(Font.DIALOG, Font.PLAIN, 46)); //TODO
                textFieldPort.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForTextFieldPort = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 250), 0, 0);

                //Label Port Check.
                //labelPortCheck = new JLabel(iconIncorrect); //TODO
                labelPortCheck = new JLabel(iconCorrect); //TODO
                // labelPortCheck.setPreferredSize(new Dimension(250, 100));
                constraintsForLabelPortCheck = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Label Port Status.
                labelPortStatus = new JLabel("must be between 1024 and 65535"); //TODO
                labelPortStatus.setPreferredSize(new Dimension(250, 100));
                labelPortStatus.setFont(fontChinese.deriveFont(Font.BOLD, 20.f));
                constraintsForLabelPortStatus = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 350, 0, 0), 0, 0);

                //Button Set Up.
                buttonSetUp = new JButton(text.getSetUp());
                buttonSetUp.setPreferredSize(new Dimension(250, 100));
                buttonSetUp.setBackground(Color.WHITE);
                buttonSetUp.setBorder(new LineBorder(Color.BLACK, 2));
                buttonSetUp.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonSetUp = new GridBagConstraints(
                        0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
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
            frame.getContentPane().removeAll();
            frame.getContentPane().add(labelPort, constraintsForLabelPort);
            frame.getContentPane().add(textFieldPort, constraintsForTextFieldPort);
            frame.getContentPane().add(labelPortCheck, constraintsForLabelPortCheck);
            frame.getContentPane().add(labelPortStatus, constraintsForLabelPortStatus);
            frame.getContentPane().add(buttonSetUp, constraintsForButtonSetUp);
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
            frame.getContentPane().add(buttonApply, constraintsForButtonApply);
            frame.getContentPane().add(buttonBackSettings, constraintsForButtonBackSettings);
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
    public void setIconIncorrectToLabelPortCheck()
    {
        SwingUtilities.invokeLater(()-> labelPortCheck.setIcon(iconIncorrect));
    }
    public void setIconCorrectToLabelPortCheck()
    {
        SwingUtilities.invokeLater(()-> labelPortCheck.setIcon(iconCorrect));
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
            menuHelp.setText(text.getHelp());
            menuItemSettings.setText(text.getSettings());
            menuItemAbout.setText(text.getAbout());

            //Frame Main Menu.
            labelPort.setText(text.getPort());
            buttonSetUp.setText(text.getSetUp());

            //Frame Settings.
            labelLanguage.setText(text.getLanguage());
            buttonApply.setText(text.getApply());
            buttonBackSettings.setText(text.getBack());
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
}