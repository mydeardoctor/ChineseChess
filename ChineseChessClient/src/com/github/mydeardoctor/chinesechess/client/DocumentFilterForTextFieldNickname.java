package com.github.mydeardoctor.chinesechess.client;

import com.github.mydeardoctor.chinesechess.DocumentFilterForTextField;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentFilterForTextFieldNickname extends DocumentFilterForTextField
{
    private final Pattern patternForFifteenCharacters;
    public DocumentFilterForTextFieldNickname()
    {
        super();
        String regExForFifteenCharacters =
            "^" +
            ".{1,15}" +
            "$";
        patternForFifteenCharacters = Pattern.compile(regExForFifteenCharacters);
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException
    {
        String finalString = getFinalStringWhenInserted(fb, offset, string);

        //Check length with regEx.
        Matcher matcherForFifteenCharacters = patternForFifteenCharacters.matcher(finalString);
        if(matcherForFifteenCharacters.matches())
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
        Matcher matcherForFifteenCharacters = patternForFifteenCharacters.matcher(finalString);
        if(matcherForFifteenCharacters.matches())
        {
            //Check length with math.
            if(finalString.length() <= 15)
            {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
