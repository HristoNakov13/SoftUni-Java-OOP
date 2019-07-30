package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTest {
    private static final String PESHO_HERO_NAME = "Pesho";
    private static final int PESHO_HERO_LEVEL_LOWEST = 60;
    private static final int PESHO_HIGHEST_INT = 10;
    private static final int PESHO_LOWEST_AGI = 1;
    private static final int PESHO_HIGHEST_STR = 15;
    private static final Item PESHO_ITEM = new Item(PESHO_HIGHEST_STR, PESHO_LOWEST_AGI, PESHO_HIGHEST_INT);
    private static final Hero PESHO_HERO = new Hero(PESHO_HERO_NAME, PESHO_HERO_LEVEL_LOWEST, PESHO_ITEM);
    private static final int A_HERO_ADDED_SIZE = 1;
    private static final int EMPTY_REPOSITORY_SIZE = 0;
    private static final String PESHO_TO_STRING = String.format("Hero: %s – %d%n" +
                    "  *  Strength: %d%n" +
                    "  *  Agility: %d%n" +
                    "  *  Intelligence: %d%n", PESHO_HERO_NAME, PESHO_HERO_LEVEL_LOWEST,
            PESHO_HIGHEST_STR, PESHO_LOWEST_AGI, PESHO_HIGHEST_INT);

    private static final String IVAN_HERO_NAME = "Ivan";
    private static final int IVAN_HERO_LEVEL_LOWEST = 100;
    private static final int IVAN_LOWEST_INT = 2;
    private static final int IVAN_HIGHEST_AGI = 20;
    private static final int IVAN_LOWEST_STR = 3;
    private static final Item IVAN_ITEM = new Item(IVAN_LOWEST_STR, IVAN_HIGHEST_AGI, IVAN_LOWEST_INT);
    private static final Hero IVAN_HERO = new Hero(IVAN_HERO_NAME, IVAN_HERO_LEVEL_LOWEST, IVAN_ITEM);
    private static final Hero DUPLICATE_PESHO = new Hero(PESHO_HERO_NAME, 1,  IVAN_ITEM);

    private HeroRepository repository;

    @Before
    public void init() {
        this.repository = new HeroRepository();
    }

    @Test
    public void shouldReturnCorrectAddedHeroesCount() {
        Assert.assertEquals(EMPTY_REPOSITORY_SIZE, this.repository.getCount());
        this.repository.add(PESHO_HERO);
        Assert.assertEquals(A_HERO_ADDED_SIZE, this.repository.getCount());
    }

    @Test
    public void shouldAddHeroSuccessfully() {
        this.repository.add(IVAN_HERO);
        int expectedSize = A_HERO_ADDED_SIZE;
        Assert.assertEquals(expectedSize, this.repository.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAddingAlreadyExistingHero() {
        this.repository.add(PESHO_HERO);
        this.repository.add(DUPLICATE_PESHO);
    }

    @Test
    public void shouldRemoveHeroFromRepository() {
        this.repository.add(IVAN_HERO);
        this.repository.remove(IVAN_HERO_NAME);
        int actualSize = this.repository.getCount();
        Assert.assertEquals(EMPTY_REPOSITORY_SIZE, actualSize);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenRemoveNonExistingHero() {
        this.repository.add(IVAN_HERO);
        this.repository.remove(PESHO_HERO_NAME);
    }

    @Test
    public void shouldReturnCorrectHeroWithHighestStrength() {
        this.repository.add(PESHO_HERO);
        this.repository.add(IVAN_HERO);
        Hero hero = this.repository.getHeroWithHighestStrength();
        int actualStrength = hero.getItem().getStrength();

        Assert.assertEquals(PESHO_HIGHEST_STR, actualStrength);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenNoHigherWithHighestStrengthFound() {
        this.repository.getHeroWithHighestStrength();
    }

    @Test
    public void shouldReturnCorrectHeroWithHighestAgility() {
        this.repository.add(PESHO_HERO);
        this.repository.add(IVAN_HERO);
        Hero hero = this.repository.getHeroWithHighestAgility();
        int actualAgility = hero.getItem().getAgility();

        Assert.assertEquals(IVAN_HIGHEST_AGI, actualAgility);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenNoHigherWithHighestAgilityFound() {
        this.repository.getHeroWithHighestAgility();
    }

    @Test
    public void shouldReturnCorrectHeroWithHighestIntelligence() {
        this.repository.add(PESHO_HERO);
        this.repository.add(IVAN_HERO);
        Hero hero = this.repository.getHeroWithHighestIntelligence();
        int actualIntelligence = hero.getItem().getIntelligence();

        Assert.assertEquals(PESHO_HIGHEST_INT, actualIntelligence);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenNoHigherWithHighestIntelligenceFound() {
        this.repository.getHeroWithHighestIntelligence();
    }

    @Test
    public void shouldReturnCorrectHeroInformationAsString() {
        this.repository.add(PESHO_HERO);
        String actual = this.repository.toString();
        Assert.assertEquals(PESHO_TO_STRING, actual);
    }
}