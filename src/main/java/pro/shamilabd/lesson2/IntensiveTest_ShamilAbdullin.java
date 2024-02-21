package pro.shamilabd.lesson2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Маркерный интерфейс, позволяющий пометить методы-тесты
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IntensiveTest_ShamilAbdullin {
}
