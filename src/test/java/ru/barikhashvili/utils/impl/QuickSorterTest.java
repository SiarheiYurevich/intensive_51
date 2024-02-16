package ru.barikhashvili.utils.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.barikhashvili.utils.Sorter;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuickSorterTest {
    private static Sorter<Integer> sorter;

    @BeforeAll
    static void init() {
        sorter = new QuickSorter<>(Integer::compareTo);
    }

    @Test
    @DisplayName("Метод sort должен выбросить NullPointerException, если переданный массив равен null")
    void sort_shouldThrowNullPointerException_ifArrayArgumentIsNull() {
        assertAll(
                () -> assertThrows(
                        NullPointerException.class,
                        () -> sorter.sort(null, 0, 1),
                        "Array is null"
                ),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> sorter.sort(null, 1),
                        "Array is null"
                ),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> sorter.sort(null),
                        "Array is null"
                )
        );
    }

    @Test
    @DisplayName("Метод sort должен выбросить IllegalArgumentException, если стартовый индекс больше конечного")
    void sort_shouldThrowIllegalArgumentException_ifStartIndexIsGreaterThanEndIndex() {
        Integer[] arr = new Integer[10];
        int fromIndex = 5;
        int toIndex = 3;

        assertThrows(
                IllegalArgumentException.class,
                () -> sorter.sort(arr, fromIndex, toIndex),
                String.format("Start index is greater than end index: %d, %d", fromIndex, toIndex)
        );
    }

    @Test
    @DisplayName("Метод sort должен выбросить IndexOutOfBoundsException, если индексы выходят за диапазон массива")
    void sort_shouldThrowIndexOutOfBoundsException_ifIndexesAreOutOfArrayRange() {
        Integer[] arr = new Integer[1];

        assertAll(
                () -> {
                    int fromIndex = -1;
                    int toIndex = 0;

                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> sorter.sort(arr, fromIndex, toIndex),
                            String.format("Incorrect indexes: %d, %d", fromIndex, toIndex)
                    );
                },
                () -> {
                    int fromIndex = 0;
                    int toIndex = 1;

                    assertThrows(
                            IndexOutOfBoundsException.class,
                            () -> sorter.sort(arr, fromIndex, toIndex),
                            String.format("Incorrect indexes: %d, %d", fromIndex, toIndex)
                    );
                }
        );
    }

    @Test
    @DisplayName("Метод sort должен отсортировать массив, если он не содержит элементов")
    void sort_shouldSortArray_ifArraySizeIsZero() {
        Integer[] arr = new Integer[0];

        sorter.sort(arr);

        assertThat(arr).isEmpty();
    }

    @Test
    @DisplayName("Метод sort должен отсортировать массив, если он включает только один элемент")
    void sort_shouldSortArray_isArrayContainsOnlyOneElement() {
        Integer[] arr = new Integer[] {1};

        sorter.sort(arr);

        assertThat(arr).hasSize(1).endsWith(1);
    }

    @Test
    @DisplayName("Метод sort должен оставить массив без изменений, если он уже отсортирован")
    void sort_shouldLeaveArrayUnchanged_ifArrayIsSorted() {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] correctArr = arr.clone();

        sorter.sort(arr);

        assertThat(arr).containsExactly(correctArr);
    }

    @Test
    @DisplayName("Метод sort должен отсортировать массив, если он не был упорядочен")
    void sort_shouldSortArray_ifArrayIsNotSorted() {
        assertAll(
                () -> {
                    Integer[] arr = new Integer[] {8,3,7,5,0,2,6,5,9,3,7};
                    Integer[] correctArr = arr.clone();
                    Arrays.sort(correctArr);

                    sorter.sort(arr);

                    assertThat(arr).containsExactly(correctArr);
                },
                () -> {
                    Integer[] arr = new Integer[] {8,3};
                    Integer[] correctArr = arr.clone();
                    Arrays.sort(correctArr);

                    sorter.sort(arr);

                    assertThat(arr).containsExactly(correctArr);
                }
        );
    }

    @Test
    @DisplayName("Метод sort должен отсортировать массив, если массив содержит null элементы")
    void sort_shouldSortArray_ifArrayContainsNullElements() {
        assertAll(
                () -> {
                    Integer[] arr = new Integer[] {9,5,6,null,10,7,12};
                    Integer[] correctArr = {5,6,7,9,10,12,null};

                    sorter.sort(arr);

                    assertThat(arr).containsExactly(correctArr);
                },
                () -> {
                    Integer[] arr = new Integer[] {null,null};
                    Integer[] correctArr = arr.clone();

                    sorter.sort(arr);

                    assertThat(arr).containsExactly(correctArr);
                },
                () -> {
                    Integer[] arr = new Integer[] {null,6,5,10,9,7,null};
                    Integer[] correctArr = {5,6,7,9,10,null,null};

                    sorter.sort(arr);

                    assertThat(arr).containsExactly(correctArr);
                },
                () -> {
                    Integer[] arr = new Integer[] {null};
                    Integer[] correctArr = {null};

                    sorter.sort(arr);

                    assertThat(arr).containsExactly(correctArr);
                }
        );
    }

    @Test
    @DisplayName("Метод sort должен отсортировать массив, если задан диапазон")
    void sort_shouldSortArray_ifRangeIsGiven() {
        assertAll(
                () -> {
                    Integer[] arr = new Integer[] {1,12,10,7,12,6,11,0};
                    int fromIndex = 2;
                    int toIndex = 5;
                    Integer[] correctArr = {1,12,6,7,10,12,11,0};

                    sorter.sort(arr, fromIndex, toIndex);

                    assertThat(arr).containsExactly(correctArr);
                },
                () -> {
                    Integer[] arr = new Integer[] {7,10,5,-1,0,3,2};
                    int toIndex = 3;
                    Integer[] correctArr = {-1,5,7,10,0,3,2};

                    sorter.sort(arr, toIndex);

                    assertThat(arr).containsExactly(correctArr);
                }
        );
    }
}