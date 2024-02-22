package com.aston.task2;

import java.util.List;

/**
 * Implementation of Assertions.
 *
 * @author Evgeniy Kanareikin
 */
public class Assertions_EvgeniyKanareikin {
    /**
     * Asserts that two lists are equal.
     *
     * @param expected the expected list
     * @param actual   the actual list
     */
    public static void assertEquals(List<?> expected, List<?> actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", but was: " + actual);
        }
    }

    /**
     * Asserts that two lists are not equal.
     *
     * @param expected the expected list
     * @param actual   the actual list
     */
    public static void assertNotEquals(List<?> expected, List<?> actual) {
        if (expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", equal Actual: " + actual);
        }
    }

    /**
     * Asserts that two objects are equal.
     *
     * @param expected the expected object
     * @param actual   the actual object
     */
    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", but was: " + actual);
        }
    }

    /**
     * Asserts that two objects are not equal.
     *
     * @param expected the expected object
     * @param actual   the actual object
     */
    public static void assertNotEquals(Object expected, Object actual) {
        if (expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", equal Actual: " + actual);
        }
    }

    /**
     * Asserts that a condition is true.
     *
     * @param condition the condition to assert
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Condition is false");
        }
    }

    /**
     * Asserts that a condition is false.
     *
     * @param condition the condition to assert
     */
    public static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("Condition is true");
        }
    }

    /**
     * Asserts that an object is null.
     *
     * @param object the object to assert
     */
    public static void assertNull(Object object) {
        if (object != null) {
            throw new AssertionError("Object is not null");
        }
    }

    /**
     * Asserts that an object is not null.
     *
     * @param object the object to assert
     */
    public static void assertNotNull(Object object) {
        if (object == null) {
            throw new AssertionError("Object is null");
        }
    }
}