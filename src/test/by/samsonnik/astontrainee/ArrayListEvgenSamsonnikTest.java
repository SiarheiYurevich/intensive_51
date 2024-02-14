package by.samsonnik.astontrainee;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayListEvgenSamsonnikTest {

    //Here are variables, what we are needing to test custom Array List
    private ArrayListEvgenSamsonnik<String> stringList;
    private ArrayListEvgenSamsonnik<Integer> integerList;
    private ArrayListEvgenSamsonnik<Double> doubleList;
    //For random values generating
    private Random random;

    @Before
    public void setUp() {
        stringList = new ArrayListEvgenSamsonnik<>();
        integerList = new ArrayListEvgenSamsonnik<>();
        doubleList = new ArrayListEvgenSamsonnik<>();
        random = new Random();
    }

    //Testing array capacity initialization
    @Test
    public void testListInit() {
        assertNotEquals(0, stringList.size());
        assertNotEquals(5, stringList.size());
        assertEquals(10, stringList.size());
        stringList.split(4);
        assertEquals(4, stringList.size());
    }

    //Testing initialization with incorrect input data
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacity() {
        ArrayListEvgenSamsonnik<Integer> invalidCapacity = new ArrayListEvgenSamsonnik<>(-1);
    }

    //We test adding to String array data and checking, that was correct and data was saved
    @Test
    public void testAddStringElements() {
        stringList.add("Vladimir");
        stringList.add("Petr");
        stringList.add("Alex");
        stringList.add("Ivan");

        assertEquals("Vladimir", stringList.get(0));
        assertEquals("Petr", stringList.get(1));
        assertEquals("Alex", stringList.get(2));
        assertEquals("Ivan", stringList.get(3));

        assertNotEquals("Vladimir", stringList.get(3));
        assertNotEquals("Petr", stringList.get(2));
        assertNotEquals("Alex", stringList.get(1));
        assertNotEquals("Ivan", stringList.get(0));

    }

    //We test adding to Integer array data and checking, that was correct and data was saved
    @Test
    public void testAddIntegerElements() {
        integerList.add(100);
        integerList.add(200);
        integerList.add(300);
        integerList.add(400);

        assert (integerList.get(0) == 100);
        assert (integerList.get(1) == 200);
        assert (integerList.get(2) == 300);
        assert (integerList.get(3) == 400);

        assert (integerList.get(3) != 100);
        assert (integerList.get(2) != 200);
        assert (integerList.get(1) != 300);
        assert (integerList.get(0) != 400);
    }

    //We test adding to Double array data and checking, that was correct and data was saved
    @Test
    public void testAddDoubleElements() {
        doubleList.add(22.3);
        doubleList.add(44.7);
        doubleList.add(111.56);
        doubleList.add(987.9);

        assert (doubleList.get(0) == 22.3);
        assert (doubleList.get(1) == 44.7);
        assert (doubleList.get(2) == 111.56);
        assert (doubleList.get(3) == 987.9);

        assert (doubleList.get(3) != 22.2);
        assert (doubleList.get(2) != 44.7);
        assert (doubleList.get(1) != 111.56);
        assert (doubleList.get(0) != 987.9);
    }

    //Here we test adding to Integer array data and checking, that this was correct and we can get data by the index
    @Test
    public void testAddAndGetIntegerElementByIndex() {
        integerList.add(0, 20);
        integerList.add(5, 23);
        integerList.add(6, 24);
        integerList.add(7, 25);
        integerList.add(13, 99);

        assert (integerList.get(0) == 20);
        assert (integerList.get(5) == 23);
        assert (integerList.get(6) == 24);
        assert (integerList.get(7) == 25);
        assert (integerList.get(13) == 99);
    }

    //Here we test adding to Double array data and checking, that this was correct and we can get data by the index
    @Test
    public void testAddAndGetDoubleElementByIndex() {
        doubleList.add(0, 20.7);
        doubleList.add(2, 13.2);
        doubleList.add(8, 11.2);
        doubleList.add(4, 344.2);
        doubleList.add(13, 123.2);

        assert (doubleList.get(0) == 20.7);
        assert (doubleList.get(2) == 13.2);
        assert (doubleList.get(9) == 11.2);
        assert (doubleList.get(4) == 344.2);
        assert (doubleList.get(13) == 123.2);

    }

    //Here we test adding to String array data and checking, that this was correct and we can get data by the index
    @Test
    public void testAddAndGetStringElementByIndex() {
        stringList.add(3, "A");
        stringList.add(5, "B");
        stringList.add(7, "C");
        stringList.add(9, "D");
        stringList.add(0, "E");

        assertEquals("A", stringList.get(3));
        assertEquals("B", stringList.get(5));
        assertEquals("C", stringList.get(7));
        assertEquals("D", stringList.get(9));
        assertEquals("E", stringList.get(0));

    }

    //Here we test setting and removing elements with different types of data
    @Test
    public void testSetAndRemoveElement() {

        //adding data
        stringList.set(7, "Test");
        stringList.set(0, "Vasili");

        //we test that data was added and this was correct
        assertEquals("Test", stringList.get(7));
        assertEquals("Vasili", stringList.get(0));

        //removing data
        stringList.remove(7);
        stringList.remove(0);

        // checking, that data was removed
        assertNull(stringList.get(7));
        assertNull(stringList.get(0));

        //we test that data was added and this was correct
        doubleList.set(4, 888.6);
        doubleList.set(9, 222.2);
        assert (doubleList.get(4) == 888.6);
        assert (doubleList.get(9) == 222.2);

        //removing data
        doubleList.remove(4);
        doubleList.remove(9);

        // checking, that data was removed
        assertNull(doubleList.get(4));
        assertNull(doubleList.get(9));

        //we test that data was added and this was correct
        integerList.set(2, 55);
        integerList.set(5, 22);
        assert (integerList.get(2) == 55);
        assert (integerList.get(5) == 22);

        //removing data
        integerList.remove(2);
        integerList.remove(5);

        // checking, that data was removed
        assertNull(integerList.get(2));
        assertNull(integerList.get(5));
    }

    // checking method, that removing all data
    @Test
    public void testClearElement() {

        //adding data
        for (int i = 0; i < 20; i++) {
            integerList.add(random.nextInt());
        }

        //removing all data
        integerList.clear();

        //checking, that all data was removed
        for (int i = 0; i < integerList.size(); i++) {
            assertNull(integerList.get(i));
        }
    }

    //Here we are testing custom sorting inner different types of data with custom quick sorting algorithm
    @Test
    public void testQuickSortElement() {

        //String data for sorting
        String[] arrayForStringListTesting = new String[]{"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S",
                "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};

        //adding Double and Integer data
        for (int i = 0; i < 20; i++) {
            integerList.add(random.nextInt(200));
            doubleList.add(random.nextDouble(200.5));
        }

        //Test boolean result, before sorting, here have to be False
        assertFalse(integerList.isSorted());
        assertFalse(doubleList.isSorted());

        //There are sorting Integer and Double data
        integerList.quickSort(Comparator.naturalOrder());
        doubleList.quickSort(Comparator.naturalOrder());

        //There are checking, that Integer and Double data was sorted correctly
        assertTrue(integerList.isSorted());
        assertTrue(doubleList.isSorted());

        //adding String data to custom Array List
        for (String s : arrayForStringListTesting) {
            stringList.add(s);
        }

        //Test boolean result, before sorting, here have to be False
        assertFalse(stringList.isSorted());

        //There are sorting String data
        stringList.quickSort(Comparator.naturalOrder());

        //There is checking, that String data was sorted correctly
        assertTrue(stringList.isSorted());
    }

    // Here we are testing void, which returns boolean value
    @Test
    public void testIsSortedVoid() {

        //adding Integer and Double data
        for (int i = 0; i < 6; i++) {
            integerList.add(i);
            doubleList.add(i + 0.1);
        }

        //checking, that method is correct
        assertTrue(integerList.isSorted());
        assertTrue(doubleList.isSorted());

        //deleting all values
        integerList.clear();
        doubleList.clear();

        //adding Integer and Double values to check this void with random data
        for (int i = 0; i < 6; i++) {
            integerList.add(random.nextInt());
            doubleList.add(random.nextDouble());
        }

        //Here are random values, result have to be false
        assertFalse(integerList.isSorted());
        assertFalse(doubleList.isSorted());

    }

    //This method trim the array
    @Test
    public void testSplitVoid() {

        //trimming different arrays
        integerList.split(6);
        doubleList.split(7);
        stringList.split(8);

        //checking that this was correct
        assertEquals(6, integerList.size());
        assertEquals(7, doubleList.size());
        assertEquals(8, stringList.size());

    }
}
