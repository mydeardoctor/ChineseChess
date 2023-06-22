package com.github.mydeardoctor.chinesechess.client;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentListenerForTextFieldPort implements DocumentListener
{
    private boolean portCorrect;
    private final Pattern patternForPortRange;
    private final GUI gui;
    private static final Logger logger = Logger.getLogger(DocumentListenerForTextFieldPort.class.getName());

    public DocumentListenerForTextFieldPort(GUI gui)
    {
        portCorrect = true;
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
            portCorrect = checkPort(portText);
            if(portCorrect)
            {
                gui.setPortCorrect();
            }
            else
            {
                gui.setPortIncorrect();
            }
        }
        catch(BadLocationException ex)
        {
            gui.setPortIncorrect();

            logger.logp(Level.WARNING, this.getClass().getName(), "changeGUI",
                    "Could not get contents of textFieldPort.", ex);
        }
    }
    private boolean checkPort(String portText)
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
        catch(NumberFormatException e)
        {
            result = false;

            logger.logp(Level.WARNING, this.getClass().getName(), "checkPort",
                    "Could not parse contents of textFieldPort.", e);
        }

        return result;
    }
    public boolean getIsPortCorrect()
    {
        return portCorrect;
    }
}
