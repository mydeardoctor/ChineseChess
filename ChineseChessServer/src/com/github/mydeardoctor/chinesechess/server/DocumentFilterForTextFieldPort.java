package com.github.mydeardoctor.chinesechess.server;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentFilterForTextFieldPort extends DocumentFilter
{
    private final Pattern patternOnlyNumbers;
    public DocumentFilterForTextFieldPort()
    {
        super();
        String regExOnlyNumbers = "^[0-9]*$";
        patternOnlyNumbers = Pattern.compile(regExOnlyNumbers);
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {
        int originalLength = fb.getDocument().getLength();
        String originalString = fb.getDocument().getText(0, originalLength);

        String leftPart;
        if(offset == 0)
        {
            leftPart = "";
        }
        else
        {
            leftPart = originalString.substring(0, offset);
        }

        String rightPart;
        if(offset > (originalLength - 1))
        {
            rightPart = "";
        }
        else
        {
            rightPart = originalString.substring(offset, originalLength);
        }

        String finalString = leftPart + string + rightPart;

        if(finalString.length() <= 5)
        {
            Matcher matcherOnlyNumbers = patternOnlyNumbers.matcher(finalString);
            if(matcherOnlyNumbers.matches())
            {
                super.insertString(fb, offset, string, attr);
            }
        }
    }
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
    {
        int originalLength = fb.getDocument().getLength();
        String originalString = fb.getDocument().getText(0, originalLength);

        String leftPart;
        if(offset == 0)
        {
            leftPart = "";
        }
        else
        {
            leftPart = originalString.substring(0, offset);
        }

        String rightPart;
        if(offset > (originalLength - 1))
        {
            rightPart = "";
        }
        else
        {
            rightPart = originalString.substring(offset, originalLength);
        }

        String rightPartWithoutDeletedCharacters = rightPart.substring(length, rightPart.length());

        String finalString = leftPart + text + rightPartWithoutDeletedCharacters;

        if(finalString.length() <= 5)
        {
            Matcher matcherOnlyNumbers = patternOnlyNumbers.matcher(finalString);
            if(matcherOnlyNumbers.matches())
            {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
