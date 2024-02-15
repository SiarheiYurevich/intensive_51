package ru.barikhashvili.collections.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.barikhashvili.collections.IntensiveList;

import java.util.*;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование класса ArrayList_IrakliBarikhashvili")
class ArrayList_IrakliBarikhashviliTest {
    @Test
    @DisplayName("Конструктор должен выбросить NullPointerException если переданная коллекция null")
    void constructor_shouldThrowNullPointerException_ifCollectionArgumentIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new ArrayList_IrakliBarikhashvili<>(null),
                "Collection is null"
        );
    }

    @Test
    @DisplayName("Конструктор должен выбросить IllegalArgumentException если переданная ёмкость меньше нуля")
    void constructor_shouldThrowIllegalArgumentException_ifCapacityArgumentIsLessThanZero() {
        int incorrectCapacity = -1;

        assertThrows(
                IllegalArgumentException.class,
                () -> new ArrayList_IrakliBarikhashvili<>(incorrectCapacity),
                "Invalid capacity: " + incorrectCapacity
        );
    }

    @Test
    @DisplayName("Метод size должен вернуть правильный размер массива, если список создается через конструктор")
    void size_shouldReturnCorrectArraySize_ifListIsCreatedViaConstructor() {
        assertAll(
                () -> {
                    // arrange
                    var collectionWithElements = List.of(1, 2, 3);
                    var listWithElementsFromCollection = new ArrayList_IrakliBarikhashvili<>(collectionWithElements);
                    // act
                    int listSize = listWithElementsFromCollection.size();
                    // assert
                    assertThat(listSize).isEqualTo(collectionWithElements.size());
                },
                () -> {
                    // arrange
                    var emptyCollection = List.of();
                    var listWithElementsFromCollection = new ArrayList_IrakliBarikhashvili<>(emptyCollection);
                    // act
                    int listSize = listWithElementsFromCollection.size();
                    // assert
                    assertThat(listSize).isZero();
                },
                () -> {
                    // arrange
                    var emptyList = new ArrayList_IrakliBarikhashvili<>();
                    // act
                    int emptyListSize = emptyList.size();
                    // assert
                    assertThat(emptyListSize).isZero();
                },
                () -> {
                    // arrange
                    var listWithCustomCapacity = new ArrayList_IrakliBarikhashvili<>(9);
                    // act
                    int listSize = listWithCustomCapacity.size();
                    // assert
                    assertThat(listSize).isZero();
                },
                () -> {
                    // arrange
                    var listWithZeroCapacity = new ArrayList_IrakliBarikhashvili<>(0);
                    // act
                    int listSize = listWithZeroCapacity.size();
                    // assert
                    assertThat(listSize).isZero();
                }
        );
    }

    @Test
    @DisplayName("Метод size должен вернуть правильный размер массива, когда выполняется добавление, удаление, очищение и обрезка")
    void size_shouldReturnCorrectArraySize_whenAddingRemoveSplitAndClear() {
        // arrange
        var elements = List.of(90, 12, 75, 34, 89);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);
        list.add(44);
        // act
        int listSizeAfterAdding = list.size();
        // assert
        assertThat(listSizeAfterAdding).isEqualTo(6);

        // arrange
        list.split(4);
        // act
        var listSizeAfterSplitting = list.size();
        // assert
        assertThat(listSizeAfterSplitting).isEqualTo(4);

        // arrange
        list.remove(0);
        list.remove(2);
        // act
        var listSizeAfterRemoving = list.size();
        // assert
        assertThat(listSizeAfterRemoving).isEqualTo(2);

        // arrange
        list.clear();
        // act
        var listSizeAfterClearing = list.size();
        // assert
        assertThat(listSizeAfterClearing).isZero();
    }

    @Test
    @DisplayName("Метод add должен добавить элемент в конец списка, если список пустой")
    void add_shouldAddElement_ifListIsEmpty() {
        Consumer<IntensiveList<Integer>> checkingElements = emptyList -> {
            // arrange
            Random random = new Random();
            Integer[] addedValues = new Integer[1000];
            for (int i = 0; i < addedValues.length; i++) {
                addedValues[i] = random.nextInt(addedValues.length);
            }
            // act
            for (int value : addedValues) emptyList.add(value);
            // assert
            assertThat(emptyList.size()).isEqualTo(addedValues.length);
            for (int i = 0; i < addedValues.length; i++) {
                assertThat(emptyList.get(i)).isEqualTo(addedValues[i]);
            }
        };

        assertAll(
                () -> {
                    // arrange
                    var emptyListWithCapacityOne = new ArrayList_IrakliBarikhashvili<Integer>(1);
                    // assert
                    checkingElements.accept(emptyListWithCapacityOne);
                },
                () -> {
                    // arrange
                    var emptyListWithCapacityZero = new ArrayList_IrakliBarikhashvili<Integer>(0);
                    // assert
                    checkingElements.accept(emptyListWithCapacityZero);
                },
                () -> {
                    // arrange
                    var emptyListWithCapacityTwo = new ArrayList_IrakliBarikhashvili<Integer>(2);
                    // assert
                    checkingElements.accept(emptyListWithCapacityTwo);
                },
                () -> {
                    // arrange
                    var emptyListWithDefaultCapacity = new ArrayList_IrakliBarikhashvili<Integer>(List.of());
                    // assert
                    checkingElements.accept(emptyListWithDefaultCapacity);
                },
                () -> {
                    // arrange
                    var emptyListWithDefaultCapacity = new ArrayList_IrakliBarikhashvili<Integer>();
                    // assert
                    checkingElements.accept(emptyListWithDefaultCapacity);
                },
                () -> {
                    // arrange
                    Integer[] elements = new Integer[20];
                    Arrays.fill(elements, 1);
                    var emptyListWithDefaultCapacity = new ArrayList_IrakliBarikhashvili<>(List.of(elements));
                    emptyListWithDefaultCapacity.clear();
                    // assert
                    checkingElements.accept(emptyListWithDefaultCapacity);
                },
                () -> {
                    // arrange
                    Integer[] elements = new Integer[20];
                    Arrays.fill(elements, 2);
                    var emptyListWithDefaultCapacity = new ArrayList_IrakliBarikhashvili<>(List.of(elements));
                    emptyListWithDefaultCapacity.split(0);
                    // assert
                    checkingElements.accept(emptyListWithDefaultCapacity);
                },
                () -> {
                    // arrange
                    Integer[] elements = new Integer[20];
                    Arrays.fill(elements, 2);
                    var emptyListWithDefaultCapacity = new ArrayList_IrakliBarikhashvili<>(List.of(elements));
                    for (int i = 0; i < elements.length; i++) {
                        emptyListWithDefaultCapacity.remove(0);
                    }
                    // assert
                    checkingElements.accept(emptyListWithDefaultCapacity);
                }
        );
    }

    @Test
    @DisplayName("Метод add должен выбросить исключение IndexOutOfBoundsException, если индекс вставки за пределами списка")
    void add_shouldThrowIndexOutOfBoundsException_ifIndexIsOutsideBoundsOfList() {
        var elements = List.of(1, 2, 3, 4, 5);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);

        assertAll(
                () -> {
                    int index = -1;

                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.add(index, 1337),
                            "Invalid index to insert element: " + index
                    );
                },
                () -> {
                    int index = elements.size() + 1;

                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.add(index, 1337),
                            "Invalid index to insert element: " + index
                    );
                }
        );
    }

    @Test
    @DisplayName("Метод add должен добавлять null объекты в список")
    void add_shouldAddElement_ifElementIsNull() {
        // arrange
        var list = new ArrayList_IrakliBarikhashvili<Integer>(1);
        // act
        list.add(null);
        var addedElement = list.get(0);
        // assert
        assertThat(addedElement).isNull();
        assertThat(list.size()).isOdd();
    }

    private <T> void testInsertAtPosition(int insertionPos, T element, Collection<? extends T> elements) {
        // arrange
        IntensiveList<T> testedList = new ArrayList_IrakliBarikhashvili<>(elements);
        ArrayList<T> correctList = new ArrayList<>(elements);
        correctList.add(insertionPos, element);
        // act
        testedList.add(insertionPos, element);
        // assert
        for (int i = 0; i < correctList.size(); i++) {
            assertThat(testedList.get(i)).isEqualTo(correctList.get(i));
        }
    }

    @Test
    @DisplayName("Метод add должен вставить элемент, если вставка в начало списка")
    void add_shouldAddElement_ifInsertAtBeginning() {
        var elements = List.of(10, 20, 30, 40, 50);
        int beginningIndex = 0;
        int addedElement = 5;
        testInsertAtPosition(beginningIndex, addedElement, elements);
    }

    @Test
    @DisplayName("Метод add должен вставить элемент, если вставка в середину списка")
    void add_shouldAddElement_ifInsertAtMiddle() {
        var elements = List.of(10, 20, 30, 40, 50);
        int midIndex = elements.size() / 2;
        int addedElement = 25;
        testInsertAtPosition(midIndex, addedElement, elements);
    }

    @Test
    @DisplayName("Метод add должен вставить элемент, если вставка в конец списка")
    void add_shouldAddElement_ifInsertAtEnd() {
        var elements = List.of(10, 20, 30, 40, 50);
        int beginningIndex = elements.size();
        int addedElement = 60;
        testInsertAtPosition(beginningIndex, addedElement, elements);
    }

    @Test
    @DisplayName("Метод add должен расширить список и вставить элемент, если ёмкости недостаточно")
    void add_shouldAddElement_ifCapacityIsNotEnough() {
        // arrange
        var smallCapacityList = new ArrayList_IrakliBarikhashvili<>(1);
        Integer addedElement = 10;
        // act
        smallCapacityList.add(addedElement);
        // assert
        assertThat(smallCapacityList.get(0)).isEqualTo(addedElement);
        assertThat(smallCapacityList.size()).isOne();
    }

    @Test
    @DisplayName("Метод get должен вернуть элемент списка по указанному индексу, если индекс корректный")
    void get_shouldReturnListElementByIndex_ifIndexIsCorrect() {
        var elements = List.of(1, 2, 3, 4);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);

        for (int i = 0; i < elements.size(); i++) {
            assertThat(list.get(i)).isEqualTo(elements.get(i));
        }
    }

    @Test
    @DisplayName("Метод get должен выбросить IndexOutOfBoundsException, если индекс выходит за пределы границ списка")
    void get_shouldThrowIndexOutOfBoundsException_ifIndexOutsideBoundsOfList() {
        var elements = List.of(1, 2, 3, 4);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);

        assertAll(
                () -> {
                    int indexLessThanZero = -1;

                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.get(indexLessThanZero),
                            "Invalid index to get element: " + indexLessThanZero
                    );
                },
                () -> {
                    int indexOutsideListBoundaries = elements.size();

                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.get(indexOutsideListBoundaries),
                            "Invalid index to get element: " + indexOutsideListBoundaries
                    );
                }
        );
    }

    @Test
    @DisplayName("Метод set должен изменить значение элемента в списке по указанному индексу, если индекс корректный")
    void set_shouldSetNewValueByIndex_ifIndexIsCorrect() {
        var elements = List.of(1,2,3,4);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);

        for (int i = 0; i < elements.size(); i++) {
            var prevValue = list.set(i, list.get(i) + 1);
            assertThat(prevValue).isEqualTo(elements.get(i));
        }

        for (int i = 0; i < elements.size(); i++) {
            assertThat(list.get(i)).isEqualTo(elements.get(i) + 1);
        }
    }

    @Test
    @DisplayName("Метод set должен выбросить IndexOutOfBoundsException, если индекс выходит за пределы границ списка")
    void set_shouldThrowIndexOutOfBoundsException_ifIndexOutsideBoundsOfList() {
        var elements = List.of(1, 2, 3, 4);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);
        assertAll(
                () -> {
                    int indexLessThanZero = -1;
                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.set(indexLessThanZero, 0),
                            "Invalid index to get element: " + indexLessThanZero
                    );
                },
                () -> {
                    int indexOutsideListBoundaries = elements.size();
                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.set(indexOutsideListBoundaries, 0),
                            "Invalid index to get element: " + indexOutsideListBoundaries
                    );
                }
        );
    }

    @Test
    @DisplayName("Метод remove должен удалить элемент по указанному индексу, если индекс нулевой")
    void remove_shouldDeleteElementByIndex_ifIndexIsZero() {
        var elements = List.of(1,2,3);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);

        for (Integer element : elements) {
            var removedElement = list.remove(0);
            assertThat(removedElement).isEqualTo(element);
        }

        assertThat(list.size()).isZero();
    }

    @Test
    @DisplayName("Метод remove должен удалить элемент по указанному индексу, если удаление с середины списка")
    void remove_shouldDeleteElementByIndex_ifDeletingFromMiddle() {
        var elements = List.of(1,2,3);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);

        for (int i = 1; i < elements.size(); i++) {
            var removedElement = list.remove(1);
            assertThat(removedElement).isEqualTo(elements.get(i));
        }

        assertThat(list.size()).isOne();
        assertThat(list.get(0)).isEqualTo(elements.get(0));
    }

    @Test
    @DisplayName("Метод remove должен выбросить IndexOutOfBoundsException, если индекс выходит за пределы границ списка")
    void remove_shouldThrowIndexOutOfBoundsException_ifIndexOutsideBoundsOfList() {
        var elements = List.of(1, 2, 3, 4);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);
        assertAll(
                () -> {
                    int indexLessThanZero = -1;
                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.remove(indexLessThanZero),
                            "Invalid index to get element: " + indexLessThanZero
                    );
                },
                () -> {
                    int indexOutsideListBoundaries = elements.size();
                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.remove(indexOutsideListBoundaries),
                            "Invalid index to get element: " + indexOutsideListBoundaries
                    );
                }
        );
    }

    @Test
    @DisplayName("Метод clear должен удалить все элементы из списка")
    void clear_shouldRemoveAllElementsFromList() {
        var list = new ArrayList_IrakliBarikhashvili<>(List.of(1,2,3));
        list.clear();
        assertThat(list.size()).isZero();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    @DisplayName("Метод quickSort должен отсортировать в порядке возрастания элементы списка")
    void quickSort_shouldSortListElementsInAscendingOrder() {
        assertAll(
                () -> {
                    Integer[] elements = new Integer[] {90, null, 1, 44, 36, -19, 100, null, 50, 5};
                    var list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    list.quickSort(Integer::compare);

                    assertThat(list.isSorted(Integer::compare)).isTrue();
                },
                () -> {
                    Integer[] elements = new Integer[] {90, 1, 44, 36, -19, 100, 50, 5};
                    var list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    list.quickSort(Integer::compare);

                    assertThat(list.isSorted(Integer::compare)).isTrue();
                },
                () -> {
                    var list = new ArrayList_IrakliBarikhashvili<Integer>();
                    list.quickSort(Integer::compare);

                    assertThat(list.isSorted(Integer::compare)).isTrue();
                }
        );
    }

    @Test
    @DisplayName("Метод isSorted производит сравнение объектов по hashCode, если элементы списка не реализуют Сomparable")
    void isSorted_sortsObjectsUsingHashCode_ifElementsDoNotImplementComparable() {
        int listSize = 15;
        Object[] elements = new Object[listSize];
        for (int i = 0; i < listSize; i++) {
            elements[i] = new Object();
        }
        Arrays.sort(elements, Comparator.comparingInt(Object::hashCode));

        assertAll(
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isTrue();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    var temp = list.get(0);
                    list.set(0, list.get(listSize - 1));
                    list.set(listSize - 1, temp);

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isFalse();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    list.set(0, null);

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isFalse();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    list.set(listSize - 1, null);

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isTrue();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>();

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isTrue();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(List.of(2));

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isTrue();
                }
        );
    }

    @Test
    @DisplayName("Метод isSorted производит сравнение объектов через compareTo, если элементы списка реализуют Comparable")
    void isSorted_sortsObjectsUsingCompareTo_ifElementsImplementComparable() {
        int listSize = 15;
        Integer[] elements = new Integer[listSize];
        for (int i = 0; i < listSize; i++) elements[i] = i;

        assertAll(
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isTrue();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    var temp = list.get(0);
                    list.set(0, list.get(listSize - 1));
                    list.set(listSize - 1, temp);

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isFalse();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    list.set(0, null);

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isFalse();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(Arrays.asList(elements));
                    list.set(listSize - 1, null);

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isTrue();
                },
                () -> {
                    IntensiveList<Object> list = new ArrayList_IrakliBarikhashvili<>(List.of(2,1));

                    boolean isSortedList = list.isSorted();

                    assertThat(isSortedList).isFalse();
                }
        );
    }

    @Test
    @DisplayName("Метод isSorted должен выбросить NullPointerException, если компаратор равен null")
    void isSorted_shouldThrowNullPointerException_ifComparatorArgumentIsNull() {
        var list = new ArrayList_IrakliBarikhashvili<>();
        assertThrows(
                NullPointerException.class,
                () -> list.isSorted(null),
                "Comparator is null"
        );
    }

    @Test
    @DisplayName("Метод split должен обрезать список согласно указанному размеру, если размер корректный")
    void split_shouldTrimListToSpecifiedSize_ifSizeIsCorrect() {
        assertAll(
                () -> {
                    var list = new ArrayList_IrakliBarikhashvili<>(List.of(1,2,3));

                    list.split(0);

                    assertThat(list.size()).isZero();
                    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
                },
                () -> {
                    var elements = List.of(1,2,3,4);
                    var list = new ArrayList_IrakliBarikhashvili<>(elements);
                    int newSize = 2;

                    list.split(newSize);

                    assertThat(list.size()).isEqualTo(newSize);
                    for (int i = 0; i < newSize; i++) {
                        assertThat(list.get(i)).isEqualTo(elements.get(i));
                    }
                },
                () -> {
                    var elements = List.of(1,2,3,4);
                    var list = new ArrayList_IrakliBarikhashvili<>(elements);
                    int newSize = 4;

                    list.split(newSize);

                    assertThat(list.size()).isEqualTo(newSize);
                    for (int i = 0; i < newSize; i++) {
                        assertThat(list.get(i)).isEqualTo(elements.get(i));
                    }
                }
        );
    }

    @Test
    @DisplayName("Метод split должен выбросить IndexOutOfBoundsException, если новый размер списка выходит за границы")
    void split_shouldThrowIndexOutOfBoundsException_ifIndexOutsideBoundsOfList() {
        var elements = List.of(1, 2, 3, 4);
        var list = new ArrayList_IrakliBarikhashvili<>(elements);
        assertAll(
                () -> {
                    int sizeLessThanZero = -1;
                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.split(sizeLessThanZero),
                            "Invalid index to get element: " + sizeLessThanZero
                    );
                },
                () -> {
                    int sizeOutsideListBoundaries = elements.size() + 1;
                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> list.split(sizeOutsideListBoundaries),
                            "Size is incorrect: " + sizeOutsideListBoundaries
                    );
                }
        );
    }

    @Test
    @DisplayName("Метод toString должен возвращать строковое представление списка")
    void toString_shouldReturnStringRepresentationOfList() {
        assertAll(
                () -> {
                    // list.size() > 0
                    // arrange
                    var elements = Arrays.asList(null, 1, 2, 3, null);
                    var list = new ArrayList_IrakliBarikhashvili<>(elements);
                    // act
                    var nonEmptyListRepresentation = list.toString();
                    // assert
                    assertThat(nonEmptyListRepresentation).isEqualTo(elements.toString());
                },
                () -> {
                    // list.size() == 0
                    // arrange
                    var list = new ArrayList_IrakliBarikhashvili<>();
                    // act
                    var emptyListRepresentation = list.toString();
                    // assert
                    assertThat(emptyListRepresentation).isEqualTo(Collections.EMPTY_LIST.toString());
                }
        );
    }
}