package com.github.mydeardoctor.chinesechess.server;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
        changeGUI(e);
    }
    @Override
    public void removeUpdate(DocumentEvent e)
    {
        changeGUI(e);
    }
    @Override
    public void changedUpdate(DocumentEvent e)
    {
        changeGUI(e);
    }
    private void changeGUI(DocumentEvent e)
    {
        try
        {
            String portText = e.getDocument().getText(0, e.getDocument().getLength());
            boolean result = checkPort(portText);
            if(result)
            {
                gui.setPortCorrect();
            }
            else
            {
                gui.setPortIncorrect();
            }
        }
        catch(Exception ex)
        {
            gui.setPortIncorrect();
        }
    }
    public boolean checkPort(String portText)
    {
        boolean result;

        try
        {
            //Check range with regEx.
            Matcher matcherForPortRange = patternForPortRange.matcher(portText);
            if(matcherForPortRange.matches())
            {
                //Parse.
                int portNumber = Integer.parseInt(portText);

                //Check range with math.
                result = (portNumber >= 1024) && (portNumber <= 65535);
            }
            else
            {
                result = false;
            }
        }
        catch(Exception e)
        {
            result = false;
        }

        return result;
    }
}
