package collection;

import repository.IntensiveList;

import java.util.Comparator;
import java.util.Objects;


/**
 * Класс реализующий интерфейс IntensiveList, предоставляющий лист на основе массива элементов E
 * @author Кузнецов Дмитрий Юрьевич
 */
public class ArrayList_DmitryKuznetsov<E> implements IntensiveList<E> {

    private int capacity = 10;

    private int size = 0;

    private boolean isSorted = false;

    private Object[] myList = new Object[capacity];

    /**
     * @return Количество элементов списка.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод добавляющий элемент в конец списка
     * @param element переменная типа E - добавляемый в конец списка элемент
     */
    @Override
    public void add(E element) {
        try {
            if (capacity <= size) {
                capacityAddExtension(element, myList);
            } else {
                myList[size] = element;
            }
            size++;
            isSorted = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод добавляющий элемент в список на указанное место (индекс), смещая остальные элементы вправло на один индекс
     * @param index переменная типа int - индекс на место которого будет вставлен элемент
     * @param element переменная типа E - добавляемый элемент
     */
    @Override
    public void add(int index, E element) {
        try {
            if (index < 0 || index >= capacity) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            capacityAddExtension(element, myList, index);
            size++;
            isSorted = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return элемент списка по индексу index
     * @param index переменная типа int - индекс элемента который необходимо получить из листа
     */
    @Override
    public E get(int index) {
        try {
            if (index < 0 || index >= capacity) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            return (E) myList[index];
        } catch (ClassCastException classCastException) {
            classCastException.printStackTrace();
            return null;
        }
    }

    /**
     * Метод заменяет значение элемента в списке
     * @param index переменная типа int - индекс где будет заменено значение
     * @param element переменная типа E - добавляемый элемент (значение)
     */
    @Override
    public E set(int index, E element) {
        try {
            if (index < 0 || index >= capacity) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            myList[index] = element;
            isSorted = false;
            return (E) myList[index];
        } catch (ClassCastException classCastException) {
            classCastException.printStackTrace();
            return null;
        }
    }

    /**
     * Метод удаляет  элемент из списка
     * @param index переменная типа int - индекс удаляемого элемента
     */
    @Override
    public E remove(int index) {
        try {
            if (index < 0 || index >= capacity) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            size--;
            return removeFromMyList(index, myList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод удаляет все элементы листа и приводит емкость к значению по умолчанию (capacity = 10)
     */
    @Override
    public void clear() {
        try {
            capacity = 10;
            myList = new Object[capacity];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Метод реализующий алгоритм быстрой сортировки
     *  @param comparator объект типа Comparator<E>, используемый для сравнения элементов списка.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        try {
            if (size == 0) {
                return;
            }
            quickSort(myList, 0, myList.length - 1, comparator);
            isSorted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return отсортирован список или нет
     */
    @Override
    public boolean isSorted() {
        try {
            return isSorted;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Метод урезает список до указанного размера
     * @param size переменная типа int - размер до которого нужно урезать список
     */
    @Override
    public void split(int size) {
        try {
            if (size < 0 || size >= capacity) {
                throw new IndexOutOfBoundsException("Invalid index: " + size);
            }
            splitMyList(myList, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Рекурсивный метод сортировки массива.
     * @param myListSort массив элементов типа Object[] - текущий/исходный список.
     * @param lowIndex переменная типа int - нижний индекс.
     * @param highIndex переменная типа int - верхний индекс.
     * @param comparator объект типа Comparator<E>, используемый для сравнения элементов массива.
     */
    private void quickSort(Object[] myListSort, int lowIndex, int highIndex, Comparator<E> comparator) {
        try {
            if (lowIndex < highIndex) {
                int part = partition(myListSort, lowIndex, highIndex, comparator);
                quickSort(myListSort, lowIndex, part - 1, comparator);
                quickSort(myListSort, part + 1, highIndex, comparator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Определяет положение опорного элемента и переставляет элементы массива.
     * @param myListSort массив элементов типа Object[] - текущий/исходный список.
     * @param lowIndex переменная типа int - нижний индекс.
     * @param highIndex переменная типа int - верхний индекс.
     * @param comparator объект типа Comparator<E>, используемый для сравнения элементов массива.
     * @return индекс опорного элемента
     */
    private int partition(Object[] myListSort, int lowIndex, int highIndex, Comparator<E> comparator) {
        Object pivot = myListSort[highIndex];
        int i = (lowIndex - 1);
        for (int j = lowIndex; j < highIndex; j++) {
            int compared = comparator.compare((E) myListSort[j], (E) pivot);
            if (compared < 0) {
                i++;
                Object temp = myListSort[i];
                myListSort[i] = myListSort[j];
                myListSort[j] = temp;
            }
        }
        Object temp = myListSort[i + 1];
        myListSort[i + 1] = myListSort[highIndex];
        myListSort[highIndex] = temp;
        return i + 1;
    }

    /**
     * Метод добавляющий элемент в конец списка
     * @param element переменная типа E - добавляемый в конец списка элемент
     * @param myListExtension массив элементов типа Object[] - текущий/исходный список
     */
    private void capacityAddExtension(E element, Object[] myListExtension) {
        try {
            capacity++;
            myList = new Object[capacity];
            for (int i = 0; i < myListExtension.length; i++) {
                myList[i] = myListExtension[i];
            }
            myList[capacity] = element;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод добавляющий элемент в список на указанное место (индекс), смещая остальные элементы вправло на один индекс
     * @param index переменная типа int - индекс на место которого будет вставлен элемент
     * @param element переменная типа E - добавляемый элемент
     * @param myListExtension массив элементов типа Object[] - текущий/исходный список
     */
    private void capacityAddExtension(E element, Object[] myListExtension, int index) {
        try {
            capacity++;
            myList = new Object[capacity];
            for (int i = 0; i < myListExtension.length; i++) {
                if (i == index) {
                    myList[i] = element;
                } else if (i > index) {
                    myList[i] = myListExtension[i - 1];
                } else {
                    myList[i] = myListExtension[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод удаляет  элемент из списка
     * @param index переменная типа int - индекс удаляемого элемента
     * @param myListRemove массив элементов типа Object[] - текущий/исходный список
     */
    private E removeFromMyList(int index, Object[] myListRemove) {
        try {
            E element = (E) myList[index];
            capacity--;
            myList = new Object[capacity];
            for (int i = 0; i < myListRemove.length; i++) {
                if (i == index) {
                    i++;
                } else {
                    myList[i - 1] = myListRemove[i];
                }
            }
            return element;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод урезает список до указанного размера
     * @param sizeSplit переменная типа int - размер до которого нужно урезать список
     * @param splitMyList массив элементов типа Object[] - текущий/исходный список
     */
    private void splitMyList(Object[] splitMyList, int sizeSplit) {
        try {
            capacity = sizeSplit;
            myList = new Object[capacity];
            for (int i = 0; i < myList.length; i++) {
                myList[i] = splitMyList[i];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
