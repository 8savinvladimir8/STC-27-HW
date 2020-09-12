package ru.savin.homework02;

/**
 * Экземпляр класса {@code DataItem} представляет один элемент данных, который будет помещён в ячейку массива.
 *
 * @author Savin Vladimir
 */
public class DataItem {
    private final int item;

    public DataItem(int item) {
        this.item = item;
    }

    /**
     * Метод возвращает ключ.
     * @return ключ.
     */
    public int getKey() {
        return item;
    }
}
