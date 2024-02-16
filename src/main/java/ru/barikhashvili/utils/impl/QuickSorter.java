package ru.barikhashvili.utils.impl;

import ru.barikhashvili.utils.Sorter;

import java.util.*;

import static ru.barikhashvili.utils.impl.NullableComparator.compare;

/**
 * Класс, который реализует интерфейс {@link Sorter} и предоставляет метод для
 * сортировки массива с помощью алгоритма быстрой сортировки (QuickSort).
 * <p>Средняя временная сложность данного алгоритма составляет O(n*log(n)), а худшая - O(n^2).
 * </p>
 * @param <T> тип объектов, которые будут храниться в массиве.
 */
public class QuickSorter<T> implements Sorter<T> {
    /**
     * Компаратор, используемый для сравнения элементов массива.
     */
    private Comparator<? super T> comparator;

    /**
     * Конструктор класса QuickSorter, который устанавливает
     * компаратор для сравнения элементов типа T.
     *
     * @param comparator компаратор, который определяет порядок
     * сортировки элементов типа T.
     *
     * @throws NullPointerException если comparator равен null.
     */
    public QuickSorter(Comparator<? super T> comparator) {
        setComparator(comparator);
    }

    /**
     * Устанавливает компаратор для сравнения элементов типа T.
     *
     * @param comparator компаратор, который определяет порядок
     * сортировки элементов типа T.
     *
     * @throws NullPointerException Если comparator является null.
     */
    public void setComparator(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "Comparator is null");
        this.comparator = comparator;
    }

    /**
     * Сортирует все элементы массива arr в порядке возрастания
     * с применением алгоритма быстрой сортировки.
     *
     * @param arr массив, который необходимо отсортировать.
     *
     * @throws NullPointerException если массив arr равен null.
     */
    @Override
    public void sort(T[] arr) {
        if (arr.length == 0) return;
        sort(arr, arr.length - 1);
    }

    /**
     * Сортирует часть массива arr в порядке возрастания от начала
     * до toIndex включительно с применением алгоритма быстрой сортировки.
     *
     * @param arr массив, который необходимо отсортировать.
     * @param toIndex индекс конца сортируемого диапазона (исключительно).
     *
     * @throws NullPointerException если массив arr равен null.
     * @throws IndexOutOfBoundsException если toIndex выходит за
     * пределы границ массива (toIndex >= arr.length)
     */
    @Override
    public void sort(T[] arr, int toIndex) {
        Objects.requireNonNull(arr, "Array is null");
        sort(arr, 0, toIndex);
    }

    /**
     * Сортирует часть массива arr в указанном диапазоне в порядке
     * возрастания с применением алгоритма быстрой сортировки.
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
    @Override
    public void sort(T[] arr, int fromIndex, int toIndex) {
        requireNonNullArrayAndCorrectIndexes(arr, fromIndex, toIndex);

        Deque<Pair<Integer>> ranges = new LinkedList<>();
        var startingRange = Pair.of(fromIndex, toIndex);
        ranges.push(startingRange);

        while (!ranges.isEmpty()) {
            var range = ranges.pop();
            if (range.isSecondGreaterThanFirst()
                    || range.isIdenticalPairOfObjects()) continue;

            var start = range.firstObject;
            var end = range.secondObject;

            if (end - start == 1 && compare(arr[start], arr[end], comparator) > 0) {
                var temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }

            int splitIndex = splitArrayByMidNumber(arr, start, end);
            var rangeWithSmallerElements = Pair.of(start, splitIndex - 1);
            var rangeWithGreaterElements = Pair.of(splitIndex + 1, end);

            ranges.push(rangeWithSmallerElements);
            ranges.push(rangeWithGreaterElements);
        }
    }

    /**
     * Класс, представляющий собой пару объектов, которые могут быть сравнены между собой.
     *
     * @param <T> Тип объектов, которые будут храниться в паре.
     * Должен быть наследником {@link Comparable} для обеспечения возможности сравнения.
     */
    public static class Pair<T extends Comparable<T>> {
        /**
         * Первый объект в паре.
         */
        private T firstObject;

        /**
         * Второй объект в паре.
         */
        private T secondObject;

        /**
         * Конструктор, создающий пару из двух объектов.
         *
         * @param firstObject первый объект.
         * @param secondObject второй объект.
         *
         * @throws NullPointerException если один из аргументов конструктора равен null.
         */
        Pair(T firstObject, T secondObject) {
            Objects.requireNonNull(firstObject, "Первый объект имеет значение null");
            Objects.requireNonNull(firstObject, "Второй объект имеет значение null");
            this.firstObject = firstObject;
            this.secondObject = secondObject;
        }

        /**
         * Статический метод для создания пары без явного вызова конструктора.
         *
         * @param firstObject первый объект.
         * @param secondObject второй объект.
         *
         * @return новая пара объектов.
         */
        private static <T extends Comparable<T>> Pair<T> of(T firstObject, T secondObject) {
            return new Pair<>(firstObject, secondObject);
        }

        /**
         * Проверяет, являются ли объекты в паре идентичными (с использованием метода equals).
         *
         * @return <i>true</i>, если объекты идентичны, <i>false</i> - в противном случае.
         */
        boolean isIdenticalPairOfObjects() {
            return firstObject.equals(secondObject);
        }

        /**
         * Проверяет, является ли второй объект больше первого (с использованием
         * метода {@link Comparable#compareTo}).
         *
         * @return <i>true</i>, если второй объект больше, <i>false</i> - в противном случае.
         */
        boolean isSecondGreaterThanFirst() {
            return firstObject.compareTo(secondObject) > 0;
        }

        /**
         * Возвращает первый объект в паре.
         *
         * @return Первый объект.
         */
        public T getFirstObject() {
            return firstObject;
        }

        /**
         * Устанавливает новый первый объект в паре.
         *
         * @param firstObject новый первый объект.
         *
         * @throws NullPointerException если firstObject равен null.
         */
        public void setFirstObject(T firstObject) {
            Objects.requireNonNull(firstObject);
            this.firstObject = firstObject;
        }

        /**
         * Возвращает второй объект в паре.
         *
         * @return Второй объект.
         */
        public T getSecondObject() {
            return secondObject;
        }

        /**
         * Устанавливает новый второй объект в паре.
         *
         * @param secondObject новый второй объект.
         *
         * @throws NullPointerException если secondObject is null.
         */
        public void setSecondObject(T secondObject) {
            Objects.requireNonNull(secondObject);
            this.secondObject = secondObject;
        }
    }

    /**
     * Находит средний элемент в указанном диапазоне массива
     * и меняет его местами с последним элементом диапазона.
     *
     * @param arr массив для поиска среднего элемента.
     * @param fromIndex индекс начала рабочего диапазона (включительно).
     * @param toIndex индекс конца рабочего диапазона (исключительно).
     *
     * @return Средний элемент массива, который был перемещен в конец диапазона.
     *
     * @throws NullPointerException если arr является null.
     * @throws IndexOutOfBoundsException если стартовый и конечный индексы
     * выходят за пределы границ массива (fromIndex < 0 || toIndex >= arr.length).
     * @throws IllegalArgumentException если fromIndex > toIndex.
     */
    private T findMidAndSwapWithLast(T[] arr, int fromIndex, int toIndex) {
        requireNonNullArrayAndCorrectIndexes(arr, fromIndex, toIndex);
        int mid = (fromIndex + toIndex) / 2;
        var temp = arr[mid];
        arr[mid] = arr[toIndex];
        arr[toIndex] = temp;
        return temp;
    }

    /**
     * Разделяет элементы массива на два подмассива, используя элемент
     * в середине как точку разделения (опорную точку):
     * <ul>
     *      <li>Элементы больше опорного перемещаются вправо.</li>
     *      <li>Элементы меньше опорного перемещаются влево.</li>
     * </ul>
     *
     * @param arr массив для разделения.
     * @param fromIndex индекс начала диапазона разделения (включительно).
     * @param toIndex индекс конца диапазона разделения (исключительно).
     *
     * @return Новый индекс среднего (опорного) элемента после разделения.
     *
     * @throws NullPointerException если arr является null.
     * @throws IndexOutOfBoundsException если стартовый и конечный индексы
     * выходят за пределы границ массива (fromIndex < 0 || toIndex >= arr.length).
     * @throws IllegalArgumentException если fromIndex > toIndex.
     */
    private int splitArrayByMidNumber(T[] arr, int fromIndex, int toIndex) {
        requireNonNullArrayAndCorrectIndexes(arr, fromIndex, toIndex);
        var mid = findMidAndSwapWithLast(arr, fromIndex, toIndex);
        var midPos = fromIndex - 1;

        for (int i = fromIndex; i <= toIndex; i++) {
            if (compare(arr[i], mid, comparator) < 1) {
                midPos++;

                var tempElement = arr[midPos];
                arr[midPos] = arr[i];
                arr[i] = tempElement;
            }
        }

        return midPos;
    }

    /**
     * Проверяет, что переданный массив строк не является null,
     * а также что заданные индексы fromIndex и toIndex находятся в пределах
     * границ массива и следуют порядку слева направо (fromIndex <= toIndex).
     *
     * @param arr массив элементов для проверки.
     * @param fromIndex индекс начала рабочего диапазона (включительно).
     * @param toIndex индекс конца рабочего диапазона (исключительно).
     *
     * @throws NullPointerException если arr является null.
     * @throws IndexOutOfBoundsException если стартовый и конечный индексы
     * выходят за пределы границ массива (fromIndex < 0 || toIndex >= arr.length).
     * @throws IllegalArgumentException если fromIndex > toIndex.
     */
    private void requireNonNullArrayAndCorrectIndexes(T[] arr, int fromIndex, int toIndex) {
        Objects.requireNonNull(arr, "Array is null");
        if (fromIndex < 0 || toIndex >= arr.length) {
            throw new IndexOutOfBoundsException(
                    String.format("Incorrect indexes: %d, %d", fromIndex, toIndex)
            );
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    String.format("Start index is greater than end index: %d, %d", fromIndex, toIndex)
            );
        }
    }
}
