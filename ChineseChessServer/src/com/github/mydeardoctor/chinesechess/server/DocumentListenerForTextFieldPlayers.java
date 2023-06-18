package com.github.mydeardoctor.chinesechess.server;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentListenerForTextFieldPlayers implements DocumentListener
{
    private boolean playersCorrect;
    private final Pattern patternForPlayersRange;
    private final GUI gui;
    private static final Logger logger = Logger.getLogger(DocumentListenerForTextFieldPlayers.class.getName());

    public DocumentListenerForTextFieldPlayers(GUI gui)
    {
        super();

        playersCorrect = true;
        String regExForPlayersRange =
            "^"+
            "[2-9]|" +
            "[1-9][0-9]|" +
            "100"+
            "$";
        patternForPlayersRange = Pattern.compile(regExForPlayersRange);

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
            String playersText = e.getDocument().getText(0, e.getDocument().getLength());
            playersCorrect = checkPlayers(playersText);
            if(playersCorrect)
            {
                gui.setPlayersCorrect();
            }
            else
            {
                gui.setPlayersIncorrect();
            }
        }
        catch(BadLocationException ex)
        {
            gui.setPlayersIncorrect();

            logger.logp(Level.WARNING, this.getClass().getName(), "changeGUI",
                    "Could not get contents of textFieldPlayers.", ex);
        }
    }
    private boolean checkPlayers(String playersText)
    {
        boolean result;

        try
        {
            //Check range with regEx.
            Matcher matcherForPlayersRange = patternForPlayersRange.matcher(playersText);
            if(matcherForPlayersRange.matches())
            {
                //Parse.
                int playersNumber = Integer.parseInt(playersText);

                //Check range with math.
                result = (playersNumber >= 2) && (playersNumber <= 100);
            }
            else
            {
                result = false;
            }
        }
        catch(NumberFormatException e)
        {
            result = false;

            logger.logp(Level.WARNING, this.getClass().getName(), "checkPlayers",
                    "Could not parse contents of textFieldPlayers.", e);
        }

        return result;
    }
    public boolean getArePlayersCorrect()
    {
        return playersCorrect;
    }
}
