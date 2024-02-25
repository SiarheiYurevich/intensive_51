package task_2;

import junit.framework.AssertionFailedError;
import task_1.IntensiveList;

import java.util.List;

/**
 * Класс для проверки равенства различных типов данных с помощью статических методов.
 */
public class Assertions_MikhailShchechin {

    /**
     * Проверяет значение на истинность.
     *
     * @param actual фактическое значение.
     * @throws AssertionFailedError если фактическое значение false
     */
    public static void assertTrue(boolean actual) throws AssertionFailedError {
        assertEquals(actual, true);
    }

    /**
     * Проверяет значение на ложность.
     *
     * @param actual фактическое значение.
     *
     * @throws AssertionFailedError если фактическое значение true
     */
    public static void assertFalse(boolean actual) throws AssertionFailedError {
        assertEquals(actual, false);
    }

    /**
     * Проверяет равенство двух булевых значений.
     *
     * @param actual Фактическое значение
     * @param expected Ожидаемое значение
     *
     * @throws AssertionFailedError если значения не равны
     */
    public static void assertEquals(boolean actual, boolean expected) throws AssertionFailedError {
        if (actual != expected) {
            throw new AssertionFailedError("Значения не равны");
        }
    }

    /**
     * Проверяет равенство двух объектов по ссылкам.
     *
     * @param actual Фактический объект.
     * @param expected Ожидаемый объект.
     * @return true - объекты равны или false - объекты не равны
     * @throws AssertionFailedError если объекты не равны.
     */
    private static boolean isObjectsEquals(Object actual, Object expected) throws AssertionFailedError {
        if (actual == expected) return true;
        if (actual == null || expected == null) {
            throw new AssertionFailedError("Объекты не равны");
        }
        return false;
    }

    /**
     * Проверяет равенство двух списков.
     *
     * @param actual Фактический список.
     * @param expected Ожидаемый список.
     *
     * @throws AssertionError если списки не содержат одинаковые элементы
     */
    public static <T> void assertEquals(List<? extends T> actual, List<? extends T> expected) {
        if (isObjectsEquals(actual, expected)) return;
        if (!expected.equals(actual)) {
            throw new AssertionFailedError("Объекты не равны");
        }
    }
}
