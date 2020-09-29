package ru.savin.homework08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Map;

/**
 * Класс {@code MapCleaner} реализует интерфейс CleanerForMap.
 * @author Savin Vladimir
 */
public class MapCleaner implements CleanerForMap<String> {
    static final Logger log = LogManager.getLogger(MapCleaner.class.getName());

    @Override
    public void clean(Map<String, ?> map, Collection<String> keysToClean, Collection<String> keysToOutput) {
        if (!checkKeys(map, keysToClean)) {
            throw new IllegalArgumentException("Карта не содержит ключа, указанного для очистки.");
        }
        if (!checkKeys(map, keysToOutput)) {
            throw new IllegalArgumentException("Карта не содержит ключа, указанного для вывода.");
        }
        cleanKeys(map, keysToClean);
        outputKeys(map, keysToOutput);
    }

    @Override
    public boolean checkKeys(Map<String, ?> map, Collection<String> keysToCheck) {
        for (String key : keysToCheck) {
            if (!map.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод очищает ключи карты, указанные в переданной коллекции.
     * @param map карта для обработки.
     * @param keysToClean коллекция ключей для очистки.
     */
    private void cleanKeys(Map<String, ?> map, Collection<String> keysToClean) {
        for (String key : keysToClean) {
            map.replace(key, null);
        }
    }

    /**
     * Метод выводит ключи карты, указанные в переданной коллекции.
     * @param map карта для обработки.
     * @param keysToOutput коллекция ключей для вывода.
     */
    private void outputKeys(Map<String, ?> map, Collection<String> keysToOutput) {
        for (String key : keysToOutput) {
            Object value = map.get(key);
            if (value != null) {
                log.info("Ключ: '{}', значение: {}", key, value.toString());
            } else {
                log.info("Ключ: '{}', значение: null", key);
            }
        }
    }
}
