package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
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
    private GridLocation prevGridLocationSelected;
    private Figure prevFigureSelected;
    private HashSet<GridLocation> prevPossibleMoves;
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
    private void setRandomSides() //TODO: Changed for debug purposes.
    {
//        players.clear();
//        players.add(Player.HUMAN);
//        players.add(Player.CPU);
//        int randomIndex = randomGenerator.nextInt(2);
//        playerRed = players.get(randomIndex);
//        players.remove(randomIndex);
//        playerBlack = players.get(0);
          playerRed = Player.CPU;
          playerBlack = Player.HUMAN;
    }
    private void gridInit()  //TODO: Make icons as static final variables.
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
        grid.get(new GridLocation(2,0)).setFigure(new ElephantBlack(playerBlack, elephantBlack));
        grid.get(new GridLocation(3,0)).setFigure(new AdvisorBlack(playerBlack, advisorBlack));
        grid.get(new GridLocation(4,0)).setFigure(new GeneralBlack(playerBlack, generalBlack));
        grid.get(new GridLocation(5,0)).setFigure(new AdvisorBlack(playerBlack, advisorBlack));
        grid.get(new GridLocation(6,0)).setFigure(new ElephantBlack(playerBlack, elephantBlack));
        grid.get(new GridLocation(7,0)).setFigure(new Horse(playerBlack, horseBlack));
        grid.get(new GridLocation(8,0)).setFigure(new Chariot(playerBlack, chariotBlack));
        grid.get(new GridLocation(1,2)).setFigure(new Cannon(playerBlack, cannonBlack));
        grid.get(new GridLocation(7,2)).setFigure(new Cannon(playerBlack, cannonBlack));
        grid.get(new GridLocation(0,3)).setFigure(new SoldierBlack(playerBlack, soldierBlack));
        grid.get(new GridLocation(2,3)).setFigure(new SoldierBlack(playerBlack, soldierBlack));
        grid.get(new GridLocation(4,3)).setFigure(new SoldierBlack(playerBlack, soldierBlack));
        grid.get(new GridLocation(6,3)).setFigure(new SoldierBlack(playerBlack, soldierBlack));
        grid.get(new GridLocation(8,3)).setFigure(new SoldierBlack(playerBlack, soldierBlack));

        //Red figures.
        grid.get(new GridLocation(0,6)).setFigure(new SoldierRed(playerRed, soldierRed));
        grid.get(new GridLocation(2,6)).setFigure(new SoldierRed(playerRed, soldierRed));
        grid.get(new GridLocation(4,6)).setFigure(new SoldierRed(playerRed, soldierRed));
        grid.get(new GridLocation(6,6)).setFigure(new SoldierRed(playerRed, soldierRed));
        grid.get(new GridLocation(8,6)).setFigure(new SoldierRed(playerRed, soldierRed));
        grid.get(new GridLocation(1,7)).setFigure(new Cannon(playerRed, cannonRed));
        grid.get(new GridLocation(7,7)).setFigure(new Cannon(playerRed, cannonRed));
        grid.get(new GridLocation(0,9)).setFigure(new Chariot(playerRed, chariotRed));
        grid.get(new GridLocation(1,9)).setFigure(new Horse(playerRed, horseRed));
        grid.get(new GridLocation(2,9)).setFigure(new ElephantRed(playerRed, elephantRed));
        grid.get(new GridLocation(3,9)).setFigure(new AdvisorRed(playerRed, advisorRed));
        grid.get(new GridLocation(4,9)).setFigure(new GeneralRed(playerRed, generalRed));
        grid.get(new GridLocation(5,9)).setFigure(new AdvisorRed(playerRed, advisorRed));
        grid.get(new GridLocation(6,9)).setFigure(new ElephantRed(playerRed, elephantRed));
        grid.get(new GridLocation(7,9)).setFigure(new Horse(playerRed, horseRed));
        grid.get(new GridLocation(8,9)).setFigure(new Chariot(playerRed, chariotRed));
    }
    private void gameStateInit()
    {
        panelBoardReference.repaint();

        if(Player.HUMAN == playerRed)
        {
            JOptionPane.showMessageDialog(panelBoardReference, text.getPlayerRed(), "",
                    JOptionPane.INFORMATION_MESSAGE);
            gameState = GameState.HUMAN_TURN_CHOOSE_FIGURE;
            statusBarReference.setText(text.getChooseFigure());
        }
        else //Player.HUMAN == playerBlack
        {
            JOptionPane.showMessageDialog(panelBoardReference, text.getPlayerBlack(), "",
                    JOptionPane.INFORMATION_MESSAGE);
            gameState = GameState.CPU_TURN;
            statusBarReference.setText(text.getCpuTurn());
            cpuTurn();
        }
    }
    public void gridLocationSelected(GridLocation gridLocationSelected)
    {
        if(gameState == GameState.CPU_TURN)
        {
            return;
        }

        Figure figureSelected;
        Player figurePlayer;
        switch(gameState)
        {
            case HUMAN_TURN_CHOOSE_FIGURE:
                figureSelected = grid.get(gridLocationSelected).getFigure();
                if(figureSelected != null) //If figure was selected.
                {
                    figurePlayer = figureSelected.getPlayer();
                    if(figurePlayer==Player.HUMAN) //If your own figure was selected.
                    {
                        saveAndHighlightSelectedFigure(gridLocationSelected, figureSelected);

                        gameState = GameState.HUMAN_TURN_CHOOSE_DESTINATION;
                        statusBarReference.setText(text.getChooseDestination());
                    }
                }
                break;

            case HUMAN_TURN_CHOOSE_DESTINATION:
                figureSelected = grid.get(gridLocationSelected).getFigure();
                if(figureSelected != null) //If there is a figure in destination.
                {
                    figurePlayer = figureSelected.getPlayer();
                    if(figurePlayer==Player.HUMAN) //If your own figure was selected again.
                    {
                        grid.get(prevGridLocationSelected).setSelection(null); //Unhighlight previously selected figure.
                        for(GridLocation gridLocation : prevPossibleMoves)     //Unhighlight previously selected moves.
                        {
                            grid.get(gridLocation).setSelection(null);
                        }

                        saveAndHighlightSelectedFigure(gridLocationSelected, figureSelected);
                    }
                    else //If enemy figure was selected.
                    {
                        if(prevPossibleMoves.contains(gridLocationSelected)) //If possible move.
                        {
                            moveFigure(gridLocationSelected);
                        }
                    }
                }
                else //If destination is empty.
                {
                    if(prevPossibleMoves.contains(gridLocationSelected)) //If possible move.
                    {
                        moveFigure(gridLocationSelected);
                    }
                }
                break;
        }
    }
    private void saveAndHighlightSelectedFigure(GridLocation gridLocationSelected, Figure figureSelected)
    {
        prevGridLocationSelected = gridLocationSelected;                                  //Save selected location.
        prevFigureSelected = figureSelected;                                              //Save selected figure.
        prevPossibleMoves = figureSelected.getPossibleMoves(gridLocationSelected, grid);  //Save possible moves.

        grid.get(gridLocationSelected).setSelection(selection); //Highlight selected figure.
        for(GridLocation gridLocation : prevPossibleMoves)      //Highlight possible moves.
        {
            grid.get(gridLocation).setSelection(selection);
        }

        panelBoardReference.repaint();
    }
    private void moveFigure(GridLocation gridLocationSelected)
    {
        grid.get(prevGridLocationSelected).setSelection(null); //Unhighlight previously selected figure.
        for(GridLocation prevPossibleMove : prevPossibleMoves) //Unhighlight previously selected moves.
        {
            grid.get(prevPossibleMove).setSelection(null);
        }

        grid.get(prevGridLocationSelected).setFigure(null);           //Move figure from previous location...
        grid.get(gridLocationSelected).setFigure(prevFigureSelected); //...to new location.

        panelBoardReference.repaint();

        //check for endfame. if not. TODO: Implement.
        gameState = GameState.CPU_TURN;
        statusBarReference.setText(text.getCpuTurn());
        cpuTurn();
    }
    private void cpuTurn() //TODO: Implement.
    {
        //do something;
        //check for endgame. if not.
        gameState = GameState.HUMAN_TURN_CHOOSE_FIGURE;
        statusBarReference.setText(text.getChooseFigure());
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
