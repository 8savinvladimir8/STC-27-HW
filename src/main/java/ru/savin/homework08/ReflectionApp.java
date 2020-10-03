package ru.savin.homework08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс {@code ReflectionApp} демонстрирует возможности Reflection API.
 * @author Savin Vladimir
 */
public class ReflectionApp {
    static final Logger log = LogManager.getLogger(ReflectionApp.class.getName());

    public static void main(String[] args) {
        Cleaner cleaner = new CleanerChooser();

        Object testObject = new TestObject();
        log.info("Обработка объекта: ");
        cleaner.clean(testObject, Arrays.asList("A", "aBool", "anInt"), Arrays.asList("anInteger", "B", "aDbl"));

        log.info("");
        log.info("Обработка карты:");
        Map<String, String> map = new HashMap<>();
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.put("D", "4");
        map.put("E", "5");
        cleaner.clean(map, Arrays.asList("A", "B", "C"), Arrays.asList("A", "E", "D"));
    }


    private static class TestObject {
        private String A = "A";
        private String B = "B";
        private Integer anInteger = 84;
        private boolean aBool = true;
        private int anInt = 48;
        private double aDbl = 4.8;
    }
}
