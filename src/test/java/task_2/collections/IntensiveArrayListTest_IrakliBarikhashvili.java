package task_2.collections;

import task_2.IntensiveTest_IrakliBarikhashvili;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static task_2.Assertions_IrakliBarikhashvili.*;

public class IntensiveArrayListTest_IrakliBarikhashvili {
    @IntensiveTest_IrakliBarikhashvili
    public void test() {
        var sortedList = new ArrayList_IrakliBarikhashvili<>(List.of(1,2,3,4,5));
        var unsortedList = new ArrayList_IrakliBarikhashvili<>(List.of(10,5,3,6,2,1,7));

        var sortedListIsSorted = sortedList.isSorted();
        var unsortedListIsUnsorted = unsortedList.isSorted();

        assertTrue(sortedListIsSorted);
        assertFalse(unsortedListIsUnsorted);
    }

    @IntensiveTest_IrakliBarikhashvili
    public void quickSort_shouldSortElements_whenElementsAreUnsorted() {
        Integer[] elements = {10,5,3,6,2,1,7};
        var unsortedList = new ArrayList_IrakliBarikhashvili<>(List.of(elements));
        Arrays.sort(elements);
        var correctlySortedList = new ArrayList_IrakliBarikhashvili<>(List.of(elements));

        unsortedList.quickSort(Integer::compare);

        assertEquals(unsortedList, correctlySortedList);
    }

    @IntensiveTest_IrakliBarikhashvili
    public void lists_shouldEquals_whenListsContainTheSameObjects() {
        var elements = List.of(1,2,3,4,5);
        var list1 = new LinkedList<>(elements);
        var list2 = new ArrayList<>(elements);
        assertEquals(list1, list2);
    }

    // Тест, который должен завалиться
    @IntensiveTest_IrakliBarikhashvili
    public void lists_shouldEquals_whenListsContainDifferentObjects() {
        var list1 = new LinkedList<>(List.of(5,4,3,2,1));
        var list2 = new ArrayList<>(List.of(1,2,3,4,5));

        assertEquals(list1, list2);
    }
}
