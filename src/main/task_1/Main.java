package task_1;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        ArrayList_MikhailShchechin arrayList = new ArrayList_MikhailShchechin<>();

        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }

        System.out.println(arrayList);

        System.out.println(arrayList.isSorted());

        arrayList.remove(5);

        arrayList.set(2, 11);

        System.out.println(arrayList);

        arrayList.add(1, 12);

        arrayList.add(34);

        System.out.println(arrayList);

        

    }
}
