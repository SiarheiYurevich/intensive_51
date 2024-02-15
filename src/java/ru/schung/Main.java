package ru.schung;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IntensiveList<Integer> list = new ArrayList_IvanovMikhail<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(14);
        list.add(11);

        list.quickSort(Comparator.comparing(Integer::intValue));
        System.out.println(list.isSorted());
        System.out.println(list.toString());

        list.remove(3);
        System.out.println(list.toString());

        list.split(8);
        System.out.println(list.toString());

        list.clear();
        System.out.println(list.toString());



    }
}
