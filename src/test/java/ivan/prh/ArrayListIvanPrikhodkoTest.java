package ivan.prh;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


class ArrayListIvanPrikhodkoTest {

    @Test
    void size() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5});
        assertThat(list.size()).isEqualTo(7);
        list = new ArrayList_IvanPrikhodko<>(new Integer[]{});
        assertThat(list.size()).isEqualTo(0);
        list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5, 1, 9, 20, 4, 6, 8, 5});
        assertThat(list.size()).isEqualTo(14);
    }

    @Test
    void add() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{});
        list.add(1);
        assertThat(list.get(0)).isEqualTo(1);
        list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5});
        list.add(300);
        assertThat(list.get(7)).isEqualTo(300);
    }

    @Test
    void indexAdd() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5});
        list.add(3, 35);
        assertThat(list.get(3)).isEqualTo(35);
    }

    @Test
    void get() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5});
        assertThat(list.get(6)).isEqualTo(5);
    }

    @Test
    void set() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5});
        list.set(3, 35);
        assertThat(list.get(3)).isEqualTo(35);
    }

    @Test
    void remove() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5});

        list.remove(6);
        assertThat(list.get(6)).isNull();

        list.remove(0);
        assertThat(list.get(0)).isEqualTo(9);
    }

    @Test
    void clear() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{1, 9, 20, 4, 6, 8, 5});
        list.clear();

        assertThat(list.size()).isEqualTo(0);
        assertThat(list.get(0)).isNull();
    }

    @Test
    void quickSort() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{4, 9, 20, 10, 6, 8, 5});
        list.quickSort(Comparator.comparing(a -> a));
        assertThat(list.get(0)).isEqualTo(4);
        assertThat(list.get(6)).isEqualTo(20);
        assertThat(list.get(2)).isEqualTo(6);
    }

    @Test
    void isSorted() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{});
        assertThat(list.isSorted()).isTrue();
        list.add(23);
        assertThat(list.isSorted()).isTrue();
        list.add(21);
        assertThat(list.isSorted()).isFalse();
        list.add(22);
        assertThat(list.isSorted()).isFalse();
        list.quickSort(Comparator.comparing(a -> a));
        assertThat(list.isSorted()).isTrue();
    }

    @Test
    void split() {
        ArrayList_IvanPrikhodko<Integer> list = new ArrayList_IvanPrikhodko<>(new Integer[]{4, 9, 20, 10, 6, 8, 5});
        list.split(3);
        assertThat(list.get(0)).isEqualTo(4);
        assertThat(list.get(2)).isEqualTo(20);
        assertThat(list.get(3)).isNull();
    }
}