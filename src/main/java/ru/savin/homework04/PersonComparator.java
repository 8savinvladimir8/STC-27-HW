package ru.savin.homework04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

/**
 * Класс {@code PersonComparator} реализует компаратор для объектов Person.
 * @author Savin Vladimir
 */
public class PersonComparator implements Comparator<Person> {
    static final Logger log = LogManager.getLogger(PersonComparator.class.getName());

    /**
     * Метод сравнивает значения полей "пол", "возраст" и "имя" переденных ему объектов {@code Person}.
     * @param obj1 Передаёт методу первый объект {@code Person}.
     * @param obj2 Передаёт методу второй объект {@code Person}.
     * @return возвращает результат сравнения полей "пол", "возраст" и "имя", переданных методу объектов {@code Person}.
     */
    @Override
    public int compare(Person obj1, Person obj2) {
        int comparison = obj2.getSex().compareTo(obj1.getSex());
        if (comparison == 0) {
            comparison = obj2.getAge() - obj1.getAge();
        }
        if (comparison == 0) {
            comparison =  obj1.getName().compareTo(obj2.getName());
        }
        return comparison;
    }
}
