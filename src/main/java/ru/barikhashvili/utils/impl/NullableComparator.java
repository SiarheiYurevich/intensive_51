package ru.barikhashvili.utils.impl;

import java.util.Comparator;
import java.util.Objects;

/**
 * Класс, предоставляющий методы для сравнения двух объектов, допускающих значения null.
 */
public class NullableComparator {
    private NullableComparator() {}

    /**
     * Сравнивает два объекта типа T. Использует переданный компаратор для сравнения,
     * если оба объекта не являются null.
     *
     * @param o1 первый объект для сравнения.
     * @param o2 второй объект для сравнения.
     * @param <T> тип объектов для сравнения. Тип должен быть наследником
     * класса {@link Comparable}.
     *
     * @return отрицательное целое число, если o1 меньше o2, положительное
     * целое число, если o1 больше o2, и ноль, если o1 и o2 равны. Если
     * один из объектов равен null, то он считается больше любого ненулевого
     * объекта. Если оба объекта равны null, то они считаются равными.
     */
    public static <T> int compare(Comparable<? super T> o1, T o2) {
        var res = nullableCompare(o1, o2);
        if (res != null) return res;
        return o1.compareTo(o2);
    }

    /**
     * Сравнивает два объекта типа T. Использует переданный компаратор для сравнения,
     * если оба объекта не являются null.
     *
     * @param o1 первый объект для сравнения.
     * @param o2 второй объект для сравнения.
     * @param c компаратор, реализующий {@link Comparable}, с помощью которого
     * осуществляется сравнение объектов
     * @param <T> тип объектов для сравнения.
     *
     * @return отрицательное целое число, если o1 меньше o2, положительное
     * целое число, если o1 больше o2, и ноль, если o1 и o2 равны. Если
     * один из объектов равен null, то он считается больше любого ненулевого
     * объекта. Если оба объекта равны null, то они считаются равными.
     *
     * @throws NullPointerException если компаратор равен null.
     */
    public static <T> int compare(T o1, T o2, Comparator<? super T> c) {
        Objects.requireNonNull(c, "Comparator is null");
        var res = nullableCompare(o1, o2);
        if (res != null) return res;
        return c.compare(o1, o2);
    }

    /**
     * Сравнивает два объекта, допускающие значения null.
     * При сравнении большим оказывает тот объект, который принимает значение null
     *
     * @param o1 первый объект сравнения
     * @param o2 второй объект сравнения
     * @return если:
     * <ul>
     *      <li>оба объекта null, <b>возвращается 0</b></li>
     *      <li>o1 не null, o2 == null, <b>возвращается -1</b></li>
     *      <li>o1 == null, а o2 не null, <b>возвращается 1</b></li>
     *      <li>ни один из объектов не null, <b>возвращается null</b></li>
     * </ul>
     */
    private static Integer nullableCompare(Object o1, Object o2) {
        if (o1 == null && o2 == null) return 0;
        if (o2 == null) return -1;
        if (o1 == null) return 1;
        return null;
    }
}
