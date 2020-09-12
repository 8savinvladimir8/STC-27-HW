package ru.savin.homework02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс {@code HashTable} представляет реализацию хэш-таблицы (hash-map) c линейным пробированием.
 *
 * @author Savin Vladimir
 */
public class HashTable {
    static final Logger log = LogManager.getLogger(HashTable.class.getName());
    private final DataItem[] hashArray;
    private final int arraySize;
    private final DataItem deletedItem;

    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        deletedItem = new DataItem(-1);
    }

    /**
     * Метод выводит хэш-таблицу в строку.
     */
    public void displayTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("Таблица: ");

        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                sb.append(hashArray[i].getKey()).append(" ");
            } else {
                sb.append("** ");
            }
        }

        log.info(sb);
        log.info("");
    }

    /**
     * Метод возвращает хэш-значение в виде остатка от деления ключа на размер массива.
     * @param key значение ключа для вычисления хэша.
     * @return результат хэширования ключа.
     */
    public int getHash(int key) {
        return key % arraySize;
    }

    /**
     * Метод вставки элемента (предполагается, что таблица не заполнена).
     * @param item элемент данных для вставки в массив.
     */
    public void insert(DataItem item) {
        int key = item.getKey();
        int hashValue = getHash(key);

        // пока не будет найдена пустая ячейка или удалённое значение (-1)
        // переходим к следующей ячейке
        // (при достижении конца таблицы - возвращаемся к началу)
        while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
            ++hashValue;
            hashValue %= arraySize;
        }

        // вставляем элемент в ячейку
        hashArray[hashValue] = item;
    }

    /**
     * Метод удаления элемента.
     * @param key ключ.
     */
    public void delete(int key) {
        int hashVal = getHash(key);

        // двигаемся по ячейкам пока не будет найдена пустая
        // если ключ найден, то удаляем его - ставим метку (-1)
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                hashArray[hashVal] = deletedItem;
            }

            ++hashVal;
            hashVal %= arraySize;
        }
    }

    /**
     * Метод поиска элемента.
     * @param key ключ.
     * @return значение найденного элемента, иначе -1.
     */
    public int find(int key)
    {
        int hashValue = getHash(key);

        // двигаемся по ячейкам пока не будет найдена пустая
        // если ключ найден, то возвращаем его, иначе -1
        while (hashArray[hashValue] != null) {
            if (hashArray[hashValue].getKey() == key) {
                return key;
            }

            ++hashValue;
            hashValue %= arraySize;
        }

        return -1;
    }
}
