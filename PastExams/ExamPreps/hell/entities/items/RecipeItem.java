package hell.entities.items;

import hell.interfaces.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeItem extends ItemImpl implements Recipe {
    private List<String> requiredItems;

    public RecipeItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPoints, int damageBonus, String... args) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPoints, damageBonus);
        this.requiredItems = new ArrayList<>(Arrays.asList(args));
    }

    @Override
    public List<String> getRequiredItems() {
        return this.requiredItems;
    }
}
