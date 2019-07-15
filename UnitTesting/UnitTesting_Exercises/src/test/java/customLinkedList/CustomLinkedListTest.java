package customLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p05_CustomLinkedList.CustomLinkedList;

public class CustomLinkedListTest {
    private static final String FIRST_STRING_ELEMENT = "String";
    private static final String SECOND_STRING_ELEMENT = "Second";
    private static final String INVALID_STRING = "Third";
    private static final int INDEX_OF_SECOND_ELEMENT = 1;
    private static final int INVALID_INDEX = -2;
    private CustomLinkedList<String> linkedList;

    @Before
    public void setUp() {
        this.linkedList = new CustomLinkedList<>();
        this.linkedList.add(FIRST_STRING_ELEMENT);
        this.linkedList.add(SECOND_STRING_ELEMENT);
    }

    @Test
    public void getsCorrectElementValueByIndex() {
        String actual = this.linkedList.get(INDEX_OF_SECOND_ELEMENT);
        Assert.assertEquals(SECOND_STRING_ELEMENT, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenGetIndexOutOfBounds() {
        this.linkedList.get(INVALID_INDEX);
    }

    @Test
    public void addsElementAtEndOfCollection() {
        this.linkedList.add(FIRST_STRING_ELEMENT);
        String actual = this.linkedList.get(INDEX_OF_SECOND_ELEMENT + 1);
        Assert.assertEquals(FIRST_STRING_ELEMENT, actual);
    }

    @Test
    public void setsCorrectElementValueByIndex() {
        this.linkedList.set(INDEX_OF_SECOND_ELEMENT, FIRST_STRING_ELEMENT);
        String actual = this.linkedList.get(INDEX_OF_SECOND_ELEMENT);
        Assert.assertEquals(FIRST_STRING_ELEMENT, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSetIndexOutOfBounds() {
        this.linkedList.set(INVALID_INDEX, FIRST_STRING_ELEMENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenRemoveAtIndexOutOfBounds() {
        this.linkedList.removeAt(INVALID_INDEX);
    }

    @Test
    public void removesCorrectElementByIndex() {
        String actual = this.linkedList.removeAt(INDEX_OF_SECOND_ELEMENT);
        Assert.assertEquals(SECOND_STRING_ELEMENT, actual);
    }

    @Test
    public void returnsTrueForElementListContains() {
        boolean result = this.linkedList.contains(SECOND_STRING_ELEMENT);
        Assert.assertTrue(result);
    }

    @Test
    public void returnsFalseForElementListDoesNotContain() {
        boolean result = this.linkedList.contains(INVALID_STRING);
        Assert.assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removesElementFromCollection() {
        this.linkedList.remove(SECOND_STRING_ELEMENT);
        String test = this.linkedList.get(INDEX_OF_SECOND_ELEMENT);
    }

    @Test
    public void returnsCorrectIndexOfRemovedElement() {
        int actual = this.linkedList.remove(SECOND_STRING_ELEMENT);
        Assert.assertEquals(INDEX_OF_SECOND_ELEMENT, actual);
    }

    @Test
    public void returnsNegativeOneIndexForRemoveOfNotFoundElement() {
        int actual = this.linkedList.remove(INVALID_STRING);
        Assert.assertEquals(-1, actual);
    }

    @Test
    public void returnsCorrectIndexOfElement() {
        int actual = this.linkedList.indexOf(SECOND_STRING_ELEMENT);
        Assert.assertEquals(INDEX_OF_SECOND_ELEMENT, actual);
    }

    @Test
    public void returnsNegativeOneForIndexOfElementNotContained() {
        int actual = this.linkedList.indexOf(INVALID_STRING);
        Assert.assertEquals(-1, actual);
    }
}
