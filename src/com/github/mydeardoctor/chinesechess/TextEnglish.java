package com.github.mydeardoctor.chinesechess;

class TextEnglish extends Text
{
    @Override
    String getTitle()
    {
        return "Chinese Chess";
    }
    @Override
    String getHelp()
    {
        return "Help";
    }
    @Override
    String getPlay()
    {
        return "Play";
    }
    @Override
    String getLoad()
    {
        return "Load";
    }
    @Override
    String getRules()
    {
        return "Rules";
    }
    @Override
    String getSettings()
    {
        return "Settings";
    }
    @Override
    String getAbout()
    {
        return "About";
    }
    @Override
    String getAboutVerbose()
    {
        return String.format("Chinese Chess\nProgrammer:%16s\nComposer:%16s", "my_dear_doctor", "eskimolly");
    }
}
