package com.aston.tasks.task_1;

import com.aston.tasks.task_1.Model.ComparatorHuman;
import com.aston.tasks.task_1.Model.Human;

/**
 * Данный класс MainTestArrayList тестирует работу методов коллекции ArrayListIgnatevAndrey<E>
 */

public class MainTestArrayList {

    public static void main(String[] args) {
        ArrayListIgnatevAndrey<Human> humanArrayListIgnatevAndrey = new ArrayListIgnatevAndrey<>();

        humanArrayListIgnatevAndrey.add(new Human(10, "Bob_10"));
        humanArrayListIgnatevAndrey.add(new Human(11, "Bob_11"));
        humanArrayListIgnatevAndrey.add(new Human(111, "Bob_111"));
        humanArrayListIgnatevAndrey.add(new Human(101, "Bob_101"));
        humanArrayListIgnatevAndrey.add(new Human(12, "Bob_12"));
        humanArrayListIgnatevAndrey.add(new Human(11, "Bob_11"));
        humanArrayListIgnatevAndrey.add(new Human(100, "Bob_100"));

        humanArrayListIgnatevAndrey.quickSort(new ComparatorHuman());

//        for (int i = 0; i < humanArrayListIgnatevAndrey.size(); i++) {
//            System.out.println(humanArrayListIgnatevAndrey.get(i));
//        }

        humanArrayListIgnatevAndrey.split(3);

        for (int i = 0; i < humanArrayListIgnatevAndrey.size(); i++) {
            System.out.println(humanArrayListIgnatevAndrey.get(i));
        }

    }
}
