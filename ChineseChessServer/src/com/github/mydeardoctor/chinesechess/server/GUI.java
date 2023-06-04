package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    //Common frame features.
    private JFrame frame;
    private PanelBackground panelBackground;
    private GridBagLayout gridBagLayout;
    private JLabel labelPort;
    private JButton buttonSetUp;
    GridBagConstraints constraintsForLabelPort;
    GridBagConstraints constraintsForButtonSetUp;

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

        initializeCommonFrameFeatures();
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

                //Label Port.
                labelPort = new JLabel(text.getPort());
                labelPort.setPreferredSize(new Dimension(300, 100));
                labelPort.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForLabelPort = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0);

                //Button Set Up.
                buttonSetUp = new JButton(text.getSetUp());
                buttonSetUp.setPreferredSize(new Dimension(200, 100));
                buttonSetUp.setBackground(Color.WHITE);
                buttonSetUp.setBorder(new LineBorder(Color.BLACK, 2));
                buttonSetUp.setFont(fontChinese.deriveFont(Font.BOLD, 46.f));
                constraintsForButtonSetUp = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0);
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
            showCommonFrame();

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
    private void showCommonFrame()
    {
        SwingUtilities.invokeLater(()->
        {
            frame.getContentPane().removeAll(); //TODO
            frame.getContentPane().add(labelPort, constraintsForLabelPort);
            frame.getContentPane().add(buttonSetUp, constraintsForButtonSetUp);
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
}