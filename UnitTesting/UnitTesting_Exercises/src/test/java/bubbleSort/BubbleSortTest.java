package bubbleSort;

import org.junit.Assert;
import org.junit.Test;
import p04_BubbleSortTest.Bubble;

public class BubbleSortTest {
    private static final int[] UNSORTED_ARRAY = {4, 3, 20, 5};
    private static final int[] SORTED_ARRAY = {1, 2, 3, 4};
    private static final int EXPECTED_UNSORTED_FIRST_ELEMENT = 3;
    private static final int EXPECTED_UNSORTED_LAST_ELEMENT = 20;
    private static final int EXPECTED_SORTED_FIRST_ELEMENT = 1;
    private static final int EXPECTED_SORTED_LAST_ELEMENT = 4;


    @Test
    public void shouldSortArray() {
        Bubble.sort(UNSORTED_ARRAY);
        int firstElement = UNSORTED_ARRAY[0];
        int lastElement = UNSORTED_ARRAY[UNSORTED_ARRAY.length - 1];

        Assert.assertEquals(firstElement, EXPECTED_UNSORTED_FIRST_ELEMENT);
        Assert.assertEquals(lastElement, EXPECTED_UNSORTED_LAST_ELEMENT);
    }

    @Test
    public void shouldNotChangeStateOfArray() {
        Bubble.sort(SORTED_ARRAY);
        int firstElement = SORTED_ARRAY[0];
        int lastElement = SORTED_ARRAY[SORTED_ARRAY.length - 1];

        Assert.assertEquals(firstElement, EXPECTED_SORTED_FIRST_ELEMENT);
        Assert.assertEquals(lastElement, EXPECTED_SORTED_LAST_ELEMENT);
    }


}
