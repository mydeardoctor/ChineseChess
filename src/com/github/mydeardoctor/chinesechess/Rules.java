package com.github.mydeardoctor.chinesechess;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Rules
{
    //Rules attributes.
    private final HashMap<Location, Tile> grid;
    private boolean palaceSelected;
    private boolean riverSelected;

    //Game attributes.
    private Game game;

    public Rules()
    {
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
    public void setGame(Game game)
    {
        this.game = game;
    }
    public void setGridForGoalRule()
    {
        resetGrid();

        //Move red figures.
        grid.get(new Location(4,6)).setFigure(null);
        grid.get(new Location(1,7)).setFigure(null);
        grid.get(new Location(1,6)).setFigure(game.getCannonRed1());
        grid.get(new Location(7,7)).setFigure(null);
        grid.get(new Location(7,6)).setFigure(game.getCannonRed2());
        grid.get(new Location(0,9)).setFigure(null);
        grid.get(new Location(3,7)).setFigure(game.getChariotRed1());
        grid.get(new Location(8,9)).setFigure(null);
        grid.get(new Location(4,6)).setFigure(game.getChariotRed2());

        //Move black figures.
        grid.get(new Location(4,0)).setFigure(null);
        grid.get(new Location(3,2)).setFigure(game.getGeneralBlack());
        grid.get(new Location(1,2)).setFigure(null);
        grid.get(new Location(1,3)).setFigure(game.getCannonBlack1());
        grid.get(new Location(7,2)).setFigure(null);
        grid.get(new Location(7,3)).setFigure(game.getCannonBlack2());
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
        grid.get(new Location(4,8)).setFigure(game.getGeneralRed());

        //Move black figures.
        grid.get(new Location(4,0)).setFigure(null);
        grid.get(new Location(4,1)).setFigure(game.getGeneralBlack());

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
        grid.get(new Location(4,8)).setFigure(game.getAdvisorRed1());

        //Move black figures.
        grid.get(new Location(3,0)).setFigure(null);
        grid.get(new Location(4,1)).setFigure(game.getAdvisorBlack1());

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
        grid.get(new Location(4,7)).setFigure(game.getElephantRed1());

        //Move black figures.
        grid.get(new Location(2,0)).setFigure(null);
        grid.get(new Location(4,2)).setFigure(game.getElephantBlack1());

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
        grid.get(new Location(4,4)).setFigure(game.getHorseRed1());

        //Move black figures.
        grid.get(new Location(1,2)).setFigure(null);
        grid.get(new Location(3,2)).setFigure(game.getCannonBlack1());
        grid.get(new Location(7,2)).setFigure(null);
        grid.get(new Location(5,2)).setFigure(game.getCannonBlack2());

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
        grid.get(new Location(3,7)).setFigure(game.getChariotRed1());

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
        grid.get(new Location(4,4)).setFigure(game.getSoldierRed3());

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
        grid.get(new Location(0,6)).setFigure(game.getSoldierRed1());
        grid.get(new Location(2,6)).setFigure(game.getSoldierRed2());
        grid.get(new Location(4,6)).setFigure(game.getSoldierRed3());
        grid.get(new Location(6,6)).setFigure(game.getSoldierRed4());
        grid.get(new Location(8,6)).setFigure(game.getSoldierRed5());
        grid.get(new Location(1,7)).setFigure(game.getCannonRed1());
        grid.get(new Location(7,7)).setFigure(game.getCannonRed2());
        grid.get(new Location(0,9)).setFigure(game.getChariotRed1());
        grid.get(new Location(1,9)).setFigure(game.getHorseRed1());
        grid.get(new Location(2,9)).setFigure(game.getElephantRed1());
        grid.get(new Location(3,9)).setFigure(game.getAdvisorRed1());
        grid.get(new Location(4,9)).setFigure(game.getGeneralRed());
        grid.get(new Location(5,9)).setFigure(game.getAdvisorRed2());
        grid.get(new Location(6,9)).setFigure(game.getElephantRed2());
        grid.get(new Location(7,9)).setFigure(game.getHorseRed2());
        grid.get(new Location(8,9)).setFigure(game.getChariotRed2());

        //Put black figures.
        grid.get(new Location(0,0)).setFigure(game.getChariotBlack1());
        grid.get(new Location(1,0)).setFigure(game.getHorseBlack1());
        grid.get(new Location(2,0)).setFigure(game.getElephantBlack1());
        grid.get(new Location(3,0)).setFigure(game.getAdvisorBlack1());
        grid.get(new Location(4,0)).setFigure(game.getGeneralBlack());
        grid.get(new Location(5,0)).setFigure(game.getAdvisorBlack2());
        grid.get(new Location(6,0)).setFigure(game.getElephantBlack2());
        grid.get(new Location(7,0)).setFigure(game.getHorseBlack2());
        grid.get(new Location(8,0)).setFigure(game.getChariotBlack2());
        grid.get(new Location(1,2)).setFigure(game.getCannonBlack1());
        grid.get(new Location(7,2)).setFigure(game.getCannonBlack2());
        grid.get(new Location(0,3)).setFigure(game.getSoldierBlack1());
        grid.get(new Location(2,3)).setFigure(game.getSoldierBlack2());
        grid.get(new Location(4,3)).setFigure(game.getSoldierBlack3());
        grid.get(new Location(6,3)).setFigure(game.getSoldierBlack4());
        grid.get(new Location(8,3)).setFigure(game.getSoldierBlack5());
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