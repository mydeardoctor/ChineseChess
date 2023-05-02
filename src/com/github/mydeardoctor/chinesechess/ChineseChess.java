package com.github.mydeardoctor.chinesechess;

public class ChineseChess
{
    private GUI gui = null;


    private ChineseChess()
    {


        gui = new GUI();
    }
    public static void main(String[] args)
    {
        ChineseChess chineseChess = new ChineseChess();

        //System.out.println(chineseChess.text.getTitle());
    }
}