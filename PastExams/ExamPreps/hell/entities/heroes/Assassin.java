package hell.entities.heroes;

public class Assassin extends HeroImpl {
    private static final int DEFAULT_STRENGTH = 25;
    private static final int DEFAULT_AGILITY = 100;
    private static final int DEFAULT_INTELLIGENCE = 15;
    private static final int DEFAULT_HITPOINTS = 150;
    private static final int DEFAULT_DAMAGE = 300;

    public Assassin(String name) {
        super(name,
                DEFAULT_STRENGTH,
                DEFAULT_AGILITY,
                DEFAULT_INTELLIGENCE,
                DEFAULT_HITPOINTS,
                DEFAULT_DAMAGE);
    }
}
