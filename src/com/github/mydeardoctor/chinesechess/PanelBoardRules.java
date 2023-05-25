package com.github.mydeardoctor.chinesechess;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelBoardRules extends PanelBoard
{
    private BufferedImage selectionSquare;
    private Rules rules;
    public PanelBoardRules(BufferedImage selectionSquare)
    {
        super();
        this.selectionSquare = selectionSquare;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if(rules.getPalaceSelected()==true)
        {
            g2d.drawImage(selectionSquare,
                    getX0Board() + getTileLength() + getTileLength()*3 - 0,
                    getY0Board() + getTileLength() + getTileLength()*7 - 0,
                    getTileLength()*2, getTileLength()*2, this);
        }
        if(rules.getRiverSelected()==true)
        {
            g2d.drawImage(selectionSquare,
                    getX0Board() + getTileLength() - 0,
                    getY0Board() + getTileLength() + getTileLength()*4 - 0,
                    getTileLength()*8, getTileLength(), this);
        }
    }
    public void setRules(Rules rules)
    {
        this.rules = rules;
    }
}
