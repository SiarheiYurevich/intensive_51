package com.aston.tasks.task_1;

import java.util.Comparator;

/**
 * Реализация динамического массива (аналог ArrayList, не потокобезопасный)
 *
 * @param <E> - параметр обобщегого типа.
 */

public class ArrayListIgnatevAndrey<E> implements IntensiveList<E> {

    /**
     * Начальное значение элементов в коллекции.
     */
    private int size = 0;
    /**
     * Дефолтная емкость коллекции.
     */
    private int capacity = 16;
    /**
     * Массив обобщенного типа.
     */
    private E[] arrayElements;
    /**
     * Поле определяет - отсортирована ли коллекция.
     */
    private boolean isSorted = false;

    /**
     * Конструктор с параметрами
     *
     * @param capacity - Данный парамет определяет емкость коллекции.
     */

    public ArrayListIgnatevAndrey(int capacity) {
        this.capacity = capacity;
        this.arrayElements = (E[]) new Object[capacity];
    }

    /**
     * Дефолтный конструктор без параметров.
     */
    public ArrayListIgnatevAndrey() {
        this.arrayElements = (E[]) new Object[this.capacity];
    }

    /**
     * Внутренний get метод для privet поля isSorted.
     *
     * @return - возвращает значение поля isSorted.
     */
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Внутренний set метод для privet поля isSorted.
     *
     * @return - меняет значение поля isSorted.
     */
    private void setSorted(boolean sorted) {
        isSorted = sorted;
    }

    /**
     * Внутренний get метод для privet поля size.
     *
     * @return - возвращает значение поля size.
     */
    private int getSize() {
        return size;
    }


    /**
     * Число элементов, которое содержится в коллекции.
     *
     * @return - величиеа int , которое определяет число элементов в коллекции.
     */
    public int size() {
        return getSize();
    }

    /**
     * Добавление нового элемента в конец коллекции
     *
     * @param element
     */
    public void add(E element) {

        if (this.size < this.capacity) {
            arrayElements[size] = element;
            this.size++;
        } else {
            this.capacity += 16;
            E[] arrayElementsNew = (E[]) new Object[this.capacity];
            System.arraycopy(arrayElements, 0, arrayElementsNew, 0, arrayElements.length);
            arrayElements = arrayElementsNew;
            arrayElements[size] = element;
            this.size++;
            setSorted(false);
        }
    }

    /**
     * Добавление элемента в середину коллекции, по индексу.
     *
     * @param index   - индекс определяющий ячейку масива,
     *                куда будет положен новый элемент.
     * @param element - Новый элемент коллекции который будет добавлен.
     */
    public void add(int index, E element) {
        if (this.size < this.capacity) {
            int i = index;
            E bufferValueCurrent = arrayElements[i];
            E bufferValueNext;
            while (i < arrayElements.length - 1) {
                bufferValueNext = arrayElements[i + 1];
                arrayElements[i + 1] = bufferValueCurrent;
                bufferValueCurrent = bufferValueNext;
                i++;
            }
            arrayElements[index] = element;
            this.size++;
            setSorted(false);
        } else {
            this.capacity += 16;
            E[] arrayElementsNew = (E[]) new Object[this.capacity];
            System.arraycopy(arrayElements, 0, arrayElementsNew, 0, arrayElements.length);
            arrayElements = arrayElementsNew;

            int i = index;
            E bufferValueCurrent = arrayElements[i];
            E bufferValueNext;
            while (i < arrayElements.length - 1) {
                bufferValueNext = arrayElements[i + 1];
                arrayElements[i + 1] = bufferValueCurrent;
                bufferValueCurrent = bufferValueNext;
                i++;
            }
            arrayElements[index] = element;
            this.size++;
            setSorted(false);
        }
    }

    /**
     * Получение элемента по индексу
     *
     * @param index - Индекс ячейки массива, по которуму получаем элемент.
     * @return - Сам элемент коллекции получаемый по значению индекса.
     */

    public E get(int index) {
        return arrayElements[index];
    }

    /**
     * Заменяем существующий элемент по индексу на новый.
     *
     * @param index   - параметр определяющий индекс в
     *                массиве заменяемого элемента.
     * @param element - Новый элемент который будет хранится в массиве.
     * @return - Старый элемент который будет заменён.
     */
    public E set(int index, E element) {
        E currentElement = arrayElements[index];
        arrayElements[index] = element;
        return currentElement;
    }

    /**
     * Удаление элемента коллекции по индексу
     *
     * @param index - данный параметр обозначает индекс
     *              в массиве удаляемого элемента.
     * @return - возвращаем удаляемый объект типа Е
     */
    public E remove(int index) {
        int i = index;
        E currentValue = arrayElements[index];
        while (i < arrayElements.length - 1) {
            arrayElements[i] = arrayElements[i + 1];
            i++;
        }
        this.size--;
        return currentValue;
    }

    /**
     * Удаляем все элементы коллекции, capacity приводим к дефолтному.
     */
    public void clear() {
        arrayElements = (E[]) new Object[capacity];
    }

    /**
     * Метод вызывающий алгоритмбыстрой сортировки по возрастанию.
     *
     * @param comparator - в качестве параметра ожидаем класс с реализацией
     *                   интерфейса Comparator, а именно переопределенный метод compare().
     *                   Через который и будут сравниваться элементы массива E[] arrayElements.
     */
    public void quickSort(Comparator<E> comparator) {
        quickSortOut(arrayElements, 0, size() - 1, comparator);
        setSorted(true);
    }

    /**
     * Алгоритм быстрой сортировки
     *
     * @param sortArr    - Массив для сортировки E[] arrayElements.
     * @param low        - Всегда значение первого элемента массива равно 0.
     * @param high       - Верхней границей сортировки является максимальное число элементов.
     *                   в массиве отличных от null и соотвествующих последнему индексу массива size() - 1.
     * @param comparator - в качестве параметра ожидаем класс с реализацией.
     *                   интерфейса Comparator, а именно переопределенный метод compare().
     */
    private void quickSortOut(E[] sortArr, int low, int high, Comparator<E> comparator) {
        //завершить,если массив пуст или уже нечего делить
        if (sortArr.length == 0 || low >= high) return;

        //выбираем опорный элемент
        int middle = low + (high - low) / 2;
        E border = sortArr[middle];

        //разделияем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(sortArr[i], border) < 0)
                i++;
            while (comparator.compare(sortArr[j], border) > 0)
                j--;
            if (i <= j) {
                E swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                i++;
                j--;
            }
        }
        //рекурсия для сортировки левой и правой части
        if (low < j) quickSortOut(sortArr, low, j, comparator);
        if (high > i) quickSortOut(sortArr, i, high, comparator);
    }

    /**
     * Обрезаем коллекцию до указанного размера size
     *
     * @param size - параметр определяющий границу среза коллекции.
     */
    public void split(int size) {
        E[] arrayElementsNew = (E[]) new Object[size];
        System.arraycopy(arrayElements, 0, arrayElementsNew, 0, size);
        arrayElements = arrayElementsNew;
        this.size = size;
    }

}


