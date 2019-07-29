package hell.core.factory;

import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.List;

public interface Factory {
    Hero createHero(String name, String type);

    Item createItem(List<String> arguments);

    Recipe createRecipe(List<String>arguments);
}
