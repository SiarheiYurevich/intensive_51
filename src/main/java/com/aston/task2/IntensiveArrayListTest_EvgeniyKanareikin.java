package com.aston.task2;

import com.aston.task1.ArrayList_EvgeniyKanareikin;

public class IntensiveArrayListTest_EvgeniyKanareikin {
    public static void main(String[] args) {
        TestRunner_EvgeniyKanareikin testRunner = new TestRunner_EvgeniyKanareikin("com.aston");
        testRunner.run();
    }

    @IntensiveTest_EvgeniyKanareikin
    public void test() {
        ArrayList_EvgeniyKanareikin<Integer> list = new ArrayList_EvgeniyKanareikin<>();
        list.add(1);
        list.add(8);
        list.add(5);
        list.add(2);
        list.add(3);
        list.quickSort(Integer::compareTo);
        Assertions_EvgeniyKanareikin.assertTrue(list.isSorted());
    }
}