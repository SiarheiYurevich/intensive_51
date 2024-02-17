public class Assertions_EgorChagorov {

  public static <T> void assertEquals(IntensiveList<T> expected, IntensiveList<T> actual) {
    if (!expected.equals(actual)) {
      throw new AssertionError("Expected: " + expected + " but was: " + actual);
    }
  }
}
