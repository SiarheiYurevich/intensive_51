package ru.schung;

import test.TestRunner_IvanovMikhail;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestRunner_IvanovMikhail testRunner = new TestRunner_IvanovMikhail();
        testRunner.run("test");

    }


}