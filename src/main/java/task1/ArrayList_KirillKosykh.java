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
        if ((double) this.shift / (double) this.array.length >= 0.75) {
            int[] biggerArray = new int[shift * 2];
            if (shift >= 0) System.arraycopy(this.array, 0, biggerArray, 0, shift);
            this.array = biggerArray;
        }
        if (this.array.length / this.shift >= 4) {
            int[] smallerArray = new int[shift * 2];
            if (shift >= 0) System.arraycopy(this.array, 0, smallerArray, 0, shift);
            this.array = smallerArray;
        }
    }

    /**
     * Получает размер массива
     */
    @Override
    public int size() {
        return this.shift;
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
        int count = this.shift;

        while (count > index) {
            this.array[count] = this.array[--count];
        }
        this.array[index] = element;

        this.shift++;

        resizeArray();
    }

    /**
     * Получает элемент по заданному индексу.
     *
     * @param index элемент для добавления
     */
    @Override
    public Integer get(int index) {
        if (index >= this.shift) throw new IllegalArgumentException();
        return this.array[index];
    }

    /**
     * Меняет значение элемента по заданному индексу.
     *
     * @param index   индекс в массиве, который будет отредактирован
     * @param element значение элемента
     */
    @Override
    public Integer set(int index, Integer element) {
        if (index >= this.shift) throw new IllegalArgumentException();
        return this.array[index];
    }

    /**
     * Удаляет элемент по заданному индексу и смещает остальные элементы.
     *
     * @param index индекс элемента для удаления в массиве
     */
    @Override
    public Integer remove(int index) {
        if (index >= this.shift || index < 0) throw new IllegalArgumentException();
        int answer = this.array[index];

        for (int i = shift; i > index; i--) {
            this.array[i - 1] = this.array[i];
        }
        this.shift--;

        resizeArray();

        return answer;
    }

    /**
     * Очищает массив, приводит его к дефолтным параметрам.
     */
    @Override
    public void clear() {
        this.shift = 0;
        this.array = new int[10];
    }

    /**
     * Производит быструю сортировку массива в зависимости от переданного comparator.
     * по дефолту массив сортируется по неубыванию
     *
     * @param comparator стратегия сортировки массива
     */
    @Override
    public void quickSort(Comparator<Integer> comparator) {
        Arrays.sort(this.array);
        if (comparator != Comparator.naturalOrder()) {
            int start = 0, end = shift - 1;
            while (start < end) {
                int temp = this.array[start];
                this.array[start] = this.array[end];
                this.array[end] = temp;
                start++;
                end--;
            }
        }
    }

    /**
     * Показывает отсортирован ли массив.
     * Сравниваем элементы попарно, при несоблюдении условия неубывания прерываем проход
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < this.shift; i++) {
            if (this.array[i] < this.array[i - 1]) return false;
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
        if (size > this.shift) throw new IllegalArgumentException();
        int[] splitArray = new int[size * 2];
        if (size >= 0) System.arraycopy(this.array, 0, splitArray, 0, size);
        this.array = splitArray;
    }
}
