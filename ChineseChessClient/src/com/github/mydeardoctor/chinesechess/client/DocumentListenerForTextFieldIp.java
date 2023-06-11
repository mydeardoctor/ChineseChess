package com.github.mydeardoctor.chinesechess.client;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentListenerForTextFieldIp implements DocumentListener
{
    public static final String REGEX_FOR_IP =
        "^" +
        "([0-9]|[0-9][0-9]|[0-1][0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
        "([0-9]|[0-9][0-9]|[0-1][0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
        "([0-9]|[0-9][0-9]|[0-1][0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
        "([0-9]|[0-9][0-9]|[0-1][0-9][0-9]|2[0-4][0-9]|25[0-5])" +
        "$";
    private final Pattern patternForIp;
    private String ipAddressWithoutLeadingZeros;
    private final GUI gui;

    public DocumentListenerForTextFieldIp(GUI gui)
    {
        super();

        patternForIp = Pattern.compile(REGEX_FOR_IP);

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
            String ipText = e.getDocument().getText(0, e.getDocument().getLength());
            boolean result = checkIp(ipText);
            if(result)
            {
                gui.setIpCorrect();
            }
            else
            {
                gui.setIpIncorrect();
            }
        }
        catch(Exception ex)
        {
            gui.setIpIncorrect();
        }
    }
    public boolean checkIp(String ipText)
    {
        boolean result;

        try
        {
            //Check with regEx.
            Matcher matcherForIp = patternForIp.matcher(ipText);

            if(matcherForIp.matches())
            {
                //Get groups.
                String byte1 = matcherForIp.group(1);
                String byte2 = matcherForIp.group(2);
                String byte3 = matcherForIp.group(3);
                String byte4 = matcherForIp.group(4);

                //Parse.
                int[] ipAddress = new int[4];
                ipAddress[0] = Integer.parseInt(byte1);
                ipAddress[1] = Integer.parseInt(byte2);
                ipAddress[2] = Integer.parseInt(byte3);
                ipAddress[3] = Integer.parseInt(byte4);
                ipAddressWithoutLeadingZeros =
                        ipAddress[0] + "." +
                        ipAddress[1] + "." +
                        ipAddress[2] + "." +
                        ipAddress[3];

                //Check range with math.
                result =
                        (
                        (ipAddress[0] >= 0) && (ipAddress[0] <= 255) &&
                        (ipAddress[1] >= 0) && (ipAddress[1] <= 255) &&
                        (ipAddress[2] >= 0) && (ipAddress[2] <= 255) &&
                        (ipAddress[3] >= 0) && (ipAddress[3] <= 255)
                        );
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
    public String getIpAddressWithoutLeadingZeros()
    {
        return ipAddressWithoutLeadingZeros;
    }
}
