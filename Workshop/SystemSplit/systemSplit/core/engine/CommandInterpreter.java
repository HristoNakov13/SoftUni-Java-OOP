package systemSplit.core.engine;

public class CommandInterpreter {
    public CommandInterpreter() {
    }

    public String[] interpretCommand(String line) {
        String substring = "";
        if (line.contains("(")) {
            substring = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
        }
        if (substring.isEmpty()) {
            return new String[0];
        }
        return substring.split(", ");
    }

    public String getCommandName(String line) {
        if (line.contains("(")) {
            return line.substring(0, line.indexOf("("));
        }
        return "System Split";
    }
}
