# intensive_51

# Задание 1: Реализация своего динамического списка

### Общие требования:
- Клонировать репозиторий https://github.com/SiarheiYurevich/intensive_51
- Создать ветку `task_1_name_surname` от ветки `develop`
- Создать Pull Request
- Использовать `.gitignore`

### Задачи:
1. Создать интерфейс `IntensiveList` и класс `ArrayList_NameSurname`, имплементирующий данный интерфейс.
```java
public interface IntensiveList<E> {
    int size();
    void add(E element);
    void add(int index, E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);
    void clear(); // удаляем все элементы, capacity приводим к дефолтному
    void quickSort(Comparator<E> comparator); // реализуем быструю сортировку, дефолт по возрастанию
    boolean isSorted();
    void split(int size); // обрезаем список до указанного размера
}
```
2. Реализовать методы интерфейса.
3. Задокументировать код на уровне класса и публичных методов (javadoc).
