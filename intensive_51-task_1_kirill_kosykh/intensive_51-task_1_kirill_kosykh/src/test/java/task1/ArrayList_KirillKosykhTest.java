package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayList_KirillKosykhTest {

    ArrayList_KirillKosykh<Integer> list = new ArrayList_KirillKosykh<>();

    @BeforeEach
    public void setUp() {
        list.clear();
        list.add(1);
        list.add(2);
        list.add(3);
    }


    @Test
    void sizeValueIsOkWhileInitialisationAndAddingAndRemoving() {
        assertEquals(3, list.size());

        list.add(1);
        list.add(2);

        assertEquals(5, list.size());

        list.remove(1);
        assertEquals(4, list.size());
    }

    @Test
    void addingElementsIsOkAndValueIsOk() {

        assertEquals(3, list.size());

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void addingElementInCustomPositionIsOk() {

        assertEquals(3, list.size());
        list.add(2, 4);

        assertEquals(4, list.get(2));
    }

    @Test
    void addingElementInWrongIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> list.add(5, 10));
    }

    @Test
    void gettingElementByIndexIsOk() {
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void gettingElementByWrongIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> list.get(4));
    }

    @Test
    void settingElementInExistedIndexIsOk() {
        list.set(0, 4);
        assertEquals(4, list.get(0));
    }

    @Test
    void settingElementInWrongIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> list.set(5, 10));
        assertThrows(IllegalArgumentException.class, () -> list.set(-1, 10));
    }

    @Test
    void removingExistedElementIsOk() {
        assertEquals(1, list.remove(0));
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    void removingElementByWrongIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> list.remove(4));
        assertThrows(IllegalArgumentException.class, () -> list.remove(-1));
    }

    @Test
    void clearingListIsOk() {
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void quickSortingIsOk() {
        list.set(0, 5);
        assertEquals(5, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        list.quickSort(Comparator.naturalOrder());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    void isSortedWorkingOk() {
        assertTrue(list.isSorted());
        list.set(0, 5);
        assertFalse(list.isSorted());
    }

    @Test
    void splitMethodWorksOk() {
        assertThrows(IllegalArgumentException.class, () -> list.split(-1));
        assertThrows(IllegalArgumentException.class, () -> list.split(4));
        list.split(2);
        assertEquals(2, list.size());
    }
}