package org.example;

import org.example.ArrayList_IvanPrikhodko;
import org.example.Assertions_IvanPrikhodko;
import org.example.IntensiveTest_IvanPrikhodko;

import java.util.Comparator;

/**
 * Класс содержащий тесты для {@code IntensiveArrayList}.
 * @author Иван Приходько
 */
public class IntensiveArrayListTest_IvanPrikhodko {

    /**
     * Проверка сортировки коллекции.
     */
    @IntensiveTest_IvanPrikhodko
    public void TestSort() {
        ArrayList_IvanPrikhodko<Integer> list1 = new ArrayList_IvanPrikhodko<>();
        list1.add(1);
        list1.add(5);
        list1.add(6);
        list1.add(3);
        ArrayList_IvanPrikhodko<Integer> list2 = new ArrayList_IvanPrikhodko<>();
        list2.add(1);
        list2.add(3);
        list2.add(5);
        list2.add(6);

        list1.quickSort(Comparator.comparing(Integer::intValue));
        Assertions_IvanPrikhodko.assertEquals(list1, list2);
    }
}
