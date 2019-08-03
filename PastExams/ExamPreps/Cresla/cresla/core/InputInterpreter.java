package cresla.core;

import java.util.Arrays;
import java.util.List;

public class InputInterpreter {
    public InputInterpreter() {

    }

    public String getCommandName(String line) {

        return line.split(" ")[0];
    }

    public List<String> getCommandArguments(String line) {
        String[] splitIntoArray = line.split(" ");
        return Arrays.asList(splitIntoArray).subList(1, splitIntoArray.length);
    }
}
