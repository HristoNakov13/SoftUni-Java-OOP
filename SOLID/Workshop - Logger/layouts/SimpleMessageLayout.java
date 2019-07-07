package layouts;

public class SimpleMessageLayout implements Layout {
    @Override
    public String formatMessage(String date, String reportLevel, String message) {
        return message;
    }
}
