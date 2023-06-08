package com.github.mydeardoctor.chinesechess.client;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentListenerForTextFieldPort implements DocumentListener
{
    private final Pattern patternForPortRange;
    private final GUI gui;

    public DocumentListenerForTextFieldPort(GUI gui)
    {
        super();

        String regExForPortRange =
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
        patternForPortRange = Pattern.compile(regExForPortRange);

        this.gui = gui;
    }
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        checkPort(e);
    }
    @Override
    public void removeUpdate(DocumentEvent e)
    {
        checkPort(e);
    }
    @Override
    public void changedUpdate(DocumentEvent e)
    {
        checkPort(e);
    }
    private void checkPort(DocumentEvent e)
    {
        try
        {
            String portText = e.getDocument().getText(0, e.getDocument().getLength());

            //Check range with regEx.
            Matcher matcherForPortRange = patternForPortRange.matcher(portText);
            if(matcherForPortRange.matches())
            {
                //Parse.
                int portNumber = Integer.parseInt(portText);

                //Check range with math.
                if((portNumber >= 1024) && (portNumber <= 65535))
                {
                    gui.setPortCorrect();
                }
                else
                {
                    gui.setPortIncorrect();
                }
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
