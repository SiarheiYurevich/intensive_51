package com.aston.task2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that scans the specified packages for methods annotated with the
 * {@link IntensiveTest_EvgeniyKanareikin} annotation and executes the test methods.
 *
 * @author Evgeniy Kanareikin
 */
public class TestRunner_EvgeniyKanareikin {
    /**
     * The packages to scan for test classes.
     */
    private final String packagesToScan;

    /**
     * Constructs a new test runner with the specified packages to scan.
     *
     * @param packagesToScan the packages to scan for test classes
     */
    public TestRunner_EvgeniyKanareikin(String packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    /**
     * Runs all the test methods in the specified packages.
     */
    public void run() {
        List<Class<?>> testClasses = new ArrayList<>();
        for (String packageName : packagesToScan.split(",")) {
            try {
                testClasses.addAll(getClassesForPackage(packageName));
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Error scanning package: " + packageName);
                e.printStackTrace();
            }
        }
        for (Class<?> testClass : testClasses) {
            Method[] methods = testClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(IntensiveTest_EvgeniyKanareikin.class)) {
                    try {
                        Object object = method.getDeclaringClass().getDeclaredConstructor().newInstance();
                        method.invoke(object);
                        System.out.println("Test passed: " + method.getName());
                    } catch (IllegalAccessException | InvocationTargetException |
                             InstantiationException | NoSuchMethodException e) {
                        System.out.println("Error running test: " + method.getName());
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * Gets all the classes in the specified package.
     *
     * @param packageName the package to scan
     * @return a list of classes in the specified package
     * @throws ClassNotFoundException if a class cannot be found
     * @throws IOException            if an I/O error occurs
     */
    private static List<Class<?>> getClassesForPackage(String packageName) throws ClassNotFoundException, IOException {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(path);
        if (resource == null) {
            throw new ClassNotFoundException("Package not found: " + packageName);
        }
        File directory = new File(resource.getFile());
        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isDirectory()) {
                    classes.addAll(getClassesForPackage(packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                    classes.add(Class.forName(className));
                }
            }
        } else {
            throw new IOException("Package directory not found: " + directory);
        }
        return classes;
    }
}