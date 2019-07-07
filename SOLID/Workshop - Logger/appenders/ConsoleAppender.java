package appenders;

import layouts.Layout;

public class ConsoleAppender extends AppenderImpl {

    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    public ConsoleAppender(Layout layout, String reportLevel) {
        super(layout, reportLevel);
    }

    @Override
    public void append(String date, String reportLevel, String message) {
        String formatted = super.formatMessage(date, reportLevel, message);
        System.out.println(formatted);
        super.successfullyAppended();
    }
}
