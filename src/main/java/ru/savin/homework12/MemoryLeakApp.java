package ru.savin.homework12;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс {@code MemoryLeakApp} позволяет продемонстрировать работу Garbage Collector,
 * а также показать результат переполнения памяти HEAP ("кучи") и Metaspace:
 * исключения "OutOfMemoryError: Java Heap Space" и "OutOfMemoryError: Metaspace".
 * @author Savin Vladimir
 */
public class MemoryLeakApp {
    static final Logger log = LogManager.getLogger(MemoryLeakApp.class.getName());

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isExit = false;
            while (!isExit) {
                log.info("");
                log.info("Введите № операции:");
                log.info("1 - демонстрация работы GC и ошибка 'OutOfMemoryError: Java Heap Space'");
                log.info("2 - ошибка 'OutOfMemoryError: Metaspace' - в 'VM Options' конфига нужно добавить: " +
                        "-XX:MaxMetaspaceSize=100m");

                String line = br.readLine();

                switch (line) {
                    case "1":
                        OOMEHeapLeak heapLeak = new OOMEHeapLeak();
                        heapLeak.generateHeapOOME();
                        isExit = true;
                        break;
                    case "2":
                        OOMEMetaspaceLeak metaspaceLeak = new OOMEMetaspaceLeak();
                        metaspaceLeak.generateMetaspaceOOME();
                        isExit = true;
                        break;
                    default:
                        log.error("Введён некорректный номер операции");
                        break;
                }
            }
        }
    }
}
