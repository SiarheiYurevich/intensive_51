

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;


public class TestRunner_EgorChagorov {
  public static void runTests(Class<?> clazz)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    Object obj = Class.forName("ArrayList_EgorChagorovTest").newInstance();
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.isAnnotationPresent(Task2.IntensiveTest_EgorChagorov.class)) {
        try {
          method.invoke(obj);
          System.out.println("Test " + method.getName() + " passed.");
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Test " + method.getName() + " failed: " + e.getCause());
        }
      }
    }
  }

  public static void main(String[] args)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException {

    runTests(ArrayList_EgorChagorovTest.class);

  }
}
