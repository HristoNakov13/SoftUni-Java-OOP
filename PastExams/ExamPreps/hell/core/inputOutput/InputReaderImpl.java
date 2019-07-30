package hell.core.inputOutput;

import hell.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReaderImpl implements InputReader {
    private BufferedReader bfr;

    public InputReaderImpl() {
        this.bfr = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        try {
            return this.bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
