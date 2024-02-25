package test;

import ru.schung.IntensiveList;

public class Assertions_IvanovMikhail {
    /**
     * Метод, тестирущий отсортирован ли кастомный лист
     * @param list Тестируемый кастомный лист
     * @param <E>
     */
    public static <E extends Comparable<? super E>> void assertListIsSorted(IntensiveList<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                throw new AssertionError("List isn't sorted");
            }
        }
        System.out.println("List is sorted");
    }

    /**
     * Метод сравнения двух кастомных листов
     * @param expected ожидаемый
     * @param actual тестируемый
     */
    public static void assertEquals(IntensiveList<?> expected, IntensiveList<?> actual) {
        if (expected == null || actual == null || expected.size() != actual.size() || !comparingLists(expected, actual)) {
            throw new AssertionError("Lists are not equal");
        }
        System.out.println("Lists are equal");


    }

    /**
     * Вспомогательный метод глубокого сравнения листов
     * @param expected ожидаемый
     * @param actual текущий
     * @return равны/неравны
     */
    static boolean comparingLists(IntensiveList<?> expected, IntensiveList<?> actual) {
        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i) != actual.get(i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Метод для проверки размера листа
     * @param size предполагаемый размер
     * @param list тестируемый кастомный лист
     */
    public static void assertSize(int size, IntensiveList<?> list) {
        if (list.size() != size) {
            throw new AssertionError("Wrong value of size");
        }
        System.out.println("Size values are same and equal " + size);
    }

    public static <E> void assertSet(int position, E element,  IntensiveList<E> list) {
        if (list.get(position) != element ) {
            throw new AssertionError("Element " + element + " in wrong position " + position);
        }

        System.out.println("Element " + element + " in proper position " + position);
    }
}
