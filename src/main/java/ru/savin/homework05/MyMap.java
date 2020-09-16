package ru.savin.homework05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Класс {@code HashTable} представляет реализацию хэш-таблицы (hash-map) с использованием обобщённых типов и методов.
 * @param <K> ключ элемента.
 * @param <V> значение ключа.
 * @author Savin Vladimir
 */
public class MyMap<K, V> {
    static final Logger log = LogManager.getLogger(MyMap.class.getName());
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    @SuppressWarnings("unchecked")
    private MyEntry<K, V>[] values = new MyEntry[DEFAULT_CAPACITY];

    /**
     * Метод вставки элемента.
     * @param key ключ.
     * @param value знчение.
     */
    public void put(K key, V value) {
        boolean insert = true;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i].setValue(value);
                insert = false;
            }
        }
        if (insert) {
            ensureCapacity();
            values[size++] = new MyEntry<>(key, value);
        }
    }

    /**
     * Метод проверки и увеличения ёмкости заполняемого массива.
     */
    private void ensureCapacity() {
        if (size == values.length) {
            int newSize = values.length * 2;
            values = Arrays.copyOf(values, newSize);
        }
    }

    /**
     * Метод возвращает значение элемента по ключу.
     * @param key ключ.
     * @return значение элемента.
     */
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null && values[i].getKey().equals(key)) {
                return values[i].getValue();
            }
        }
        return null;
    }

    /**
     * Метод удаляет элемент по ключу.
     * @param key ключ.
     */
    public void remove(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i] = null;
                size--;
                condenseArray(i);
            }
        }
    }

    /**
     * Метод убирает "разрывы" в массиве при удалении элементов ("уплотняет" элементы массива).
     * @param start
     */
    private void condenseArray(int start) {
        for (int i = start; i < size; i++) {
            values[i] = values[i + 1];
        }
    }
}
