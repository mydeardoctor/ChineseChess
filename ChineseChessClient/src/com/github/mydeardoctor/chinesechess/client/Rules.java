package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Rules
{
    //Rules attributes.
    private final Figure generalRed;
    private final Figure advisorRed1;
    private final Figure advisorRed2;
    private final Figure elephantRed1;
    private final Figure elephantRed2;
    private final Figure horseRed1;
    private final Figure horseRed2;
    private final Figure chariotRed1;
    private final Figure chariotRed2;
    private final Figure cannonRed1;
    private final Figure cannonRed2;
    private final Figure soldierRed1;
    private final Figure soldierRed2;
    private final Figure soldierRed3;
    private final Figure soldierRed4;
    private final Figure soldierRed5;
    private final Figure generalBlack;
    private final Figure advisorBlack1;
    private final Figure advisorBlack2;
    private final Figure elephantBlack1;
    private final Figure elephantBlack2;
    private final Figure horseBlack1;
    private final Figure horseBlack2;
    private final Figure chariotBlack1;
    private final Figure chariotBlack2;
    private final Figure cannonBlack1;
    private final Figure cannonBlack2;
    private final Figure soldierBlack1;
    private final Figure soldierBlack2;
    private final Figure soldierBlack3;
    private final Figure soldierBlack4;
    private final Figure soldierBlack5;
    private final HashMap<Location, Tile> grid;
    private boolean palaceSelected;
    private boolean riverSelected;

    public Rules()
    {
        //Initialize figures.
        //Red figures.
        generalRed = new GeneralRed();
        advisorRed1 = new AdvisorRed();
        advisorRed2 = new AdvisorRed();
        elephantRed1 = new ElephantRed();
        elephantRed2 = new ElephantRed();
        horseRed1 = new HorseRed();
        horseRed2 = new HorseRed();
        chariotRed1 = new ChariotRed();
        chariotRed2 = new ChariotRed();
        cannonRed1 = new CannonRed();
        cannonRed2 = new CannonRed();
        soldierRed1 = new SoldierRed();
        soldierRed2 = new SoldierRed();
        soldierRed3 = new SoldierRed();
        soldierRed4 = new SoldierRed();
        soldierRed5 = new SoldierRed();
        //Black figures.
        generalBlack = new GeneralBlack();
        advisorBlack1 = new AdvisorBlack();
        advisorBlack2 = new AdvisorBlack();
        elephantBlack1 = new ElephantBlack();
        elephantBlack2 = new ElephantBlack();
        horseBlack1 = new HorseBlack();
        horseBlack2 = new HorseBlack();
        chariotBlack1 = new ChariotBlack();
        chariotBlack2 = new ChariotBlack();
        cannonBlack1 = new CannonBlack();
        cannonBlack2 = new CannonBlack();
        soldierBlack1 = new SoldierBlack();
        soldierBlack2 = new SoldierBlack();
        soldierBlack3 = new SoldierBlack();
        soldierBlack4 = new SoldierBlack();
        soldierBlack5 = new SoldierBlack();

        grid = initializeGrid();
        setPalaceAndRiverSelections(false, false);
    }
    private HashMap<Location, Tile> initializeGrid()
    {
        HashMap<Location, Tile> grid = new HashMap<>();
        for(int y = 0; y <= 9; y++)
        {
            for(int x = 0; x <= 8; x++)
            {
                Location location = new Location(x, y);
                Tile tile = new Tile(null, false);
                grid.put(location, tile);
            }
        }
        return grid;
    }
    public void setGridForGoalRule()
    {
        resetGrid();

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

        //Set selections.
        setPalaceAndRiverSelections(false, false);
    }
    public void setGridForPalaceRule()
    {
        resetGrid();

        //Set selections.
        setPalaceAndRiverSelections(true, false);
    }
    public void setGridForRiverRule()
    {
        resetGrid();

        //Set selections.
        setPalaceAndRiverSelections(false, true);
    }
    public void setGridForGeneralRule()
    {
        resetGrid();

        //Move red figures.
        grid.get(new Location(4,9)).setFigure(null);
        grid.get(new Location(4,8)).setFigure(generalRed);

        //Move black figures.
        grid.get(new Location(4,0)).setFigure(null);
        grid.get(new Location(4,1)).setFigure(generalBlack);

        //Set selections.
        grid.get(new Location(4,7)).setSelected(true);
        grid.get(new Location(3,8)).setSelected(true);
        grid.get(new Location(4,8)).setSelected(true);
        grid.get(new Location(5,8)).setSelected(true);
        grid.get(new Location(4,9)).setSelected(true);
        setPalaceAndRiverSelections(false, false);
    }
    public void setGridForAdvisorRule()
    {
        resetGrid();

        //Move red figures.
        grid.get(new Location(3,9)).setFigure(null);
        grid.get(new Location(4,8)).setFigure(advisorRed1);

        //Move black figures.
        grid.get(new Location(3,0)).setFigure(null);
        grid.get(new Location(4,1)).setFigure(advisorBlack1);

        //Set selections.
        grid.get(new Location(3,7)).setSelected(true);
        grid.get(new Location(5,7)).setSelected(true);
        grid.get(new Location(4,8)).setSelected(true);
        grid.get(new Location(3,9)).setSelected(true);
        setPalaceAndRiverSelections(false, false);
    }
    public void setGridForElephantRule()
    {
        resetGrid();

        //Move red figures.
        grid.get(new Location(2,9)).setFigure(null);
        grid.get(new Location(4,7)).setFigure(elephantRed1);

        //Move black figures.
        grid.get(new Location(2,0)).setFigure(null);
        grid.get(new Location(4,2)).setFigure(elephantBlack1);

        //Set selections.
        grid.get(new Location(2,5)).setSelected(true);
        grid.get(new Location(6,5)).setSelected(true);
        grid.get(new Location(4,7)).setSelected(true);
        grid.get(new Location(2,9)).setSelected(true);
        setPalaceAndRiverSelections(false, false);
    }
    public void setGridForHorseRule()
    {
        resetGrid();

        //Move red figures.
        grid.get(new Location(1,9)).setFigure(null);
        grid.get(new Location(4,4)).setFigure(horseRed1);

        //Move black figures.
        grid.get(new Location(1,2)).setFigure(null);
        grid.get(new Location(3,2)).setFigure(cannonBlack1);
        grid.get(new Location(7,2)).setFigure(null);
        grid.get(new Location(5,2)).setFigure(cannonBlack2);

        //Set selections.
        grid.get(new Location(2,3)).setSelected(true);
        grid.get(new Location(6,3)).setSelected(true);
        grid.get(new Location(4,4)).setSelected(true);
        grid.get(new Location(2,5)).setSelected(true);
        grid.get(new Location(6,5)).setSelected(true);
        grid.get(new Location(3,6)).setSelected(true);
        grid.get(new Location(5,6)).setSelected(true);
        setPalaceAndRiverSelections(false, false);
    }
    public void setGridForChariotRule()
    {
        resetGrid();

        //Move red figures.
        grid.get(new Location(0,9)).setFigure(null);
        grid.get(new Location(3,7)).setFigure(chariotRed1);

        //Set selections.
        grid.get(new Location(3,0)).setSelected(true);
        grid.get(new Location(3,1)).setSelected(true);
        grid.get(new Location(3,2)).setSelected(true);
        grid.get(new Location(3,3)).setSelected(true);
        grid.get(new Location(3,4)).setSelected(true);
        grid.get(new Location(3,5)).setSelected(true);
        grid.get(new Location(3,6)).setSelected(true);
        grid.get(new Location(2,7)).setSelected(true);
        grid.get(new Location(3,7)).setSelected(true);
        grid.get(new Location(4,7)).setSelected(true);
        grid.get(new Location(5,7)).setSelected(true);
        grid.get(new Location(6,7)).setSelected(true);
        grid.get(new Location(3,8)).setSelected(true);
        setPalaceAndRiverSelections(false, false);
    }
    public void setGridForCannonRule()
    {
        resetGrid();

        //Set selections.
        grid.get(new Location(1,0)).setSelected(true);
        grid.get(new Location(1,3)).setSelected(true);
        grid.get(new Location(1,4)).setSelected(true);
        grid.get(new Location(1,5)).setSelected(true);
        grid.get(new Location(1,6)).setSelected(true);
        grid.get(new Location(0,7)).setSelected(true);
        grid.get(new Location(1,7)).setSelected(true);
        grid.get(new Location(2,7)).setSelected(true);
        grid.get(new Location(3,7)).setSelected(true);
        grid.get(new Location(4,7)).setSelected(true);
        grid.get(new Location(5,7)).setSelected(true);
        grid.get(new Location(6,7)).setSelected(true);
        grid.get(new Location(1,8)).setSelected(true);
        setPalaceAndRiverSelections(false, false);
    }
    public void setGridForSoldierRule()
    {
        resetGrid();

        //Move red figures.
        grid.get(new Location(4,6)).setFigure(null);
        grid.get(new Location(4,4)).setFigure(soldierRed3);

        //Set selections.
        grid.get(new Location(4,3)).setSelected(true);
        grid.get(new Location(3,4)).setSelected(true);
        grid.get(new Location(4,4)).setSelected(true);
        grid.get(new Location(5,4)).setSelected(true);
        setPalaceAndRiverSelections(false, false);
    }
    private void resetGrid()
    {
        Set<Map.Entry<Location, Tile>> gridSet = grid.entrySet();
        for(Map.Entry<Location, Tile> gridEntry : gridSet)
        {
            gridEntry.getValue().setFigure(null);
            gridEntry.getValue().setSelected(false);
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
    public HashMap<Location, Tile> getGrid()
    {
        return grid;
    }
    public boolean getPalaceSelected()
    {
        return palaceSelected;
    }
    public boolean getRiverSelected()
    {
        return riverSelected;
    }
    private void setPalaceAndRiverSelections(boolean palaceSelected, boolean riverSelected)
    {
        this.palaceSelected = palaceSelected;
        this.riverSelected = riverSelected;
    }
}