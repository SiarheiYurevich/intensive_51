package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayList_VictorMiheevTest {

    private ArrayList_VictorMiheev list;

    @BeforeEach
    void setUp() {
        list = new ArrayList_VictorMiheev();
    }

    @Test
    @DisplayName("test size of empty list")
    void ifEmptyList_size_thenZero() {
        Assertions.assertEquals(0, list.size());
    }

    @Test
    @DisplayName("test size of 3 values list")
    void ifNotEmptyList_size_thenArraySize() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(3, list.size());
    }

    @Test
    @DisplayName("test add value \"5\" to list")
    void ifAdd5_add_thenArray() {
        list.add(5);
        Assertions.assertArrayEquals(new Integer[]{5, null}, list.getArray());
    }

    @Test
    @DisplayName("test add value \"5\" with index \"6\" to list")
    void ifAdd5WithIndex6_add_thenArray() {
        list.add(6, 5);
        Assertions.assertEquals(5, list.get(6));
    }

    @Test
    @DisplayName("test set value \"5\" with index \"6\" to empty list")
    void ifSetValue5Index6_set_thenReturnNull() {
        Integer i = list.set(6, 5);
        Assertions.assertNull(i);
    }

    @Test
    @DisplayName("test set value \"5\" with index \"0\" to empty list")
    void ifSetValue5Index0_set_thenReturnPrevElement3() {
        list.add(3);
        Integer i = list.set(0, 5);
        Assertions.assertEquals(3, i);
    }

    @Test
    @DisplayName("test remove not null element with index \"6\" to list")
    void ifRemoveNotNullElementIndex6_remove_thenReturn5() {
        list.add(6, 5);
        Integer i = list.remove(6);
        Assertions.assertEquals(5, i);
    }

    @Test
    @DisplayName("test clear array with 3 elements result new Array[2]")
    void ifAdd3elements_clear_thenEmptyArray() {
        list.add(1);
        list.add(1);
        list.add(1);
        list.clear();
        Assertions.assertArrayEquals(new Integer[2], list.getArray());
    }

    @Test
    @DisplayName("test quick integer Sort")
    void ifUnsortedArray_quickSort_SortedArray() {
        list.add(20);
        list.add(50);
        list.add(30);
        list.add(10);
        list.add(40);
        list.quickSort(Integer::compareTo);
        Integer[] expectedArray = new Integer[]{10, 20, 30, 40, 50, null, null, null};
        Assertions.assertArrayEquals(expectedArray, list.getArray());
    }

    @Test
    @DisplayName("test sorted array")
    void ifSortedList_isSorted_true() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(7, 7);
        Assertions.assertTrue(list.isSorted());
    }

    @Test
    @DisplayName("test unsorted array")
    void ifUnsortedList_isSorted_true() {
        list.add(1);
        list.add(2);
        list.add(7);
        list.add(3);
        Assertions.assertFalse(list.isSorted());
    }

    @Test
    @DisplayName("test split array with 5 elements to size=3 ")
    void ifSize3_split_arrayWith3Elements() {
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(7, 1);
        list.split(3);
        Assertions.assertArrayEquals(new Integer[]{1, 1, 1}, list.getArray());
    }
}