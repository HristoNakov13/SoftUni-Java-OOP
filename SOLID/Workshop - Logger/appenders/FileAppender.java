package appenders;

import layouts.Layout;

public class FileAppender extends AppenderImpl {
    private StringBuilder file;

    public FileAppender(Layout layout) {
        super(layout);
        this.file = new StringBuilder();
    }

    public FileAppender(Layout layout, String reportLevel) {
        super(layout, reportLevel);
        this.file = new StringBuilder();
    }

    @Override
    public void append(String date, String reportLevel, String message) {
        String msg = super.formatMessage(date, reportLevel, message);
        this.writeOnFile(msg);
    }

    private void writeOnFile(String message) {
        this.file.append(message).append(System.lineSeparator());
        super.successfullyAppended();
    }

    public String getFileContentAsString() {
        return this.file.toString();
    }

    public int getFileSize() {
        String asString = this.file.toString();
        int size = 0;
        for (int i = 0; i < asString.length(); i++) {

            char currentChar = asString.charAt(i);
            if(Character.isLetter(currentChar)) {
                size += currentChar;
            }
        }
        return size;
    }

}
