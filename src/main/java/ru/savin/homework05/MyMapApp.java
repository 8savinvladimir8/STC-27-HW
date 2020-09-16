package ru.savin.homework05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс {@code HashTable} позволяет сравнить работу пользовательской реализации Map и стандартной.
 * @author Savin Vladimir
 */
public class MyMapApp {
    static final Logger log = LogManager.getLogger(MyMapApp.class.getName());

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // создание таблицы
            MyMap<String, Integer> myMap = new MyMap<>();
            Map<String, Integer> map = new HashMap<>();

            // заполнение таблицы данными
            myMap.put("Ваня", 1);
            map.put("Ваня", 1);
            log.info("Добавили элемент: \"Ваня\", 1");
            myMap.put("Вася", 2);
            map.put("Вася", 2);
            log.info("Добавили элемент: \"Вася\", 2");
            myMap.put("Петя", 3);
            map.put("Петя", 3);
            log.info("Добавили элемент: \"Петя\", 3");
            myMap.put("Паша", 4);
            map.put("Паша", 4);
            log.info("Добавили элемент: \"Паша\", 4");
            myMap.put("Коля", 5);
            map.put("Коля", 5);
            log.info("Добавили элемент: \"Коля\", 5");

            while (true) {
                log.info("");
                log.info("Введите № операции (exit для выхода):");
                log.info("1 - добавить элемент в таблицу");
                log.info("2 - удалить элемент из таблицы");
                log.info("3 - найти элемент в таблице");

                String line = br.readLine();
                if (line.equals("exit")) return;

                String key = "";
                int value;

                switch(line)
                {
                    case "1":
                        log.info("Введите ключ для добавления (строка): ");
                        key = br.readLine();
                        log.info("Введите значение для добавления (целое число): ");
                        value = Integer.parseInt(br.readLine());
                        // своя реализация
                        myMap.put(key, value);
                        // стандартная реализация
                        map.put(key, value);
                        log.info("Элемент добавлен.");
                        break;
                    case "2":
                        log.info("Введите значение ключа для удаления (строка): ");
                        key = br.readLine();
                        // своя реализация
                        myMap.remove(key);
                        // стандартная реализация
                        map.remove(key);
                        log.info("Элемент удалён.");
                        break;
                    case "3":
                        log.info("Введите значение ключа для поиска (строка): ");
                        key = br.readLine();
                        // своя реализация
                        if (myMap.get(key) != null) {
                            log.info("Ключ \"{}\" найден, значение: '{}'.", key, myMap.get(key));
                        } else {
                            log.info("Ключ \"{}\" не найден.", key);
                        }
                        // стандартная реализация
                        if (map.get(key) != null) {
                            log.info("Ключ \"{}\" найден, значение: '{}'.", key, map.get(key));
                        } else {
                            log.info("Ключ \"{}\" не найден.", key);
                        }
                        break;
                    default:
                        log.info("Введён некорректный номер операции");
                        break;
                }
            }
        } catch (Exception e) {
            log.error(e.fillInStackTrace());
        }
    }
}
