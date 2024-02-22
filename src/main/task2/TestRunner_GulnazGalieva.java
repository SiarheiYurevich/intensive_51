package main.task2;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Данный класс сканирует указанный пакет на наличие методов,
 * аннотированных аннотацией @IntensiveTest_GulnazGalieva,
 * и выполняет проверку тестов - запускает тестовые методы.
 * Под пакеты не проверяет. Необходимо передать имя пакета, где находятся тестовые классы.
 * Если в пакете нет методов,аннотированных аннотацией @IntensiveTest_GulnazGalieva,
 * выбрасывает NullPointerException.
 *
 * @author Гульназ Галиева
 */
public class TestRunner_GulnazGalieva {

    /**
     * Сканирует указанный пакет на наличие методов, аннотированных аннотацией @IntensiveTest_GulnazGalieva,
     * запускает и выполняет проверку тестов
     *
     * @param packageName - имя пакета для сканирования.
     */
    public static void run(String packageName)  {
        TestRunner_GulnazGalieva testRunner_gulnazGalieva = new TestRunner_GulnazGalieva();
        Set <Class> classes = testRunner_gulnazGalieva.findAllClassesUsingClassLoader(packageName);
        for (Class cls:classes) {
            Method[] methods = cls.getDeclaredMethods();
            for (Method method:methods) {
                IntensiveTest_GulnazGalieva  annotation = method.getAnnotation(IntensiveTest_GulnazGalieva.class);
                if (annotation != null){
                    try{Object obj= cls.getConstructor().newInstance();
                        method.invoke(obj);
                    } catch (InstantiationException | NoSuchMethodException | InvocationTargetException
                             | IllegalAccessException e) {
                            e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Возвращает Set классов, которые содержутся в заданном пакете.
     *
     * @param packageName - имя пакета для получения потока классов.
     */
    private Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }
    /**
     * Возвращает: объект Class для класса с указанным именем.
     * Выдает: ClassNotFoundException – если класс не может быть найден
     *
     * @param className - имя класса, который содержится в пакете.
     * @param packageName - имя пакета.
     *
     */
    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}





