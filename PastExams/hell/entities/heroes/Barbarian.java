package hell.entities.heroes;

public class Barbarian extends HeroImpl {
    private static final int DEFAULT_STRENGTH = 90;
    private static final int DEFAULT_AGILITY = 25;
    private static final int DEFAULT_INTELLIGENCE = 10;
    private static final int DEFAULT_HITPOINTS = 350;
    private static final int DEFAULT_DAMAGE = 150;

    public Barbarian(String name) {
        super(name,
                DEFAULT_STRENGTH,
                DEFAULT_AGILITY,
                DEFAULT_INTELLIGENCE,
                DEFAULT_HITPOINTS,
                DEFAULT_DAMAGE);
    }
}
