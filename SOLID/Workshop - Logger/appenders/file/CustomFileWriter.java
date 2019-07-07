package appenders.file;

import layouts.Layout;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomFileWriter extends BufferAppender {
    public CustomFileWriter(Layout layout) {
        super(layout);
    }

    public CustomFileWriter(Layout layout, String reportLevel) {
        super(layout, reportLevel);
    }

    public void writeBufferedTextOnFile(String path) throws IOException {

        String extension = path.substring(path.lastIndexOf("."));

        if (!FileValidator.isValidFileExtension(extension)) {
            throw new IllegalArgumentException(String.format("Unsupported file extension: %s", extension));
        }
        if (!FileValidator.gotConfirmationToProceed(path)) {
            return;
        }

        try (BufferedWriter fileWriter = Files.newBufferedWriter(Paths.get(path))) {
            fileWriter.write(this.getBufferedTextAsString());
            super.clearBufferedText();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
