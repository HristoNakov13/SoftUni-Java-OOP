package hell.core.factory;

import hell.entities.items.CommonItem;
import hell.entities.items.ItemImpl;
import hell.entities.items.RecipeItem;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class HeroAndItemFactory implements Factory{
    private static final String HERO_FOLDER_PATH = "hell.entities.heroes.";
    private static final int PARAMS_NEEDED_FOR_ITEM_CREATION = 7;

    @Override
    @SuppressWarnings("unchecked call")
    public Hero createHero(String name, String type) {
        Hero hero = null;
        try {
            Class clazz = Class.forName(HERO_FOLDER_PATH + type);
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            hero = (Hero)constructor.newInstance(name);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return hero;
    }

    @Override
    public Item createItem(List<String> arguments) {
        String name = arguments.get(0);
        int strengthBonus = Integer.parseInt(arguments.get(2));
        int agilityBonus = Integer.parseInt(arguments.get(3));
        int intelligenceBonus = Integer.parseInt(arguments.get(4));
        int hitPointsBonus = Integer.parseInt(arguments.get(5));
        int damageBonus = Integer.parseInt(arguments.get(6));

        return new CommonItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus) {
        };
    }

    @Override
    public Recipe createRecipe(List<String> arguments) {
        String name = arguments.get(0);
        int strengthBonus = Integer.parseInt(arguments.get(2));
        int agilityBonus = Integer.parseInt(arguments.get(3));
        int intelligenceBonus = Integer.parseInt(arguments.get(4));
        int hitPointsBonus = Integer.parseInt(arguments.get(5));
        int damageBonus = Integer.parseInt(arguments.get(6));

        String[] requiredItems = new String[arguments.size() - PARAMS_NEEDED_FOR_ITEM_CREATION];

        int counter = 0;
        for (int i = PARAMS_NEEDED_FOR_ITEM_CREATION; i < arguments.size(); i++) {
            requiredItems[counter++] = arguments.get(i);
        }
        return new RecipeItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus, requiredItems);
    }
}
