package com.github.mydeardoctor.chinesechess;

import java.util.Random;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.net.URL;

class Game
{
    private Text text;
    private PanelBoard panelBoardReference;
    private JLabel statusBarReference;
    private BufferedImage advisorBlack;
    private BufferedImage advisorRed;
    private BufferedImage cannonBlack;
    private BufferedImage cannonRed;
    private BufferedImage chariotBlack;
    private BufferedImage chariotRed;
    private BufferedImage elephantBlack;
    private BufferedImage elephantRed;
    private BufferedImage generalBlack;
    private BufferedImage generalRed;
    private BufferedImage horseBlack;
    private BufferedImage horseRed;
    private BufferedImage soldierBlack;
    private BufferedImage soldierRed;
    ArrayList<Player> players;
    private Player playerRed;
    private Player playerBlack;
    private Random randomGenerator;
    private ArrayList<Figure> figures;
    Game(Text text, PanelBoard panelBoardReference, JLabel statusBarReference)
    {
        this.text = text;
        this.panelBoardReference = panelBoardReference;
        this.statusBarReference = statusBarReference;
        iconsInit();
        players = new ArrayList<>();
        randomGenerator = new Random();
        figures = new ArrayList<>();
    }
    @SuppressWarnings("DataFlowIssue")
    private void iconsInit()
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
    public void gameStart()
    {
        setRandomSides();
        figuresInit();
    }
    private void setRandomSides()
    {
        players.clear();
        players.add(Player.HUMAN);
        players.add(Player.CPU);
        int randomIndex = randomGenerator.nextInt(2);
        playerRed = players.get(randomIndex);
        players.remove(randomIndex);
        playerBlack = players.get(0);

        if(Player.HUMAN == playerRed)
        {
            statusBarReference.setText(text.getPlayerRed());
        }
        else //Player.HUMAN == playerBlack
        {
            statusBarReference.setText(text.getPlayerBlack());
        }
    }
    private void figuresInit()
    {
        figures.clear();

        //Black figures.
        figures.add(new Chariot(playerBlack, chariotBlack, 0,0));
        figures.add(new Horse(playerBlack, horseBlack, 1,0));
        figures.add(new Elephant(playerBlack, elephantBlack, 2,0));
        figures.add(new Advisor(playerBlack, advisorBlack, 3,0));
        figures.add(new General(playerBlack, generalBlack, 0));
        figures.add(new Advisor(playerBlack, advisorBlack, 5,0));
        figures.add(new Elephant(playerBlack, elephantBlack, 6,0));
        figures.add(new Horse(playerBlack, horseBlack, 7,0));
        figures.add(new Chariot(playerBlack, chariotBlack, 8,0));
        figures.add(new Cannon(playerBlack, cannonBlack, 1,2));
        figures.add(new Cannon(playerBlack, cannonBlack, 7,2));
        figures.add(new Soldier(playerBlack, soldierBlack, 0,3));
        figures.add(new Soldier(playerBlack, soldierBlack, 2,3));
        figures.add(new Soldier(playerBlack, soldierBlack, 4,3));
        figures.add(new Soldier(playerBlack, soldierBlack, 6,3));
        figures.add(new Soldier(playerBlack, soldierBlack, 8,3));

        //Red figures.
        figures.add(new Soldier(playerRed, soldierRed, 0,6));
        figures.add(new Soldier(playerRed, soldierRed, 2,6));
        figures.add(new Soldier(playerRed, soldierRed, 4,6));
        figures.add(new Soldier(playerRed, soldierRed, 6,6));
        figures.add(new Soldier(playerRed, soldierRed, 8,6));
        figures.add(new Cannon(playerRed, cannonRed, 1,7));
        figures.add(new Cannon(playerRed, cannonRed, 7,7));
        figures.add(new Chariot(playerRed, chariotRed, 0,9));
        figures.add(new Horse(playerRed, horseRed, 1,9));
        figures.add(new Elephant(playerRed, elephantRed, 2,9));
        figures.add(new Advisor(playerRed, advisorRed, 3,9));
        figures.add(new General(playerRed, generalRed, 9));
        figures.add(new Advisor(playerRed, advisorRed, 5,9));
        figures.add(new Elephant(playerRed, elephantRed, 6,9));
        figures.add(new Horse(playerRed, horseRed, 7,9));
        figures.add(new Chariot(playerRed, chariotRed, 8,9));
    }
    public void refreshText(Text text)
    {
        this.text = text;
        statusBarReference.setText(" ");
    }
    public ArrayList<Figure> getFigures()
    {
        return figures;
    }
}
