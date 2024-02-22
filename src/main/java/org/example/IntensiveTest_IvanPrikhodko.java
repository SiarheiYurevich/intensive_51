package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Аннтоацией помечаются тестовые методы, для выполнения с помощью метода run(), класса {@code TestRunner_IvanPrikhodko}.
 * @author Иван Приходько
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface IntensiveTest_IvanPrikhodko {

}
