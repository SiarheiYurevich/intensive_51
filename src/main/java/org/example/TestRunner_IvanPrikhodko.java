package org.example;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс запускающий тесты.
 * @author Иван Приходько
 */
public class TestRunner_IvanPrikhodko {
    /**
     * Коллекия пакетов переданных в конструктор.
     */
    private final List<String> scanPackage;
    /**
     * Коллекция классов для сканирования.
     */
    private List<Class<?>> classes = new ArrayList<>();

    /**
     * @param scanPackage Пакеты для сканирования.
     */
    public TestRunner_IvanPrikhodko(List<String> scanPackage) {
        this.scanPackage = scanPackage;
    }

    /**
     * Производит сканирование пакетов из коллекции scanPackage, и запускает методы помеченные анотацией
     * {@code @IntensiveTest_IvanPrikhodko}.
     */
    public void run()  {
        scan();
        for (var c : classes) {
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                for (Annotation annotation : method.getAnnotations()) {
                    if (annotation.annotationType().equals(IntensiveTest_IvanPrikhodko.class)) {
                        try {
                            method.invoke(c.getDeclaredConstructor().newInstance());
                        } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                                 NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        System.out.println("Тесты прошли успешно!");
    }

    /**
     * Сканирует пакеты из коллекции scanPackage и заполняет коллекцию classes классами,
     * которые находятся в указанных пакетах.
     */
    private void scan() {
        for(String p : scanPackage) {
            classes.addAll(loadClassesFromPackage(p));
        }
    }

    /**
     * Метод сканирует указанный пакет на наличее классов и создает коллекцию с этими классами.
     * @param packageName Название пакета.
     * @return Коллекцию классов пакета.
     */
    private Collection<Class<?>> loadClassesFromPackage(String packageName) {
        List<Class<?>> classList = new ArrayList<>();

        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageURL = classLoader.getResource(packagePath);

        if (packageURL == null) {
            throw new IllegalArgumentException("Package " + packageName + " not found.");
        }

        File packageDir = new File(packageURL.getFile());
        if (packageDir.exists()) {
            File[] files = packageDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".class")) {
                        String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                        Class<?> loadedClass;
                        try {
                            loadedClass = Class.forName(className);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        classList.add(loadedClass);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Package " + packageName + " not found.");
        }

        return classList;
    }
}
