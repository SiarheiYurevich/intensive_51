package com.igorpopovich.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class ArrayList_IgorPopovichTest {
    private ArrayList_IgorPopovich<Integer> testList;

    @BeforeEach
    void setUp() {
        testList = new ArrayList_IgorPopovich<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
    }


    @Test
    void testSize_ShouldReturnCorrectSize_WhenElementsAreAdded() {
        int expectedSize = 5;

        int actualSize = testList.size();

        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    void testSize_ShouldReturnZero_WhenNoElementsAreAdded() {
        testList.clear();
        int expectedSize = 0;

        int actualSize = testList.size();

        Assertions.assertEquals(expectedSize, actualSize);
    }


    @Test
    void testAddElement_ShouldIncreaseSizeByOne_WhenElementIsAdded() {
        int initialSize = testList.size();
        int expectedSize = initialSize + 1;
        int element = 6;

        testList.add(element);
        int actualSize = testList.size();

        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    void testAddElement_ShouldAddElementToTheEndOfTheList() {
        int element6 = 7;
        int element7 = 8;

        testList.add(element6);
        testList.add(element7);

        Assertions.assertEquals(element6, testList.get(5));
        Assertions.assertEquals(element7, testList.get(6));
    }


    @Test
    void testAddIndexAndElement_ShouldIncreaseSizeByOne_WhenElementByIndexIsAdded() {
        int initialSize = testList.size();
        int expectedSize = initialSize + 1;
        int index = 5;
        int element = 10;

        testList.add(index, element);
        int actualSize = testList.size();

        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    void testAddIndexAndElement_ShouldAddElementAtSpecifiedIndex() {
        int index = 1;
        int element = 15;

        testList.add(index, element);

        Assertions.assertEquals(1, testList.get(0));
        Assertions.assertEquals(element, testList.get(index));
        Assertions.assertEquals(2, testList.get(index + 1));
    }

    @Test
    void testAddIndexAndElement_ShouldThrowException_WhenInvalidIndexIsSpecified() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.add(7, 20);
        });
    }


    @Test
    void testGet_ShouldReturnElementAtSpecifiedIndex() {
        Assertions.assertEquals(1, testList.get(0));
        Assertions.assertEquals(2, testList.get(1));
        Assertions.assertEquals(3, testList.get(2));
    }

    @Test
    void testGet_ShouldThrowException_WhenInvalidIndexIsSpecified() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.get(6);
        });
    }


    @Test
    void testSet_ShouldUpdateElementAtSpecifiedIndex() {
        testList.set(0, 25);
        testList.set(1, 100);

        Assertions.assertEquals(25, testList.get(0));
        Assertions.assertEquals(100, testList.get(1));
    }

    @Test
    void testSet_ShouldThrowException_WhenInvalidIndexIsSpecified() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.set(8, 20);
        });
    }


    @Test
    void testRemove_ShouldRemoveElementAtSpecifiedIndex() {
        int index = 0;
        int expectedRemovedElement = 1;

        int actualRemovedElement = testList.remove(index);

        Assertions.assertEquals(expectedRemovedElement, actualRemovedElement);
        Assertions.assertEquals(4, testList.size());
        Assertions.assertEquals(2, testList.get(0));
        Assertions.assertEquals(3, testList.get(1));
    }

    @Test
    void testRemove_ShouldThrowException_WhenInvalidIndexIsSpecified() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.remove(7);
        });
    }


    @Test
    void testClear_ShouldRemoveAllElementsAndResetSize() {
        Assertions.assertEquals(5, testList.size());

        testList.clear();

        Assertions.assertEquals(0, testList.size());
    }


    @Test
    void testQuickSort_ShouldSortElementsInAscendingOrder() {
        testList.set(0, 16);
        testList.set(1, 5);
        testList.set(2, 111);
        testList.set(3, 34);
        testList.set(4, 15);
        Comparator<Integer> comparator = Comparator.naturalOrder();

        testList.quickSort(comparator);

        Assertions.assertEquals(5, testList.get(0));
        Assertions.assertEquals(15, testList.get(1));
        Assertions.assertEquals(16, testList.get(2));
        Assertions.assertEquals(34, testList.get(3));
        Assertions.assertEquals(111, testList.get(4));
    }

    @Test
    void testQuickSort_ShouldSortElementsInDescendingOrder() {
        testList.set(0, 16);
        testList.set(1, 5);
        testList.set(2, 111);
        testList.set(3, 34);
        testList.set(4, 15);
        Comparator<Integer> comparator = Comparator.reverseOrder();

        testList.quickSort(comparator);

        Assertions.assertEquals(111, testList.get(0));
        Assertions.assertEquals(34, testList.get(1));
        Assertions.assertEquals(16, testList.get(2));
        Assertions.assertEquals(15, testList.get(3));
        Assertions.assertEquals(5, testList.get(4));

    }


    @Test
    void isSorted_ShouldReturnTrue_WhenListIsSorted() {
        boolean isSorted = testList.isSorted();

        Assertions.assertTrue(isSorted);
    }

    @Test
    void isSorted_ShouldReturnFalse_WhenListIsNotSorted() {
        ArrayList_IgorPopovich<Integer> unsortedList = new ArrayList_IgorPopovich<>();
        unsortedList.add(17);
        unsortedList.add(1);
        unsortedList.add(34);

        boolean isSorted = unsortedList.isSorted();

        Assertions.assertFalse(isSorted);
    }


    @Test
    void testSplit_ShouldReduceSizeToSpecifiedValue() {
        testList.split(3);

        Assertions.assertEquals(3, testList.size());
    }

    @Test
    void testSplit_ShouldThrowException_WhenInvalidSizeIsSpecified() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testList.split(6);
        });
    }
}
