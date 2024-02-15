package collection;

import repository.IntensiveList;

import java.util.Comparator;
import java.util.Objects;

public class ArrayList_DmitryKuznetsov<E> implements IntensiveList<E> {

    private int capacity = 10;

    private int size = 0;

    private Object[] myList = new Object[capacity];

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

    private void capacitySetExtension(E element, Object[] myListExtension, int index) {
        try {
            myList = new Object[index];
            for (int i = 0; i < myListExtension.length; i++) {
                myList[i] = myListExtension[i];
            }
            myList[index] = element;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        try {
            if (capacity <= size) {
                capacityAddExtension(element, myList);
            } else {
                myList[size] = element;
            }
            size++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(int index, E element) {
        try {
            if (capacity < index) {
                capacitySetExtension(element, myList, index);
            } else {
                capacityAddExtension(element, myList, index);
            }
            size++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        try {
            return (E) myList[index];
        } catch (ClassCastException classCastException) {
            classCastException.printStackTrace();
            return null;
        }
    }

    @Override
    public E set(int index, E element) {
        try {
            if (capacity >= index) {
                myList[index] = element;
            } else {
                capacitySetExtension(element, myList, index);
            }
            return element;
        } catch (ClassCastException classCastException) {
            classCastException.printStackTrace();
            return null;
        }
    }

    @Override
    public E remove(int index) {
        try {
            size--;
            return removeFromMyList(index, myList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void clear() {
        try {
            capacity = 10;
            myList = new Object[capacity];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void quickSort(Comparator<E> comparator) {

    }

    @Override
    public boolean isSorted() {
        try {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void split(int size) {
        splitMyList(myList, size);
    }

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
