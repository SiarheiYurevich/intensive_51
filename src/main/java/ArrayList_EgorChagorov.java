import java.util.Comparator;

/**
 * Implementation of a dynamic list similar to ArrayList.
 *
 * @param <E> the type of elements in this list
 */
public class ArrayList_EgorChagorov<E> implements IntensiveList<E> {

  private static final int DEFAULT_CAPACITY = 10;
  private Object[] elements;
  private int size;

  /**
   * Constructs an empty list with an initial capacity of ten.
   */
  public ArrayList_EgorChagorov() {
    this.elements = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  /**
   * Returns the number of elements in this list.
   *
   * @return the number of elements in this list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Appends the specified element to the end of this list.
   *
   * @param element the element to be appended to this list
   */
  @Override
  public void add(E element) {
    ensureCapacity(size + 1);
    elements[size++] = element;
  }

  /**
   * Ensures that the list has a minimum capacity to accommodate the specified number of elements.
   * If the current capacity is less than the specified minimum capacity, the capacity of the list
   * is increased.
   *
   * @param minCapacity the desired minimum capacity
   */
  private void ensureCapacity(int minCapacity) {
    if (minCapacity > elements.length) {
      int newCapacity = elements.length * 2;
      if (newCapacity < minCapacity) {
        newCapacity = minCapacity;
      }
      Object[] newElements = new Object[newCapacity];
      System.arraycopy(elements, 0, newElements, 0, size);
      elements = newElements;
    }
  }

  /**
   * Inserts the specified element at the specified position in this list.
   *
   * @param index   the index at which the specified element is to be inserted
   * @param element the element to be inserted
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public void add(int index, E element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    ensureCapacity(size + 1);
    System.arraycopy(elements, index, elements, index + 1, size - index);
    elements[index] = element;
    size++;
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index the index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    return (E) elements[index];
  }

  /**
   * Replaces the element at the specified position in this list with the specified element.
   *
   * @param index   the index of the element to be replaced
   * @param element the element to be stored at the specified position
   * @return the element previously at the specified position
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public E set(int index, E element) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    E oldValue = (E) elements[index];
    elements[index] = element;
    return oldValue;
  }

  /**
   * Removes the element at the specified position in this list.
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    E removedElement = (E) elements[index];
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(elements, index + 1, elements, index, numMoved);
    }
    elements[--size] = null;
    return removedElement;
  }

  /**
   * Removes all elements from this list.
   */
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      elements[i] = null;
    }
    size = DEFAULT_CAPACITY;
  }

  /**
   * Sorts this list using quick sort.
   *
   * @param comparator the comparator to determine the order of this list
   */
  @Override
  public void quickSort(Comparator<E> comparator) {
    Sort(0, size - 1, comparator);

  }

  /**
   * Recursively sorts the elements of the list using the quicksort algorithm.
   *
   * @param low        the lowest index of the range to be sorted
   * @param high       the highest index of the range to be sorted
   * @param comparator the comparator to determine the order of the elements
   */
  private void Sort(int low, int high, Comparator<E> comparator) {
    if (low < high) {
      int partitionIndex = partition(low, high, comparator);
      Sort(low, partitionIndex - 1, comparator);
      Sort(partitionIndex + 1, high, comparator);
    }
  }

  /**
   * Partitions the list into two sublists and returns the partition index.
   *
   * @param low        the lowest index of the range to be partitioned
   * @param high       the highest index of the range to be partitioned
   * @param comparator the comparator to determine the order of the elements
   * @return the partition index
   */
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

  /**
   * Swaps the elements at the specified indices in the list.
   *
   * @param i the index of the first element to be swapped
   * @param j the index of the second element to be swapped
   */
  private void swap(int i, int j) {
    Object temp = elements[i];
    elements[i] = elements[j];
    elements[j] = temp;
  }

  /**
   * Checks if this list is sorted according to the natural ordering of its elements.
   *
   * @return true if this list is sorted, false otherwise
   */
  @Override
  public boolean isSorted() {
    for (int i = 1; i < size; i++) {
      if (((Comparable<E>) elements[i - 1]).compareTo((E) elements[i]) > 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Trims the size of this list to the specified size.
   *
   * @param size the new size of the list
   * @throws IllegalArgumentException if the specified size is negative
   */
  @Override
  public void split(int size) {
    if (size < 0 || size > this.size) {
      throw new IllegalArgumentException("Invalid size");
    }
    for (int i = this.size - 1; i >= size; i--) {
      elements[i] = null;
    }
    this.size = size;
  }
}
