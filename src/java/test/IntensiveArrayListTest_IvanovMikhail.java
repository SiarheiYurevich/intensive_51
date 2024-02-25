package test;

import ru.schung.ArrayList_IvanovMikhail;

import java.util.Comparator;

public class IntensiveArrayListTest_IvanovMikhail {

    //Проверка равенства двух листов
    @IntensiveTest_IvanovMikhail
    public void testListAreEquals() {
        ArrayList_IvanovMikhail<Integer> firstList = new ArrayList_IvanovMikhail<>();
        ArrayList_IvanovMikhail<Integer> secondList = new ArrayList_IvanovMikhail<>();
        firstList.add(1);
        firstList.add(2);
        firstList.add(3);

        secondList.add(1);
        secondList.add(2);
        secondList.add(3);

        Assertions_IvanovMikhail.assertEquals(firstList, secondList);
    }

    //Проверка отсортированности листа
    @IntensiveTest_IvanovMikhail
    public void testListIsSorted() {
        ArrayList_IvanovMikhail<Integer> list = new ArrayList_IvanovMikhail<>();
        list.add(6);
        list.add(3);
        list.add(7);
        list.add(9);
        list.quickSort(Comparator.naturalOrder());

        Assertions_IvanovMikhail.assertListIsSorted(list);

    }

    //Проверка размера листа
    @IntensiveTest_IvanovMikhail
    public void testSize() {
        ArrayList_IvanovMikhail<String> list = new ArrayList_IvanovMikhail<>();

       list.add("First");
       Assertions_IvanovMikhail.assertSize(1, list);

       list.add("Second");
       Assertions_IvanovMikhail.assertSize(2, list);

       list.add("Third");
       Assertions_IvanovMikhail.assertSize(3, list);

       list.remove(2);
       Assertions_IvanovMikhail.assertSize(2, list);

       list.clear();
       Assertions_IvanovMikhail.assertSize(0, list);

    }

    //Проверка методов вставки элементов
    @IntensiveTest_IvanovMikhail
    public void testAdd() {
        ArrayList_IvanovMikhail<Double> list = new ArrayList_IvanovMikhail<>();
        Double testDoubleValue = 3.3;
        Double secondTestDoubleValue = 4.4;
        list.add(2.2);
        list.add(testDoubleValue);
        list.add(1.1);

        list.set(2, secondTestDoubleValue);

        Assertions_IvanovMikhail.assertSet(1, testDoubleValue, list);
        Assertions_IvanovMikhail.assertSet(2, secondTestDoubleValue, list);

    }
}
