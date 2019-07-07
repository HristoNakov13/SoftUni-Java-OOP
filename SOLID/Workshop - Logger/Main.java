import appenders.Appender;
import appenders.ConsoleAppender;
import appenders.SortedAppender;
import appenders.file.CustomFileReader;
import appenders.file.CustomFileWriter;
import appenders.file.BufferAppender;
import layouts.*;
import loggers.Logger;
import loggers.MessageLogger;

public class Main {
    public static void main(String[] args) {

        Layout simple = new FullLayout();
        Layout xml = new XmlLayout();
        Layout dateAndLevel = new DateAndLevel();
        Layout message = new SimpleMessageLayout();

        Appender consoleApp = new ConsoleAppender(simple);
        Appender normalFileApp = new BufferAppender(xml, "ERROR");
        Appender sorted = new SortedAppender(message, "WARNING");

        CustomFileWriter writer = new CustomFileWriter(dateAndLevel);

        Logger logger = new MessageLogger(consoleApp, normalFileApp, writer, sorted);

        logger.logInfo("3/31/2015 5:33:07 PM", "Everything seems fine");
        logger.logWarning("3/31/2015 5:33:07 PM", "Warning: ping is too high - disconnect imminent");
        logger.logError("3/31/2015 5:33:07 PM", "Error parsing request");
        logger.logCritical("3/31/2015 5:33:07 PM", "No connection string found in App.config");
        logger.logFatal("3/31/2015 5:33:07 PM", "mscorlib.dll does not respond");

        System.out.println(logger.logStatistics());

        logger.logFatal("3/31/2019 5:33:07 PM", "boom boom crash");
        logger.logFatal("2/1/2018 17:22:07 AM", "lol");
        System.out.println(((SortedAppender) sorted).getReportLevelLog("FATAL"));


        String invalidFile = "C:\\Users\\Username\\Desktop\\untitled\\src\\text.jpg";
        String path = "C:\\Users\\Username\\Desktop\\untitled\\src\\text.txt";

        CustomFileReader reader = new CustomFileReader();

        try {
            writer.writeBufferedTextOnFile(path);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        System.out.println(reader.getFileSize(path));
        try {
            System.out.println(reader.getFileContentAsString(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
