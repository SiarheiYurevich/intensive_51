package com.igorpopovich.task2;

import java.util.List;

/**
 * Класс, содержащий утверждения для проверки тестовых условий.
 */

public class Assertions_IgorPopovich {

    /**
     * Проверяет, что два списка равны.
     *
     * @param expected ожидаемый список
     * @param actual   фактический список
     * @throws AssertionError если списки не равны
     */
    public static void assertEquals(List<?> expected, List<?> actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Assertion failed: Lists are not equal");
        }
    }

    /**
     * Проверяет, что условие истинно. Если условие ложно, выбрасывается исключение AssertionError.
     *
     * @param condition проверяемое условие
     * @throws AssertionError если условие ложно
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Assertion failed: expected true, but was false");
        }
    }

    /**
     * Проверяет, что условие ложно. Если условие истинно, выбрасывается исключение AssertionError.
     *
     * @param condition проверяемое условие
     * @throws AssertionError если условие истинно
     */
    public static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("Assertion failed: expected false, but was true");
        }
    }
}
