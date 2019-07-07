package appenders.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CustomFileReader  {

    public CustomFileReader() {
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
        if (!FileValidator.isValidFileExtension(extension)) {
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
