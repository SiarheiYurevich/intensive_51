import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;


class ArrayList_EgorChagorovTest {
  @Test
//  @IntensiveTest_EgorChagorov
  public void testSize() {
    // Initialize an empty list and check size
    List<Integer> list = new ArrayList<>();
    assert list.size() == 0;

    // Add elements and check size
    list.add(5);
    list.add(10);
    list.add(15);
    assert list.size() == 3;

    // Remove elements and check size
    list.remove(0);
    list.remove(0);
    assert list.size() == 1;
  }
  @Test
  public void testAdd() {
    // Initialize an empty list and add elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(5);
    list.add(10);
    list.add(15);

    // Check if elements are added correctly
    ArrayList_EgorChagorov<Integer> expectedList = new ArrayList_EgorChagorov<>();
    expectedList.add(5);
    expectedList.add(10);
    expectedList.add(15);
    Assertions_EgorChagorov.assertEquals(expectedList, list);
  }
  @Test
  public void testAddAtIndex() {
    // Initialize a list with elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(5);
    list.add(10);
    list.add(15);

    // Add element at index 1
    list.add(1, 12);

    // Check if element is added correctly
    assert list.size() == 4;
    assert list.get(1) == 12;
  }
  @Test
  public void testGet() {
    // Initialize a list with elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(5);
    list.add(10);
    list.add(15);

    // Check if elements are retrieved correctly
    assert list.get(0) == 5;
    assert list.get(1) == 10;
    assert list.get(2) == 15;
  }

  @Test
  public void testSet() {
    // Initialize a list with elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(5);
    list.add(10);
    list.add(15);

    // Set element at index 1
    list.set(1, 12);

    // Check if element is set correctly
    assert list.get(1) == 12;
  }
  @Test
  public void testRemove() {
    // Initialize a list with elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(5);
    list.add(10);
    list.add(15);

    // Remove element at index 1
    int removedElement = list.remove(1);

    // Check if element is removed correctly
    assert removedElement == 10;
    assert list.size() == 2;
    assert list.get(0) == 5;
    assert list.get(1) == 15;
  }

    @Test
  public void testClear() {
    // Initialize a list with elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(5);
    list.add(10);
    list.add(15);

    // Clear the list
    list.clear();

    // Check if list is cleared
    assert list.size() == 10;
  }
  @Test
  @IntensiveTest_EgorChagorov
  public void testQuickSort() {
    // Initialize a list with elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(15);
    list.add(5);
    list.add(10);

    // Sort the list
    list.quickSort(Comparator.naturalOrder());
    ArrayList_EgorChagorov<Integer> expectedList = new ArrayList_EgorChagorov<>();
    // Check if list is sorted
    expectedList.add(5);
    expectedList.add(10);
    expectedList.add(15);
    Assertions_EgorChagorov.assertEquals(list,expectedList);
  }
  @Test
  public void testIsSorted() {
    // Initialize a sorted list
    ArrayList_EgorChagorov<Integer> sortedList = new ArrayList_EgorChagorov<>();
    sortedList.add(5);
    sortedList.add(10);
    sortedList.add(15);

    // Initialize an unsorted list
    ArrayList_EgorChagorov<Integer> unsortedList = new ArrayList_EgorChagorov<>();
    unsortedList.add(15);
    unsortedList.add(5);
    unsortedList.add(10);

    // Check if lists are sorted
    assert sortedList.isSorted();
    assert !unsortedList.isSorted();


  }
  @Test
  // Test method for split(int size) method
  public void testSplit() {
    // Initialize a list with elements
    ArrayList_EgorChagorov<Integer> list = new ArrayList_EgorChagorov<>();
    list.add(5);
    list.add(10);
    list.add(15);
    list.add(20);
    list.add(25);

    // Split the list
    list.split(3);

    // Check if list is split correctly
    assert list.size() == 3;
    assert list.get(0) == 5;
    assert list.get(1) == 10;
    assert list.get(2) == 15;
  }

}
