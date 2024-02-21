package pro.shamilabd.lesson2;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Класс позволяет просканировать переданный пакет на наличие методов, помеченных аннотацией IntensiveTest_ShamilAbdullin
 * и имена которых заканчиваются на Test и запустить эти методы.
 */
public class TestRunner_ShamilAbdullin {

    private final String packages;

    public TestRunner_ShamilAbdullin(String packages) {
        this.packages = packages;
    }

    /**
     * Ищет методы, помеченные аннотацией IntensiveTest_ShamilAbdullin
     * и имена которых заканчиваются на Test и запускает эти методы
     */
    public void run() {
        Set<Class<?>> allTests = findAllTests();
        List<Method> methodsWithAnnotation = new ArrayList<>();
        for (Class<?> element : allTests) {
            Method[] allMethods = element.getMethods();
            methodsWithAnnotation.addAll(Arrays.stream(allMethods)
                    .filter(e -> e.isAnnotationPresent(IntensiveTest_ShamilAbdullin.class))
                    .toList());
        }

        for (Method method : methodsWithAnnotation) {
            if (!method.getName().endsWith("Test")) {
                continue;
            }
            try {
                Object object = method.getDeclaringClass().getDeclaredConstructor().newInstance();
                method.invoke(object);
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("Failed.");
                throw new RuntimeException(e);
            }
        }
        System.out.println("TestRunner passed.");
    }

    private Set<Class<?>> findAllTests() {
        Reflections reflections = new Reflections(packages, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }
}
