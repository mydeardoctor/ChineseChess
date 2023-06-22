package com.github.mydeardoctor.chinesechess;

import java.io.IOException;
import java.util.logging.*;

public abstract class ParentLogger
{
    private static final Logger logger = Logger.getLogger(ParentLogger.class.getPackageName());
    public static void initialize(String logFileName)
    {
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.WARNING);

        //Add Console Handler.
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        consoleHandler.setFormatter(new LogFormatter());
        logger.addHandler(consoleHandler);

        //Add File Handler.
        try
        {
            FileHandler fileHandler = new FileHandler(logFileName, 10000, 1, false);
            fileHandler.setLevel(Level.WARNING);
            fileHandler.setFormatter(new LogFormatter());
            logger.addHandler(fileHandler);
        }
        catch (IOException e)
        {
            logger.logp(Level.WARNING, ParentLogger.class.getName(), "initialize",
                    "Could not add fileHandler to parent logger.", e);
        }
    }
}