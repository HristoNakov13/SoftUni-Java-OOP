package appenders.file;

import appenders.AppenderImpl;
import layouts.Layout;

public class BufferAppender extends AppenderImpl {
    private StringBuilder file;

    public BufferAppender(Layout layout) {
        super(layout);
        this.file = new StringBuilder();
    }

    public BufferAppender(Layout layout, String reportLevel) {
        super(layout, reportLevel);
        this.file = new StringBuilder();
    }

    @Override
    public void append(String date, String reportLevel, String message) {
        String msg = super.formatMessage(date, reportLevel, message);
        this.bufferText(msg);
    }

    private void bufferText(String message) {
        this.file.append(message).append(System.lineSeparator());
        super.successfullyAppended();
    }

    protected void clearBufferedText() {
        this.file.setLength(0);
    }

    public String getBufferedTextAsString() {
        return this.file.toString();
    }


}
