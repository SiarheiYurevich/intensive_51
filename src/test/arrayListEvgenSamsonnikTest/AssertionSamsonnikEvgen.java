package arrayListEvgenSamsonnikTest;

import by.samsonnik.astontrainee.IntensiveList;

public class AssertionSamsonnikEvgen<E> {

    public static <E> boolean assertEquals(IntensiveList<E> actual, IntensiveList<E> expected) {
        if (actual.equals(expected)) return true;
        else throw new AssertionError("Actual and Expected lists are different!");
    }

    public static <E> boolean assertNoTEquals(IntensiveList<E> actual, IntensiveList<E> expected) {
        if (!actual.equals(expected)) return false;
        else throw new AssertionError("Actual and Expected lists are the same!");
    }
}
