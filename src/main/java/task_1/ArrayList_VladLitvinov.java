package task_1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс ArrayList_NameSurname представляет собой реализацию интерфейса IntensiveList для хранения элементов типа E.
 * Внутреннее представление данных основано на динамическом массиве.
 */

public class ArrayList_VladLitvinov<E> implements IntensiveList<E> {
    private Object[] array;
    private int size;
    private int capacity = 10;
    private final double LOAD_FACTOR = 0.75;
    /**
     * Конструктор класса. Создает список ArrayList_NameSurname с начальной емкостью DEFAULT_CAPACITY.
     */
    public ArrayList_VladLitvinov() {
        array = new Object[capacity];
        size = 0;
    }
    /**
     * Конструктор класса. Создает список ArrayList_NameSurname с заданной емкостью.
     */
    public ArrayList_VladLitvinov(int capacity) {
        array = new Object[capacity];
        size = 0;
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return размер списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    @Override
    public void add(E element) {
        add(size, element);
    }
    /**
     * Добавляет элемент в указанную позицию списка.
     * Если внутренний массив недостаточно вместителен, его емкость увеличивается.
     * Все элементы, находящиеся справа от указанной позиции, сдвигаются вправо.
     *
     * @param index   позиция для вставки элемента
     * @param element элемент для добавления
     * @throws IndexOutOfBoundsException если указанная позиция выходит за границы списка
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        size++;
        capacityChange();
        arrayLengthChange(index);
        array[index] = element;
    }
    /**
     * Убеждается, что внутренний массив имеет достаточную емкость для хранения указанного количества элементов.
     * Если емкости недостаточно, размер массива увеличивается.
     */
    private void capacityChange() {
        if (LOAD_FACTOR < (double) size / capacity) {
            capacity = (int) (capacity * 1.5);
        }
    }

    private void arrayLengthChange(int index) {
        System.arraycopy(array, index, array, index + 1, size - index);
    }

    /**
     * Возвращает элемент, находящийся в указанной позиции списка.
     *
     * @param index позиция элемента
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если указанная позиция выходит за границы списка
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (E) array[index];
    }

    /**
     * Заменяет элемент, находящийся в указанной позиции списка, новым элементом и возвращает старый элемент.
     *
     * @param index   позиция элемента для замены
     * @param element новый элемент
     * @return старый элемент
     * @throws IndexOutOfBoundsException если указанная позиция выходит за границы списка
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        E oldElement = (E) array[index];
        array[index] = element;
        return oldElement;
    }

    /**
     * Удаляет элемент по указанному индексу.
     * Следующие элементы смещаются на одну позицию к началу списка.
     *
     * @param index индекс элемента для удаления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     * @return старый эелмент
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        size--;
       Object o = array[index];
       System.arraycopy(array, index + 1, array, index, size - index);
        return (E) o;
    }

    /**
     * Создаёт новый массив с дефолтной емкостью
     */

    @Override
    public void clear() {
        size = 0;
        capacity = 10;
        array = new Object[capacity];
    }

    /**
     * Сортирует список в соответствии с заданным компаратором, используя алгоритм быстрой сортировки.
     *
     * @param comparator компаратор для сравнения элементов списка
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }
    /**
     * Рекурсивно выполнить быструю сортировку элементов списка в указанном диапазоне индексов.
     *
     * @param fromIndex начальный индекс диапазона сортировки
     * @param toIndex   конечный индекс диапазона сортировки
     * @param comparator компаратор для сравнения элементов списка
     */
    private void quickSort(int fromIndex, int toIndex, Comparator<E> comparator) {
        if (fromIndex < toIndex) {
            int pivotIndex = partition(fromIndex, toIndex, comparator);
            quickSort(fromIndex, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, toIndex, comparator);
        }
    }

    /**
     * Выполняет разделение элементов списка в указанном диапазоне индексов с использованием заданного компаратора.
     * Возвращает индекс опорного элемента после разделения.
     *
     * @param fromIndex начальный индекс диапазона разделения
     * @param toIndex   конечный индекс диапазона разделения
     * @param comparator компаратор для сравнения элементов списка
     * @return индекс опорного элемента после разделения
     */
    @SuppressWarnings(value = "unchecked")
    private int partition(int fromIndex, int toIndex, Comparator<E> comparator) {
        E pivotElement = (E) array[toIndex];
        int i = fromIndex - 1;
        for (int j = fromIndex; j < toIndex; j++) {
            if (comparator.compare((E) array[j], pivotElement) <= 0) {
                i++;
                swapElements(i, j);
            }
        }
        swapElements(i + 1, toIndex);
        return i + 1;
    }

    /**
     * Обменивает элементы списка с указанными индексами.
     *
     * @param index1 индекс первого элемента для обмена
     * @param index2 индекс второго элемента для обмена
     */
    private void swapElements(int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Проверяет, отсортирован ли список в порядке возрастания с использованием естественного порядка элементов.
     *
     * @return true, если список отсортирован; false в противном случае
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean isSorted() {
        for (int i = 0; i < size - 1; i++) {
            if (((Comparable<E>) array[i]).compareTo((E) array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    /**
     * Разделяет список на две части, указанного размера. Оригинальный список будет изменен.
     *
     * @param size размер первой части списка
     * @throws IllegalArgumentException Если указанный размер недопустим (отрицательный или больше текущего размера списка)
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size)
            throw new IllegalArgumentException("Invalid size: " + size);

        this.size = size;
        Arrays.fill(array, size, this.size, null);
    }
}
