package main.task1;
import java.util.*;
/**
 * Имплементация IntensiveList interface.
 * Этот класс позволяет сохранять и манипулировать элементами на базе динамического массива.
 * @author Гульназ Галиева
 */

public class ArrayList_GulnazGalieva<E> implements IntensiveList<E> {

    /** Дефолтный объем динамического массива */
    private static final int DEFAULT_CAPACITY = 10;

    /** Инициализируется массив с дефолтным объемом */
    private Object[] elementArray = new Object[DEFAULT_CAPACITY];

    /** Размер динамического массива */
    private int size;

    /** Объем динамического массива */
    private int capacity = DEFAULT_CAPACITY;




    /**
     * @ return возвращает размер массива.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет следующий элемент в динамический массив.
     *
     * @param element - элемент для добавления в динамический массив.
     */
    @Override
    public void add(E element) {

        if (size == capacity) {
            grow(elementArray);
        }
        elementArray[size] = element;
        size++;
    }

    /**
     * Добавляет элемент в динамический массив по индексу.
     *
     * @param index - индекс динамического массива для добавления элемента.
     * @param element - элемент для добавления в динамический массив.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит" +
                    " за пределами динамического массива: " + index);
        }
        if (size == capacity) {
            grow(elementArray);
        }
        moveToRight(index);
        elementArray[index] = element;
    }

    /**
     * Получает элемент из динамического массива по индексу.
     *
     * @param index - индекс динамического массива для добавления элемента.
     * @return возвращает элемент по заданному индексу.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит" +
                    " за пределами динамического массива: " + index);
        }
        return (E) elementArray[index];
    }

    /**
     * Обновляет элемент в динамическом массиве по индексу.
     *
     * @param index - индекс динамического массива для обновления элемента.
     * @param element - элемент для обновления элемента в динамическом массиве.
     * @return возвращает обновленный элемент по заданному индексу.
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит" +
                    " за пределами динамического массива: " + index);
        }
        elementArray[index] = element;
        return (E) elementArray[index];
    }

    /**
     * Удаляет элемент в динамическом массиве по индексу.
     *
     * @param index - индекс динамического массива для удаления элемента.
     * @return возвращает удаленный элемент по заданному индексу.
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит" +
                    " за пределами динамического массива: " + index);
        }
        Object buffer = elementArray[index];
        moveToLeft(index);
        return (E) buffer;
    }

    /**
     * Удаляет все элементы динамического массива и его объем приводит к дефолтному.
     */
    @Override
    public void clear() {
        elementArray = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Сортирует элементы динамического массива по алгоритму "Быстрая сортировка".
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSorted(0, size-1, comparator);

    }

    /**
     * Возвращает true/false в зависимости от того, что динамический массив отсортирован или нет.
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
           if(((Comparable<E>)elementArray[i-1]).compareTo((E)elementArray[i]) >0){
               return false;
           }
        }
        return true;
    }

    /**
     * Обрезает список до указанного размера.
     *
     * @param size  - задает новый размер динамическому массиву.
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size) {
            throw new RuntimeException("Заданный размер выходит " +
                    "за пределами размера динамического массива, " +
                    "текущий размер массива: " + this.size);
        }
        if (size == this.size) {
            capacity = size;
        } else {
            Object[] array = new Object[size];
            System.arraycopy(elementArray, 0, array, 0, array.length);
            elementArray = array;
            capacity = array.length;
            this.size = size;
        }
    }

    /**
     * Процедура увеличивает текущий объем динамического массива в 1,5 раза.
     */
    private void grow(Object[] array) {
        capacity = array.length + array.length / 2 + 1;
        elementArray = new Object[capacity];
        System.arraycopy(array, 0, elementArray, 0, array.length);
    }

    /**
     * Процедура сдвигает в право элементы динамического массива от элемента, находящегося
     * по заданному индексу.
     * @param index - индекс динамического массива для сдвига элементов в право.
     */
    private void moveToRight(int index) {
        System.arraycopy(elementArray, index, elementArray, index + 1, size - index);
        size++;
    }

    /**
     * Процедура сдвигает в лево элементы динамического массива.
     * @param index - индекс динамического массива для сдвига элементов в лево.
     */
    private void moveToLeft(int index) {
        System.arraycopy(elementArray, index + 1, elementArray, index, size - (index + 1));
        size--;
    }

    /**
     * Процедура рекурсивно сортирует динамичсеский массив.
     *
     * @param leftIndex - первый левый индекс динамического массива.
     * @param rightIndex - крайний правый индекс динамического массива.
     */
    private void quickSorted( int leftIndex, int rightIndex, Comparator<E> comparator) {
        //Выход из рекурсии
        if (size == 0 || leftIndex >= rightIndex) return;

        //1. Выбираем опорный элемент
        E pivot = (E) elementArray[(leftIndex + rightIndex) / 2];
        int leftMarkerIndex = leftIndex;
        int rightMarkerIndex = rightIndex;

        while (leftMarkerIndex <= rightMarkerIndex) {
            //2. Перекладываем все элементы влево или вправо от опрного элемента
            //Двигаем левый маркер слева направо, если элемент меньше, чем pivot
            while (comparator.compare((E) elementArray[leftMarkerIndex], pivot) < 0) leftMarkerIndex++;
            while (comparator.compare((E) elementArray[rightMarkerIndex],pivot) >0) rightMarkerIndex--;
            if(leftMarkerIndex<=rightMarkerIndex){
                E swap = (E) elementArray[leftMarkerIndex];
                elementArray[leftMarkerIndex] = elementArray[rightMarkerIndex];
                elementArray[rightMarkerIndex] = swap;
                leftMarkerIndex++;
                rightMarkerIndex--;
            }
        }

        //3. Рекурсия для сортировки левой и правой части
        if (leftIndex < rightMarkerIndex) quickSorted( leftIndex, rightMarkerIndex, comparator);
        if (rightIndex > leftMarkerIndex) quickSorted(leftMarkerIndex, rightIndex, comparator);
    }

    /**
     * Представление динамического массива в виде строки.
     */
    @Override
    public String toString() {
//        return  Arrays.toString(elementArray);
        return  to(elementArray);
    }

    private String to(Object[] array) {
        if (array == null)
            return "null";

        int iMax = this.size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append((array[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

}
