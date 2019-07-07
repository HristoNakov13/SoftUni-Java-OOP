import appenders.Appender;
import appenders.ConsoleAppender;
import appenders.FileAppender;
import layouts.DateAndLevel;
import layouts.Layout;
import layouts.SimpleLayout;
import layouts.XmlLayout;
import loggers.Logger;
import loggers.MessageLogger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Layout simple = new SimpleLayout();
        Layout xml = new XmlLayout();
        Layout dateAndLevel = new DateAndLevel();

        Appender consoleApp = new ConsoleAppender(simple);
        Appender normalFileApp = new FileAppender(xml, "ERROR");
        Appender dateLevelFileAppender = new FileAppender(dateAndLevel, "FATAL");

        Logger logger = new MessageLogger(consoleApp, normalFileApp, dateLevelFileAppender);

        logger.logInfo("3/31/2015 5:33:07 PM", "Everything seems fine");
        logger.logWarning("3/31/2015 5:33:07 PM", "Warning: ping is too high - disconnect imminent");
        logger.logError("3/31/2015 5:33:07 PM", "Error parsing request");
        logger.logCritical("3/31/2015 5:33:07 PM", "No connection string found in App.config");
        logger.logFatal("3/31/2015 5:33:07 PM", "mscorlib.dll does not respond");

        System.out.println(logger.logStatistics());
        FileAppender fileAppender = (FileAppender) dateLevelFileAppender;
        System.out.println(fileAppender.getBufferedTextAsString());

        String invalidFile = "C:\\Users\\Username\\Desktop\\untitled\\src\\text.jpg";
        String path = "C:\\Users\\Username\\Desktop\\untitled\\src\\text.txt";

        try {
            fileAppender.writeBufferedTextOnFile(path);
            System.out.println(fileAppender.getFileContentAsString(path));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        System.out.println(fileAppender.getFileSize(invalidFile));
    }
}
