package ru.savin.homework04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс {@code PersonSortingWithArrays} содержит метод сортировки массива объектов {@code Person}.
 * @author Savin Vladimir
 */
public class PersonSortingWithArrays implements PersonSorter {
    static final Logger log = LogManager.getLogger(PersonSortingWithArrays.class.getName());

    /**
     * Метод сортирует массив объектов {@code Person}.
     * @param persons массив объектов {@code Person}.
     * @param personComparator компаратор для объектов {@code Person}.
     * @return отсортированный массив объектов {@code Person}.
     */
    @Override
    public Person[] sort(Person[] persons, Comparator<Person> personComparator) {
        if (persons[0] == null) {
            log.error("Список персон пуст.");
        } else {
            log.info("Отсортированный список персон:");
            Arrays.sort(persons, personComparator);
        }

        return persons;
    }
}
