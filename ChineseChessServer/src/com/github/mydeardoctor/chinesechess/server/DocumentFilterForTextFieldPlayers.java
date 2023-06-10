package com.github.mydeardoctor.chinesechess.server;

import com.github.mydeardoctor.chinesechess.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class DocumentFilterForTextFieldPlayers extends DocumentFilterForTextField
{
    private final Pattern patternForThreeNumbers;
    public DocumentFilterForTextFieldPlayers()
    {
        super();
        String regExForThreeNumbers =
                "^" +
                "[0-9]|" +
                "[1-9][0-9]|" +
                "[1-9][0-9][0-9]" +
                "$";
        patternForThreeNumbers = Pattern.compile(regExForThreeNumbers);
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException
    {
        String finalString = getFinalStringWhenInserted(fb, offset, string);

        //Check length with regEx.
        Matcher matcherForThreeNumbers = patternForThreeNumbers.matcher(finalString);
        if(matcherForThreeNumbers.matches())
        {
            //Check length with math.
            if(finalString.length() <= 3)
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
        Matcher matcherForThreeNumbers = patternForThreeNumbers.matcher(finalString);
        if(matcherForThreeNumbers.matches())
        {
            //Check length with math.
            if(finalString.length() <= 3)
            {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
