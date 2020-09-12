package ru.savin.homework02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Класс {@code HashTableApp} позволяет создать хэш-таблицу с целыми числами и работать с ней.
 *
 * @author Savin Vladimir
 */
public class HashTableApp {
    static final Logger log = LogManager.getLogger(HashTableApp.class.getName());

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // ввод размеров таблицы
            log.info("Введите размер хэш-таблицы: ");
            int size = Integer.parseInt(br.readLine());

            log.info("Введите кол-во эл-ов (меньше размера таблицы) для заполнения таблицы: ");
            int n = Integer.parseInt(br.readLine());

            int keysPerCell = 10;

            // создание таблицы
            HashTable hashTable = new HashTable(size);

            int key = 0;
            DataItem dataItem;

            // заполнение таблицы данными
            for (int i = 0; i < n; i++) {
                key = (int)(java.lang.Math.random() * keysPerCell * size);
                dataItem = new DataItem(key);
                hashTable.insert(dataItem);
            }

            while (true) {
                log.info("");
                log.info("Введите № операции (exit для выхода):");
                log.info("1 - показать элементы таблицы");
                log.info("2 - добавить элемент в таблицу");
                log.info("3 - удалить элемент из таблицы");
                log.info("4 - найти элемент в таблице");

                String line = br.readLine();
                if (line.equals("exit")) return;

                switch(line)
                {
                    case "1":
                        hashTable.displayTable();
                        break;
                    case "2":
                        log.info("Введите значение ключа для вставки: ");
                        key = Integer.parseInt(br.readLine());
                        dataItem = new DataItem(key);
                        hashTable.insert(dataItem);
                        break;
                    case "3":
                        log.info("Введите значение ключа для удаления: ");
                        key = Integer.parseInt(br.readLine());
                        hashTable.delete(key);
                        break;
                    case "4":
                        log.info("Введите значение ключа для поиска: ");
                        key = Integer.parseInt(br.readLine());

                        if (hashTable.find(key) != -1) {
                            log.info("Ключ '{}' найден.", key);
                        } else {
                            log.info("Ключ '{}' не найден.", key);
                        }
                        break;
                    default:
                        log.info("Введён некорректный номер операции");
                        break;
                }
            }
        } catch (Exception e) {
            log.error(e.fillInStackTrace());
        }
    }
}
