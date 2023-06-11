package com.github.mydeardoctor.chinesechess.server;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentListenerForTextFieldPlayers implements DocumentListener
{
    private final Pattern patternForPlayersRange;
    private final GUI gui;

    public DocumentListenerForTextFieldPlayers(GUI gui)
    {
        super();

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
            boolean result = checkPlayers(playersText);
            if(result)
            {
                gui.setPlayersCorrect();
            }
            else
            {
                gui.setPlayersIncorrect();
            }
        }
        catch(Exception ex)
        {
            gui.setPlayersIncorrect();
        }
    }
    public boolean checkPlayers(String playersText)
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
        catch(Exception e)
        {
            result = false;
        }

        return result;
    }
}
