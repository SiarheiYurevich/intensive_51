package com.igorpopovich.task1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс, представляющий динамический список.
 *
 * @param <E> тип элементов списка
 */
public class ArrayList_IgorPopovich<E> implements IntensiveList<E>{
    private int size;
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    /**
     * Создает новый пустой список с начальной емкостью по умолчанию.
     */
    public ArrayList_IgorPopovich() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return размер списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
    }

    /**
     * Добавляет элемент в указанную позицию списка.
     *
     * @param index   позиция для вставки элемента
     * @param element элемент для добавления
     */
    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент списка по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент списка
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return (E) elements[index];
    }

    @Override
    public E set(int index, Object element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        elements[index] = element;
        return (E) elements[index];
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        E removedElement = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Удаляет все элементы из списка и приводит емкость к значению по умолчанию.
     */
    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Быстрая сортировка списка с использованием указанного компаратора.
     *
     * @param comparator компаратор для сортировки
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        sortMethod(0, size - 1, comparator);
    }

    /**
     * Проверяет, отсортирован ли список.
     *
     * @return true, если список отсортирован; false, если нет
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
     * Обрезает список до указанного размера.
     *
     * @param size новый размер списка
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
        this.size = size;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * GROWTH_FACTOR, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void sortMethod(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int partitionIndex = partition(low, high, comparator);
            sortMethod(low, partitionIndex - 1, comparator);
            sortMethod(partitionIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = (E) elements[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((E) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = (E) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
