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
            "[1-9]|" +
            "[1-9][0-9]|" +
            "100"+
            "$";
        patternForPlayersRange = Pattern.compile(regExForPlayersRange);

        this.gui = gui;
    }
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        checkPlayers(e);
    }
    @Override
    public void removeUpdate(DocumentEvent e)
    {
        checkPlayers(e);
    }
    @Override
    public void changedUpdate(DocumentEvent e)
    {
        checkPlayers(e);
    }
    private void checkPlayers(DocumentEvent e)
    {
        try
        {
            String playersText = e.getDocument().getText(0, e.getDocument().getLength());

            //Check range with regEx.
            Matcher matcherForPlayersRange = patternForPlayersRange.matcher(playersText);
            if(matcherForPlayersRange.matches())
            {
                //Parse.
                int playersNumber = Integer.parseInt(playersText);

                //Check range with math.
                if((playersNumber >= 1) && (playersNumber <= 100))
                {
                    gui.setPlayersCorrect();
                }
                else
                {
                    gui.setPlayersIncorrect();
                }
            }
            else
            {
                gui.setPlayersIncorrect();
            }
        }
        catch(Exception exception)
        {
            gui.setPlayersIncorrect();
        }
    }
}
