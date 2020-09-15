package ru.savin.homework04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * Класс {@code PersonArray} позволяет сгенерировать массив объектов {@code Person}.
 * @author Savin Vladimir
 */
public class PersonArray {
    static final Logger log = LogManager.getLogger(PersonArray.class.getName());
    private final Random randomizer = SecureRandom.getInstanceStrong();
    private final Person[] persons;

    public PersonArray(int size) throws NoSuchAlgorithmException {
        persons = new Person[size];
    }

    /**
     * Метод генерирует объекты Person и заполняет ими массив.
     * @return массив сгенерированных объектов Person.
     */
    public void generateArray() {
        for (int i = 0; i < persons.length; i++) {
            int age = randomizer.nextInt(100) + 1;

            String name = "";
            int randomName = randomizer.nextInt(9);
            switch(randomName) {
                case 0:
                    name = "Иван";
                    break;
                case 1:
                    name = "Пётр";
                    break;
                case 2:
                    name = "Василий";
                    break;
                case 3:
                    name = "Алексей";
                    break;
                case 4:
                    name = "Александр";
                    break;
                case 5:
                    name = "Мария";
                    break;
                case 6:
                    name = "Анастасия";
                    break;
                case 7:
                    name = "Ирина";
                    break;
                case 8:
                    name = "Наталья";
                    break;
                case 9:
                    name = "Елена";
                    break;
                default:
                    log.error("Имя не найдено.");
                    break;
            }

            String sex;
            // определяем пол в зависимости от имени
            if (randomName < 5) {
                sex = Sex.MAN;
            } else {
                sex = Sex.WOMAN;
            }

            persons[i] = new Person(age, sex, name);
        }
    }

    /**
     * Метод возвращает массив сгенерированных объектов Person.
     * @return массив сгенерированных объектов Person.
     */
    public Person[] getPersons() {
        if (persons[0] == null) {
            log.error("Список персон пуст.");
        } else {
            return Arrays.copyOf(persons, persons.length);
        }
        return new Person[0];
    }
}
