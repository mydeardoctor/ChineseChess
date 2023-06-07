package com.github.mydeardoctor.chinesechess.server;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class DocumentFilterForTextFieldPort extends DocumentFilter
{
    private final Pattern patternForFiveNumbers;
    public DocumentFilterForTextFieldPort()
    {
        super();
        String regExForFiveNumbers =
                "^" +
                "[1-9]|" +
                "[1-9][0-9]|" +
                "[1-9][0-9][0-9]|" +
                "[1-9][0-9][0-9][0-9]|" +
                "[1-9][0-9][0-9][0-9][0-9]" +
                "$";
        patternForFiveNumbers = Pattern.compile(regExForFiveNumbers);
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException
    {
        //Reconstruct final string.
        int originalLength = fb.getDocument().getLength();
        String originalString = fb.getDocument().getText(0, originalLength);
        String leftPart = getLeftPart(originalString, offset);
        String rightPart = getRightPart(originalString, originalLength, offset);
        String finalString = leftPart + string + rightPart;

        //Check length with regEx.
        Matcher matcherForFiveNumbers = patternForFiveNumbers.matcher(finalString);
        if(matcherForFiveNumbers.matches())
        {
            //Check length with math.
            if(finalString.length() <= 5)
            {
                super.insertString(fb, offset, string, attr);
            }
        }
    }
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException
    {
        //Reconstruct final string.
        int originalLength = fb.getDocument().getLength();
        String originalString = fb.getDocument().getText(0, originalLength);
        String leftPart = getLeftPart(originalString, offset);
        String rightPart = getRightPart(originalString, originalLength, offset);
        String rightPartCut = rightPart.substring(length, rightPart.length());
        String finalString = leftPart + text + rightPartCut;

        //Check length with regEx.
        Matcher matcherForFiveNumbers = patternForFiveNumbers.matcher(finalString);
        if(matcherForFiveNumbers.matches())
        {
            //Check length with math.
            if(finalString.length() <= 5)
            {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
    private String getLeftPart(String originalString, int offset)
    {
        String leftPart;

        if(offset == 0)
        {
            leftPart = "";
        }
        else
        {
            leftPart = originalString.substring(0, offset);
        }

        return leftPart;
    }
    private String getRightPart(String originalString, int originalLength, int offset)
    {
        String rightPart;

        if(offset > (originalLength - 1))
        {
            rightPart = "";
        }
        else
        {
            rightPart = originalString.substring(offset, originalLength);
        }

        return rightPart;
    }
}