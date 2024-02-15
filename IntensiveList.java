import java.util.Comparator;

public interface IntensiveList<E> {
    int size();
    void add(E element);
    void add(int index, E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);
    void clear();
    void quickSort(Comparator<E> comparator);
    boolean isSorted();
    void split(int size);

}
