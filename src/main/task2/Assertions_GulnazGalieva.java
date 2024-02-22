package main.task2;

/**
 * Данный класс позволяет сравнить полученный boolean - результат тестового метода
 * с ожидаемым boolean - результатом.
 * @author Гульназ Галиева
 */

public class Assertions_GulnazGalieva {

    /**
     * Сравнивает полученный результат тестового метода на значение true.
     *
     * @param result - полученное boolean - значение проверяемого метода.
     */
    public static void assertTrue (boolean result){
        if (result){
            System.out.println("Тестирование метода завершился успешно");}
        else {
            throw new RuntimeException("Тестирование метода завершился с ошибкой");
        }
     }

    /**
     * Сравнивает полученный результат тестового метода на значение false.
     *
     * @param result - полученное boolean - значение проверяемого метода.
     */
     public static void assertFalse (boolean result){
        if (!result){
            System.out.println("Тестирование метода завершился успешно");}
        else {
            throw new RuntimeException("Тестирование метода завершился с ошибкой");
        }
     }

}
