import appenders.Appender;
import appenders.ConsoleAppender;
import appenders.FileAppender;
import layouts.DateAndLevel;
import layouts.Layout;
import layouts.SimpleLayout;
import layouts.XmlLayout;
import loggers.Logger;
import loggers.MessageLogger;

public class Main {
    public static void main(String[] args) {

        Layout simple = new SimpleLayout();
        Layout xml = new XmlLayout();
        Layout dateAndLevel = new DateAndLevel();

        Appender consoleApp = new ConsoleAppender(simple);
        Appender fileAppender = new FileAppender(xml, "ERROR");
        Appender dateLevelFileAppender = new FileAppender(dateAndLevel, "FATAL");

        Logger logger = new MessageLogger(consoleApp, fileAppender, dateLevelFileAppender);

        logger.logInfo("3/31/2015 5:33:07 PM", "Everything seems fine");
        logger.logWarning("3/31/2015 5:33:07 PM", "Warning: ping is too high - disconnect imminent");
        logger.logError("3/31/2015 5:33:07 PM", "Error parsing request");
        logger.logCritical("3/31/2015 5:33:07 PM", "No connection string found in App.config");
        logger.logFatal("3/31/2015 5:33:07 PM", "mscorlib.dll does not respond");

        System.out.println(logger.logStatistics());
        System.out.println(((FileAppender) dateLevelFileAppender).getFileContentAsString());
    }
}
