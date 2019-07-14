package listIterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p03_IteratorTest.ListIterator;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {
    private static final String[] ELEMENTS = {"First", "Second", "Third"};
    private static final String FIRST_ELEMENT = "First";
    private static final String[] NULL_ARRAY = null;
    private ListIterator listIterator;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.listIterator = new ListIterator(ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void shouldThrowWhenNullElementsInConstructor() throws OperationNotSupportedException {
        ListIterator empty = new ListIterator(NULL_ARRAY);
    }

    @Test
    public void shouldReturnTrueWhenMovingIndexFromStart() {
        boolean status = this.listIterator.move();
        Assert.assertTrue(status);
    }

    @Test
    public void shouldReturnFalseWhenMovingAtEnd() {
        this.listIterator.move();
        this.listIterator.move();
        this.listIterator.move();
        boolean status = this.listIterator.move();
        Assert.assertFalse(status);
    }

    @Test
    public void shouldReturnTrueWhenHasNextElement() {
        boolean status = this.listIterator.hasNext();
        Assert.assertTrue(status);
    }

    @Test
    public void shouldReturnFalseWhenNoNextElement() {
        this.listIterator.move();
        this.listIterator.move();
        this.listIterator.move();
        boolean status = this.listIterator.hasNext();
        Assert.assertFalse(status);
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrowWhenPrintOnEmptyIterator() throws OperationNotSupportedException {
        ListIterator empty = new ListIterator();
        empty.print();
    }

    @Test
    public void shouldPrintFirstElement() {
        String actual = this.listIterator.print();
        Assert.assertEquals(actual, FIRST_ELEMENT);
    }
}
