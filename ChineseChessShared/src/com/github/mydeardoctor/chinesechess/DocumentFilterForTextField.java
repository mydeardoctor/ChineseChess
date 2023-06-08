package com.github.mydeardoctor.chinesechess;

import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public abstract class DocumentFilterForTextField extends DocumentFilter
{
    public DocumentFilterForTextField()
    {
        super();
    }
    protected String getFinalStringWhenInserted(FilterBypass fb, int offset, String string)
            throws BadLocationException
    {
        int originalLength = fb.getDocument().getLength();
        String originalString = fb.getDocument().getText(0, originalLength);
        String leftPart = getLeftPart(originalString, offset);
        String rightPart = getRightPart(originalString, originalLength, offset);
        return leftPart + string + rightPart;
    }
    protected String getFinalStringWhenReplaced(FilterBypass fb, int offset, int length, String text)
            throws BadLocationException
    {
        int originalLength = fb.getDocument().getLength();
        String originalString = fb.getDocument().getText(0, originalLength);
        String leftPart = getLeftPart(originalString, offset);
        String rightPart = getRightPart(originalString, originalLength, offset);
        String rightPartCut = rightPart.substring(length);
        return leftPart + text + rightPartCut;
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
