package com.github.mydeardoctor.chinesechess;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParentLogger
{
    private static final Logger logger = Logger.getLogger(ParentLogger.class.getPackageName());
    public ParentLogger()
    {
        super();
    }
    public void initialize(String logFileName)
    {
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);

        //Add Console Handler.
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new LogFormatter());
        logger.addHandler(consoleHandler);

        //Add File Handler.
        try
        {
            FileHandler fileHandler = new FileHandler(logFileName, 10000, 1, false);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new LogFormatter());
            logger.addHandler(fileHandler);
        }
        catch (IOException e)
        {
            logger.logp(Level.WARNING, this.getClass().getName(), "initialize",
                    "Could not add fileHandler to logger.", e);
        }
    }
}