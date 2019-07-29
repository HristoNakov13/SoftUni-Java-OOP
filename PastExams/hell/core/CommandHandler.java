package hell.core;

import hell.interfaces.Manager;

import java.util.List;

class CommandHandler {
    private Manager manager;

    CommandHandler() {
        this.manager = new ManagerImpl();
    }

    String handleCommand(String commandName, List<String> arguments) {
        String result = "";
        switch (commandName) {
            case "Hero":
                result = this.manager.addHero(arguments);
                break;
            case "Item":
                result = this.manager.addItem(arguments);
                break;
            case "Recipe":
                result = this.manager.addRecipe(arguments);
                break;
            case "Inspect":
                result = this.manager.inspect(arguments);
                break;
            case "Quit":
                result = this.manager.quit();
                break;
        }
        return result;
    }
}
