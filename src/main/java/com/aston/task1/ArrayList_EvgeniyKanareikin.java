package com.aston.task1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Custom implementation of a dynamic list (analogous to ArrayList, not thread-safe)
 *
 * @param <E> the type of elements in the list
 */
public class ArrayList_EvgeniyKanareikin<E> implements IntensiveList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty list with the default initial capacity
     */
    public ArrayList_EvgeniyKanareikin() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with the initial capacity equal to initSize
     *
     * @param initSize the initial capacity
     * @throws IllegalArgumentException if the initSize is less zero
     */
    public ArrayList_EvgeniyKanareikin(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("Illegal size: " + initSize);
        }
        elements = new Object[initSize];
    }

    /**
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param element the element to be added to this list
     */
    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param index   the index at which the specified element is to be inserted
     * @param element the element to be inserted
     */
    @Override
    public void add(int index, E element) {
        checkRange(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified position in this list
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public E get(int index) {
        checkRange(index);
        return (E) elements[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     *
     * @param index   the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element to be stored at the specified position
     */
    @Override
    public E set(int index, E element) {
        checkRange(index);
        ensureCapacity(size + 1);
        return (E) (elements[index] = element);
    }

    /**
     * Removes the element at the specified position in this list
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    @Override
    public E remove(int index) {
        checkRange(index);
        E removedElement = (E) elements[index];
        int numElementsToShift = size - index - 1;
        if (numElementsToShift > 0) {
            System.arraycopy(elements, index + 1, elements, index, numElementsToShift);
        }
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Removes all elements from this list and sets the capacity to the default value
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Sorts the elements in this list using the specified comparator
     *
     * @param comparator the comparator to determine the order of the elements
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Checks if the elements in this list are sorted in ascending order
     *
     * @return true if the elements are sorted, otherwise false
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (((Comparable<E>) elements[i]).compareTo((E) elements[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Trims the size of this list to the specified size.
     *
     * @param size the new size of the list
     * @throws IllegalArgumentException if the specified size is negative or greater than the current size
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size) {
            throw new IllegalArgumentException("Size: " + size + ", Current Size: " + this.size);
        }
        if (size < this.size) {
            Arrays.fill(elements, size, this.size, null);
            this.size = size;
        }
    }

    private void ensureCapacity(int needCapacity) {
        if (needCapacity > elements.length) {
            int newCapacity = elements.length * 2 + 1;
            if (newCapacity < needCapacity) {
                newCapacity = needCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void checkRange(int index) {
        if (index < 0 || index >= (size + 1)) {
            throw new IllegalArgumentException("Illegal index:" + index);
        }
    }

    private void quickSort(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = (E) elements[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((E) elements[j], pivot) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}