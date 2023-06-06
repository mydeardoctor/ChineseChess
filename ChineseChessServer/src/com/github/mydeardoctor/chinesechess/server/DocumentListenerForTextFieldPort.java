package com.github.mydeardoctor.chinesechess.server;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentListenerForTextFieldPort implements DocumentListener
{
    private final Pattern patternPortRange;

    //GUI attributres.
    private final GUI gui;
    public DocumentListenerForTextFieldPort(GUI gui)
    {
        super();

        String regExPortRange =
            "^"+
            "102[4-9]|" +
            "10[3-9][0-9]|" +
            "1[1-9][0-9][0-9]|"+
            "[2-9][0-9][0-9][0-9]|" +
            "[1-5][0-9][0-9][0-9][0-9]|" +
            "6[0-4][0-9][0-9][0-9]|" +
            "65[0-4][0-9][0-9]|" +
            "655[0-2][0-9]|" +
            "6553[0-5]" +
            "$";
        patternPortRange = Pattern.compile(regExPortRange);

        this.gui = gui;
    }
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        matchWithRegEx(e);
    }
    @Override
    public void removeUpdate(DocumentEvent e)
    {
        matchWithRegEx(e);
    }
    @Override
    public void changedUpdate(DocumentEvent e)
    {
        matchWithRegEx(e);
    }
    private void matchWithRegEx(DocumentEvent e)
    {
        try
        {
            String string = e.getDocument().getText(0, e.getDocument().getLength());
            Matcher matcherPortRange = patternPortRange.matcher(string);
            if(matcherPortRange.matches())
            {
                gui.setPortCorrect();
            }
            else
            {
                gui.setPortIncorrect();
            }
        }
        catch(Exception exception)
        {
            gui.setPortIncorrect();
        }
    }
}
