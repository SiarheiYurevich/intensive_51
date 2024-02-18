package task_1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayList_VladLitvinov<E> implements IntensiveList<E> {
    private Object[] array;
    private int size;
    private int capacity = 10;
    private final double LOAD_FACTOR = 0.75;

    public ArrayList_VladLitvinov() {
        array = new Object[capacity];
        size = 0;
    }

    public ArrayList_VladLitvinov(int capacity) {
        array = new Object[capacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        size++;
        capacityChange();
        arrayLengthChange(index);
        array[index] = element;
    }

    private void capacityChange() {
        if (LOAD_FACTOR < (double) size / capacity) {
            capacity = (int) (capacity * 1.5);
        }
    }

    private void arrayLengthChange(int index) {
        System.arraycopy(array, index, array, index + 1, size - index);
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) array[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        return (E) (array[index] = element);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        size--;
       Object o = array[index];
       System.arraycopy(array, index + 1, array, index, size - index);
        return (E) o;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = 10;
        array = new Object[capacity];
    }

    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    private void quickSort(int fromIndex, int toIndex, Comparator<E> comparator) {
        if (fromIndex < toIndex) {
            int pivotIndex = partition(fromIndex, toIndex, comparator);
            quickSort(fromIndex, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, toIndex, comparator);
        }
    }
    @SuppressWarnings(value = "unchecked")
    private int partition(int fromIndex, int toIndex, Comparator<E> comparator) {
        E pivotElement = (E) array[toIndex];
        int i = fromIndex - 1;
        for (int j = fromIndex; j < toIndex; j++) {
            if (comparator.compare((E) array[j], pivotElement) <= 0) {
                i++;
                swapElements(i, j);
            }
        }
        swapElements(i + 1, toIndex);
        return i + 1;
    }

    private void swapElements(int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean isSorted() {
        for (int i = 0; i < size - 1; i++) {
            if (((Comparable<E>) array[i]).compareTo((E) array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    @Override
    public void split(int size) {
        if (size < 0 || size > this.size)
            throw new IllegalArgumentException("Invalid size: " + size);

        this.size = size;
        Arrays.fill(array, size, this.size, null);
    }
}
