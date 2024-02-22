package task1;

import java.util.Comparator;

/**
 * Имплементация IntensiveList interface.
 * Этот класс позволяет сохранять и манипулировать элементами на базе массива
 */
public class ArrayList_KirillKosykh<E> implements IntensiveList<E> {

    private int shift;
    private final int CAPACITY = 10;
    private Object[] list;

    /**
     * Конструктор, инициализирующий массив дефолтного размера.
     */
    public ArrayList_KirillKosykh() {
        this.shift = 0;
        this.list = new Object[CAPACITY];
    }

    /**
     * Контроль за размером массива
     * если более 75% занято, то расширяем
     * усли меньше 25% занято, то уменьшаем
     */
    private void resizeArray() {
        if ((double) shift / (double) list.length >= 0.75) {
            Object[] biggerArray = new Object[shift * 2];
            if (shift >= 0) System.arraycopy(list, 0, biggerArray, 0, shift);
            list = biggerArray;
        }
        if (list.length / shift >= 4) {
            Object[] smallerArray = new Object[shift * 2];
            if (shift >= 0) System.arraycopy(list, 0, smallerArray, 0, shift);
            list = smallerArray;
        }
    }

    /**
     * Получает размер массива
     */
    @Override
    public int size() {
        return shift;
    }

    /**
     * Добавляет элемент в конец ArrayList_KirillKosykh.
     *
     * @param element элемент для добавления в конец
     */
    @Override
    public void add(E element) {
        list[shift++] = element;
        resizeArray();
    }

    /**
     * Добавляет элемент в заданный индекс, смещая остальные элементы.
     *
     * @param index   индекс в массиве куда добавляется элемент
     * @param element элемент для добавления
     */
    @Override
    public void add(int index, E element) {

        if (index > shift) throw new IllegalArgumentException();

        int count = shift;

        while (count > index) {
            list[count] = list[--count];
        }
        list[index] = element;

        shift++;

        resizeArray();
    }

    /**
     * Получает элемент по заданному индексу.
     *
     * @param index элемент для добавления
     */
    @Override
    public E get(int index) {
        if (index >= shift) throw new IllegalArgumentException();
        return (E) list[index];
    }

    /**
     * Меняет значение элемента по заданному индексу.
     *
     * @param index   индекс в массиве, который будет отредактирован
     * @param element значение элемента
     */
    @Override
    public E set(int index, E element) {
        if (index >= shift || index < 0) throw new IllegalArgumentException();
        list[index] = element;
        return (E) list[index];
    }

    /**
     * Удаляет элемент по заданному индексу и смещает остальные элементы.
     *
     * @param index индекс элемента для удаления в массиве
     */
    @Override
    public E remove(int index) {
        if (index >= shift || index < 0) throw new IllegalArgumentException();
        Object answer = list[index];

        for (int i = index; i < shift; i++) {
            list[i] = list[i + 1];
        }
        shift--;

        resizeArray();

        return (E) answer;
    }

    /**
     * Очищает массив, приводит его к дефолтным параметрам.
     */
    @Override
    public void clear() {
        shift = 0;
        list = new Object[CAPACITY];
    }

    /**
     * Производит быструю сортировку массива в зависимости от переданного comparator.
     * по дефолту массив сортируется по неубыванию
     *
     * @param comparator стратегия сортировки массива
     */
    @Override
    public void quickSort(Comparator<E> comparator) {

        selfWrittenQuickSort(0, shift - 1, comparator);
    }

    /**
     * Выполнение быстрой сортировки с помощью рекурсии
     *
     * @param left       левая граница массива(подмассива)
     * @param right      правая граница массива(подмассива)
     * @param comparator стратегия сортировки
     */

    private void selfWrittenQuickSort(int left, int right, Comparator<E> comparator) {
        if (left < right) {
            int pivot = partition(left, right, comparator);
            selfWrittenQuickSort(left, pivot - 1, comparator);
            selfWrittenQuickSort(pivot + 1, right, comparator);
        }
    }

    /**
     * Вычисление опорного элемента в массиве(подмассиве)
     *
     * @param left       левая граница массива(подмассива)
     * @param right      правая граница массива(подмассива)
     * @param comparator стратегия сортировки
     */
    private int partition(int left, int right, Comparator<E> comparator) {
        E pivot = (E) list[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare((E) list[j], pivot) <= 0) {
                i++;
                swapElements(i, j);
            }
        }
        swapElements(i + 1, right);
        return i + 1;
    }

    /**
     * Выполнение перестановки элементов
     *
     * @param i левая граница массива(подмассива)
     * @param j правая граница массива(подмассива)
     */
    private void swapElements(int i, int j) {
        Object temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    /**
     * Показывает отсортирован ли массив.
     * Сравниваем элементы попарно, при несоблюдении условия неубывания прерываем проход
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < shift; i++) {
            if (((Comparable<E>) list[i - 1]).compareTo((E) list[i]) > 0) return false;
        }
        return true;
    }

    /**
     * Обрезает список до указанного размера
     *
     * @param size размер уменьшенного списка
     */
    @Override
    public void split(int size) {
        if (size > shift || size < 0) throw new IllegalArgumentException();
        Object[] splitArray = new Object[size * 2];
        System.arraycopy(list, 0, splitArray, 0, size);
        list = splitArray;
        this.shift = size;
    }
}
