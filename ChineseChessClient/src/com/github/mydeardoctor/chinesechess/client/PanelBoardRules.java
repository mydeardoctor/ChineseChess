package com.github.mydeardoctor.chinesechess.client;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PanelBoardRules extends PanelBoard
{
    private final BufferedImage selectionPalace;
    private final BufferedImage selectionRiver;

    //Rules attributes.
    private Rules rules;

    public PanelBoardRules(HashMap<Class<? extends Figure>, BufferedImage> imagesOfFigures,
                           BufferedImage selectionFigure,
                           BufferedImage selectionPalace,
                           BufferedImage selectionRiver)
    {
        super(imagesOfFigures, selectionFigure);
        this.selectionPalace = selectionPalace;
        this.selectionRiver = selectionRiver;
    }
    public void setRules(Rules rules)
    {
        super.setGrid(rules.getGrid());
        this.rules = rules;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        if(rules.getPalaceSelected())
        {
            int palaceLength = (int)(1.2*tileLength*2);
            int palaceOffset = (int)((palaceLength - tileLength*2)/2.0);
            int palaceX = x0Board + tileLength + tileLength*3 - palaceOffset;
            int palaceY = y0Board + tileLength + tileLength*7 - palaceOffset;
            g2d.drawImage(selectionPalace, palaceX, palaceY, palaceLength, palaceLength, this);
        }
        if(rules.getRiverSelected())
        {
            int riverWidth = (int)(1.04*tileLength*8);
            int riverHeight = (int)(1.4*tileLength);
            int riverXOffset = (int)((riverWidth - tileLength*8)/2.0);
            int riverYOffset = (int)((riverHeight - tileLength)/2.0);
            int riverX = x0Board + tileLength - riverXOffset;
            int riverY = y0Board + tileLength + tileLength*4 - riverYOffset;
            g2d.drawImage(selectionRiver, riverX, riverY, riverWidth, riverHeight, this);
        }
    }
}