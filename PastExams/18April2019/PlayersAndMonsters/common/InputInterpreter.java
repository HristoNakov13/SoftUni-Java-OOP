package common;

import java.util.Arrays;

public class InputInterpreter {

    public InputInterpreter() {
    }

    public String getCommandName (String line) {
        return line.split(" ")[0];
    }

    public String[] getCommandArguments(String line) {
        String[] split = line.split(" ");
        return Arrays.copyOfRange(split, 1, split.length);
    }
}
