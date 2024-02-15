package ivan.prh;

import java.util.Comparator;
/**
 * Класс является имплементацией интерфейса IntensiveList<E>, предоставляющей возможность создания списка
 * элементов типа E, на основе массива.
 * @author Иван Приходько
 */
public class ArrayList_IvanPrikhodko<E> implements IntensiveList<E> {
    private final int CAPACITY = 10;
    private E[] arr = (E[]) new Object[CAPACITY];
    private int size = 0;
    private boolean isSorted = true;

    public ArrayList_IvanPrikhodko() {
    }

    /**
     * Конструктор проинимающий массив элементов типа E для заполнения списка.
     * @param arr массив, задающий значения для элементов списка.
     */
    public ArrayList_IvanPrikhodko(E[] arr) {
        this.arr = arr.clone();
        size = arr.length;
        if(arr.length > 1)
            isSorted = false;
    }

    /**
     * @return Количество элементов списка.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка.
     * @param element переменная типа E добавляемая в конец списка.
     */
    @Override
    public void add(E element) {
        expansion();
        arr[size - 1] = element;
        isSorted = size == 1;
    }


    /**
     * Добавляет элемент списка на указанную позицию.
     * @param index позиция нового элемента списка.
     * @param element переменная типа E добавляемая в список.
     */
    @Override
    public void add(int index, E element) {
        expansion();
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = element;
    }

    /**
     * @param index номер элемента списка.
     * @return элемент списка.
     */
    @Override
    public E get(int index) {
        return arr[index];
    }
    /**
     * Изменяет значение элемента списка.
     * @param index номер элемента списка, на место которого будет вставлено значение.
     * @param element переменная типа E добавляемая в список.
     */
    @Override
    public E set(int index, E element) {
        arr[index] = element;
        return arr[index];
    }

    /**
     * Удаляет элемент списка с указанным номером.
     * @param index номер элемента списка.
     * @return Удаленный элемент списка.
     */
    @Override
    public E remove(int index) {
        E deleteElement = arr[index];
        if(index == size - 1) {
            E[] newArr = (E[]) new Object[arr.length];
            if (arr.length != 0)
                System.arraycopy(arr, 0, newArr, 0, size - 1);
            arr = newArr;
        } else {
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        }
        size--;
        return deleteElement;
    }

    /**
     * Удаляет все элементы и приводит размер массива к значению по умолчанию.
     */
    @Override
    public void clear() {
        arr = (E[]) new Object[CAPACITY];
        size = 0;
    }

    /**
     * Выполняет быструю сортировку списка.
     * @param comparator объект типа Comparator<E>, используемый для сравнения элементов списка.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        if (size == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1, comparator);
        isSorted = true;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Урезает список до указанного размера.
     * @param size размер урезанного списка.
     */
    @Override
    public void split(int size) {
        E[] newArr = (E[]) new Object[arr.length];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    /**
     * Проверка на наличие свободных ячеек в массиве.
     */
    private void expansion() {
        size++;
        if(arr.length < size) {
            var arrLength = size + size/2;
            E[] newArr = (E[]) new Object[arrLength];
            if(arr.length != 0)
                System.arraycopy(arr, 0, newArr, 0, size - 1);
            arr = newArr;
        }
    }

    /**
     * Рекурсивный метод сортировки массива.
     * @param arr массив элементов тип E.
     * @param low нижний индекс.
     * @param high верхний индекс.
     * @param comparator объект типа Comparator<E>, используемый для сравнения элементов массива.
     */
    private void quickSort(E[] arr, int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int pi = partition(arr, low, high, comparator);

            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);
        }
    }


    /**
     * Определяет положение опорного элемента и переставляет элементы массива.
     * @param arr массив элементов тип E.
     * @param low нижний индекс.
     * @param high верхний индекс.
     * @param comparator объект типа Comparator<E>, используемый для сравнения элементов массива.
     * @return индекс опорного элемента
     */
    private int partition(E[] arr, int low, int high, Comparator<E> comparator) {
        E pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            int compared = comparator.compare(arr[j], pivot);
            if (compared < 0) {
                i++;


                E temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }


        E temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;


        return i + 1;
    }
}
