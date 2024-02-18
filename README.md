# intensive_51

Задание 1.


1) Реализовать свой динамический список (аналог ArrayList, не потокобезопасный)
   Общие требования:
- клонировать репозиторий https://github.com/SiarheiYurevich/intensive_51
- от ветки develop создать ветку task_1_name_surname, работать только в своей ветке, создать pull-request, не забываем .gitignore

- создать интерфейс IntensiveList и класс ArrayList_NameSurname, имплементирующий данный интерфейс:

  public interface IntensiveList<E> {
  int size();
  void add(E element);
  void add(int index, E element);
  E get(int index);
  E set(int index, E element);
  E remove(int index);
  void clear();//удаляем все элементы, capacity приводим к дефолтному
  void quickSort(Comparator<E> comparator);//реализуем быструю сортировку, дефолт по возрастанию
  boolean isSorted();
  void split(int size);//обрезаем список до указанного размера
  }
- реализовать методы интерфейса.
- код должен быть задокументирован на уровне класса и публичных методов (javadoc).


2) Читаем Адитья Бхаргава "Грокаем алгоритмы", полностью.