package com.igorpopovich.task2;

import com.igorpopovich.task1.ArrayList_IgorPopovich;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для проверки отсортированности массива.
 */

public class IntensiveArrayListTest_IgorPopovich {

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки
     */

    public static void main(String[] args) {
        TestRunner_IgorPopovich testRunner = new TestRunner_IgorPopovich();
        testRunner.run("com.igorpopovich.task2.IntensiveArrayListTest_IgorPopovich");
    }


    @IntensiveTest_IgorPopovich
    public static void isSorted_ShouldReturnTrue_WhenListIsSorted() {
        ArrayList_IgorPopovich<Integer> testList = new ArrayList_IgorPopovich<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);

        boolean isSorted = testList.isSorted();

        Assertions_IgorPopovich.assertTrue(isSorted);
    }

    @IntensiveTest_IgorPopovich
    public static void isSorted_ShouldReturnFalse_WhenListIsNotSorted() {
        ArrayList_IgorPopovich<Integer> unsortedList = new ArrayList_IgorPopovich<>();
        unsortedList.add(17);
        unsortedList.add(1);
        unsortedList.add(34);

        boolean isSorted = unsortedList.isSorted();

        Assertions_IgorPopovich.assertFalse(isSorted);
    }

    @IntensiveTest_IgorPopovich
    public static void listsAreEqual_ShouldReturnTrueWhenListsAreEqual(){
        List<Integer> list1 = List.of(1,2,3,4);
        List<Integer> list2 = List.of(1,2,3,4);

        Assertions_IgorPopovich.assertEquals(list1, list2);

    }
}
