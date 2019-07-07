package appenders;

public interface Appender {
    void append(String date, String reportLevel, String message);
    boolean canAppend(String reportLevel);
    String appenderStatistics();
}
