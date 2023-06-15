package com.github.mydeardoctor.chinesechess;

import java.util.GregorianCalendar;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter
{
    public LogFormatter()
    {
        super();
    }
    @Override
    public String format(LogRecord record)
    {
        //Get Date and Time.
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(record.getMillis());
        int day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = gregorianCalendar.get(GregorianCalendar.MONTH) + 1;
        int year = gregorianCalendar.get(GregorianCalendar.YEAR);
        int hour = gregorianCalendar.get(GregorianCalendar.HOUR_OF_DAY);
        int minute = gregorianCalendar.get(GregorianCalendar.MINUTE);
        int second = gregorianCalendar.get(GregorianCalendar.SECOND);
        int millisecond = gregorianCalendar.get(GregorianCalendar.MILLISECOND);
        String dateAndTime = String.format("%02d.%02d.%d %02d:%02d:%02d:%03d",
                day, month, year, hour, minute, second, millisecond);

        String className = record.getSourceClassName();
        String methodName = record.getSourceMethodName();
        String level = record.getLevel().toString();
        String message = record.getMessage();

        Throwable exception = record.getThrown();

        if(exception != null)
        {
            String stackTrace = "";
            StackTraceElement[] stackTraceElements = exception.getStackTrace();
            for(StackTraceElement stackTraceElement : stackTraceElements)
            {
                stackTrace = stackTrace.concat(stackTraceElement.toString()).concat("\n");
            }

            return String.format("%s\n%s::%s\n%s: %s\n%s\n%s\n",
                    dateAndTime,
                    className, methodName,
                    level, message,
                    exception,
                    stackTrace);
        }
        else
        {
            return String.format("%s\n%s::%s\n%s: %s\n\n",
                    dateAndTime,
                    className, methodName,
                    level, message);
        }
    }
}