package task_2;

import task_2.collections.IntensiveList;

import java.util.List;

/**
 * Класс предоставляет вспомогательные методы для проверки равенства различных типов
 * данных с помощью статических методов.
 */
public class Assertions_IrakliBarikhashvili {
    /**
     * Проверяет значение на истинность.
     *
     * @param actual фактическое значение.
     * @throws AssertionError если фактическое значение ложно (expected == false)
     */
    public static void assertTrue(boolean actual) {
        assertEquals(actual, true);
    }

    /**
     * Проверяет значение на ложность.
     *
     * @param actual фактическое значение.
     *
     * @throws AssertionError если фактическое значение истинно (expected == true)
     */
    public static void assertFalse(boolean actual) {
        assertEquals(actual, false);
    }

    /**
     * Проверяет равенство двух булевых значений.
     *
     * @param actual Фактическое значение
     * @param expected Ожидаемое значение
     *
     * @throws AssertionError если значения не равны (actual != expected)
     */
    public static void assertEquals(boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(generateMessage(actual, expected));
        }
    }

    /**
     * Генерирует сообщение об ошибке с указанием фактического и ожидаемого значений.
     *
     * @param actual Фактическое значение.
     * @param expected Ожидаемое значение.
     *
     * @return текст сообщения об ошибке.
     */
    private static String generateMessage(Object actual, Object expected) {
        return String.format("""
                        %nФактическое\t: %s
                        Ожидаемое\t: %s
                        """,
                actual,
                expected);
    }

    /**
     * Проверяет равенство двух списков типа {@link IntensiveList}.
     *
     * @param actual Фактический список.
     * @param expected Ожидаемый список.
     *
     * @throws AssertionError если списки не содержат одинаковые элементы
     */
    public static <T> void assertEquals(
            IntensiveList<? extends T> actual,
            IntensiveList<? extends T> expected) {
        if (isObjectsEquals(actual, expected)) return;
        if (actual.size() != expected.size()) {
            throw new AssertionError(generateMessage(actual, expected));
        }

        for (int i = 0; i < actual.size(); i++) {
            if (!actual.get(i).equals(expected.get(i))) {
                throw new AssertionError(generateMessage(actual, expected));
            }
        }
    }

    /**
     * Проверяет равенство двух объектов по ссылкам.
     *
     * @param actual Фактический объект.
     * @param expected Ожидаемый объект.
     * @return true - объекты равны по ссылкам, false - объекты не равны
     * @throws AssertionError если объекты не равны.
     */
    private static boolean isObjectsEquals(Object actual, Object expected) {
        if (actual == expected) return true;
        if (actual == null || expected == null) {
            throw new AssertionError(generateMessage(actual, expected));
        }
        return false;
    }

    /**
     * Проверяет равенство двух списков типа {@link List}.
     *
     * @param actual Фактический список.
     * @param expected Ожидаемый список.
     *
     * @throws AssertionError если списки не содержат одинаковые элементы
     */
    public static <T> void assertEquals(
            List<? extends T> actual,
            List<? extends T> expected) {
        if (isObjectsEquals(actual, expected)) return;
        if (!expected.equals(actual)) {
            throw new AssertionError(generateMessage(actual, expected));
        }
    }
}
