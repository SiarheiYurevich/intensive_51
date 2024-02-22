package task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Сласс для тестирования является ли массив отсортированным
 */
public class IntensiveArrayListTest_KirillKosykh {

    public static void main(String[] args) {
        TestRunner_KirillKosykh testRunner = new TestRunner_KirillKosykh();
        testRunner.run("task2", "task1");
    }

    /**
     * Тестовый метод для проверки сортировки массива
     */
    @IntensiveTest_KirillKosykh
    public static void test() {

        List<Integer> list = List.of(2,3,1,4);
        Assertions_KirillKosykh.assertEquals(Arrays.asList(1,2,3,4), sortList(list));
    }

    /**
     * Реализация сортировки массива по неубыванию
     *
     * @param list передаваеммый массив для сортировки
     */
    private static List<Integer> sortList(List<Integer> list) {
        return list.stream().sorted(Comparator.comparingInt(Integer::intValue)).toList();
    }
}
