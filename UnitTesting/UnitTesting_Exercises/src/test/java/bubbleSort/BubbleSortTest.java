package bubbleSort;

import org.junit.Assert;
import org.junit.Test;
import p04_BubbleSortTest.Bubble;

public class BubbleSortTest {
    private static final int[] UNSORTED_ARRAY = {4, 3, 20, 5};
    private static final int[] SORTED_ARRAY = {1, 2, 3, 4};
    private static final int EXPECTED_UNSORTED_FIRST_ELEMENT = 3;
    private static final int EXPECTED_UNSORTED_LAST_ELEMENT = 20;
    private static final int EXPECTED_SORTED_FIRST_ELEMENT = SORTED_ARRAY[0];
    private static final int EXPECTED_SORTED_LAST_ELEMENT = SORTED_ARRAY[SORTED_ARRAY.length - 1];


    @Test
    public void shouldSortArray() {
        Bubble.sort(UNSORTED_ARRAY);
        int actualFirstElement = UNSORTED_ARRAY[0];
        int actualLastElement = UNSORTED_ARRAY[UNSORTED_ARRAY.length - 1];

        Assert.assertEquals(EXPECTED_UNSORTED_FIRST_ELEMENT, actualFirstElement);
        Assert.assertEquals(EXPECTED_UNSORTED_LAST_ELEMENT, actualLastElement);
    }

    @Test
    public void shouldNotChangeStateOfArray() {
        Bubble.sort(SORTED_ARRAY);
        int actualFirstElement = SORTED_ARRAY[0];
        int actualLastElement = SORTED_ARRAY[SORTED_ARRAY.length - 1];

        Assert.assertEquals(EXPECTED_SORTED_FIRST_ELEMENT, actualFirstElement);
        Assert.assertEquals(EXPECTED_SORTED_LAST_ELEMENT, actualLastElement);
    }


}
