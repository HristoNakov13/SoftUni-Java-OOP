package databaseExtended;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

public class DatabaseExtendedTest {
    private static final Person FIRST_DEFAULT_PERSON = new Person(1, "Ivan");
    private static final Person SECOND_DEFAULT_PERSON = new Person(2, "Pesho");
    private static final Person TEST_PERSON = new Person(3, "Test");
    private static final Person NULL_PERSON = null;
//    private static final int DEFAULT_DATABASE_LENGTH = 2;
    private static final Person[] PERSON_ARRAY_EQUAL_TO_DATABASE_CAPACITY = new Person[16];
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(FIRST_DEFAULT_PERSON, SECOND_DEFAULT_PERSON);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenEmptyConstructor() throws OperationNotSupportedException {
        Database empty = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenPassingBiggerArrayInConstructorThanDataBaseCapacity() throws OperationNotSupportedException {
        Database overLimit = new Database(new Person[PERSON_ARRAY_EQUAL_TO_DATABASE_CAPACITY.length + 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenAddingPersonWithNullValue() throws OperationNotSupportedException {
        this.database.add(NULL_PERSON);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenAddingToFullDatabase() throws OperationNotSupportedException {
        Database full = new Database(PERSON_ARRAY_EQUAL_TO_DATABASE_CAPACITY);
        full.add(TEST_PERSON);
    }

    @Test
    public void shouldReturnAddedPeopleAsPersonArray() {
        Person[] people = this.database.getElements();
        Assert.assertEquals(people[0].getUsername(), FIRST_DEFAULT_PERSON.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenRemovingFromEmptyDatabase() throws OperationNotSupportedException {
        this.database.remove();
        this.database.remove();
        this.database.remove();
    }

    @Test
    public void shouldFindAndReturnUserByID() throws OperationNotSupportedException {
        this.database.add(TEST_PERSON);
        Person actual = this.database.findById(TEST_PERSON.getId());
        Assert.assertEquals(actual.getId(), TEST_PERSON.getId());
    }

    @Test
    public void shouldFindAndReturnUserByUsername() throws OperationNotSupportedException {
        this.database.add(TEST_PERSON);
        Person actual = this.database.findByUsername(TEST_PERSON.getUsername());
        Assert.assertEquals(actual.getUsername(), TEST_PERSON.getUsername());
    }
}
