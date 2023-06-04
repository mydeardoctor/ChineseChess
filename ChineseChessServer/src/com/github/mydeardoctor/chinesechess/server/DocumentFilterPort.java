package com.github.mydeardoctor.chinesechess.server;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentFilterPort extends DocumentFilter
{
    public static final String regex =
            "^("+
            "102[4-9]|" +
            "10[3-9][0-9]|" +
            "1[1-9][0-9][0-9]|"+
            "[2-9][0-9][0-9][0-9]|" +
            "[1-5][0-9][0-9][0-9][0-9]|" +
            "6[0-4][0-9][0-9][0-9]|" +
            "65[0-4][0-9][0-9]|" +
            "655[0-2][0-9]|" +
            "6553[0-5]" +
            ")$";
    public DocumentFilterPort()
    {
        super();
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {

        if ((fb.getDocument().getLength() + string.length()) <= 5)
        {
            String completeString = fb.getDocument().getText(0, fb.getDocument().getLength());
            completeString += string;

            if(Pattern.matches(regex, completeString))
            {
                super.insertString(fb, offset, string, attr);
            }
        }
    }
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
    {
        if ((fb.getDocument().getLength() + text.length()) <= 5)
        {
            String completeString = fb.getDocument().getText(0, fb.getDocument().getLength());
            completeString += text;

            if(Pattern.matches(regex, completeString))
            {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
