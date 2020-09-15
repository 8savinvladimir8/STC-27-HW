package ru.savin.homework04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс {@code PersonBubbleSorting} содержит метод сортировки массива объектов {@code Person}.
 * @author Savin Vladimir
 */
public class PersonBubbleSorting implements PersonSorter {
    static final Logger log = LogManager.getLogger(PersonBubbleSorting.class.getName());

    /**
     * Метод сортирует массив объектов {@code Person} (сортировка 'пузырьком').
     * @param persons массив объектов {@code Person}.
     * @param personComparator компаратор для объектов {@code Person}.
     * @return отсортированный массив объектов {@code Person}.
     */
    @Override
    public Person[] sort(Person[] persons, Comparator<Person> personComparator) {
        if (persons[0] == null) {
            log.error("Список персон пуст.");
        } else {
            Set<String> personEquals = new HashSet<>();
            log.info("Отсортированный список персон:");

            for (int i = 1; i <= persons.length - 1; i++) {
                for (int j = 0; j <= persons.length - 1 - i; j++) {
                    if (personComparator.compare(persons[j], persons[j + 1]) > 0) {
                        Person temp = persons[j];
                        persons[j] = persons[j + 1];
                        persons[j + 1] = temp;
                    }

                    // если Person совпадают, то бросаем исключение
                    if (personComparator.compare(persons[j], persons[j + 1]) == 0) {
                        try {
                            throw new PersonsEqualsException("Персоны совпадают: ");
                        } catch (PersonsEqualsException e) {
                            // выводим сообщения только для тех дублей, для которых не выводили раньше
                            if (personEquals.add(persons[j].getSex() + persons[j].getAge() + persons[j].getName())) {
                                log.error(e.fillInStackTrace());
                                log.error("Пол='{}', возраст={}, имя='{}'",
                                        persons[j].getSex(), persons[j].getAge(), persons[j].getName());
                            }
                        }
                    }
                }
            }
        }

        return persons;
    }
}
