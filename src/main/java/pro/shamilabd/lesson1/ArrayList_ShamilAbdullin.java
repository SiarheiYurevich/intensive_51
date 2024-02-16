package pro.shamilabd.lesson1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс представляет из себя динамический generic массив, аналог системного ArrayList.
 * Реализует интерфейс IntensiveList (в учебных целях).
 * @param <E>
 */
public class ArrayList_ShamilAbdullin<E> implements IntensiveList<E> {

    public static final int DEFAULT_CAPACITY = 8;
    private int size;
    private int capacity;
    private E[] array;
    private boolean isSorted;

    /**
     * Инициализация массива с дефолтным значением.
     */
    public ArrayList_ShamilAbdullin() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Инициализация массива с указанной емкостью.
     * @param capacity
     */
    public ArrayList_ShamilAbdullin(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be more than 0");
        }
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    /**
     * Возвращает количество не null элементов в массиве.
     * @return количество не null элементов
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает текущую максимальную емкость динамического массива.
     * @return capacity емкость массива
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Добавляет элемент в следующую пустую ячейку массива.
     * Если мест нет, то расширяем массив и добавляет элемент.
     * @param element добавляемый элемент
     */
    @Override
    public void add(E element) {
        if (element == null) {
            return;
        }
        isSorted = false;
        checkOrUpdateArray();
        array[size++] = element;
    }

    private void checkOrUpdateArray() {
        if (size >= capacity) {
            E[] newArray = (E[]) new Object[capacity * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
            capacity *= 2;
        }
    }

    /**
     * Добавляет элемент в указанную ячейку массива со сдвигом остальных элементов на следующие позиции.
     * Если мест нет, то расширяем массив и добавляет элемент.
     * @param index позиция, на которую добавляется элемент
     * @param element добавляемый элемент
     */
    @Override
    public void add(int index, E element) {
        if (element == null) {
            return;
        }
        isSorted = false;
        checkOrUpdateArray();
        if (index <= size) {
            E[] leftArray = (E[]) new Object[index + 1];
            E[] rightArray = (E[]) new Object[capacity - index + 1];
            E[] newArray = (E[]) new Object[capacity];
            System.arraycopy(array, 0, leftArray, 0, index);
            leftArray[index] = element;
            System.arraycopy(array, index, rightArray, 0, capacity - index);
            System.arraycopy(leftArray, 0, newArray, 0, index + 1);
            System.arraycopy(rightArray, 0, newArray, index + 1, capacity - (index + 1));
            array = newArray;
            size++;
        } else {
            if (index >= capacity) {
                throw new ArrayIndexOutOfBoundsException();
            }
            // TODO: надо переделать
            array[index] = element;
            size = index + 1;
        }
    }

    /**
     * Возвращение элемента массива по его индексу
     * @param index индекс возвращаемого элемента
     * @return возвращаемый элемент или null
     */
    @Override
    public E get(int index) {
        return array[index];
    }


    /**
     * Добавление либо замена элемента массива на указанном индексе
     * @param index индекс заменяемой ячейки массива
     * @param element новый элемент
     * @return новый элемент
     */
    @Override
    public E set(int index, E element) {
        isSorted = false;
        if (index >= capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = element;
        return element;
    }

    /**
     * Удаление элемента из указанной позиции со сдвигом следующих элементов на меньший индекс
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        E obj = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return obj;
    }

    /**
     *  Очищение ячеек массива от всех значений
     */
    @Override
    public void clear() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        array = (E[]) new Object[capacity];
        isSorted = false;
    }

    /**
     * Быстрая сортировка текущего динамического массива по указанному компаратору
     * @param comparator компаратор для определения порядка сортировки
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        if (comparator == null) {
            comparator = (E t1, E t2) -> ((Comparable<E>) t1).compareTo(t2);
        }
        array = ArrayList_ShamilAbdullin.quickSort(array, comparator);
        isSorted = true;
    }

    /**
     * Быстрая сортировка переданного массива по указанному компаратору
     * @param arr сортируемый массив
     * @param comparator компаратор для определения порядка сортировки
     * @return отсортированный массив
     * @param <T> generic тип
     */
    public static <T> T[] quickSort(T[] arr, Comparator<T> comparator) {
        if (arr.length < 2) {
            return arr; // 0 и 1 уже отсортированы
        }

        int center = arr.length / 2; // Центальный индекс. Берется в качестве опорного индекса
        T centerValue = arr[center]; // Опорное значение
        T[] leftArr = (T[]) new Object[arr.length]; // массивы берутся с запасом на случай плохого опорного значения
        T[] rightArr = (T[]) new Object[arr.length];

        int j = 0, k = 0; // Счетчики длинн массивов. По ним потом обрежем только нужные данные
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            }
            if (comparator.compare(arr[i], centerValue) < 0) {
                leftArr[j++] = arr[i];
            } else if (comparator.compare(arr[i], centerValue) > 0) {
                rightArr[k++] = arr[i];
            } else if (comparator.compare(arr[i], centerValue) == 0 && i != center) { // для поиска значений, совпадающим с центральным значением
                rightArr[k++] = arr[i];
            }
        }

        // Разделили, теперь Влавствуем.
        leftArr = quickSort(Arrays.copyOfRange(leftArr, 0, j), comparator);
        rightArr = quickSort(Arrays.copyOfRange(rightArr, 0, k), comparator);

        T[] sorted = (T[]) new Object[arr.length]; // Итоговый отсортированный массив
        System.arraycopy(leftArr, 0, sorted, 0, j);
        sorted[j] = centerValue;
        System.arraycopy(rightArr, 0, sorted, leftArr.length + 1, k);

        return sorted;
    }

    /**
     * Является ли массив отсортированным.
     * @return логическое значение, отсортирован или нет
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Урезание элементов массива от 0 и до указанного значения.
     * Без изменения емкости массива.
     * @param newSize значение, до которого будет урезан массив
     */
    @Override
    public void split(int newSize) {
        if (newSize >= size) {
            return;
        }

        for (int i = newSize; i < capacity; i++) {
            array[i] = null;
        }
        size = newSize;
    }

    /**
     * Строковое предстваление текущего объекта.
     * @return
     */
    @Override
    public String toString() {
        return "ArrayList_ShamilAbdullin{"
                + "size=" + size
                + ", capacity=" + capacity
                + ", array=" + Arrays.toString(array)
                + ", isSorted="
                + isSorted
                + '}';
    }
}
