package appenders;

public enum ReportLevel {
    INFO("INFO, WARNING, ERROR, CRITICAL, FATAL"),
    WARNING("WARNING, ERROR, CRITICAL, FATAL"),
    ERROR("ERROR, CRITICAL, FATAL"),
    CRITICAL("CRITICAL, FATAL"),
    FATAL("FATAL")
    ;


    private String value;

    ReportLevel(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name();
    }

    public String getAvailableReportLevels() {
        return this.value;
    }
}
