package hell.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CommandInterpreter {
    private String commandName;

    CommandInterpreter() {
    }

    List<String> interpretCommand(String line) {
        String[] array = line.split(" ");
        this.setCommandName(array[0]);

        List<String> commandLine = new ArrayList<>();
        commandLine.addAll(Arrays.asList(array).subList(1, array.length));
        return commandLine;
    }

    private void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    String getCommandName() {
        return commandName;
    }
}
