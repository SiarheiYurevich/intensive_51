package task_2;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRunner_MikhailShchechin {
    /**
     * Выполняет поиск и запуск тестов в указанных пакетах
     *
     * @param packageNames имя пакетов сканирования
     */
    public void run(String... packageNames) {
        List<Method> testMethods = Stream.of(packageNames)
                .map(TestRunner_MikhailShchechin::getMethodsInPackage)
                .flatMap(List::stream)
                .filter(method -> method.isAnnotationPresent(IntensiveTest_MikhailShchechin.class))
                .collect(Collectors.toList());

        testMethods.forEach(method -> {
            try {
                method.invoke(null);
                System.out.println("Test passed: " + method.getName());
            } catch (Exception e) {
                System.out.println("Test failed: " + method.getName());
            }
        });
    }

    /**
     * Сканирует пакет на предмет методов.
     * @param packageName имя пакета сканирования
     * @return возвращает список методов
     */
    private static List<Method> getMethodsInPackage(String packageName) {
        List<Method> testMethods = new ArrayList<>();
        try {
            String packagePath = packageName.replace(".", "/");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL packageURL = classLoader.getResource(packagePath);

            if (packageURL != null) {
                String packageDirectory = packageURL.getFile();
                File[] files = new File(packageDirectory).listFiles();

                if (files != null){
                    for (File file: files) {
                        if (file.isFile() && file.getName().endsWith(".class")) {
                            String className = String.format("%s.%s",
                                    packageName, file.getName().substring(0, file.getName().length() - 6));
                            Class<?> currentClass = Class.forName(className);
                            for (Method method: currentClass.getDeclaredMethods()) {
                                if(method.isAnnotationPresent(IntensiveTest_MikhailShchechin.class)){
                                    testMethods.add(method);
                                }
                            }
                        }
                    }
                }
            }
        }catch(ClassNotFoundException e){
            throw  new RuntimeException(e);
        }
        return testMethods;
    }
}
