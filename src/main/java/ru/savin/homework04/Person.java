package ru.savin.homework04;

/**
 * Объект класса {@code Person} хранит данные о персоне.
 * @author Savin Vladimir
 */
public class Person {
    private final int age;
    private final String sex;
    private final String name;

    public Person(int age, String sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Пол='" + sex + '\'' +
                ", возраст=" + age +
                ", имя='" + name + '\'';
    }
}
