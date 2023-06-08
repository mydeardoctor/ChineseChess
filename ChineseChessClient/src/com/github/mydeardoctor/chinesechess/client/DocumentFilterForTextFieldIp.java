package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.DocumentFilterForTextField;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class DocumentFilterForTextFieldIp extends DocumentFilterForTextField
{
    private final Pattern patternForNumbersAndDot;
    public DocumentFilterForTextFieldIp()
    {
        super();
        String regExForNumbersAndDot =
            "^" +
            "[0-9\\.]{1,15}" +
            "$";
        patternForNumbersAndDot = Pattern.compile(regExForNumbersAndDot);
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException
    {
        String finalString = getFinalStringWhenInserted(fb, offset, string);

        //Check with regEx.
        Matcher matcherForNumbersAndDot = patternForNumbersAndDot.matcher(finalString);
        if(matcherForNumbersAndDot.matches())
        {
            //Check length with math.
            if(finalString.length() <= 15)
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
        Matcher matcherForNumbersAndDot = patternForNumbersAndDot.matcher(finalString);
        if(matcherForNumbersAndDot.matches())
        {
            //Check length with math.
            if(finalString.length() <= 15)
            {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
