package task2;

import java.util.List;

/**
 * Этот класс предоставляет assertion методы для выполнения тестов
 */
public class Assertions_KirillKosykh {

    public static <T> void assertEquals(List<T> expected, List<T> actual) {

        /**
         * Утверждает что два List равны друг другу.
         *
         * @param expected ожидаемый результат
         * @param actual   получившийся результат
         * @param <T>      тип элементов в lists
         * @throws AssertionError если результаты не совпали
         */
        if (!expected.equals(actual)) {
            throw new AssertionError(String.format("Expected: %s, Actual: %s", expected, actual));
        }
    }
}
