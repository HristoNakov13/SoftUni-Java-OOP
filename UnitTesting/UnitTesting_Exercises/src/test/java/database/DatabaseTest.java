package database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Integer[] DEFAULT_DATABASE_VALUES = {1, 2, 3};
    private static final Integer DEFAULT_DATABASE_LENGTH = 3;
    private static final Integer[] ARRAY_EQUAL_TO_DATABASE_CAPACITY = new Integer[16];
    private static final Integer ELEMENT = 5;
    private static final Integer NULL_ELEMENT = null;

    //passing an Integer array with null values to Database constructor will not throw
    //with current implementation

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(DEFAULT_DATABASE_VALUES);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenEmptyConstructor() throws OperationNotSupportedException {
        Database empty = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenAddingNullElement() throws OperationNotSupportedException {
        this.database.add(NULL_ELEMENT);
    }

    @Test
    public void shouldReturnElementsInArray() {
        Integer[] elements = this.database.getElements();
        Assert.assertEquals(elements[0], DEFAULT_DATABASE_VALUES[0]);
        Assert.assertEquals(elements[1], DEFAULT_DATABASE_VALUES[1]);
        Assert.assertEquals(elements[2], DEFAULT_DATABASE_VALUES[2]);
    }

    @Test
    public void addedElementShouldGoAtTheEnd() throws OperationNotSupportedException {
        this.database.add(ELEMENT);
        Integer actual = this.database.getElements()[DEFAULT_DATABASE_LENGTH];
        Assert.assertEquals(actual, ELEMENT);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenAddingToFullCapacityDatabase() throws OperationNotSupportedException {
        Database full = new Database(ARRAY_EQUAL_TO_DATABASE_CAPACITY);
        full.add(ELEMENT);
    }

    @Test
    public void shouldRemoveLastElement() throws OperationNotSupportedException {
        this.database.add(ELEMENT);
        this.database.add(ELEMENT + 1);
        this.database.remove();
        Integer lastElement = this.database.getElements()[DEFAULT_DATABASE_LENGTH];
        Assert.assertEquals(lastElement, ELEMENT);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenRemovingFromEmptyDatabase() throws OperationNotSupportedException {
        this.database.remove();
        this.database.remove();
        this.database.remove();
        this.database.remove();
    }

}
