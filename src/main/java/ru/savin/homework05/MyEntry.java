package ru.savin.homework05;

/**
 * Экземпляр класса {@code MyEntry} представляет пару 'ключ, значение' - элемент карты.
 * @param <K> ключ элемента.
 * @param <V> значение ключа.
 * @author Savin Vladimir
 */
public class MyEntry<K, V> {
    private final K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
