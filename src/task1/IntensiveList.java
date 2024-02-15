package task1;

import java.util.Comparator;

public interface IntensiveList <E>  {

    int size(); //определить размер массива? возвращается размер массива

    void add(E element); // добавить элемент в масссив

    void add(int index, E element);// добавить элемент по индексу

    E get(int index); // получить элемент по индексу

    E set(int index, E element);// изменить элемент по индексу

    E remove(int index);// удалить по индексу

    void clear();//удаляем все элементы, capacity приводим к дефолтному

    void quickSort(Comparator<E> comparator);//реализуем быструю сортировку, дефолт по возрастанию

    boolean isSorted();//определяем, отсортирован ли массив

    void split(int size);//обрезаем список до указанного размера
}
