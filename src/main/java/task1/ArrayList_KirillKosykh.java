package task1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Имплементация IntensiveList interface.
 * Этот класс позволяет сохранять и манипулировать элементами на базе массива int'ов.
 */
public class ArrayList_KirillKosykh implements IntensiveList<Integer> {

    private int shift;
    private int[] array;

    /**
     * Конструктор, инициализирующий массив размером 10.
     */
    public ArrayList_KirillKosykh() {
        this.shift = 0;
        this.array = new int[10];
    }

    /**
     * Конструктор, инициализирующий массив для заданного количества элементов.
     *
     * @param shift количество элементов
     */
    public ArrayList_KirillKosykh(int shift) {
        this.shift = shift;
        this.array = new int[shift * 2];
    }

    /**
     * Контроль за размером массива
     * если более 75% занято, то расширяем
     * усли меньше 25% занято, то уменьшаем
     */
    private void resizeArray() {
        if ((double) shift / (double) array.length >= 0.75) {
            int[] biggerArray = new int[shift * 2];
            if (shift >= 0) System.arraycopy(array, 0, biggerArray, 0, shift);
            array = biggerArray;
        }
        if (array.length / shift >= 4) {
            int[] smallerArray = new int[shift * 2];
            if (shift >= 0) System.arraycopy(array, 0, smallerArray, 0, shift);
            array = smallerArray;
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
     * @param element элемент для добавления
     */
    @Override
    public void add(Integer element) {
        array[shift++] = element;
        resizeArray();
    }

    /**
     * Добавляет элемент в заданный индекс, смещая остальные элементы.
     *
     * @param index   индекс в массиве куда добавляется элемент
     * @param element элемент для добавления
     */
    @Override
    public void add(int index, Integer element) {
        int count = shift;

        while (count > index) {
            array[count] = array[--count];
        }
        array[index] = element;

        shift++;

        resizeArray();
    }

    /**
     * Получает элемент по заданному индексу.
     *
     * @param index элемент для добавления
     */
    @Override
    public Integer get(int index) {
        if (index >= shift) throw new IllegalArgumentException();
        return array[index];
    }

    /**
     * Меняет значение элемента по заданному индексу.
     *
     * @param index   индекс в массиве, который будет отредактирован
     * @param element значение элемента
     */
    @Override
    public Integer set(int index, Integer element) {
        if (index >= shift || index < 0) throw new IllegalArgumentException();
        return array[index];
    }

    /**
     * Удаляет элемент по заданному индексу и смещает остальные элементы.
     *
     * @param index индекс элемента для удаления в массиве
     */
    @Override
    public Integer remove(int index) {
        if (index >= shift || index < 0) throw new IllegalArgumentException();
        int answer = array[index];

        for (int i = shift; i > index; i--) {
            array[i - 1] = array[i];
        }
        shift--;

        resizeArray();

        return answer;
    }

    /**
     * Очищает массив, приводит его к дефолтным параметрам.
     */
    @Override
    public void clear() {
        shift = 0;
        array = new int[10];
    }

    /**
     * Производит быструю сортировку массива в зависимости от переданного comparator.
     * по дефолту массив сортируется по неубыванию
     *
     * @param comparator стратегия сортировки массива
     */
    @Override
    public void quickSort(Comparator<Integer> comparator) {
        selfWrittenQuickSort(array, 0, shift);
        if (comparator != Comparator.naturalOrder()) {
            int start = 0, end = shift - 1;
            while (start < end) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
                start++;
                end--;
            }
        }
    }

    /**
     * Выполнение быстрой сортировки с помощью рекурсии
     *
     * @param array массив, который сортируем
     * @param left левая граница массива(подмассива)
     * @param right правая граница массива(подмассива)
     */
    private void selfWrittenQuickSort(int[] array, int left, int right) {
        if (array.length == 0 || left >= right) return;

        int mid = left + (right - left) / 2;
        int target = array[mid];

        int i = left, j = right;

        while (i <= j) {

            while (array[i] < target) i++;
            while (array[j] > target) j--;

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) selfWrittenQuickSort(array, left, j);
        if (right > i) selfWrittenQuickSort(array, i, right);
    }

    /**
     * Показывает отсортирован ли массив.
     * Сравниваем элементы попарно, при несоблюдении условия неубывания прерываем проход
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < shift; i++) {
            if (array[i] < array[i - 1]) return false;
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
        if (size > shift) throw new IllegalArgumentException();
        int[] splitArray = new int[size * 2];
        if (size >= 0) System.arraycopy(array, 0, splitArray, 0, size);
        array = splitArray;
    }
}
