package com.sursindmitry.task1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation of a custom dynamic list (analogous to ArrayList, not thread safe)
 */
public class ArrayList_DmitrySursin<E> implements IntensiveList<E> {

  private Object[] elements;
  private final int DEFAULT_CAPACITY = 1;
  private int size;

  private boolean isSorted;

  /**
   * Constructor custom ArrayList with default capacity
   */
  public ArrayList_DmitrySursin() {
    this.elements = new Object[DEFAULT_CAPACITY];
    this.size = 0;
    this.isSorted = false;
  }

  /**
   * Constructor custom ArrayList with custom capacity
   */
  public ArrayList_DmitrySursin(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity cannot be less than or equal to zero");
    } else {
      this.elements = new Object[capacity];
      this.size = 0;
      this.isSorted = false;
    }
  }


  /**
   * Returns the number of elements in a list
   *
   * @return the number of elements in a list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Adds an element to the end of the list
   *
   * @param element new element
   */

  @Override
  public void add(E element) {
    ensureCapacity(size + 1);

    elements[size++] = element;
    isSorted = false;
  }

  /**
   * Adds an element to the list at the specified index
   *
   * @param index   index
   * @param element new element
   */
  @Override
  public void add(int index, E element) {
    checkIndex(index);

    ensureCapacity(size + 1);

    for (int i = size; i > index; i--) {
      elements[i] = elements[i - 1];
    }

    elements[index] = element;
    size++;
    isSorted = false;
  }

  /**
   * Returns the element by index
   *
   * @param index the index of the element to be returned
   * @return the element at the specified index
   */
  @Override
  public E get(int index) {
    checkIndex(index);

    return (E) elements[index];
  }

  /**
   * Replaces the element in the list at the specified index with the specified element.
   *
   * @param index   index of the element to be replaced
   * @param element a new element that will be installed at the specified position
   * @return new element
   */
  @Override
  public E set(int index, E element) {
    checkIndex(index);

    elements[index] = element;
    isSorted = false;
    return element;
  }

  /**
   * Removes an element by index
   *
   * @param index index of the element to be removed
   * @return removed element
   */
  @Override
  public E remove(int index) {
    checkIndex(index);

    E removedElement = (E) elements[index];

    for (int i = index; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }

    elements[size - 1] = null;
    size--;
    isSorted = false;

    return removedElement;
  }

  /**
   * Removes all elements of a collection
   */
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      elements[i] = null;
    }
    size = 0;
  }

  /**
   * Sorting elements
   */
  @Override
  public void quickSort(Comparator<E> comparator) {
    quickSortRecursive(comparator, 0, size - 1);
    isSorted = true;
  }

  private void quickSortRecursive(Comparator<E> comparator, int low, int high) {
    if (low < high) {
      int partitionIndex = partition(comparator, low, high);

      quickSortRecursive(comparator, low, partitionIndex - 1);
      quickSortRecursive(comparator, partitionIndex + 1, high);
    }
  }

  private int partition(Comparator<E> comparator, int low, int high) {
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
  public boolean isSorted() {
    return isSorted;
  }

  @Override
  public void split(int size) {
    if (size < 0 || size > this.size) {
      throw new IllegalArgumentException("Invalid size: " + size);
    }
  }

  private void checkIndex(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", out of bounds");
    }
  }

  private void ensureCapacity(int minCapacity) {
    if (minCapacity > elements.length) {
      int newCapacity = elements.length * 2;
      Object[] newArray = new Object[newCapacity];
      System.arraycopy(elements, 0, newArray, 0, size);
      elements = newArray;
    }
  }
}
