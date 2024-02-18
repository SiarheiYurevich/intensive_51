package task_1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayList_VladLitvinov<E> implements IntensiveList<E> {
    private E[] array;
    private int size;
    private int capacity = 10;
    private final double loadFactor = 0.75;

    public ArrayList_VladLitvinov() {
        array = (E[]) new Object[capacity];
        size = 0;
    }

    public ArrayList_VladLitvinov(int capacity) {
        array = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void capacityChange(){
        if (loadFactor < (double) size / capacity) {
            capacity = (int) (capacity * 1.5);
        }
    }

    @Override
    public void add(E element) {
        size++;
        capacityChange();
        array[size - 1] = element;
    }

    @Override
    public void add(int index, E element) {
        E[] arrayWithNewElement;
        E[] arrayNumOne = (E[]) new Object[index + 1];
        E[] arrayNumTwo;
        if (loadFactor < (double) size() / capacity) {
            capacity *= (int) 1.5;
        }
        arrayWithNewElement = (E[]) new Object[capacity];
        arrayNumTwo = (E[]) new Object[arrayWithNewElement.length - arrayNumOne.length];

        System.arraycopy(array, 0, arrayNumOne, 0, index);
        arrayNumOne[index] = element;
        System.arraycopy(array, index, arrayNumTwo, 0, size() - index);

        System.arraycopy(arrayNumOne, 0, arrayWithNewElement, 0, arrayNumOne.length);
        System.arraycopy(arrayNumTwo, 0, arrayWithNewElement, arrayNumOne.length, arrayNumTwo.length);

        System.arraycopy(arrayWithNewElement, 0, array, 0, size() + 1);
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public E set(int index, E element) {
        return array[index] = element;
    }

    @Override
    public E remove(int index) {
        E e = array[index];
        E[] arrayWithNewElement = (E[]) new Object[capacity];
        E[] arrayNumOne = (E[]) new Object[index];
        E[] arrayNumTwo = (E[]) new Object[arrayWithNewElement.length - arrayNumOne.length];

        System.arraycopy(array, 0, arrayNumOne, 0, index);
        System.arraycopy(array, index, arrayNumTwo, 0, size() - index - 1);

        System.arraycopy(arrayNumOne, 0, arrayWithNewElement, 0, arrayNumOne.length);
        System.arraycopy(arrayNumTwo, 0, arrayWithNewElement, arrayNumOne.length, arrayNumTwo.length);

        System.arraycopy(arrayWithNewElement, 0, array, 0, size() - 1);
        return e;
    }

    @Override
    public void clear() {
        capacity = 10;
        for (int i = 0; i < size(); i++) {
            array[i] = null;
        }
    }

    @Override
    public void quickSort(Comparator<E> comparator) {
;
    }

    @Override
    public boolean isSorted() {
        return false;
    }

    @Override
    public void split(int size) {

    }
}
