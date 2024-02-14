package com.aston.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListEvgeniyKanareikinTest {
    private ArrayList_EvgeniyKanareikin<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList_EvgeniyKanareikin<>();
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    void testAdd() {
        list.add(1);
        assertEquals(1, list.get(0));
        list.add(2);
        assertEquals(2, list.get(1));
    }

    @Test
    void testAddAtIndex() {
        list.add(0, 1);
        assertEquals(1, list.get(0));
        list.add(1, 2);
        assertEquals(2, list.get(1));
        list.add(2, 3);
        assertEquals(3, list.get(2));
    }

    @Test
    void testGet() {
        list.add(1);
        assertEquals(1, list.get(0));
        list.add(2);
        assertEquals(2, list.get(1));
    }

    @Test
    void testSet() {
        list.add(1);
        list.set(0, 2);
        assertEquals(2, list.get(0));
        list.add(2);
        list.set(1, 3);
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.remove(0));
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.remove(0));
        assertEquals(3, list.get(0));
        assertEquals(3, list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testQuickSort() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.quickSort(Comparator.naturalOrder());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testIsSorted() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.isSorted());
        list.add(0);
        assertFalse(list.isSorted());
    }

    @Test
    void testSplit() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.split(3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }
}