package org.example;

/**
 * Класс предоставляет методы для сравнения коллекций типа {@code IntensiveList}.
 * @author Иван Приходько
 */
public class Assertions_IvanPrikhodko {

    /**
     * Метод сравнивает две коллекции. Коллекции считаются равными, если все элементы коллекции равны.
     * В противном случае выбрасывается {@code AssertionError}.
     * @param list1 Первый лист для сравнения.
     * @param list2 Второй лист для сравнения.
     */
    public static void assertEquals(IntensiveList list1, IntensiveList list2) {
        if(list1.size() != list2.size())
            throw new AssertionError("Листы имеют разное количество элементов");
        for (int i = 0; i < list1.size(); i++) {
            if(!list1.get(i).equals(list2.get(i)))
                throw new AssertionError("Ожидолось list2[" + i + "] = " + list1.get(i)
                        + ", но было: " + list2.get(i));
        }
    }
}
