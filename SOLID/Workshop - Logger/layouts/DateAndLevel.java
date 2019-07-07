package layouts;

public class DateAndLevel implements Layout {
    @Override
    public String formatMessage(String date, String reportLevel, String message) {
        return String.format("Date: %s Threat level: %s", date, reportLevel);
    }
}
