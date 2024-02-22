package com.igorpopovich.task2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Класс для запуска тестовых методов, аннотированных аннотацией @IntensiveTest_NameSurname.
 */

public class TestRunner_IgorPopovich {

    /**
     * Запускает тестовые методы, аннотированные аннотацией @IntensiveTest_NameSurname.
     *
     * @param className полное имя класса для сканирования
     */

    public void run(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            for (Method method : clazz.getDeclaredMethods()) {
                Annotation annotation = method.getAnnotation(IntensiveTest_IgorPopovich.class);
                if (annotation != null) {
                    try {
                        method.setAccessible(true);
                        method.invoke(null);
                        System.out.println("Test passed: " + method.getName());
                    } catch (Exception e) {
                        System.out.println("Test failed: " + method.getName());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        }
    }
}
