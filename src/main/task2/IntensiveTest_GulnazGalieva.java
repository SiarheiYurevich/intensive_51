package main.task2;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Данная аннотация позволяет идентифицировать метод test для тестирования класса ArrayList_GulnazGalieva
 * @author Гульназ Галиева
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveTest_GulnazGalieva {
}
