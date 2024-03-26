package task_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Тестовый класс, для проверки является ли массив отсортированным
 *
 * @autor Mikhail
 */
public class IntensiveArrayListTest_MikhailShchechin {

    public static void main(String[] args) {
        TestRunner_MikhailShchechin testRunnerMikhailShchechin = new TestRunner_MikhailShchechin();
        testRunnerMikhailShchechin.run("task_1", "task_2");
    }

    /**
     * Тестовый метод для проверки сортировки массива
     */
    @IntensiveTest_MikhailShchechin
    public static void test() {
        List<Integer> list = List.of(2,3,1,4);
        Assertions_MikhailShchechin.assertEquals(Arrays.asList(1,2,3,4), sortList(list));
    }

    /**
     * Реализация сортировки массива по не убыванию
     *
     * @param list - передаваемый массив для сортировки
     */
    private static List<Integer> sortList(List<Integer> list) {
        return list.stream().sorted(Comparator.comparingInt(Integer::intValue)).toList();
    }

}
