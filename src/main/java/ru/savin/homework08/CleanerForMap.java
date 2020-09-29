package ru.savin.homework08;

import java.util.Collection;
import java.util.Map;

/**
 * Интерфейс {@code CleanerForMap} служит для очистки значений ключей и проверки наличия ключей в карте.
 * @param <K> тип ключей карты.
 * @author Savin Vladimir
 */
public interface CleanerForMap<K> {
    /**
     * Метод очищает указанные ключи карты.
     * @param map карта для обработки.
     * @param keysToClean коллекция ключей для очистки.
     * @param keysToOutput коллекция ключей для вывода.
     */
    void clean(Map<K, ?> map, Collection<K> keysToClean, Collection<K> keysToOutput);

    /**
     * Метод проверяет наличие ключей в карте.
     * @param map карта для обработки.
     * @param keysToCheck коллекция ключей для проверки.
     * @return если ключ найден - true, иначе false.
     */
    boolean checkKeys(Map<K, ?> map, Collection<K> keysToCheck);
}
