package org.example;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestRunner_IvanPrikhodko runner = new TestRunner_IvanPrikhodko(List.of("org.example"));
        runner.run();
    }
}