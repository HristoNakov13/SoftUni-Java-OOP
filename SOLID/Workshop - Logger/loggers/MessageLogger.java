package loggers;

import appenders.Appender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageLogger implements Logger {

    private List<Appender> appenders;

    public MessageLogger(Appender... appender) {
        this.appenders = new ArrayList<>();
        this.init(appender);
    }

    private void init(Appender[] appenders) {
        this.appenders.addAll(Arrays.asList(appenders));
    }

    @Override
    public void logError(String date, String message) {
        this.logImplementation(date, "ERROR", message);

    }

    @Override
    public void logInfo(String date, String message) {
        this.logImplementation(date, "INFO", message);
    }

    @Override
    public void logFatal(String date, String message) {
        this.logImplementation(date, "FATAL", message);
    }

    @Override
    public void logCritical(String date, String message) {
        this.logImplementation(date, "CRITICAL", message);
    }

    @Override
    public void logWarning(String date, String message) {
        this.logImplementation(date, "WARNING", message);
    }

    private List<Appender> getAppenders() {
        return appenders;
    }

    private void logImplementation(String date, String reportLevel, String message) {
        for (Appender appender : this.getAppenders()) {
            if (appender.canAppend(reportLevel)) {
                appender.append(date, reportLevel, message);
            }
        }
    }

    public String logStatistics() {
        StringBuilder stats = new StringBuilder();
        for (Appender appender : this.getAppenders()) {
            stats
                    .append(appender.appenderStatistics())
                    .append(System.lineSeparator());
        }
        return stats.toString().trim();
    }
}
