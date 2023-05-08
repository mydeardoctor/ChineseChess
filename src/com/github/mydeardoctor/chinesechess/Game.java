package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
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
    private BufferedImage selection;
    ArrayList<Player> players;
    private Player playerRed;
    private Player playerBlack;
    private Random randomGenerator;
    private HashMap<GridLocation, GridTile> grid;
    private GameState gameState;
    private Figure prevFigureSelected;
    private GridLocation prevGridLocationSelected;
    Game(Text text, PanelBoard panelBoardReference, JLabel statusBarReference)
    {
        this.text = text;
        this.panelBoardReference = panelBoardReference;
        this.statusBarReference = statusBarReference;
        iconsInit();
        players = new ArrayList<>();
        randomGenerator = new Random();
        grid = new HashMap<>();
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
        url = getClass().getResource("/selection.png");
        try
        {
            selection = ImageIO.read(url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, text.getErrorSelection(), text.getError(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public void gameStart()
    {
        setRandomSides();
        gridInit();
        gameStateInit();
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
    private void gridInit()
    {
        grid.clear();

        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <=8;  x++)
            {
                GridLocation gridLocation = new GridLocation(x,y);
                GridTile gridTile = new GridTile(null, null);
                grid.put(gridLocation, gridTile);
            }
        }
        boolean result = grid.containsKey(new GridLocation(8,8));
        //Black figures.
        grid.get(new GridLocation(0,0)).setFigure(new Chariot(playerBlack, chariotBlack));
        grid.get(new GridLocation(1,0)).setFigure(new Horse(playerBlack, horseBlack));
        grid.get(new GridLocation(2,0)).setFigure(new Elephant(playerBlack, elephantBlack));
        grid.get(new GridLocation(3,0)).setFigure(new Advisor(playerBlack, advisorBlack));
        grid.get(new GridLocation(4,0)).setFigure(new General(playerBlack, generalBlack));
        grid.get(new GridLocation(5,0)).setFigure(new Advisor(playerBlack, advisorBlack));
        grid.get(new GridLocation(6,0)).setFigure(new Elephant(playerBlack, elephantBlack));
        grid.get(new GridLocation(7,0)).setFigure(new Horse(playerBlack, horseBlack));
        grid.get(new GridLocation(8,0)).setFigure(new Chariot(playerBlack, chariotBlack));
        grid.get(new GridLocation(1,2)).setFigure(new Cannon(playerBlack, cannonBlack));
        grid.get(new GridLocation(7,2)).setFigure(new Cannon(playerBlack, cannonBlack));
        grid.get(new GridLocation(0,3)).setFigure(new Soldier(playerBlack, soldierBlack));
        grid.get(new GridLocation(2,3)).setFigure(new Soldier(playerBlack, soldierBlack));
        grid.get(new GridLocation(4,3)).setFigure(new Soldier(playerBlack, soldierBlack));
        grid.get(new GridLocation(6,3)).setFigure(new Soldier(playerBlack, soldierBlack));
        grid.get(new GridLocation(8,3)).setFigure(new Soldier(playerBlack, soldierBlack));

        //Red figures.
        grid.get(new GridLocation(0,6)).setFigure(new Soldier(playerRed, soldierRed));
        grid.get(new GridLocation(2,6)).setFigure(new Soldier(playerRed, soldierRed));
        grid.get(new GridLocation(4,6)).setFigure(new Soldier(playerRed, soldierRed));
        grid.get(new GridLocation(6,6)).setFigure(new Soldier(playerRed, soldierRed));
        grid.get(new GridLocation(8,6)).setFigure(new Soldier(playerRed, soldierRed));
        grid.get(new GridLocation(1,7)).setFigure(new Cannon(playerRed, cannonRed));
        grid.get(new GridLocation(7,7)).setFigure(new Cannon(playerRed, cannonRed));
        grid.get(new GridLocation(0,9)).setFigure(new Chariot(playerRed, chariotRed));
        grid.get(new GridLocation(1,9)).setFigure(new Horse(playerRed, horseRed));
        grid.get(new GridLocation(2,9)).setFigure(new Elephant(playerRed, elephantRed));
        grid.get(new GridLocation(3,9)).setFigure(new Advisor(playerRed, advisorRed));
        grid.get(new GridLocation(4,9)).setFigure(new General(playerRed, generalRed));
        grid.get(new GridLocation(5,9)).setFigure(new Advisor(playerRed, advisorRed));
        grid.get(new GridLocation(6,9)).setFigure(new Elephant(playerRed, elephantRed));
        grid.get(new GridLocation(7,9)).setFigure(new Horse(playerRed, horseRed));
        grid.get(new GridLocation(8,9)).setFigure(new Chariot(playerRed, chariotRed));
    }
    private void gameStateInit() //TODO:
    {
//        if(playerRed == Player.HUMAN)
//        {
//            gameState = GameState.HUMAN_TURN;
//        }
//        else
//        {
//            gameState = GameState.CPU_TURN;
//        }
        gameState = GameState.HUMAN_TURN_CHOOSE_FIGURE;
    }
    public void gridLocationSelected(GridLocation gridLocationSelected)
    {
        //TODO:
//        if(gameState == GameState.CPU_TURN)
//        {
//            return;
//        }

        switch(gameState)
        {
            case HUMAN_TURN_CHOOSE_FIGURE:
                prevGridLocationSelected = gridLocationSelected;
                prevFigureSelected = grid.get(gridLocationSelected).getFigure();
                if(grid.get(gridLocationSelected).getFigure() != null)
                {
                    grid.get(gridLocationSelected).setSelection(selection);
                    gameState = GameState.HUMAN_TURN_CHOOSE_DESTINATION;

                    panelBoardReference.repaint();
                }
                break;

            case HUMAN_TURN_CHOOSE_DESTINATION:
                grid.get(prevGridLocationSelected).setSelection(null);
                grid.get(prevGridLocationSelected).setFigure(null);
                grid.get(gridLocationSelected).setFigure(prevFigureSelected);
                gameState = GameState.HUMAN_TURN_CHOOSE_FIGURE;

                panelBoardReference.repaint();
                break;
        }
    }
    public void refreshText(Text text)
    {
        this.text = text;
        statusBarReference.setText(" ");
    }
    public HashMap<GridLocation, GridTile> getGrid()
    {
        return grid;
    }
}
