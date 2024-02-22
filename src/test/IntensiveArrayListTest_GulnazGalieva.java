package test;

import main.task1.ArrayList_GulnazGalieva;
import main.task1.IntensiveList;
import main.task2.Assertions_GulnazGalieva;
import main.task2.IntensiveTest_GulnazGalieva;
import main.task2.TestRunner_GulnazGalieva;

/**
 * Данный класс выполняет проверку тестового метода isSorted() класса ArrayList_GulnazGalieva.
 * @author Гульназ Галиева
 */

public class IntensiveArrayListTest_GulnazGalieva {

    public static void main(String[] args) {
        TestRunner_GulnazGalieva.run("test");
    }

    @IntensiveTest_GulnazGalieva
    public void test(){
        IntensiveList<Integer> list = new ArrayList_GulnazGalieva<>();
        list.add(5);
        list.add(5);
        list.add(57);
        list.add(1000);
        Assertions_GulnazGalieva.assertTrue(list.isSorted());
    }
    @IntensiveTest_GulnazGalieva
    public void test1(){
        IntensiveList<String> list = new ArrayList_GulnazGalieva<>();
        list.add("Анна");
        list.add("Зима");
        list.add("Юля");
        list.add("Корова");
        Assertions_GulnazGalieva.assertFalse(list.isSorted());
    }

}
