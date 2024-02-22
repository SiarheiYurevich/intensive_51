import java.util.Comparator;

public interface IntensiveList<E> {
    int size();
    void add(E element);
    void add(int index, E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);
    void clear();//удаляем все элементы, capacity приводим к дефолтному
    void quickSort(Comparator<E> comparator);//реализуем быструю сортировку, дефолт по возрастанию
    boolean isSorted();
    void split(int size);//обрезаем список до указанного размера
}