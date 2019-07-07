package appenders;

import layouts.Layout;

import java.util.Arrays;
import java.util.HashSet;

public abstract class AppenderImpl implements Appender {
    private Layout layout;
    private ReportLevel reportLevel;
    private HashSet<String> availableReportLevels;
    private int msgAppendedCounter;

    protected AppenderImpl(Layout layout) {
        this.initialization(layout, "INFO");
    }

    protected AppenderImpl(Layout layout, String reportLevel) {
        this.initialization(layout, reportLevel);
    }

    private void initialization(Layout layout, String reportLevel) {
        this.availableReportLevels = new HashSet<>();
        this.layout = layout;
        this.reportLevel = ReportLevel.valueOf(reportLevel);
        this.msgAppendedCounter = 0;
        this.setReportLevel(this.reportLevel);
    }

    public void setReportLevel (ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
        String[] levels = reportLevel.getAvailableReportLevels().split(", ");
        this.availableReportLevels.clear();                         //to reset or not to reset?
        this.availableReportLevels.addAll(Arrays.asList(levels));
    }

    protected Layout getLayout() {
        return layout;
    }

    @Override
    public boolean canAppend(String reportLevel) {
        return this.availableReportLevels.contains(reportLevel);
    }


    protected String formatMessage(String date, String reportLevel, String message) {
        return this.getLayout().formatMessage(date, reportLevel, message);
    }

    void successfullyAppended() {
        this.msgAppendedCounter++;
    }

    private ReportLevel getReportLevel() {
        return this.reportLevel;
    }

    private int getMsgAppendedCounter() {
        return msgAppendedCounter;
    }

    @Override
    public String appenderStatistics() {
        String appenderType = this.getClass().getSimpleName();
        String layoutType = this.layout.getClass().getSimpleName();
        String reportLevel = this.getReportLevel().getName();
        return String.format("appenders.Appender type: %s, layouts.Layout type: %s, Report level: %s, Messages appended: %d"
                , appenderType
                , layoutType
                , reportLevel
                , this.getMsgAppendedCounter());
    }
}
