package ru.savin.homework04;

import java.util.Comparator;

/**
 * Интерфейс {@code PersonSorter} определяет функционал для реализации сортировки массива объектов {@code Person}.
 * @author Savin Vladimir
 */
public interface PersonSorter {
    Person[] sort(Person[] persons, Comparator<Person> comparator);
}
