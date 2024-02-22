package task2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс отвечает за запуск тестов с аннотацией {@code @IntensiveTest_KirillKosykh}.
 * Он сканирует packages
 * Он сканирует указанные packages на предмет тестовых классов и запускает аннотированные тестовые методы
 */
public class TestRunner_KirillKosykh {

    /**
     * Выполняет поиск и запуск тестов в заданных packages
     *
     * @param packages массив packages для сканирования
     */
    public void run(String... packages) {
        for (String packageName : packages) {
            runTestsInPackage(packageName);
        }
    }

    /**
     * Запуск тестов определенном package
     *
     * @param packageName имя package для сканирования на наличие тестов
     */

    private void runTestsInPackage(String packageName) {
        List<Method> intensiveTests = findIntensiveTests(packageName);

        for (Method method : intensiveTests) {
            try {
                System.out.printf("Runnig test: %s%n", method.getName());
                method.invoke(null);
                System.out.println("Test passed successfully");
            } catch (InvocationTargetException e) {
                System.out.printf("Test %s failed%n", e.getCause().getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Сканирует package на наличие методов с аннотацией {@code @IntensiveTest_KirillKosykh}.
     *
     * @param packageName имя package для сканирования
     * @return список методов, удовлетворяющих запросу
     */
    private List<Method> findIntensiveTests(String packageName) {
        List<Method> intensiveTests = new ArrayList<>();

        try {
            String packagePath = packageName.replace(".", "/");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL packageURL = classLoader.getResource(packagePath);

            if (packageURL != null) {
                String packageDirectory = packageURL.getFile();
                File[] files = new File(packageDirectory).listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".class")) {
                            String className = String.format("%s.%s",
                                    packageName, file.getName().substring(0, file.getName().length() - 6));

                            Class<?> currentClass = Class.forName(className);
                            for (Method method : currentClass.getDeclaredMethods()) {
                                if (method.isAnnotationPresent(IntensiveTest_KirillKosykh.class)) {
                                    intensiveTests.add(method);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return intensiveTests;
    }
}
