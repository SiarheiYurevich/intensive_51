package pro.shamilabd;

import org.opentest4j.AssertionFailedError;

import java.util.List;

/**
 * Hello world!
 *
 */
public class Assertion_ShamilAbdullin
{
    public static <T> void assertEquals(List<T> expected, List<T> actual)
    {
        if (expected == actual) return;
        if (expected.size() != actual.size()) {
            throw new AssertionFailedError("Lists not equal");
        }

        for (int i = 0; i < expected.size(); i++) {
            T expectedElement = expected.get(i);
            T actualElement = actual.get(i);
            if (!expectedElement.equals(actualElement)) {
                throw new AssertionFailedError("Lists not equal");
            }
        }
    }
    public static <T> void assertNotEquals(List<T> expected, List<T> actual)
    {
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

    public static void assertTrue(boolean actual) {
        if (!actual) throw new AssertionFailedError("Actual is false");
    }

    public static void assertFalse(boolean actual) {
        if (actual) throw new AssertionFailedError("Actual is true");
    }

    public static void assertTrue(Boolean actual) {
        if (!actual) throw new AssertionFailedError("Actual is false");
    }

    public static void assertFalse(Boolean actual) {
        if (actual) throw new AssertionFailedError("Actual is true");
    }

    public static void assertNotNull(Object actual) {
        if (actual == null) throw new AssertionFailedError("Actual is null");
    }
}
