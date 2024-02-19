package com.sursindmitry.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

class ArrayListDmitrySursinTest {

  private ArrayList_DmitrySursin<Integer> list =
      new ArrayList_DmitrySursin<>();

  @Test
  void sizeDefaultConstructor() {
    assertEquals(0, list.size());
  }

  @Test
  void sizeCustomCapacityConstructor() {
    list = new ArrayList_DmitrySursin<>(200);

    assertEquals(0, list.size());
  }

  @Test
  void sizeCustomZeroCapacityConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new ArrayList_DmitrySursin<>(0));
  }

  @Test
  void size_CustomNegativeCapacityConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new ArrayList_DmitrySursin<>(-200));
  }

  @Test
  void addOneElementToArrayList() {
    list.add(1);

    assertEquals(1, list.size());
    assertEquals(1, list.get(0));
  }

  @Test
  void addMultipleElementsToArrayList() {
    list.add(1);
    list.add(2);

    assertEquals(2, list.size());
    assertEquals(1, list.get(0));
    assertEquals(2, list.get(1));
  }

  @Test
  void addOneElementToArray() {
    list.add(0, 1);

    assertEquals(1, list.size());
    assertEquals(1, list.get(0));
  }

  @Test
  void addMultiplyElementToArray() {

    list.add(0, 1);
    list.add(1, 2);
    list.add(2, 3);


    assertEquals(3, list.size());
    assertEquals(1, list.get(0));
    assertEquals(2, list.get(1));
  }

  @Test
  void addWithInvalidIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(-1, 2);
      list.add(1, 2);
    });
  }

  @Test
  void getIndex() {
    list.add(0);

    assertEquals(0, list.get(0));
  }

  @Test
  void getWithInvalidIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.get(-1);
      list.get(1);
    });
  }

  @Test
  void setElement() {
    list.add(10);
    list.add(20);
    list.add(30);

    list.set(1, 50);

    assertEquals(3, list.size());
    assertEquals(50, list.get(1));
  }

  @Test
  void setWithInvalidIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(-1, 0);
      list.set(1, 0);
    });
  }

  @Test
  void removeElement() {
    list.add(10);
    list.add(20);
    list.add(30);

    int removedElement = (int) list.remove(1);

    assertEquals(20, removedElement);
    assertEquals(2, list.size());
    assertEquals(10, list.get(0));
    assertEquals(30, list.get(1));
  }

  @Test
  void removeWithInvalidIndex() {
    list.add(10);
    list.add(20);
    list.add(30);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.remove(4);
      list.remove(-1);
    });
  }

  @Test
  void clear() {
    list.add(10);
    list.add(20);
    list.add(30);

    list.clear();

    assertEquals(0, list.size());
    assertNull(list.get(0));
  }

  @Test
  void quickSort() {
    list.add(20);
    list.add(30);
    list.add(10);

    list.quickSort(Comparator.naturalOrder());

    assertTrue(list.isSorted());
    assertEquals(10, list.get(0));
    assertEquals(20, list.get(1));
    assertEquals(30, list.get(2));
  }



}