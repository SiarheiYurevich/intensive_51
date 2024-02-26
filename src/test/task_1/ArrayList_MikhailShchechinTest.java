package task_1;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Comparator;

public class ArrayList_MikhailShchechinTest extends TestCase {
    @Test
    public void testSize() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        assert list.size() == 0;

        list.add(1);
        list.add(2);
        list.add(3);
        assert list.size() == 3;

        list.remove(0);
        assert list.size() == 2;
    }

    @Test
    public void testAdd() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assert list.size() == 3;
        assert list.get(0) == 1;
        assert list.get(1) == 2;
        assert list.get(2) == 3;
    }

    @Test
    public void testAddForIndex() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(2, 154);

        assert list.size() == 4;
        assert list.get(2) == 154;
    }

    @Test
    public void testGet() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assert list.get(0) == 10;
        assert list.get(1) == 20;
        assert list.get(2) == 30;
    }

    @Test
    public void testSet() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(10);
        list.add(20);
        list.add(30);

        list.set(2, 45);
        assert list.get(2) == 45;
    }

    @Test
    public void testRemove() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(1);
        assert list.size() == 2;
    }

    @Test
    public void testClear() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(10);
        list.add(20);
        list.add(30);

        list.clear();
    }

    @Test
    public void testQuickSort() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(12);
        list.add(5);
        list.add(8);

        list.quickSort(Comparator.naturalOrder());
        assert list.get(0) == 5;
        assert list.get(1) == 8;
        assert list.get(2) == 12;
    }

    @Test
    public void testIsSorted() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(10);
        list.add(20);
        list.add(30);

        ArrayList_MikhailShchechin<Integer> list2 = new ArrayList_MikhailShchechin<>();
        list2.add(12);
        list2.add(5);
        list2.add(8);

        assert list.isSorted();
        assert !list2.isSorted();
    }

    @Test
    public void testSplit() {
        ArrayList_MikhailShchechin<Integer> list = new ArrayList_MikhailShchechin<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(10);
        list.add(20);
        list.add(30);

        list.split(4);
        assert list.size() == 4;
        assert list.get(0) == 1;
        assert list.get(1) == 2;
        assert list.get(2) == 3;
        assert list.get(3) == 10;
    }
}