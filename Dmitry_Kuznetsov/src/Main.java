import collection.ArrayList_DmitryKuznetsov;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList_DmitryKuznetsov<String> arrayListDmitryKuznetsov = new ArrayList_DmitryKuznetsov<>();
        arrayListDmitryKuznetsov.add("100");
        arrayListDmitryKuznetsov.add("200");
        System.out.println("Проверка метода Size: " + arrayListDmitryKuznetsov.size());
        System.out.println("Должно быть: 100 200.!   " + arrayListDmitryKuznetsov.get(0) + " " + arrayListDmitryKuznetsov.get(1));
        arrayListDmitryKuznetsov.add(1, "300");
        System.out.println("Должно быть: 100 300 200.!   " + arrayListDmitryKuznetsov.get(0) + " " + arrayListDmitryKuznetsov.get(1)  + " " + arrayListDmitryKuznetsov.get(2));
        System.out.println("Проверка метода Size: " + arrayListDmitryKuznetsov.size());
        arrayListDmitryKuznetsov.set(2, "400");
        System.out.println("Должно быть: 100 300 400.!   " + arrayListDmitryKuznetsov.get(0) + " " + arrayListDmitryKuznetsov.get(1)  + " " + arrayListDmitryKuznetsov.get(2));
        arrayListDmitryKuznetsov.remove(0);
        arrayListDmitryKuznetsov.remove(1);
        System.out.println("Должно быть: 100 300 400.!   " + arrayListDmitryKuznetsov.get(0));
        System.out.println("Проверка метода Size: " + arrayListDmitryKuznetsov.size());
        arrayListDmitryKuznetsov.clear();


        System.out.println(" ");

        ArrayList<String> arrayList =  new ArrayList<>();
        arrayList.add("100");
        arrayList.add("200");
        System.out.println("Проверка метода Size: " + arrayList.size());
        System.out.println("Должно быть: 100 200.!   " + arrayList.get(0) + " " + arrayList.get(1));
        arrayList.add(1, "300");
        System.out.println("Должно быть: 100 300 200.!   " + arrayList.get(0) + " " + arrayList.get(1) + " " + arrayList.get(2));
        System.out.println("Проверка метода Size: " + arrayList.size());
        arrayList.set(2, "400");
        System.out.println("Должно быть: 100 300 400.!   " + arrayList.get(0) + " " + arrayList.get(1) + " " + arrayList.get(2));
        arrayList.remove(0);
        arrayList.remove(1);
        System.out.println("Должно быть: 100 300 400.!   " + arrayList.get(0));
        System.out.println("Проверка метода Size: " + arrayList.size());
        arrayList.clear();

    }
}