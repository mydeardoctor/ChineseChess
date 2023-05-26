package com.github.mydeardoctor.chinesechess;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.awt.image.BufferedImage;

public class Rules
{
    //Rules attributes.
    private Figure generalRed;
    private Figure advisorRed1;
    private Figure advisorRed2;
    private Figure elephantRed1;
    private Figure elephantRed2;
    private Figure horseRed1;
    private Figure horseRed2;
    private Figure chariotRed1;
    private Figure chariotRed2;
    private Figure cannonRed1;
    private Figure cannonRed2;
    private Figure soldierRed1;
    private Figure soldierRed2;
    private Figure soldierRed3;
    private Figure soldierRed4;
    private Figure soldierRed5;
    private Figure generalBlack;
    private Figure advisorBlack1;
    private Figure advisorBlack2;
    private Figure elephantBlack1;
    private Figure elephantBlack2;
    private Figure horseBlack1;
    private Figure horseBlack2;
    private Figure chariotBlack1;
    private Figure chariotBlack2;
    private Figure cannonBlack1;
    private Figure cannonBlack2;
    private Figure soldierBlack1;
    private Figure soldierBlack2;
    private Figure soldierBlack3;
    private Figure soldierBlack4;
    private Figure soldierBlack5;
    private HashMap<Location, Tile> grid;
    private boolean palaceSelected;
    private boolean riverSelected;

    //GUI attributes.
    private BufferedImage selection;

    public Rules()
    {
        initializeFigures();
        initializeGrid();
        initializeSelections();
    }
    private void initializeFigures()
    {
        generalRed = new GeneralRed(Player.RED, null);
        advisorRed1 = new AdvisorRed(Player.RED, null);
        advisorRed2 = new AdvisorRed(Player.RED, null);
        elephantRed1 = new ElephantRed(Player.RED, null);
        elephantRed2 = new ElephantRed(Player.RED, null);
        horseRed1 = new Horse(Player.RED, null);
        horseRed2 = new Horse(Player.RED, null);
        chariotRed1 = new Chariot(Player.RED, null);
        chariotRed2 = new Chariot(Player.RED, null);
        cannonRed1 = new Cannon(Player.RED, null);
        cannonRed2 = new Cannon(Player.RED, null);
        soldierRed1 = new SoldierRed(Player.RED, null);
        soldierRed2 = new SoldierRed(Player.RED, null);
        soldierRed3 = new SoldierRed(Player.RED, null);
        soldierRed4 = new SoldierRed(Player.RED, null);
        soldierRed5 = new SoldierRed(Player.RED, null);

        generalBlack = new GeneralBlack(Player.BLACK, null);
        advisorBlack1 = new AdvisorBlack(Player.BLACK, null);
        advisorBlack2 = new AdvisorBlack(Player.BLACK, null);
        elephantBlack1 = new ElephantBlack(Player.BLACK, null);
        elephantBlack2 = new ElephantBlack(Player.BLACK, null);
        horseBlack1 = new Horse(Player.BLACK, null);
        horseBlack2 = new Horse(Player.BLACK, null);
        chariotBlack1 = new Chariot(Player.BLACK, null);
        chariotBlack2 = new Chariot(Player.BLACK, null);
        cannonBlack1 = new Cannon(Player.BLACK, null);
        cannonBlack2 = new Cannon(Player.BLACK, null);
        soldierBlack1 = new SoldierBlack(Player.BLACK, null);
        soldierBlack2 = new SoldierBlack(Player.BLACK, null);
        soldierBlack3 = new SoldierBlack(Player.BLACK, null);
        soldierBlack4 = new SoldierBlack(Player.BLACK, null);
        soldierBlack5 = new SoldierBlack(Player.BLACK, null);
    }
    private void initializeGrid()
    {
        grid = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, null);
                grid.put(location, tile);
            }
        }

        //Put red figures.
        grid.get(new Location(0,6)).setFigure(soldierRed1);
        grid.get(new Location(2,6)).setFigure(soldierRed2);
        grid.get(new Location(4,6)).setFigure(soldierRed3);
        grid.get(new Location(6,6)).setFigure(soldierRed4);
        grid.get(new Location(8,6)).setFigure(soldierRed5);
        grid.get(new Location(1,7)).setFigure(cannonRed1);
        grid.get(new Location(7,7)).setFigure(cannonRed2);
        grid.get(new Location(0,9)).setFigure(chariotRed1);
        grid.get(new Location(1,9)).setFigure(horseRed1);
        grid.get(new Location(2,9)).setFigure(elephantRed1);
        grid.get(new Location(3,9)).setFigure(advisorRed1);
        grid.get(new Location(4,9)).setFigure(generalRed);
        grid.get(new Location(5,9)).setFigure(advisorRed2);
        grid.get(new Location(6,9)).setFigure(elephantRed2);
        grid.get(new Location(7,9)).setFigure(horseRed2);
        grid.get(new Location(8,9)).setFigure(chariotRed2);

        //Put black figures.
        grid.get(new Location(0,0)).setFigure(chariotBlack1);
        grid.get(new Location(1,0)).setFigure(horseBlack1);
        grid.get(new Location(2,0)).setFigure(elephantBlack1);
        grid.get(new Location(3,0)).setFigure(advisorBlack1);
        grid.get(new Location(4,0)).setFigure(generalBlack);
        grid.get(new Location(5,0)).setFigure(advisorBlack2);
        grid.get(new Location(6,0)).setFigure(elephantBlack2);
        grid.get(new Location(7,0)).setFigure(horseBlack2);
        grid.get(new Location(8,0)).setFigure(chariotBlack2);
        grid.get(new Location(1,2)).setFigure(cannonBlack1);
        grid.get(new Location(7,2)).setFigure(cannonBlack2);
        grid.get(new Location(0,3)).setFigure(soldierBlack1);
        grid.get(new Location(2,3)).setFigure(soldierBlack2);
        grid.get(new Location(4,3)).setFigure(soldierBlack3);
        grid.get(new Location(6,3)).setFigure(soldierBlack4);
        grid.get(new Location(8,3)).setFigure(soldierBlack5);
    }
    private void initializeSelections()
    {
        setPalaceSelected(false);
        setRiverSelected(false);
    }
    public void setGui(GUI gui)
    {
        selection = gui.getSelection();
        setIconsToFigures(gui);
    }
    private void setIconsToFigures(GUI gui)
    {
        generalRed.setIcon(gui.getGeneralRedIcon());
        advisorRed1.setIcon(gui.getAdvisorRedIcon());
        advisorRed2.setIcon(gui.getAdvisorRedIcon());
        elephantRed1.setIcon(gui.getElephantRedIcon());
        elephantRed2.setIcon(gui.getElephantRedIcon());
        horseRed1.setIcon(gui.getHorseRedIcon());
        horseRed2.setIcon(gui.getHorseRedIcon());
        chariotRed1.setIcon(gui.getChariotRedIcon());
        chariotRed2.setIcon(gui.getChariotRedIcon());
        cannonRed1.setIcon(gui.getCannonRedIcon());
        cannonRed2.setIcon(gui.getCannonRedIcon());
        soldierRed1.setIcon(gui.getSoldierRedIcon());
        soldierRed2.setIcon(gui.getSoldierRedIcon());
        soldierRed3.setIcon(gui.getSoldierRedIcon());
        soldierRed4.setIcon(gui.getSoldierRedIcon());
        soldierRed5.setIcon(gui.getSoldierRedIcon());

        generalBlack.setIcon(gui.getGeneralBlackIcon());
        advisorBlack1.setIcon(gui.getAdvisorBlackIcon());
        advisorBlack2.setIcon(gui.getAdvisorBlackIcon());
        elephantBlack1.setIcon(gui.getElephantBlackIcon());
        elephantBlack2.setIcon(gui.getElephantBlackIcon());
        horseBlack1.setIcon(gui.getHorseBlackIcon());
        horseBlack2.setIcon(gui.getHorseBlackIcon());
        chariotBlack1.setIcon(gui.getChariotBlackIcon());
        chariotBlack2.setIcon(gui.getChariotBlackIcon());
        cannonBlack1.setIcon(gui.getCannonBlackIcon());
        cannonBlack2.setIcon(gui.getCannonBlackIcon());
        soldierBlack1.setIcon(gui.getSoldierBlackIcon());
        soldierBlack2.setIcon(gui.getSoldierBlackIcon());
        soldierBlack3.setIcon(gui.getSoldierBlackIcon());
        soldierBlack4.setIcon(gui.getSoldierBlackIcon());
        soldierBlack5.setIcon(gui.getSoldierBlackIcon());
    }
    public void setGridForGoalRule()
    {
        resetGrid();
        resetSelections();

        //Move red figures.
        grid.get(new Location(4,6)).setFigure(null);
        grid.get(new Location(1,7)).setFigure(null);
        grid.get(new Location(1,6)).setFigure(cannonRed1);
        grid.get(new Location(7,7)).setFigure(null);
        grid.get(new Location(7,6)).setFigure(cannonRed2);
        grid.get(new Location(0,9)).setFigure(null);
        grid.get(new Location(3,7)).setFigure(chariotRed1);
        grid.get(new Location(8,9)).setFigure(null);
        grid.get(new Location(4,6)).setFigure(chariotRed2);

        //Move black figures.
        grid.get(new Location(4,0)).setFigure(null);
        grid.get(new Location(3,2)).setFigure(generalBlack);
        grid.get(new Location(1,2)).setFigure(null);
        grid.get(new Location(1,3)).setFigure(cannonBlack1);
        grid.get(new Location(7,2)).setFigure(null);
        grid.get(new Location(7,3)).setFigure(cannonBlack2);
        grid.get(new Location(4,3)).setFigure(null);
    }
    public void setGridForPalaceRule()
    {
        resetGrid();
        resetSelections();
        setPalaceSelected(true);
    }
    public void setGridForRiverRule()
    {
        resetGrid();
        resetSelections();
        setRiverSelected(true);
    }
    public void setGridForGeneralRule()
    {
        resetGrid();
        resetSelections();

        //Move red figures.
        grid.get(new Location(4,9)).setFigure(null);
        grid.get(new Location(4,8)).setFigure(generalRed);

        //Move black figures.
        grid.get(new Location(4,0)).setFigure(null);
        grid.get(new Location(4,1)).setFigure(generalBlack);

        //Set selections.
        grid.get(new Location(4,7)).setSelection(selection);
        grid.get(new Location(3,8)).setSelection(selection);
        grid.get(new Location(4,8)).setSelection(selection);
        grid.get(new Location(5,8)).setSelection(selection);
        grid.get(new Location(4,9)).setSelection(selection);
    }
    public void setGridForAdvisorRule()
    {
        resetGrid();
        resetSelections();

        //Move red figures.
        grid.get(new Location(3,9)).setFigure(null);
        grid.get(new Location(4,8)).setFigure(advisorRed1);

        //Move black figures.
        grid.get(new Location(3,0)).setFigure(null);
        grid.get(new Location(4,1)).setFigure(advisorBlack1);

        //Set selections.
        grid.get(new Location(3,7)).setSelection(selection);
        grid.get(new Location(5,7)).setSelection(selection);
        grid.get(new Location(4,8)).setSelection(selection);
        grid.get(new Location(3,9)).setSelection(selection);
    }
    public void setGridForElephantRule()
    {
        resetGrid();
        resetSelections();

        //Move red figures.
        grid.get(new Location(2,9)).setFigure(null);
        grid.get(new Location(4,7)).setFigure(elephantRed1);

        //Move black figures.
        grid.get(new Location(2,0)).setFigure(null);
        grid.get(new Location(4,2)).setFigure(elephantBlack1);

        //Set selections.
        grid.get(new Location(2,5)).setSelection(selection);
        grid.get(new Location(6,5)).setSelection(selection);
        grid.get(new Location(4,7)).setSelection(selection);
        grid.get(new Location(2,9)).setSelection(selection);
    }
    public void setGridForHorseRule()
    {
        resetGrid();
        resetSelections();

        //Move red figures.
        grid.get(new Location(1,9)).setFigure(null);
        grid.get(new Location(4,4)).setFigure(horseRed1);

        //Move black figures.
        grid.get(new Location(1,2)).setFigure(null);
        grid.get(new Location(3,2)).setFigure(cannonBlack1);
        grid.get(new Location(7,2)).setFigure(null);
        grid.get(new Location(5,2)).setFigure(cannonBlack2);

        //Set selections.
        grid.get(new Location(2,3)).setSelection(selection);
        grid.get(new Location(6,3)).setSelection(selection);
        grid.get(new Location(4,4)).setSelection(selection);
        grid.get(new Location(2,5)).setSelection(selection);
        grid.get(new Location(6,5)).setSelection(selection);
        grid.get(new Location(3,6)).setSelection(selection);
        grid.get(new Location(5,6)).setSelection(selection);
    }
    public void setGridForChariotRule()
    {
        resetGrid();
        resetSelections();

        //Move red figures.
        grid.get(new Location(0,9)).setFigure(null);
        grid.get(new Location(3,7)).setFigure(chariotRed1);

        //Set selections.
        grid.get(new Location(3,0)).setSelection(selection);
        grid.get(new Location(3,1)).setSelection(selection);
        grid.get(new Location(3,2)).setSelection(selection);
        grid.get(new Location(3,3)).setSelection(selection);
        grid.get(new Location(3,4)).setSelection(selection);
        grid.get(new Location(3,5)).setSelection(selection);
        grid.get(new Location(3,6)).setSelection(selection);
        grid.get(new Location(2,7)).setSelection(selection);
        grid.get(new Location(3,7)).setSelection(selection);
        grid.get(new Location(4,7)).setSelection(selection);
        grid.get(new Location(5,7)).setSelection(selection);
        grid.get(new Location(6,7)).setSelection(selection);
        grid.get(new Location(3,8)).setSelection(selection);
    }
    public void setGridForCannonRule()
    {
        resetGrid();
        resetSelections();

        //Set selections.
        grid.get(new Location(1,0)).setSelection(selection);
        grid.get(new Location(1,3)).setSelection(selection);
        grid.get(new Location(1,4)).setSelection(selection);
        grid.get(new Location(1,5)).setSelection(selection);
        grid.get(new Location(1,6)).setSelection(selection);
        grid.get(new Location(0,7)).setSelection(selection);
        grid.get(new Location(1,7)).setSelection(selection);
        grid.get(new Location(2,7)).setSelection(selection);
        grid.get(new Location(3,7)).setSelection(selection);
        grid.get(new Location(4,7)).setSelection(selection);
        grid.get(new Location(5,7)).setSelection(selection);
        grid.get(new Location(6,7)).setSelection(selection);
        grid.get(new Location(1,8)).setSelection(selection);
    }
    public void setGridForSoldierRule()
    {
        resetGrid();
        resetSelections();

        //Move red figures.
        grid.get(new Location(4,6)).setFigure(null);
        grid.get(new Location(4,4)).setFigure(soldierRed3);

        //Set selections.
        grid.get(new Location(4,3)).setSelection(selection);
        grid.get(new Location(3,4)).setSelection(selection);
        grid.get(new Location(4,4)).setSelection(selection);
        grid.get(new Location(5,4)).setSelection(selection);
    }
    private void resetGrid()
    {
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setFigure(null);
            gridEntry.getValue().setSelection(null);
        }

        //Put red figures.
        grid.get(new Location(0,6)).setFigure(soldierRed1);
        grid.get(new Location(2,6)).setFigure(soldierRed2);
        grid.get(new Location(4,6)).setFigure(soldierRed3);
        grid.get(new Location(6,6)).setFigure(soldierRed4);
        grid.get(new Location(8,6)).setFigure(soldierRed5);
        grid.get(new Location(1,7)).setFigure(cannonRed1);
        grid.get(new Location(7,7)).setFigure(cannonRed2);
        grid.get(new Location(0,9)).setFigure(chariotRed1);
        grid.get(new Location(1,9)).setFigure(horseRed1);
        grid.get(new Location(2,9)).setFigure(elephantRed1);
        grid.get(new Location(3,9)).setFigure(advisorRed1);
        grid.get(new Location(4,9)).setFigure(generalRed);
        grid.get(new Location(5,9)).setFigure(advisorRed2);
        grid.get(new Location(6,9)).setFigure(elephantRed2);
        grid.get(new Location(7,9)).setFigure(horseRed2);
        grid.get(new Location(8,9)).setFigure(chariotRed2);

        //Put black figures.
        grid.get(new Location(0,0)).setFigure(chariotBlack1);
        grid.get(new Location(1,0)).setFigure(horseBlack1);
        grid.get(new Location(2,0)).setFigure(elephantBlack1);
        grid.get(new Location(3,0)).setFigure(advisorBlack1);
        grid.get(new Location(4,0)).setFigure(generalBlack);
        grid.get(new Location(5,0)).setFigure(advisorBlack2);
        grid.get(new Location(6,0)).setFigure(elephantBlack2);
        grid.get(new Location(7,0)).setFigure(horseBlack2);
        grid.get(new Location(8,0)).setFigure(chariotBlack2);
        grid.get(new Location(1,2)).setFigure(cannonBlack1);
        grid.get(new Location(7,2)).setFigure(cannonBlack2);
        grid.get(new Location(0,3)).setFigure(soldierBlack1);
        grid.get(new Location(2,3)).setFigure(soldierBlack2);
        grid.get(new Location(4,3)).setFigure(soldierBlack3);
        grid.get(new Location(6,3)).setFigure(soldierBlack4);
        grid.get(new Location(8,3)).setFigure(soldierBlack5);
    }
    private void resetSelections()
    {
        setPalaceSelected(false);
        setRiverSelected(false);
    }
    public HashMap<Location, Tile> getGrid()
    {
        return grid;
    }
    public boolean getPalaceSelected()
    {
        return palaceSelected;
    }
    public void setPalaceSelected(boolean palaceSelected)
    {
        this.palaceSelected = palaceSelected;
    }
    public boolean getRiverSelected()
    {
        return riverSelected;
    }
    public void setRiverSelected(boolean riverSelected)
    {
        this.riverSelected = riverSelected;
    }
}