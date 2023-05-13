package com.github.mydeardoctor.chinesechess;

import java.util.HashMap;
import java.util.HashSet;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Map;
import java.util.Set;

public class Game
{
    private Text text;
    private PanelBoard panelBoardReference;
    private JLabel statusBarReference;

    private BufferedImage generalRed;
    private BufferedImage advisorRed;
    private BufferedImage elephantRed;
    private BufferedImage horseRed;
    private BufferedImage chariotRed;
    private BufferedImage cannonRed;
    private BufferedImage soldierRed;
    private BufferedImage generalBlack;
    private BufferedImage advisorBlack;
    private BufferedImage elephantBlack;
    private BufferedImage horseBlack;
    private BufferedImage chariotBlack;
    private BufferedImage cannonBlack;
    private BufferedImage soldierBlack;
    private BufferedImage selection;

    private HashMap<GridLocation, GridTile> grid;
    private Player turn;
    private Phase phase;
    private HashMap<GridLocation, HashSet<GridLocation>> allAllowedMoves;
    private GridLocation prevGridLocationSelected;
    private Figure prevFigureSelected;
    public Game(GUI gui)
    {
        text = gui.getText();
        panelBoardReference = gui.getPanelBoard();
        statusBarReference = gui.getStatusBar();
        iconsInit(gui);
        grid = new HashMap<>();
        allAllowedMoves = new HashMap<>();
    }
    @SuppressWarnings("DataFlowIssue")
    private void iconsInit(GUI gui)
    {
        generalRed = gui.getGeneralRed();
        advisorRed = gui.getAdvisorRed();
        elephantRed = gui.getElephantRed();
        horseRed = gui.getHorseRed();
        chariotRed = gui.getChariotRed();
        cannonRed = gui.getCannonRed();
        soldierRed = gui.getSoldierRed();
        generalBlack = gui.getGeneralBlack();
        advisorBlack = gui.getAdvisorBlack();
        elephantBlack = gui.getElephantBlack();
        horseBlack = gui.getHorseBlack();
        chariotBlack = gui.getChariotBlack();
        cannonBlack = gui.getCannonBlack();
        soldierBlack = gui.getSoldierBlack();
        selection = gui.getSelection();
    }
    public void start()
    {
        gridInit();
        gameStateInit();
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
        //Black figures.
        grid.get(new GridLocation(0,0)).setFigure(new Chariot(Player.BLACK, chariotBlack));
        grid.get(new GridLocation(1,0)).setFigure(new Horse(Player.BLACK, horseBlack));
        grid.get(new GridLocation(2,0)).setFigure(new ElephantBlack(Player.BLACK, elephantBlack));
        grid.get(new GridLocation(3,0)).setFigure(new AdvisorBlack(Player.BLACK, advisorBlack));
        grid.get(new GridLocation(4,0)).setFigure(new GeneralBlack(Player.BLACK, generalBlack));
        grid.get(new GridLocation(5,0)).setFigure(new AdvisorBlack(Player.BLACK, advisorBlack));
        grid.get(new GridLocation(6,0)).setFigure(new ElephantBlack(Player.BLACK, elephantBlack));
        grid.get(new GridLocation(7,0)).setFigure(new Horse(Player.BLACK, horseBlack));
        grid.get(new GridLocation(8,0)).setFigure(new Chariot(Player.BLACK, chariotBlack));
        grid.get(new GridLocation(1,2)).setFigure(new Cannon(Player.BLACK, cannonBlack));
        grid.get(new GridLocation(7,2)).setFigure(new Cannon(Player.BLACK, cannonBlack));
        grid.get(new GridLocation(0,3)).setFigure(new SoldierBlack(Player.BLACK, soldierBlack));
        grid.get(new GridLocation(2,3)).setFigure(new SoldierBlack(Player.BLACK, soldierBlack));
        grid.get(new GridLocation(4,3)).setFigure(new SoldierBlack(Player.BLACK, soldierBlack));
        grid.get(new GridLocation(6,3)).setFigure(new SoldierBlack(Player.BLACK, soldierBlack));
        grid.get(new GridLocation(8,3)).setFigure(new SoldierBlack(Player.BLACK, soldierBlack));

        //Red figures.
        grid.get(new GridLocation(0,6)).setFigure(new SoldierRed(Player.RED, soldierRed));
        grid.get(new GridLocation(2,6)).setFigure(new SoldierRed(Player.RED, soldierRed));
        grid.get(new GridLocation(4,6)).setFigure(new SoldierRed(Player.RED, soldierRed));
        grid.get(new GridLocation(6,6)).setFigure(new SoldierRed(Player.RED, soldierRed));
        grid.get(new GridLocation(8,6)).setFigure(new SoldierRed(Player.RED, soldierRed));
        grid.get(new GridLocation(1,7)).setFigure(new Cannon(Player.RED, cannonRed));
        grid.get(new GridLocation(7,7)).setFigure(new Cannon(Player.RED, cannonRed));
        grid.get(new GridLocation(0,9)).setFigure(new Chariot(Player.RED, chariotRed));
        grid.get(new GridLocation(1,9)).setFigure(new Horse(Player.RED, horseRed));
        grid.get(new GridLocation(2,9)).setFigure(new ElephantRed(Player.RED, elephantRed));
        grid.get(new GridLocation(3,9)).setFigure(new AdvisorRed(Player.RED, advisorRed));
        grid.get(new GridLocation(4,9)).setFigure(new GeneralRed(Player.RED, generalRed));
        grid.get(new GridLocation(5,9)).setFigure(new AdvisorRed(Player.RED, advisorRed));
        grid.get(new GridLocation(6,9)).setFigure(new ElephantRed(Player.RED, elephantRed));
        grid.get(new GridLocation(7,9)).setFigure(new Horse(Player.RED, horseRed));
        grid.get(new GridLocation(8,9)).setFigure(new Chariot(Player.RED, chariotRed));

        panelBoardReference.repaint();
    }
    private void gameStateInit()
    {
        turn = Player.RED;
        phase = Phase.CHOOSE_FIGURE;
        statusBarReference.setText(text.getRedPlayer()+", "+text.getChooseFigure());

        getAllAllowedMoves();
    }
    public void gridLocationSelected(GridLocation gridLocationSelected)
    {
        GridTileType gridTileType;
        gridTileType = Game.checkGridTileType(gridLocationSelected, grid, turn);

        switch(phase)
        {
            case CHOOSE_FIGURE->
            {
                if(gridTileType == GridTileType.FRIENDLY_FIGURE)
                {
                    saveAndHighlightSelectedFigure(gridLocationSelected);

                    phase = Phase.CHOOSE_DESTINATION;
                    switch(turn)
                    {
                        case RED -> statusBarReference.setText(text.getRedPlayer()+", "+text.getChooseDestination());
                        case BLACK -> statusBarReference.setText(text.getBlackPlayer()+", "+text.getChooseDestination());
                    }
                }
            }

            case CHOOSE_DESTINATION->
            {
                switch(gridTileType)
                {
                    case FRIENDLY_FIGURE ->
                    {
                        Set<Map.Entry<GridLocation,GridTile>> gridSet = grid.entrySet(); //Unhighlight everything.
                        for(Map.Entry<GridLocation,GridTile> gridEntry : gridSet)
                        {
                            gridEntry.getValue().setSelection(null);
                        }

                        saveAndHighlightSelectedFigure(gridLocationSelected);
                    }
                    case ENEMY_FIGURE, EMPTY ->
                    {
                        if(allAllowedMoves.containsKey(prevGridLocationSelected)) //If chosen figure has possible moves.
                        {
                            if(allAllowedMoves.get(prevGridLocationSelected).contains(gridLocationSelected)) //If this move is possible for this figure.
                            {
                                moveFigure(gridLocationSelected);
                            }
                        }
                    }
                }
            }
        }
    }
    private void getAllAllowedMoves()
    {
        allAllowedMoves.clear();

        Set<Map.Entry<GridLocation,GridTile>> gridSet = grid.entrySet();
        for(Map.Entry<GridLocation,GridTile> gridEntry : gridSet)
        {
            Figure figure = gridEntry.getValue().getFigure();
            if(figure!=null)
            {
                Player player = figure.getPlayer();
                if(player == turn)
                {
                    GridLocation origin = gridEntry.getKey();
                    HashSet<GridLocation> allowedMoves = figure.getAllowedMoves(origin, grid, turn, generalRed, generalBlack);
                    if(allowedMoves.size()>0)
                    {
                        allAllowedMoves.put(origin, allowedMoves);
                    }
                }
            }
        }

        if(allAllowedMoves.size()==0)
        {
            statusBarReference.setText("Endgame"); //TODO: если мувов нет, то эндгейм.
        }
    }
    public static GridTileType checkGridTileType(GridLocation destination, HashMap<GridLocation, GridTile> grid, Player turn)
    {
        Figure figureAtDestination = grid.get(destination).getFigure();
        if(figureAtDestination == null)
        {
            return GridTileType.EMPTY;
        }
        else
        {
            Player playerOfFigureAtDestination = figureAtDestination.getPlayer();
            if(playerOfFigureAtDestination==turn)
            {
                return GridTileType.FRIENDLY_FIGURE;
            }
            else
            {
                return GridTileType.ENEMY_FIGURE;
            }
        }
    }
    private void saveAndHighlightSelectedFigure(GridLocation gridLocationSelected)
    {
        Figure figureSelected = grid.get(gridLocationSelected).getFigure();
        prevGridLocationSelected = gridLocationSelected;                                        //Save selected location.
        prevFigureSelected = figureSelected;                                                    //Save selected figure.

        grid.get(gridLocationSelected).setSelection(selection);                           //Highlight selected figure.
        HashSet<GridLocation> possibleMoves = allAllowedMoves.get(gridLocationSelected); //Highlight possible moves.
        if(possibleMoves!=null)
        {
            for(GridLocation possibleMove : possibleMoves)
            {
                grid.get(possibleMove).setSelection(selection);
            }
        }

        panelBoardReference.repaint();
    }
    private void moveFigure(GridLocation gridLocationSelected)
    {
        Set<Map.Entry<GridLocation,GridTile>> gridSet = grid.entrySet(); //Unhighlight everything.
        for(Map.Entry<GridLocation,GridTile> gridEntry : gridSet)
        {
            gridEntry.getValue().setSelection(null);
        }

        grid.get(prevGridLocationSelected).setFigure(null);           //Move figure from previous location...
        grid.get(gridLocationSelected).setFigure(prevFigureSelected); //...to new location.

        panelBoardReference.repaint();

        switch(turn)
        {
            case RED ->
            {
                turn = Player.BLACK;
                phase = Phase.CHOOSE_FIGURE;
                statusBarReference.setText(text.getBlackPlayer() + ", " + text.getChooseFigure());
            }
            case BLACK ->
            {
                turn = Player.RED;
                phase = Phase.CHOOSE_FIGURE;
                statusBarReference.setText(text.getRedPlayer() + ", " + text.getChooseFigure());
            }
        }
        getAllAllowedMoves();  //check for endgame. if not.
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
