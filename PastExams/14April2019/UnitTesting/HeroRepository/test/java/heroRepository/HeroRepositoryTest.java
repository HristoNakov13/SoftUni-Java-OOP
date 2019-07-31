package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class HeroRepositoryTest {
    private static final String PESHO_HERO_NAME = "Pesho";
    private static final int PESHO_HERO_LEVEL_LOWEST = 60;
    private static final int PESHO_INT = 10;
    private static final int PESHO_AGI = 1;
    private static final int PESHO_STR = 15;
    private static final Item PESHO_ITEM = new Item(PESHO_STR, PESHO_AGI, PESHO_INT);
    private static final Hero PESHO_HERO = new Hero(PESHO_HERO_NAME, PESHO_HERO_LEVEL_LOWEST, PESHO_ITEM);
    private static final int A_HERO_ADDED_SIZE = 1;
    private static final String PESHO_TO_STRING = String.format("Hero: %s – %d%n" +
                    "  *  Strength: %d%n" +
                    "  *  Agility: %d%n" +
                    "  *  Intelligence: %d%n", PESHO_HERO_NAME, PESHO_HERO_LEVEL_LOWEST,
            PESHO_STR, PESHO_AGI, PESHO_INT);

    private static final Hero NEGATIVE_STR_HERO = new Hero("Hero", 1, new Item(-1, 1, 1));
    private static final Hero NEGATIVE_AGI_HERO = new Hero("Hero", 1, new Item(1, -1, 1));
    private static final Hero NEGATIVE_INT_HERO = new Hero("Hero", 1, new Item(1, 1, -1));
    

    private HeroRepository repository;

    @Before
    public void init() {
        this.repository = new HeroRepository();
    }

    @Test
    public void shouldAddHeroSuccessfully() {
        this.repository.add(PESHO_HERO);
        int expectedSize = A_HERO_ADDED_SIZE;
        Assert.assertEquals(expectedSize, this.repository.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAddingAlreadyExistingHero() {
        this.repository.add(PESHO_HERO);
        this.repository.add(PESHO_HERO);
    }

    @Test
    public void shouldRemoveHeroFromRepository() {
        this.repository.add(PESHO_HERO);
        this.repository.remove(PESHO_HERO_NAME);
        int actualSize = this.repository.getCount();
        Assert.assertEquals(0, actualSize);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenRemoveNonExistingHero() {
        this.repository.remove(PESHO_HERO_NAME);
    }

    @Test
    public void shouldReturnCorrectHeroWithHighestStrength() {
        this.repository.add(PESHO_HERO);
        Hero hero = this.repository.getHeroWithHighestStrength();

        Assert.assertEquals(PESHO_HERO, hero);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenNoHigherWithHighestStrengthFound() {
        this.repository.add(NEGATIVE_STR_HERO);
        this.repository.getHeroWithHighestStrength();
    }

    @Test
    public void shouldReturnCorrectHeroWithHighestAgility() {
        this.repository.add(PESHO_HERO);
        Hero hero = this.repository.getHeroWithHighestAgility();

        Assert.assertEquals(PESHO_HERO, hero);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenNoHeroWithHighestAgilityFound() {

        this.repository.add(NEGATIVE_AGI_HERO);
        this.repository.getHeroWithHighestAgility();
    }

    @Test
    public void shouldReturnCorrectHeroWithHighestIntelligence() {
        this.repository.add(PESHO_HERO);
        Hero hero = this.repository.getHeroWithHighestIntelligence();

        Assert.assertEquals(PESHO_HERO, hero);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenNoHeroWithHighestIntelligenceFound() {
        this.repository.add(NEGATIVE_INT_HERO);
        this.repository.getHeroWithHighestIntelligence();
    }

    @Test
    public void shouldReturnCorrectHeroInformationAsString() {
        this.repository.add(PESHO_HERO);
        String actual = this.repository.toString();
        Assert.assertEquals(PESHO_TO_STRING, actual);
    }
}