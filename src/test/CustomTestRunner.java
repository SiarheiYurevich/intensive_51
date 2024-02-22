import arrayListEvgenSamsonnikTest.ArrayListEvgenSamsonnikTest;
import arrayListEvgenSamsonnikTest.IntensiveTestSamsonnikEvgen;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTestRunner {

    //Enter-point method, that starting findClasses method by package name
    public void runTest(String packageName) {
        System.out.println("The Custom Test Runner was started!");
        findMethods(findClasses(packageName));
    }

//    This method searches classes by package name, and puts them into the collection. On first step, we have class
//    names in String, and then, in the second step, with getClass method we transformed them into Class
    private List<Class> findClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName);
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(innerData -> innerData.endsWith(".class"))
                .map(className -> getClass(className, packageName))
                .collect(Collectors.toList());
    }

    //Here we are invoking methods in classes, that were marked with custom @IntensiveTestSamsonnikEvgen annotation,
    //and displaying messages into the terminal
    private void findMethods(List<Class> classSet) {
        for (Class classFromSet : classSet) {
            if (classFromSet == null) continue;
            Method[] methods = classFromSet.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(IntensiveTestSamsonnikEvgen.class)) {
                    try {
                        method.invoke(new ArrayListEvgenSamsonnikTest());
                    } catch (IllegalAccessException | InvocationTargetException exception) {
                        System.out.println("\n" + "THERE WAS AN EXCEPTION! " +
                                "In method " + method.getName() + " from class " + classFromSet.getName() + "\n");
                    }
                    System.out.println("The method " +  method.getName()
                            + " from class " + classFromSet.getName() + " was passed");
                }
            }
        }
        System.out.println("Done!");
    }

    //Due this method we are performing String class name into the Class
    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName +"." + className.substring(0, className.lastIndexOf(".")));
        } catch (ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
