package main.java.task1;

import java.util.Comparator;

/**
 * An implementation of the {@code IntensiveList} interface using an array-based list.
 * @param <E> the type of elements stored in this list
 */
public class ArrayList_VadimZolotarev<E> implements IntensiveList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    private int size = 0;
    private int capacity;

    /**
     * Constructs an empty list with an initial array provided.
     * @param array the initial array to use as the internal storage for the list
     */
    public ArrayList_VadimZolotarev(E[] array) {
        this.array = array;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element the element to be appended to this list
     */
    @Override
    public void add(E element) {
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = (E[]) newArray;
        }
        array[size++] = element;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index the index at which the specified element is to be inserted
     * @param element the element to be inserted
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index < size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = (E[]) newArray;
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E[] newArray = (E[]) new Object[size - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size - index - 1);
        array = newArray;
        size--;
        return (E) newArray;
    }

    /**
     * Removes all the elements from this list.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    /**
     * Sorts this list according to the order induced by the specified comparator.
     * @param comparator the comparator to determine the order of the list
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        if (size <= 1) {
            return;
        }

        int first = 0;
        int last = size - 1;
        int[] sort = new int[last - first + 1];
        int top = -1;

        sort[++top] = first;
        sort[++top] = last;

        while (top >= 0) {
            last = sort[top--];
            first = sort[top--];

            int pivotIndex = partition(comparator, first, last);

            if (pivotIndex - 1 > first) {
                sort[++top] = first;
                sort[++top] = pivotIndex - 1;
            }

            if (pivotIndex + 1 < last) {
                sort[++top] = pivotIndex + 1;
                sort[++top] = last;
            }
        }
    }

    private int partition(Comparator<E> comparator, int first, int last) {
        E pivot = array[last];
        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[last];
        array[last] = temp;

        return i + 1;
    }

    /**
     * Checks if this list is sorted in ascending order according to the natural ordering of its elements.
     * @return {@code true} if this list is sorted, {@code false} otherwise
     */
    @Override
    public boolean isSorted() {
        for (int i = 0; i < size - 1; i++) {
            Comparable<E> current = (Comparable<E>) array[i];
            Comparable<E> next = (Comparable<E>) array[i + 1];
            if (current.compareTo((E) next) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reduces the size of this list to the specified size.
     * @param size the new size of the list
     */
    @Override
    public void split(int size) {
        if (size < 0 || size >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            return;
        }
        E[] newArray = (E[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}