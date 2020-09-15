package ru.savin.homework04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

/**
 * Класс {@code PersonsApp} позволяет генерировать массив объектов {@code Person},
 * а также сортировать массив двумя разными методами.
 * @author Savin Vladimir
 */
public class PersonsApp {
    static final Logger log = LogManager.getLogger(PersonsApp.class.getName());

    public static void main(String[] args) throws NoSuchAlgorithmException {
        PersonArray personArray = new PersonArray(10);;
        Person[] persons = personArray.generateArray();
        PersonComparator personComparator = new PersonComparator();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                log.info("");
                log.info("Введите № операции (exit для выхода):");
                log.info("1 - создать массив персон");
                log.info("2 - сортировка массива персон №1 - 'Arrays.sort'");
                log.info("3 - сортировка массива персон №2 - 'пузырьком' (с исключением для дублей)");

                String line = br.readLine();
                if (line.equals("exit")) return;

                switch (line) {
                    case "1":
                        log.info("Введите целочисленное значение - размер создаваемого массива:");
                        line = br.readLine();
                        personArray = new PersonArray(Integer.parseInt(line));
                        persons = personArray.generateArray();
                        showPersons(persons);
                        break;
                    case "2":
                        PersonSorter arraysSorting = new PersonSortingWithArrays();
                        long startTime = System.nanoTime();
                        persons = arraysSorting.sort(persons, personComparator);
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime);
                        showPersons(persons);
                        log.info("Сортровка заняла: {} наносекунд (разделить на 1000000 для миллисекунд)", duration);
                        break;
                    case "3":
                        PersonSorter bubbleSorting = new PersonBubbleSorting();
                        startTime = System.nanoTime();
                        persons = bubbleSorting.sort(persons, personComparator);
                        endTime = System.nanoTime();
                        duration = (endTime - startTime);
                        showPersons(persons);
                        log.info("Сортровка заняла: {} наносекунд разделить на 1000000 для миллисекунд", duration);
                        break;
                    default:
                        log.error("Введён некорректный номер операции");
                        break;
                }
            }
        } catch (Exception e) {
            log.error(e.fillInStackTrace());
        }
    }

    /**
     * Метод выводит массив объектов {@code Person} на консоль.
     * @param persons массив объектов {@code Person}.
     */
    private static void showPersons(Person[] persons) {
        if (persons[0] == null) {
            log.error("Список персон пуст.");
        } else {
            log.info("");
            log.info("Список персон:");
            for (Person person : persons) {
                log.info(person);
            }
        }
    }
}
