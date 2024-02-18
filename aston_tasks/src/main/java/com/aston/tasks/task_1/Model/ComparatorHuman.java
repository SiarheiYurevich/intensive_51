package com.aston.tasks.task_1.Model;

import java.util.Comparator;

public class ComparatorHuman implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        if (o1.getAge() - o2.getAge() < 0){
            return -1;
        }

        if (o1.getAge() - o2.getAge() > 0){
            return 1;
        }else
            return 0;
    }
}
