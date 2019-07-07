package appenders.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class FileValidator {

    static boolean gotConfirmationToProceed(String path) throws IOException {

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

    static boolean isValidFileExtension(String extension) {
        return extension.equals(".txt");
    }
}
