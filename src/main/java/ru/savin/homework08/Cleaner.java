package ru.savin.homework08;

import java.util.Collection;

/**
 * Интерфейс {@code Cleaner} служит для очистки значений полей и проверки наличия полей у объекта.
 * @author Savin Vladimir
 */
public interface Cleaner {
    /**
     * Метод выполняет очистку и вывод указанных полей.
     * @param object объект, поля которого будут обработаны.
     * @param fieldsToClean коллекция ключей для очистки.
     * @param fieldsToOutput коллекция ключей для вывода.
     */
    void clean(Object object, Collection<String> fieldsToClean, Collection<String> fieldsToOutput);

    /**
     * Метод проверяет наличие полей у объекта.
     * @param object объект, поля которого будут обработаны.
     * @param fieldsToCheck коллекция ключей для проверки.
     * @return если ключ найден - true, иначе false.
     */
    boolean checkFields(Object object, Collection<String> fieldsToCheck);
}
