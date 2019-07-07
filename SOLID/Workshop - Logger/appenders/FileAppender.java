package appenders;

import layouts.Layout;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        this.bufferText(msg);
    }

    private void bufferText(String message) {
        this.file.append(message).append(System.lineSeparator());
        super.successfullyAppended();
    }

    public void writeBufferedTextOnFile(String path) throws IOException {

        String extension = path.substring(path.lastIndexOf("."));

        if (!isValidFileExtension(extension)) {
            throw new IllegalArgumentException(String.format("Unsupported file extension: %s", extension));
        }
        if (!gotConfirmationToProceed(path)) {
            return;
        }

        try (BufferedWriter fileWriter = Files.newBufferedWriter(Paths.get(path))) {
            fileWriter.write(this.getBufferedTextAsString());
            this.clearBufferedText();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean gotConfirmationToProceed(String path) throws IOException {

        String fileName = path.substring(path.lastIndexOf("\\") + 1);
        String nameOfWriteMethod = new Throwable()
                .getStackTrace()[1]
                .getMethodName();

        System.out.println(String.format("%s will delete current bufferedText and overwrite %s content after execution", nameOfWriteMethod, fileName));
        System.out.println("Proceed? Y/N");

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String confirmation = bfr.readLine();

        while (!confirmation.equals("Y") && !confirmation.equals("N")) {
            System.out.println("Please type Y - for \"Yes\" N - for \"NO\"");
            confirmation = bfr.readLine();
        }
        return !confirmation.equals("N");
    }

    private boolean isValidFileExtension(String extension) {
        return extension.equals(".txt");
    }

    private void clearBufferedText() {
        this.file.setLength(0);
    }

    public String getBufferedTextAsString() {
        return this.file.toString();
    }

    public String getFileSize(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return String.format("File %s does not exist", file.getName());
        }
        long size;
        size = file.length();
        return String.format("%d bytes", size);
    }

    public String getFileContentAsString(String path) {
        String extension = path.substring(path.lastIndexOf("."));
        if (!isValidFileExtension(extension)) {
            throw new IllegalArgumentException(String.format("Unsupported file extension: %s", extension));
        }

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                content
                        .append(line)
                        .append(System.lineSeparator());

                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return content.toString();
    }
}
