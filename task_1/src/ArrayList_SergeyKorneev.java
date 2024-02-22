import java.util.Comparator;

public class ArrayList_SergeyKorneev<E> implements IntensiveList{

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity = DEFAULT_CAPACITY;
    private E[] arr;

    public ArrayList_SergeyKorneev() {
        arr =  (E[]) new Object[DEFAULT_CAPACITY];;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object element) {
        ensureCapacity();
        arr[size] = (E) element;
        size++;
    }

    @Override
    public void add(int index, Object element) {

    }

    private void grow() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }

    private void ensureCapacity() {
        if (capacity == size) grow();
    }



    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) arr[index];
    }

    @Override
    public E set(int index, Object element) {
        checkIndex(index);
        arr[index] = (E) element;
        return (E) element;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Object removed = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public void clear() {
        arr = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void quickSort(Comparator comparator) {
        sortMethod(0, size - 1, comparator);
    }

    private void sortMethod(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int partitionIndex = partition(low, high, comparator);
            sortMethod(low, partitionIndex - 1, comparator);
            sortMethod(partitionIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = (E) arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((E) arr[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = (E) arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (((Comparable<E>) arr[i]).compareTo((E) arr[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void split(int size) {
        checkIndex(size - 1);
        for(int i = size; i < this.size; i++) {
            arr[i] = null;
        }
        this.size = size;
        

    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }
}
