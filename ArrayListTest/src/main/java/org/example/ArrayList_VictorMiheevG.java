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
public class ArrayList_VictorMiheevG<T> implements IntensiveList<T> {

    /**
     * Default capacity.
     * Initialized in the setDefaultCapacity method in default constructor
     */
    private int capacity = 2;
    /**
     * The data array
     */
    private T[] array = (T[]) new Object[capacity];
    /**
     * The coefficient by which the size of the array is increased
     * when the array is completely filled
     */
    private final int capacityCoefficient = 2;
    /**
     * the number of elements it contains
     */
    private int size = 0;

    private boolean isSorted = false;
    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public ArrayList_VictorMiheevG(int initialCapacity) {
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
        return size;
    }
    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    @Override
    public void add(T element) {
        if (size + 1 == capacity) increaseSize();
        if (array[size] != null)
            System.out.println("an element ".concat(String.valueOf(array[size]))
                    .concat(" with index ").concat(String.valueOf(size))
                    .concat(" was deleted!"));
        array[size] = element;
        size++;
        isSorted = false;
    }
    /**
     * Increases the capacity of this ArrayList instance
     */
    private void increaseSize() {
        int newSize = this.capacity * capacityCoefficient;
        T[] newArray = (T[]) new Object[newSize];
        if (this.capacity >= 0)
            System.arraycopy(array, 0, newArray, 0, this.capacity);
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
    public void add(int index, T element) {
        if (index >= this.capacity || (size + 1 >= capacity)) {
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
        isSorted = false;
    }
    /**
     * @param index of element
     * @return element by index
     */
    @Override
    public T get(int index) {
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
    public T set(int index, T element) {
        if (index >= capacity) return null;
        T result = get(index);
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
    public T remove(int index) {
        if (index >= capacity) return null;
        T result = array[index];
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
        array = (T[]) new Object[2];
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
    public void quickSort(Comparator<T> comparator) {
        quickSort(array, 0, this.size - 1, comparator);
        isSorted = true;
    }
    /**
     * sorting an array using the quick sort algorithm
     * @param array         to be sorted
     * @param from          the element to sort from
     * @param to            the element to sort to
     * @param comparator    to compare the elements
     */
    private void quickSort(T[] array, int from, int to, Comparator<T> comparator) {
        if (from < to) {
            int divideIndex = partition(array, from, to, comparator);
            quickSort(array, from, divideIndex - 1, comparator);
            quickSort(array, divideIndex, to, comparator);
        }
    }
    /**
     * swap elements relative to pivot
     * @param array         to be sorted
     * @param from          the element to sort from
     * @param to            the element to sort to
     * @param comparator    to compare the elements
     * @return              the index of the element to split the array
     */
    private int partition(T[] array, int from, int to, Comparator<T> comparator) {
        int pivotElementIndex = (from + to) / 2;
        while (from < to) {
            while (comparator.compare(array[from], array[pivotElementIndex]) <0) from++;
            while (comparator.compare(array[to], array[pivotElementIndex]) >0) to--;
            if (from <= to) {
                T swap = array[from];
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
        return size == 1 || isSorted;
    }
    /**
     * delete all elements in array after size(inclusive)
     * @param size the number of elements
     *             that will remain in the array (0 - size)
     */
    @Override
    public void split(int size) {
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        this.size = size;
        capacity = size;
        array = newArray;
    }
}
