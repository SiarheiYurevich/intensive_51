package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;

/**
 * The class like dynamic array
 * implementation of IntensiveList<Integer>
 *
 * @author Victor Miheev
 * @version 1.0
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public class ArrayList_VictorMiheev implements IntensiveList<Integer> {

    /**
     * Default capacity.
     * Initialized in the setDefaultCapacity method in default constructor
     */
    private int capacity = 2;
    /**
     * The data array
     */
    private Integer[] array = new Integer[capacity];
    /**
     * The coefficient by which the size of the array is increased
     * when the array is completely filled
     */
    private final int capacityCoefficient = 2;
    /**
     * the number of elements it contains
     */
    private int size = 0;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public ArrayList_VictorMiheev(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
        this.capacity = initialCapacity;
    }


    /**
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    @Override
    public void add(Integer element) {
        if (size + 1 == capacity) increaseSize();
        if (array[size] != null)
            System.out.println("an element ".concat(String.valueOf(array[size]))
                    .concat(" with index ").concat(String.valueOf(size))
                    .concat(" was deleted!"));
        array[size] = element;
        size++;
    }

    private void increaseSize() {
        int newSize = this.capacity * capacityCoefficient;
        Integer[] newArray = new Integer[newSize];
        for (int i = 0; i < this.capacity; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
        this.capacity = newSize;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, Integer element) {
        if (index > this.capacity || (size + 1 == capacity)) {
            increaseSize();
            add(index, element);
        }
        if (index == size) {
            add(element);
            return;
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
    }

    /**
     * @param index of element
     * @return element by index
     */
    @Override
    public Integer get(int index) {
        return array[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position. It can be null
     */
    @Override
    public Integer set(int index, Integer element) {
        if (index >= capacity) return null;
        Integer result = get(index);
        add(index, element);
        return result;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list. It can be null
     */
    @Override
    public Integer remove(int index) {
        if (index >= capacity) return null;
        int result = array[index];
        array[index] = null;
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[size] = null;
        return result;
    }

    /**
     * Removes all elements from this list.
     * Capacity = 2
     */
    @Override
    public void clear() {
        array = new Integer[2];
        capacity = 2;
        size = 0;
    }

    /**
     * Sorts the array by ASC using "quick sort" algorithm.
     * Using comparator to compare elements
     *
     * @param comparator - compare elements in the array
     */
    @Override
    public void quickSort(Comparator<Integer> comparator) {
        quickSort(array, 0, this.size - 1, comparator);
    }

    private void quickSort(Integer[] array, int from, int to, Comparator<Integer> comparator) {
        if (from < to) {
            int divideIndex = partition(array, from, to, comparator);
            quickSort(array, from, divideIndex - 1, comparator);
            quickSort(array, divideIndex, to, comparator);
        }
    }

    private int partition(Integer[] array, int from, int to, Comparator<Integer> comparator) {
        int pivotElementIndex = (from + to) / 2;
        while (from < to) {
            while (comparator.compare(array[from], array[pivotElementIndex]) <0) from++;
            while (comparator.compare(array[to], array[pivotElementIndex]) >0) to--;
            if (from <= to) {
                int swap = array[from];
                array[from] = array[to];
                array[to] = swap;
                from++;
                to--;
            }
        }
        return from;
    }

    /**
     * @return true if array is sorted by ASC
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * delete all elements in array after size(inclusive)
     * @param size the number of elements
     *             that will remain in the array (0 - size)
     */
    @Override
    public void split(int size) {
        Integer[] newArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        this.size = size;
        capacity = size;
        array = newArray;
    }

}
