package ru.savin.homework08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Класс {@code FieldCleanerDisplayer} предназначен для очистки и вывода поля переданного объекта.
 * @author Savin Vladimir
 */
public class FieldCleanerDisplayer {
    static final Logger log = LogManager.getLogger(FieldCleanerDisplayer.class.getName());

    /**
     * Метод очищает значение поля оъекта.
     * @param object переданный для обработки объект.
     * @param field поле объекта для очистки значения.
     */
    public void cleanField(Object object, Field field) {
        try {
            field.setAccessible(true);
            switch (field.getType().getName()) {
                case "boolean":
                    field.setBoolean(object, false);
                    break;
                case "byte":
                    field.setByte(object, (byte) 0);
                    break;
                case "char":
                    field.setChar(object, (char) 0);
                    break;
                case "short":
                    field.setShort(object, (short) 0);
                    break;
                case "int":
                    field.setInt(object, 0);
                    break;
                case "long":
                    field.setLong(object, 0);
                    break;
                case "float":
                    field.setFloat(object, 0.0f);
                    break;
                case "double":
                    field.setDouble(object, 0.0);
                    break;
                default:
                    // ссылочный тип
                    field.set(object, null);
                    break;
            }
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            log.error("Очистка поля '{}' не сработала.", field.getName());
        }
    }

    /**
     * Метод выводит значение поля объекта.
     * @param object переданный для обработки объект.
     * @param field поле объекта для вывода значения.
     */
    public void outputField(Object object, Field field) {
        try {
            field.setAccessible(true);
            switch (field.getType().getName()) {
                case "boolean":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getBoolean(object));
                    break;
                case "byte":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getByte(object));
                    break;
                case "char":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getChar(object));
                    break;
                case "short":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getShort(object));
                    break;
                case "int":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getInt(object));
                    break;
                case "long":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getLong(object));
                    break;
                case "float":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getFloat(object));
                    break;
                case "double":
                    log.info("Имя поля: '{}', значение: {}", field.getName(), field.getDouble(object));
                    break;
                default:
                    // ссылочный тип
                    Object value = field.get(object);
                    if (value != null) {
                        log.info("Имя поля: '{}', значение: {}", field.getName(), value.toString());
                    } else {
                        log.info("Имя поля: '{}', значение: null", field.getName());
                    }
                    break;
            }
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            log.error("Вывод поля '{}' не сработал.", field.getName());
        }
    }
}
