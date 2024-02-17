import java.lang.reflect.Method;


public class TestRunner_EgorChagorov {
  public static void runTests(Class<?> clazz)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    Object obj = Class.forName("ArrayList_EgorChagorovTest").newInstance();
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.isAnnotationPresent(IntensiveTest_EgorChagorov.class)) {
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
