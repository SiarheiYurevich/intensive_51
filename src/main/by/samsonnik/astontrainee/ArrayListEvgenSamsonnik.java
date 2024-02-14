package by.samsonnik.astontrainee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ArrayListEvgenSamsonnik<E> implements IntensiveList<E> {

    /**
     * Custom Array List
     *
     * Resizable-array implementation of the IntensiveList interface. Implements all basic operations like addition
     * and setting by element and index, deleting, setting ang getting element by index. Also, this class can trim
     * element's array on your custom value, remove useless element or clear a common array. Permits all elements,
     * including null. Each Custom Array List instance has a capacity. The capacity is the size of the array used to
     * store the elements in the list. An application can increase the capacity of an ArrayList instance before adding
     * a large number of elements using the ensureCapacity operation. Here was realized sorting with Quick sorting
     * algorithm.
     *
     * @since 1.0
     * @author Samsonnik Evgen
     */

    /**
     * This is the default initial capacity of the resizable-array
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * This is the default array, if the user selects zero like initial capacity
     */
    private static final Object[] EMPTY_ELEMENT = {};
    /**
     * <E> This defines the type of inner elements
     */
    private E[] elementData;
    /**
     * <E> This variable helps to control load rate and increase capacity of an array
     */
    private int lastFilledElement = 0;

    /**
     * Create an empty list with default capacity
     */
    public ArrayListEvgenSamsonnik() {
        this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Create an empty list with custom capacity
     *
     * @param initialCapacity the size of an array
     * @throws IllegalArgumentException this will be if initial capacity less than 0 or incorrect data
     */
    public ArrayListEvgenSamsonnik(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = (E[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = (E[]) EMPTY_ELEMENT;
        } else {
            throw new IllegalArgumentException("Illegal value of capacity: " + initialCapacity);
        }
    }

    /**
     * This shows how many array has inner cells
     */
    @Override
    public int size() {
        return elementData.length;
    }

    /**
     * @param element This appends this element into the array, to the end
     */
    @Override
    public void add(E element) {
        if (lastFilledElement >= elementData.length) {
            increaseCapacity();
        }
        elementData[lastFilledElement] = element;
        lastFilledElement++;
    }

    /**
     * @param element This appends this element into the definite place
     * @param index   Number of the cell, where you want to put an element
     * @throws IllegalArgumentException if there was incorrect value
     */
    @Override
    public void add(int index, E element) {
        if (index < 0) {
            throw new IllegalArgumentException("Illegal value of index: " + index);
        } else if (index == lastFilledElement) {
            add(element);
        } else if (index >= elementData.length) {
            increaseCapacityOnValue(index + 1);
            elementData[index] = element;
        } else {
            increaseCapacityOnValue(elementData.length + 1);
            System.arraycopy(elementData, index, elementData, index + 1, elementData.length - 1 - index);
            elementData[index] = element;
        }
    }

    /**
     * @param index Here you can get element by index
     * @return An element, that you chose
     * @throws IllegalArgumentException if there was illegal value of the index
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    /**
     * @param element This replaces an old element into the definite place
     * @param index   Number of the cell, where you want to put an element
     * @return An old element, that was replaced
     * @throws IllegalArgumentException if there was incorrect value
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = (E) elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    /**
     * Removes the element by specified position in this list.
     *
     * @param index - of the element to be removed
     * @return the element that was removed
     * @throws IllegalArgumentException - if index was wrong
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        if (elementData[index] == null) throw new IllegalArgumentException("The element with this index is null");
        E oldElement = (E) elementData[index];
        elementData[index] = null;
        return oldElement;
    }

    /**
     * Removes all elements in list.
     */
    @Override
    public void clear() {
        Arrays.fill(elementData, null);
        lastFilledElement = 0;
    }

    /**
     * Here was realized sorting with Quick sorting algorithm.
     * Before sorting, there is a run method to remove all null objects
     *
     * @param comparator to compare inner elements, like default, you have to use Comparator.naturalOrder();
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        removeAllNull();
        int lowBorder = 0;
        int highBorder = elementData.length - 1;
        makeSort(lowBorder, highBorder, comparator);
    }

    /**
     * @return yes, if inner data was sorted
     */
    @Override
    public boolean isSorted() {
        removeAllNull();
        for (int i = 1; i < elementData.length; i++) {
            if (((Comparable<E>) elementData[i - 1]).compareTo((E) elementData[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param size trims inner array by this size
     */
    @Override
    public void split(int size) {
        checkIndex(size);
        Object[] newElementData = new Object[size];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        this.elementData = (E[]) newElementData;
    }

    /**
     * This method is using to increase inner array capacity, like default, n * 2
     */
    private void increaseCapacity() {
        int newCapacity = elementData.length * 2;
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        this.elementData = (E[]) newElementData;
    }

    /**
     * This method is using to increase inner array capacity on custom value
     */
    private void increaseCapacityOnValue(int newCapacity) {
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        this.elementData = (E[]) newElementData;
    }

    /**
     * This method is custom's quick sorting algorithm realization, which divides array on some parts.
     * This method is using recursion
     *
     * @param lowBorder  this is a low border of an array
     * @param highBorder this is a high border of an array
     * @param comparator this is necessary to compare some comparable objects
     */
    private void makeSort(int lowBorder, int highBorder, Comparator<E> comparator) {
        if ((elementData.length == 0) || (lowBorder >= highBorder)) return;
        int pivotElementIndex = lowBorder + (highBorder - lowBorder) / 2;
        E pivotElement = elementData[pivotElementIndex];
        int low = lowBorder;
        int high = highBorder;
        while (low <= high) {
            while (comparator.compare(elementData[low], pivotElement) < 0) {
                low++;
            }
            while (comparator.compare(elementData[high], pivotElement) > 0) {
                high--;
            }
            if (low <= high) {
                E temp = elementData[low];
                elementData[low] = elementData[high];
                elementData[high] = temp;
                low++;
                high--;
            }
        }
        if (lowBorder < high) {
            makeSort(lowBorder, high, comparator);
        }
        if (highBorder > low) {
            makeSort(low, highBorder, comparator);
        }
    }

    /**
     * This method is necessary to correct sorting. Without is, method will throw Exception,
     * if here will be comparing object, which of one a null
     */
    private void removeAllNull() {
        elementData = (E[]) Arrays.stream(elementData).filter(Objects::nonNull).toArray();
    }

    private void checkIndex(int index) {
        if ((index < 0) || (index > elementData.length - 1))
            throw new IllegalArgumentException("Illegal index: " + index);
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
