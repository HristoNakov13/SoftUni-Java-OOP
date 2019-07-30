package hell.core;

import hell.core.factory.Factory;
import hell.core.factory.HeroAndItemFactory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;
import hell.interfaces.Recipe;
import hell.repository.Database;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ManagerImpl implements Manager {

    private Database database;
    private Factory factory;

    ManagerImpl() {
        this.database = new Database();
        this.factory = new HeroAndItemFactory();
    }

    @Override
    public String addHero(List<String> arguments) {
        String heroName = arguments.get(0);
        String type = arguments.get(1);

        Hero hero = this.factory.createHero(heroName, type);
        this.database.addHero(hero);

        return String.format("Created %s - %s", type, heroName);
    }

    @Override
    public String addItem(List<String> arguments) {
        String itemName = arguments.get(0);
        String forHero = arguments.get(1);

        Item item = this.factory.createItem(arguments);
        this.database.getHeroByName(forHero).addItem(item);

        return String.format("Added item - %s to Hero - %s", itemName, forHero);
    }

    @Override
    public String addRecipe(List<String> arguments) {
        String recipeName = arguments.get(0);
        String forHero = arguments.get(1);

        Recipe recipe = this.factory.createRecipe(arguments);
        this.database.getHeroByName(forHero).addRecipe(recipe);

        return String.format("Added recipe - %s to Hero - %s", recipeName, forHero);
    }

    @Override
    public String inspect(List<String> arguments) {
        String inspectHero = arguments.get(0);
        return this.database.getHeroByName(inspectHero).toString();
    }

    @Override
    public String quit() {
        List<Hero> heroes = this.database.getAllHeroesAsList();
        StringBuilder allHeroesAsString = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(1);

        heroes
                .stream()
                .sorted((hero1, hero2) -> {
                    long firstHeroStatsSum =
                            hero1.getStrength() +
                                    hero1.getAgility() +
                                    hero1.getIntelligence();
                    long secondHeroStatsSum = hero2.getStrength() +
                            hero2.getAgility() +
                            hero2.getIntelligence();

                    int compare = Long.compare(secondHeroStatsSum, firstHeroStatsSum);
                    if (compare == 0) {
                        long firstHeroHPDmgSum = hero1.getHitPoints() + hero1.getDamage();
                        long secondHeroHPDmgSum = hero2.getHitPoints() + hero2.getDamage();
                        compare = Long.compare(secondHeroHPDmgSum, firstHeroHPDmgSum);
                    }
                    return compare;
                }).forEach(hero -> {
            allHeroesAsString
                    .append(String.format("%d. %s: %s%n"
                            , counter.getAndIncrement()
                            , hero.getClass().getSimpleName()
                            , hero.getName()))
                    .append(String.format("%s%n", finalHeroInfo(hero)));
        });

        return allHeroesAsString.toString();
    }

    private String finalHeroInfo(Hero hero) {
        StringBuilder heroInfo = new StringBuilder();
        heroInfo
                .append(String.format("###HitPoints: %d%n", hero.getHitPoints()))
                .append(String.format("###Damage: %d%n", hero.getDamage()))
                .append(String.format("###Strength: %d%n", hero.getStrength()))
                .append(String.format("###Agility: %d%n", hero.getAgility()))
                .append(String.format("###Intelligence: %d%n", hero.getIntelligence()))
                .append("###Items: ");

        Collection<Item> items = hero.getItems();

        if (items.isEmpty()) {
            heroInfo.append("None");
        } else {
            for (Item item : items) {
                heroInfo.append(item.getName()).append(", ");
            }
            heroInfo.deleteCharAt(heroInfo.length() - 1).deleteCharAt(heroInfo.length() - 1); //deletes " " white space and "," at the end
        }
        return heroInfo.toString();
    }
}
