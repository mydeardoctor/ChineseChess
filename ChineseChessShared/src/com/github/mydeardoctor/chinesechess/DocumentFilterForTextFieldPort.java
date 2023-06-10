package com.github.mydeardoctor.chinesechess;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentFilterForTextFieldPort extends DocumentFilterForTextField
{
    private final Pattern patternForFiveNumbers;
    public DocumentFilterForTextFieldPort()
    {
        super();
        String regExForFiveNumbers =
                "^" +
                "[0-9]|" +
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
        String finalString = getFinalStringWhenInserted(fb, offset, string);

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
        String finalString = getFinalStringWhenReplaced(fb, offset, length, text);

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
}