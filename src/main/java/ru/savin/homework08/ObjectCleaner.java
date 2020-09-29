package ru.savin.homework08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс {@code ObjectCleaner} реализует интерфейс Cleaner
 * и служит для очистки и вывода указанных полей объекта.
 * @author Savin Vladimir
 */
public class ObjectCleaner implements Cleaner {
    static final Logger log = LogManager.getLogger(ObjectCleaner.class.getName());
    private final FieldCleanerDisplayer fieldCleanerDisplayer;

    public ObjectCleaner(FieldCleanerDisplayer fieldCleanerDisplayer) {
        this.fieldCleanerDisplayer = fieldCleanerDisplayer;
    }

    @Override
    public void clean(Object object, Collection<String> fieldsToClean, Collection<String> fieldsToOutput) {
        if (!checkFields(object, fieldsToClean)) {
            throw new IllegalArgumentException("Объект не содержит поля, указанного для очистки.");
        }
        if (!checkFields(object, fieldsToOutput)) {
            throw new IllegalArgumentException("Объект не содержит поля, указанного для вывода.");
        }
        Class<?> objectClass = object.getClass();
        // чистим поля
        for (String name : fieldsToClean) {
            try {
                fieldCleanerDisplayer.cleanField(object, objectClass.getDeclaredField(name));
            } catch (NoSuchFieldException e) {
                log.error("Поле '{}' не было найдено при проверке.", name);
                throw new IllegalArgumentException("Поле для очистки '" + name + "' не было найдено у объекта.");
            }
        }
        // выводим поля
        for (String name : fieldsToOutput) {
            try {
                fieldCleanerDisplayer.outputField(object, objectClass.getDeclaredField(name));
            } catch (NoSuchFieldException e) {
                log.error("Поле '{}' не было найдено при проверке.", name);
                throw new IllegalArgumentException("Поле для вывода '" + name + "' не было найдено у объекта.");
            }
        }
    }

    @Override
    public boolean checkFields(Object object, Collection<String> fieldsToCheck) {
        Class<?> objectClass = object.getClass();
        List<Field> fields = Arrays.asList(objectClass.getDeclaredFields());
        List<String> fieldNames = new ArrayList<>();
        fields.stream().map(Field::getName).collect(Collectors.toCollection(() -> fieldNames));
        for (String name : fieldsToCheck) {
            if (!fieldNames.contains(name)) {
                return false;
            }
        }
        return true;
    }
}
