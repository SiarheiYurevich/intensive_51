package pro.shamilabd;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class TestRunner_ShamilAbdullin {

    private final String packages;

    public TestRunner_ShamilAbdullin(String packages) {
        this.packages = packages;
    }

    public void findAndRunTests() {
        Set<Class<?>> allTests = findAllTests();
        List<Method> methodsWithAnnotation = new ArrayList<>();
        for(Class element : allTests) {
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
                Class<?> classForInvoke = Class.forName(method.getClass().getName());
                method.invoke(classForInvoke);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Set<Class<?>> findAllTests() {
        // TODO: fix test package scan
//        Reflections reflections = new Reflections(packages, new SubTypesScanner(false));
//        return new HashSet<>(reflections.getSubTypesOf(Object.class));
        Reflections reflections = new Reflections(packages);
        return reflections.getTypesAnnotatedWith(IntensiveTest_ShamilAbdullin.class);
    }

    public static void main(String[] args) {
        TestRunner_ShamilAbdullin test = new TestRunner_ShamilAbdullin("pro.shamilabd");
        test.findAndRunTests();
    }
}
