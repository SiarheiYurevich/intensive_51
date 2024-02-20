package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

class ArrayList_VictorMiheevGTest {

    private ArrayList_VictorMiheevG<Student> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList_VictorMiheevG<>();
    }

    @Test
    @DisplayName("test size of empty list")
    void ifEmptyList_size_thenZero() {
        Assertions.assertEquals(0, list.size());
    }

    @Test
    @DisplayName("test size of 3 values list")
    void ifNotEmptyList_size_thenArraySize() {
        list.add(new Student(1, "Student1"));
        list.add(new Student(2, "Student2"));
        list.add(new Student(3, "Student3"));
        Assertions.assertEquals(3, list.size());
    }

    @Test
    @DisplayName("test add value \"5\" to list")
    void ifAdd5_add_thenArray() {
        list.add(new Student(5, "Student5"));
        Object[] expectedArray = new Object[]{new Student(5, "Student5"), null};
        Assertions.assertArrayEquals(expectedArray, list.getArray());
    }

    @Test
    @DisplayName("test add value \"5\" with index \"6\" to list")
    void ifAdd5WithIndex6_add_thenArray() {
        list.add(2, new Student(2, "Student2"));
        Object[] expectedArray = new Object[]{
                null, null,
                new Student(2, "Student2"),
                null
        };
        Assertions.assertArrayEquals(expectedArray, list.getArray());
    }

    @Test
    @DisplayName("test set value \"5\" with index \"6\" to empty list")
    void ifSetValue5Index6_set_thenReturnNull() {
        Student student = list.set(6, new Student(2, "Student2"));
        Assertions.assertNull(student);
    }

    @Test
    @DisplayName("test set value \"5\" with index \"0\" to empty list")
    void ifSetValue5Index0_set_thenReturnPrevElement3() {
        list.add(new Student(2, "Student2"));
        Student i = list.set(0, new Student(2, "Student2"));
        Assertions.assertEquals(new Student(2, "Student2"), i);
    }

    @Test
    @DisplayName("test remove not null element with index \"6\" to list")
    void ifRemoveNotNullElementIndex6_remove_thenReturn5() {
        list.add(6, new Student(2, "Student2"));
        Student student = list.remove(6);
        Assertions.assertEquals(new Student(2, "Student2"), student);
    }

    @Test
    @DisplayName("test clear array with 3 elements result new Array[2]")
    void ifAdd3elements_clear_thenEmptyArray() {
        list.add(new Student(1, "Student1"));
        list.add(new Student(2, "Student2"));
        list.add(new Student(3, "Student3"));
        list.clear();
        Assertions.assertArrayEquals(new Integer[2], list.getArray());
    }

    @Test
    @DisplayName("test quick integer Sort")
    void ifUnsortedArray_quickSort_SortedArray() {
        list.add(new Student(2, "Student2"));
        list.add(new Student(5, "Student5"));
        list.add(6, new Student(6, "Student6"));
        list.add(new Student(3, "Student3"));
        list.add(new Student(1, "Student1"));
        list.add(new Student(4, "Student4"));
        list.quickSort(Comparator.comparingInt(Student::getId));
        Object[] expectedArray = new Object[]{
                new Student(1, "Student1"),
                new Student(2, "Student2"),
                new Student(3, "Student3"),
                new Student(4, "Student4"),
                new Student(5, "Student5"),
                null, new Student(6, "Student6"), null
        };
        Assertions.assertArrayEquals(expectedArray, list.getArray());
    }

    @Test
    @DisplayName("test sorted array")
    void ifSortedList_isSorted_true() {
        list.add(new Student(2, "Student2"));
        list.add(new Student(5, "Student5"));
        list.add(6, new Student(6, "Student6"));
        list.add(new Student(3, "Student3"));
        list.add(new Student(1, "Student1"));
        list.add(new Student(4, "Student4"));
        list.quickSort(Comparator.comparingInt(Student::getId));
        Object[] array = list.getArray();
        System.out.println(Arrays.toString(array));
        Assertions.assertTrue(list.isSorted());
    }

    @Test
    @DisplayName("test unsorted array")
    void ifUnsortedList_isSorted_false() {
        list.add(new Student(1, "Student1"));
        list.add(new Student(2, "Student2"));
        list.add(new Student(7, "Student7"));
        list.add(new Student(3, "Student3"));
        Assertions.assertFalse(list.isSorted());
    }

    @Test
    @DisplayName("test split array with 5 elements to size=3 ")
    void ifSize3_split_arrayWith3Elements() {
        list.add(new Student(1, "Student1"));
        list.add(new Student(2, "Student2"));
        list.add(new Student(3, "Student3"));
        list.add(new Student(4, "Student4"));
        list.add(7, new Student(7, "Student7"));
        list.split(3);
        Object[] expectedArray = new Object[]{
                new Student(1, "Student1"),
                new Student(2, "Student2"),
                new Student(3, "Student3")
        };
        Assertions.assertArrayEquals(expectedArray, list.getArray());
    }

}