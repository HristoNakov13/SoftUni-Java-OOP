package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class HeroImpl implements Hero {
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;
    private Inventory inventory;

    HeroImpl(String name, int strength, int agility, int intelligence, int hitPoints, int damage) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.inventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.inventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.inventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.inventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.inventory.getTotalDamageBonus();
    }

    @SuppressWarnings("unchecked cast")
    @Override
    public Collection<Item> getItems() {
        Field[] fields = this.inventory.getClass().getDeclaredFields();
        Map<String, Item> itemsAsMap = new LinkedHashMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ItemCollection.class)) {
                field.setAccessible(true);
                try {
                    itemsAsMap = (Map<String, Item>) field.get(this.inventory);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return itemsAsMap.values();
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);

    }

    @Override
    public String toString() {
        StringBuilder heroInfo = new StringBuilder();
        heroInfo
                .append(String.format("Hero: %s, Class: %s%n", this.getName(), this.getClass().getSimpleName()))
                .append(String.format("HitPoints: %d, Damage: %d%n", this.getHitPoints(), this.getDamage()))
                .append(String.format("Strength: %d%n", this.getStrength()))
                .append(String.format("Agility: %d%n", this.getAgility()))
                .append(String.format("Intelligence: %d%n", this.getIntelligence()))
                .append("Items: ");

        Collection<Item> items = this.getItems();

        if (items.isEmpty()) {
            heroInfo.append("None");
        } else {
            heroInfo.append(System.lineSeparator());
            for (Item item : items) {
                heroInfo
                        .append(item.toString())
                        .append(System.lineSeparator());
            }
        }
        return heroInfo.toString().trim();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
    }
}
