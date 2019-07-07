package layouts;

public class SimpleLayout implements Layout {
    @Override
    public String formatMessage(String date, String reportLevel, String message) {
        return String.format("%s - %s - %s", date, reportLevel, message);
    }
}
