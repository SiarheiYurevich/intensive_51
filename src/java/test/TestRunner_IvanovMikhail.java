package test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class TestRunner_IvanovMikhail {

    /**
     * Метод, сканирующий package на наличие методов с аннотацией @IntensiveTest_IvanovMikhail
     * @param packageName
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void run(String packageName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Package myPackage = ClassLoader.getSystemClassLoader().getDefinedPackage(packageName);
        String pkgName = myPackage.getName();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = pkgName.replace('.', '/');
        URL resource = classLoader.getResource(path);
        File directory = new File(resource.getFile());
        if (!directory.exists()) {
            throw new IllegalArgumentException("Package " + packageName + " not found");
        }
        String[] files = directory.list();
        assert files != null;
        for (String file : files) {
            if (file.endsWith(".class")) {
                String className = pkgName + '.' + file.substring(0, file.length() - 6);
                Class<?> clazz = Class.forName(className);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(IntensiveTest_IvanovMikhail.class)) {
                        Object obj = clazz.getDeclaredConstructor().newInstance();
                        try {
                            System.out.println("------------------ \nLaunch \"" + method.getName()
                                    + "\" from class: "
                                    + file.substring(0, file.length() - 6));
                            method.invoke(obj);
                        } catch (InvocationTargetException e) {
                            System.err.println("Test invocation is failed: " + method.getName());
                            e.getCause().printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
