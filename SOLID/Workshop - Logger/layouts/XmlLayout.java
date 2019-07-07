package layouts;

public class XmlLayout implements Layout {
    @Override
    public String formatMessage(String date, String reportLevel, String message) {
        StringBuilder formatted = new StringBuilder();
        formatted
                .append("<log>")
                .append(System.lineSeparator())
                .append("\t").append("<date>").append(date).append("</date>")
                .append(System.lineSeparator())
                .append("\t").append("<level>").append(reportLevel).append("</level>")
                .append(System.lineSeparator())
                .append("\t").append("<message>").append(message).append("</message>")
                .append(System.lineSeparator())
                .append("</log");
        return formatted.toString();
    }
}
