package task1;
// к сожалению мои тесты в методе main
public class Main {

    public static void main(String[] args) {

        IntensiveList<Integer> intensiveList = new ArrayList_GulnazGalieva<>();

        intensiveList.add(5);
        intensiveList.add(-5);
        intensiveList.add(0);
        intensiveList.add(100);
        intensiveList.add(258);
        intensiveList.add(7);
        intensiveList.add(15);
        intensiveList.add(1);
        intensiveList.add(10);
        intensiveList.add(9);
        intensiveList.add(16);
        intensiveList.add(1000);

        System.out.println("Представление динамического массива в виде строки");
        System.out.println(intensiveList);
        System.out.println();

        System.out.println("Добавление элемента в динамический массив по индексу");
        intensiveList.add(5, 2000);
        System.out.println(intensiveList);
        System.out.println();

        System.out.println("Получение элемента из динамического массива по индексу");
        System.out.println(intensiveList.get(5));
        System.out.println();

        System.out.println("Обновление элемента в динамическом массиве по индексу.");
        System.out.println(intensiveList.set(5,4000));
        System.out.println(intensiveList+" " +intensiveList.size());
        System.out.println();

        System.out.println("Удаление элемента в динамическом массиве по индексу.");
        System.out.println(intensiveList.remove(5));
        System.out.println(intensiveList +" " +intensiveList.size());
        System.out.println();

        System.out.println("Сортирует элементы динамического массива по алгоритму \"Быстрая сортировка\"");
        intensiveList.quickSort(Integer::compareTo);
        System.out.println(intensiveList +" " +intensiveList.size());
        System.out.println();

        System.out.println("Возвращает true/false в зависимости от того, что динамический массив отсортирован или нет");
        System.out.println(intensiveList.isSorted());
        System.out.println();

        System.out.println("Обрезает список до указанного размера");
        intensiveList.split(5);
        System.out.println(intensiveList + " " + intensiveList.size());
        System.out.println();

        System.out.println("Удаляет все элементы динамического массива и его объем приводит к дефолтному.");
        intensiveList.clear();
        System.out.println(intensiveList + " " + intensiveList.size());

    }
}
