import java.util.Comparator;

public class ArrayList_KhabibAliev<E> implements IntensiveList<E>{
    private E[] arr;
    private int len = 0; // длинна массива для пользователя
    private int capacity = 0; // длинна массива на самом деле

    public ArrayList_KhabibAliev() {
        this(16);
    }
    public ArrayList_KhabibAliev(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public void add(E element) {
        if(len + 1>= capacity){
            if(capacity == 0) capacity = 1;
            else capacity *= 2;
            E[] new_arr = (E[]) new Object[capacity];
            for (int i = 0; i < len; i++){
                new_arr[i] = arr[i];
            }
            arr = new_arr;
        }
        arr[len++] = element;
    }

    @Override
    public void add(int index, E element) {
        if (index > capacity) throw new IndexOutOfBoundsException();
        else arr[index] = element;
    }

    @Override
    public E get(int index) {
        return arr[index];
    }

    @Override
    public E set(int index, E element) {
        return arr[index]=element;
    }

    @Override
    public E remove(int index) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException();
        E data = arr[index];
        E[] new_arr = (E[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++)
            if (i == index) j--; // Skip over rm_index by fixing j temporarily
            else new_arr[j] = arr[i];
        arr = new_arr;
        capacity = --len;
        return data;
    }

    @Override
    public void clear() {
        for(int i = 0; i < capacity; i++){
            arr[i]=null;
            len = 0;
        }

    }

    @Override
    public void quickSort(Comparator<E> comparator) {
        comparator = new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return 0;   // Я чет не понял что нужно сделать (
            }
        };
    }

    @Override
    public boolean isSorted() {
        for(int i = 0; i<len; i++){
            if (i>i++) return false;
        }
        return true;
    }

    @Override
    public void split(int size) {
        len = size;
        for (int i = size; i<=capacity;i++){
            arr[i]=null;
        }
    }


}
