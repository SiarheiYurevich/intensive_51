import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс ArrayList_MikhailShchechin реализация интерфейса IntensiveList с изменяемым размером массива.
 *
 * @param <E> – тип элементов в списке
 * @autor Mikhail
 */


public class ArrayList_MikhailShchechin<E> implements IntensiveList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList_MikhailShchechin(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity <= 0");
        }
    }

    public ArrayList_MikhailShchechin() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Возвращает количество элементов в списке.
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Вставляет элемент в конец списка.
     *
     * @param element - элемент, который будет сохранен в конце списка.
     */
    @Override
    public void add(E element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size++] = element;
    }

    /**
     * Вставляет элемент по индексу в список, при этом сдвигая в право все элементы начиная от вставляемого индекса.
     *
     * @param index   - индекс добавляемого элемента.
     * @param element - элемент, который будет сохранен по указанному индексу.
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elements.length) {
            increaseCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент в указанной позиции из списка.
     *
     * @param index - индекс возвращаемого элемента.
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    /**
     * Заменяет элемент по указанному индексу в списке на указанный элемент.
     *
     * @param index   - индекс элемента замены.
     * @param element - элемент, который будет сохранен по указанному индексу.
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E previousElement = (E) elements[index];
        elements[index] = element;
        return previousElement;
    }

    /**
     * Удаляет элемент по указанному индексу в списке, при этом сдвигает все последующие элементы влево.
     *
     * @param index - индекс элемента который будет удален.
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E removedElement = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Удаление всех элементов, после привидение к размеру по умолчанию.
     */
    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Быстрая сортировка списка.
     *
     * @param comparator - параметр определяющий порядок сортировки
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        qSort(comparator, 0, size - 1);
    }

    private void qSort(Comparator<E> comparator, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(comparator, left, right);
            qSort(comparator, left, pivotIndex - 1);
            qSort(comparator, pivotIndex + 1, right);
        }
    }

    private int partition(Comparator<E> comparator, int left, int right) {
        E pivot = (E) elements[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare((E) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, right);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = (E) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Проверяет, отсортирован ли список.
     *
     * @return
     */
    @Override
    public boolean isSorted() {
        for (int i = 0; i < size - 1; i++) {
            if (((Comparable) elements[i]).compareTo(elements[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Обрезает список до указанного размера.
     *
     * @param size - размер списка.
     * @throws IllegalArgumentException
     */
    @Override
    public void split(int size) {
        if (size < 0 || size > this.size) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        elements = java.util.Arrays.copyOf(elements, size);
    }

    /**
     * В случае необходимости увеличивает емкость списка
     */
    private void increaseCapacity() {
        int newCapacity = (elements.length * 2);
        elements = java.util.Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public String toString() {
        return "ArrayList_MikhailShchechin {" +
                "elements = " + Arrays.toString(elements) +
                ", size = " + size +
                '}';
    }
}
