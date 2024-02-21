package pro.shamilabd.lesson2;

import org.junit.jupiter.api.Test;
import pro.shamilabd.lesson1.ArrayList_ShamilAbdullin;

public class IntensiveArrayListTest_ShamilAbdullin {

    @Test
    @IntensiveTest_ShamilAbdullin
    public void simpleTest() {
        ArrayList_ShamilAbdullin<Integer> list = new ArrayList_ShamilAbdullin<>();
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(3);
        list.add(5);

        list.quickSort();

        Assertion_ShamilAbdullin.assertTrue(list.isSorted());
        System.out.println("Simple test passed: " + list);
    }

    public static void main(String[] args) {
        TestRunner_ShamilAbdullin test = new TestRunner_ShamilAbdullin("pro.shamilabd");
        test.run();
    }
}
