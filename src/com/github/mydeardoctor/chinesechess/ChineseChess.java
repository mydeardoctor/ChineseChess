package com.github.mydeardoctor.chinesechess;

public class ChineseChess
{
    public static void main(String[] args)
    {
        TextEnglish textEnglish = new TextEnglish();
        TextRussian textRussian = new TextRussian();
        GUI gui = new GUI(textEnglish);
    }
}