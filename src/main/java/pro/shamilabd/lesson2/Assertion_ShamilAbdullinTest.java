package pro.shamilabd.lesson2;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for Assertion_ShamilAbdullin.
 */
public class Assertion_ShamilAbdullinTest
{
    @Test
    @IntensiveTest_ShamilAbdullin
    public void assertEqualsTest()
    {
        List<Integer> list1 =  new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 =  new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        Assertion_ShamilAbdullin.assertEquals(list1, list1);
        Assertion_ShamilAbdullin.assertEquals(list1, list2);
        System.out.println("Test with annotation");
    }

    @Test
    public void assertNotEqualsTest()
    {
        List<Integer> list1 =  new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 =  new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);

        Assertion_ShamilAbdullin.assertNotEquals(list1, list2);
        list1.add(4);
        list1.set(3, 2); // but replaced
        Assertion_ShamilAbdullin.assertNotEquals(list1, list2);
        System.out.println("Test without annotation");
    }

    @Test
    @IntensiveTest_ShamilAbdullin
    public void assertBooleansTest()
    {
        Assertion_ShamilAbdullin.assertTrue(true);
        Assertion_ShamilAbdullin.assertFalse(false);
        Assertion_ShamilAbdullin.assertTrue(Boolean.TRUE);
        Assertion_ShamilAbdullin.assertFalse(Boolean.FALSE);
        System.out.println("Test with annotation");
    }

    @Test
    public void assertNotNullTest()
    {
        Assertion_ShamilAbdullin.assertNotNull(new Object());
        System.out.println("Test without annotation");
    }

    @Test
    @IntensiveTest_ShamilAbdullin
    public void notReflectionTestMethod()
    {
        Assertion_ShamilAbdullin.assertTrue(true);
        Assertion_ShamilAbdullin.assertFalse(Boolean.FALSE);
        System.out.println("Test with annotation but not finished with Test");
    }
}
