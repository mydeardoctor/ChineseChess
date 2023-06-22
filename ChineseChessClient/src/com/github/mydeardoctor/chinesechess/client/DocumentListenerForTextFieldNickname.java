package com.github.mydeardoctor.chinesechess.client;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentListenerForTextFieldNickname implements DocumentListener
{
    private boolean nicknameCorrect;
    private final Pattern patternForNickname;
    private final GUI gui;
    private static final Logger logger = Logger.getLogger(DocumentListenerForTextFieldNickname.class.getName());

    public DocumentListenerForTextFieldNickname(GUI gui)
    {
        nicknameCorrect = false;
        String regExForNickname =
            "^" +
            ".{1,15}" +
            "$";
        patternForNickname = Pattern.compile(regExForNickname);

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
            String nicknameText = e.getDocument().getText(0, e.getDocument().getLength());
            nicknameCorrect = checkNickname(nicknameText);
            if(nicknameCorrect)
            {
                gui.setNicknameCorrect();
            }
            else
            {
                gui.setNicknameIncorrect();
            }
        }
        catch(BadLocationException ex)
        {
            gui.setNicknameIncorrect();

            logger.logp(Level.WARNING, this.getClass().getName(), "changeGUI",
                    "Could not get contents of textFieldNickname.", ex);
        }
    }
    private boolean checkNickname(String ipText)
    {
        //Check with regEx.
        Matcher matcherForNickname = patternForNickname.matcher(ipText);
        return matcherForNickname.matches();
    }
    public boolean getIsNicknameCorrect()
    {
        return nicknameCorrect;
    }
}
