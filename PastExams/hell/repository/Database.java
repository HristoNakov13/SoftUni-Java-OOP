package hell.repository;

import hell.interfaces.Hero;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private Map<String, Hero> repository;

    public Database() {
        this.repository = new LinkedHashMap<>();
    }

    public Hero getHeroByName(String name) {
        Hero hero = null;
        if (this.repository.containsKey(name)) {
            hero = this.repository.get(name);
        }
        return hero;
    }

    public void addHero (Hero hero) {
        this.repository.put(hero.getName(), hero);
    }

    public List<Hero> getAllHeroesAsList() {
        return new ArrayList<>(this.repository.values());
    }
}
