package hell.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CommandInterpreter {

    CommandInterpreter() {
    }

    List<String> interpretCommand(String line) {
        String[] array = line.split(" ");
        List<String> commandLine = new ArrayList<>();
        
        commandLine.addAll(Arrays.asList(array).subList(1, array.length));
        return commandLine;
    }

    String getCommandName(String line) {
        return line.split(" ")[0];
    }
}
