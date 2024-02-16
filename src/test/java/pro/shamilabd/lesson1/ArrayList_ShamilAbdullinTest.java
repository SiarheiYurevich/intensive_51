package pro.shamilabd.lesson1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ArrayList_ShamilAbdullinTest {

    private ArrayList_ShamilAbdullin<String> list;

    @BeforeEach
    public void init() {
        list = new ArrayList_ShamilAbdullin<>();
        fillTestList();
    }

    private void fillTestList() {
        list.add("Shamil");
        list.add("Dima");
        list.add("Zoom");
        list.add("Katya");
        list.add("Sea");
        list.add("house");
        list.add("mouse");
        list.add("VeryLongWordInThisList");
        list.add("");
        list.add(null);
        list.add("end");
    }

    @AfterEach
    public void destroy() {
        list.clear();
        list = null;
    }

    @Test
    public void sizeTest() {
        int size = list.size();
        Assertions.assertEquals(10, size);

        list.add("new value");
        size = list.size();
        Assertions.assertEquals(11, size);

        list.clear();
        size = list.size();
        Assertions.assertEquals(0, size);
    }

    @Test
    public void capacityTest() {
        int capacity = list.getCapacity();
        Assertions.assertEquals(16, capacity);

        list.add("new value");
        list.add("new value");
        list.add("new value");
        list.add("new value");
        list.add("new value");
        list.add("new value");
        list.add("new value"); // перевалили за емкость, и она должна была увеличиться вдвое
        capacity = list.getCapacity();
        Assertions.assertEquals(32, capacity);

        list.clear(); // должен сбросить к дефолтным 8
        capacity = list.getCapacity();
        Assertions.assertEquals(ArrayList_ShamilAbdullin.DEFAULT_CAPACITY, capacity);
    }

    @Test
    public void addTest() {
        String two = "two";
        list.clear();
        int size = list.size();
        list.add("one");
        list.add("two");
        int newSize = list.size();
        String twoFromList = list.get(1);

        Assertions.assertEquals(size + 2, newSize);
        Assertions.assertEquals(two, twoFromList);
    }

    @Test
    public void addToIndexTest() {
        String two = "other two";
        list.clear();
        int size = list.size();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add(1, two);
        int newSize = list.size();
        String twoFromList = list.get(1);

        Assertions.assertEquals(size + 4, newSize);
        Assertions.assertEquals(two, twoFromList);
    }

    @Test
    public void getTest() {
        String two = "two";
        list.clear();
        list.add("one");
        list.add(two);
        list.add("three");
        String twoFromList = list.get(1);

        Assertions.assertEquals(two, twoFromList);
    }

    @Test
    public void setTest() {
        String two = "two";
        list.clear();
        list.add("one");
        list.set(0, two);
        String twoFromList = list.get(0);

        Assertions.assertEquals(two, twoFromList);
    }

    @Test
    public void removeTest() {
        String two = "two";
        list.clear();
        list.add("one");
        list.add(two);
        list.add("three");
        String twoFromList = list.remove(1);

        Assertions.assertEquals(two, twoFromList);
    }

    @Test
    public void clearTest() {
        int size = list.size();
        list.clear();
        int newSize = list.size();

        Assertions.assertNotEquals(size, newSize);
        Assertions.assertEquals(0, newSize);
    }

    @Test
    public void sortedTest() {
        boolean isSorted = list.isSorted();
        list.quickSort(null);
        boolean isSortedAfterMethod = list.isSorted();

        Assertions.assertFalse(isSorted);
        Assertions.assertTrue(isSortedAfterMethod);
    }

    @Test
    public void splitTest() {
        list.split(5);
        int size = list.size();
        int capacity = list.getCapacity();

        Assertions.assertEquals(5, size);
        Assertions.assertEquals(16, capacity);
    }
}
