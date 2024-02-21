package pro.shamilabd.lesson2;

import org.opentest4j.AssertionFailedError;

import java.util.List;

/**
 * Implementation version of Assertion from Shamil Abdullin
 */
public class Assertion_ShamilAbdullin {
    /**
     * Checking two List<T> for equality
     * @param expected expected value
     * @param actual actual value
     * @param <T> generic type for List
     * @throws AssertionFailedError Type of throwing exception
     */
    public static <T> void assertEquals(List<T> expected, List<T> actual) throws AssertionFailedError {
        if (expected == actual) {
            return;
        }
        if (expected.size() != actual.size()) {
            throw new AssertionFailedError("Lists not equal");
        }

        for (int i = 0; i < expected.size(); i++) { // Тут мы уже точно знаем что размеры одинаковые
            T expectedElement = expected.get(i);
            T actualElement = actual.get(i);
            if (!expectedElement.equals(actualElement)) {
                throw new AssertionFailedError("Lists not equal");
            }
        }
    }

    /**
     *
     * Checking two List<T> for not equality
     * @param expected expected value
     * @param actual actual value
     * @param <T> generic type for List
     * @throws AssertionFailedError Type of throwing exception
     */
    public static <T> void assertNotEquals(List<T> expected, List<T> actual) throws AssertionFailedError {
        if (expected == actual) {
            throw new AssertionFailedError("Lists equal");
        }
        if (expected.size() != actual.size()) {
            return;
        }

        boolean isEqual = true;
        for (int i = 0; i < expected.size(); i++) {
            T expectedElement = expected.get(i);
            T actualElement = actual.get(i);
            if (!expectedElement.equals(actualElement)) {
                isEqual = false;
                break;
            }
        }
        if (isEqual) {
            throw new AssertionFailedError("Lists equal");
        }
    }

    /**
     * Checking value that is true
     * @param actual Checking value
     * @throws AssertionFailedError Type of throwing exception
     */
    public static void assertTrue(boolean actual) throws AssertionFailedError {
        if (!actual) {
            throw new AssertionFailedError("Actual is false");
        }
    }

    /**
     * Checking value that is false
     * @param actual Checking value
     * @throws AssertionFailedError Type of throwing exception
     */
    public static void assertFalse(boolean actual) throws AssertionFailedError {
        if (actual) {
            throw new AssertionFailedError("Actual is true");
        }
    }

    /**
     * Checking value that is true
     * @param actual Checking value
     * @throws AssertionFailedError Type of throwing exception
     */
    public static void assertTrue(Boolean actual) throws AssertionFailedError {
        if (!actual) {
            throw new AssertionFailedError("Actual is false");
        }
    }

    /**
     * Checking value that is false
     * @param actual Checking value
     * @throws AssertionFailedError Type of throwing exception
     */
    public static void assertFalse(Boolean actual) throws AssertionFailedError {
        if (actual) {
            throw new AssertionFailedError("Actual is true");
        }
    }

    /**
     * Checking value that is not null
     * @param actual Checking value
     * @throws AssertionFailedError Type of throwing exception
     */
    public static void assertNotNull(Object actual) throws AssertionFailedError {
        if (actual == null) {
            throw new AssertionFailedError("Actual is null");
        }
    }
}
