package ru.barikhashvili.collections.impl;

import ru.barikhashvili.collections.IntensiveList;
import ru.barikhashvili.utils.impl.NullableComparator;
import ru.barikhashvili.utils.impl.QuickSorter;

import java.util.*;

/**
 * Класс, реализующий интерфейс {@link IntensiveList} и использующий массив
 * для хранения элементов. Массив динамически изменяет свой размер в 1.5 раза,
 * когда процент заполнения превышает порог в 75%.
 *
 * @param <E> тип элементов в списке.
 */
public class ArrayList_IrakliBarikhashvili<E> implements IntensiveList<E> {
    /**
     * Массив для хранения элементов списка.
     */
    private Object[] elements;
    /**
     * Количество элементов в списке.
     */
    private int size = 0;
    /**
     * Процент заполнения массива, при котором его размер увеличивается.
     */
    private static final int FILLING_PERCENTAGE_TO_INCREASE_ARRAY = 75;
    /**
     * Начальная емкость массива elements по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Коэффициент увеличения ёмкости массива при расширении.
     */
    private static final double MAGNIFICATION_FACTOR = 1.5;

    /**
     * Создает пустой список со стандартной ёмкостью.
     */
    public ArrayList_IrakliBarikhashvili() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает список, содержащий элементы из указанной коллекции. В случае, если
     * коллекция не содержит ни одного элемента, создается пустой список с
     * ёмкостью по умолчанию.
     *
     * @param collection коллекция, элементы которой будут помещены в этот список.
     *
     * @throws NullPointerException если collection равен null.
     */
    public ArrayList_IrakliBarikhashvili(Collection<? extends E> collection) {
        Objects.requireNonNull(collection, "Collection is null");
        if (collection.isEmpty()) {
            elements = new Object[DEFAULT_CAPACITY];
        } else {
            elements = collection.toArray();
            size = collection.size();
        }
    }

    /**
     * Создает новый список с указанной емкостью. В случае, если указанная
     * ёмкость равна нулю, то создаётся список с ёмкостью по умолчанию.
     *
     * @param capacity начальная емкость списка.
     *
     * @throws IllegalArgumentException если емкость отрицательна (capacity < 0).
     */
    public ArrayList_IrakliBarikhashvili(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid capacity: " + capacity);
        }
        elements = new Object[capacity == 0 ? DEFAULT_CAPACITY : capacity];
    }

    /**
     * Возвращает количество элементов в этом списке.
     *
     * @return количество элементов в этом списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет указанный элемент в конец этого списка.
     * <p>Если массив, хранящий элементы списка, заполнен более чем на
     * <i>FILLING_PERCENTAGE_TO_INCREASE_ARRAY</i> процентов, создается новый массив
     * для хранения элементов списка с ёмкостью, увеличенной в <i>MAGNIFICATION_FACTOR</i>
     * раз, после чего все элементы из старого массива копируются в новый.</p>
     *
     * @param element добавляемый элемент.
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Добавляет элемент в указанную позицию в списке. Сдвигает все элементы,
     * начиная с указанной позиции, на одну позицию вправо, чтобы освободить
     * место для нового элемента. В освободившееся место добавляется
     * переданный элемент.
     *
     * <p>Если массив, хранящий элементы списка, заполнен более чем на
     * <i>FILLING_PERCENTAGE_TO_INCREASE_ARRAY</i> процентов, создается новый массив
     * для хранения элементов списка с ёмкостью, увеличенной в <i>MAGNIFICATION_FACTOR</i>
     * раз, после чего все элементы из старого массива копируются в новый.</p>
     *
     * @param index позиция, в которую нужно добавить элемент.
     * @param element элемент, который нужно добавить в список.
     *
     * @throws IndexOutOfBoundsException если индекс выходит за пределы границ списка (index > size || index < 0)
     */
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index to insert element: " + index);
        }

        if (isNotEnoughSpace()) increaseArrayCapacity();
        shiftRightByIndex(index);

        elements[index] = element;
        size++;
    }

    /**
     * Проверяет, достаточно ли места в массиве для добавления нового элемента.

     * @return <b><i>true</i></b>, если массив заполнен на FILLING_PERCENTAGE_TO_INCREASE_ARRAY
     * процентов и более, в противном случае - <b><i>false</i></b>.
     */
    private boolean isNotEnoughSpace() {
        int arrayFillingPercentage = (size + 1) * 100 / elements.length;
        return arrayFillingPercentage >= FILLING_PERCENTAGE_TO_INCREASE_ARRAY;
    }

    /**
     * Сдвигает элементы массива, начиная с указанного индекса, на одну позицию вправо.
     * Этот метод используется для освобождения места для вставки нового элемента в список.
     *
     * @param index индекс элемента, с которого начинается сдвиг.
     *
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     * списка (index > size || index < 0).
     */
    private void shiftRightByIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index to shift element: " + index);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
    }

    /**
     * Создает новый массив с увеличенной емкостью и
     * копирует в него элементы из старого массива.
     * Этот метод используется для расширения емкости списка,
     * когда в старом массиве не хватает места для добавления новых элементов.
     */
    private void increaseArrayCapacity() {
        int newArrayLength = (int) Math.ceil(elements.length * MAGNIFICATION_FACTOR);
        Object[] newArray = new Object[newArrayLength];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    /**
     * Возвращает элемент из указанной позиции списка.
     *
     * @param index индекс элемента, который нужно получить.
     *
     * @return элемент списка из указанной позиции
     *
     * @throws IndexOutOfBoundsException если индекс выходит
     * за пределы границ списка (index < 0 || index >= size).
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index to get element: " + index);
        }
        return (E) elements[index];
    }

    /**
     * Заменяет элемент из указанной позиции списка на указанный элемент.
     *
     * @param index индекс элемента, который нужно заменить.
     * @param element элемент, который нужно вставить в список.
     *
     * @return предыдущий элемент из указанной позиции.
     *
     * @throws IndexOutOfBoundsException если индекс выходит за пределы
     * границ списка (index < 0 || index >= size).
     */
    @Override
    public E set(int index, E element) {
        E prevValue = get(index);
        elements[index] = element;
        return prevValue;
    }

    /**
     * Удаляет с возвращением элемент с указанной позиции из списка.
     *
     * @param index индекс элемента, который нужно удалить.
     *
     * @return удаленный элемент.
     *
     * @throws IndexOutOfBoundsException если индекс удаляемого элемента
     * выходит за пределы списка (index < 0 || index >= size).
     */
    @Override
    public E remove(int index) {
        E elementToBeRemoved = get(index);
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        elements[size] = null;
        return elementToBeRemoved;
    }

    /**
     * Удаляет все элементы списка. Емкость списка приводится к <i>DEFAULT_CAPACITY</i>
     */
    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Сортирует этот список в порядке возрастания элементов
     * с помощью алгоритма быстрой сортировки (QuickSort).
     *
     * <p>Метод допускает сравнение <i>null объектов</i>. В таком случае при сравнении
     * таких объектов с <i>non-null объектами</i> большим оказывает тот объект,
     * который принимает значение null.</p>
     *
     * @param comparator компаратор, который определяет порядок сортировки элементов.
     *
     * @see QuickSorter#sort(Object[], int, int)
     * @see NullableComparator
     */
    @Override
    @SuppressWarnings("unchecked")
    public void quickSort(Comparator<E> comparator) {
        if (size <= 1) return;
        QuickSorter<E> sorter = new QuickSorter<>(comparator);
        sorter.sort((E[]) elements, size - 1);
    }

    /**
     * Проверяет, отсортирован ли список в порядке возрастания
     * с использованием естественного порядка элементов.
     *
     * <p>Метод допускает сравнение <i>nullable объектов</i> за счёт
     * использования {@link NullableComparator#compare(Comparable, Object)}</p>
     *
     * <p>В случае, если элемент не реализует {@link Comparable}, используется
     * {@link Comparator}, сравнивающий объекты по {@link Object#hashCode()},
     * и работа метода делегируется методу
     * {@link ArrayList_IrakliBarikhashvili#isSorted(Comparator)}.</p>
     *
     * @return <b><i>true</i></b>, если список отсортирован, в противном случае - <b><i>false</i></b>.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean isSorted() {
        if (size < 2) return true;
        if (elements[0] instanceof Comparable) {
            for (int i = 1; i < size; i++) {
                var prev = (Comparable<E>) elements[i - 1];
                var curr = (E) elements[i];
                if (NullableComparator.compare(prev, curr) > 0) {
                    return false;
                }
            }
        } else {
            Comparator<E> hashComparator = Comparator.comparingInt(Object::hashCode);
            return isSorted(hashComparator);
        }

        return true;
    }

    /**
     * Проверяет, отсортирован ли список в порядке возрастания
     * с использованием естественного порядка элементов.
     *
     * <p>Метод допускает сравнение <i>nullable объектов</i> за счёт
     * использования {@link NullableComparator#compare(Object, Object, Comparator)}.</p>
     *
     * @param comparator компаратор, используемый для определения порядка элементов.
     *
     * @return <b><i>true</i></b>, если список отсортирован, в противном случае - <b><i>false</i></b>.
     *
     * @throws NullPointerException если comparator равен null.
     */
    @SuppressWarnings("unchecked")
    public boolean isSorted(Comparator<? super E> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null");
        }
        for (int i = 1; i < size; i++) {
            var prev = (E) elements[i - 1];
            var curr = (E) elements[i];
            if (NullableComparator.compare(prev, curr, comparator) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Обрезает список до указанного размера,
     * оставляя только первые <i>size</i> элементов.
     * <p>При size == 0 происходит очистка массива, его емкость сбрасывается
     * до стандартной (DEFAULT_CAPACITY).</p>
     *
     * @param size количество элементов, которые надо
     * сохранить, начиная с самого первого.
     *
     * @throws IndexOutOfBoundsException если новый размер списка
     * выходит за его границы (size > this.size || size < 0).
     */
    @Override
    public void split(int size) {
        if (size > this.size || size < 0) {
            throw new IndexOutOfBoundsException("Size is incorrect: " + size);
        }

        if (size == 0) {
            clear();
        } else {
            elements = Arrays.copyOf(elements, size);
            this.size = size;
        }
    }

    /**
     * Возвращает строковое представление списка в формате
     * <b><i>[элемент1, элемент2, ..., элементN]</i></b>, где каждый элемент
     * отображается в виде его строкового представления.
     *
     * <p>Если элемент является ссылкой на сам список, то он отображается
     * как <b><i>thisList</i></b>, чтобы избежать бесконечной рекурсии.</p>
     *
     * @return Строковое представление списка.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i] == this ? "thisList" : elements[i]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}