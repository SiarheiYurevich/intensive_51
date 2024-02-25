package ru.schung;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Реализация динамического списка аналогичного ArrayList, но не потокобезопасного.
 * @param <E> Тип элементов в списке.
 */
public class ArrayList_IvanovMikhail<E> implements IntensiveList<E> {
    /**
     * Начальная емкость
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Буфер массива, в котором хранятся элементы ArrayList.
     */
    transient Object[] elementData;
    /**
     * Размер списка
     */
    private int size;

    /**
     * Конструктор по умолчанию
     */
    public ArrayList_IvanovMikhail() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elementData[index];
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, Object element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removedElement = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (((Comparable<E>) elementData[i - 1]).compareTo((E) elementData[i]) > 0 ) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
        this.size = size;
        Arrays.fill(elementData, size, elementData.length, null);
    }

    /**
     * Удостоверяет, что размера списка еще хватает для расширения,
     * в ином случае - размер увеличивается вдвое
     * @param minCapacity Проверяемый размер списка
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = elementData.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * Реализация быстрой сортировки
     * @param low Нижняя граница интервала сортировки
     * @param high Верхняя граница интервала сортировки
     * @param comparator Компаратор для сортировки элементов списка.
     */
    private void quickSort(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int pi = partition(low, high, comparator);
            quickSort(low, pi - 1, comparator);
            quickSort(pi + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }


    private void swap(int i, int j) {
        E temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

    @Override
    public String toString() {
        return "ArrayList_IvanovMikhail{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
