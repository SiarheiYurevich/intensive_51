package task_2.collections.utils;

/**
 * Интерфейс, который определяет методы сортировки массивов объектов.
 *
 * @param <T> тип объектов, которые будут храниться в массиве.
 */
public interface Sorter<T> {

    /**
     * Сортирует весь массив arr в порядке возрастания.
     *
     * @param arr массив, который необходимо отсортировать.
     *
     * @throws NullPointerException если массив arr равен null.
     */
    void sort(T[] arr);

    /**
     * Сортирует часть массива arr в порядке возрастания от начала до toIndex включительно.
     *
     * @param arr массив, который необходимо отсортировать.
     * @param toIndex индекс конца сортируемого диапазона (исключительно).
     *
     * @throws NullPointerException если массив arr равен null.
     * @throws IndexOutOfBoundsException если toIndex выходит за
     * пределы границ массива (toIndex >= arr.length)
     */
    void sort(T[] arr, int toIndex);

    /**
     * Сортирует часть массива arr в указанном диапазоне в порядке возрастания.
     *
     * @param arr массив, который необходимо отсортировать.
     * @param fromIndex индекс начала сортируемого диапазона (включительно).
     * @param toIndex индекс конца сортируемого диапазона (исключительно).
     *
     * @throws NullPointerException если массив arr равен null.
     * @throws IndexOutOfBoundsException если стартовый и конечный индексы
     * выходят за пределы границ массива (fromIndex < 0 || toIndex >= arr.length).
     * @throws IllegalArgumentException если fromIndex > toIndex.
     */
    void sort(T[] arr, int fromIndex, int toIndex);
}
