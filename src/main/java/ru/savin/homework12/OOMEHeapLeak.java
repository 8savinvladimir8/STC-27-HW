package ru.savin.homework12;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс {@code OOMEHeapLeak} демонстрирует работу Garbage Collector
 * и вывод ошибки "OutOfMemoryError: Java Heap Space".
 * @author Savin Vladimir
 */
public class OOMEHeapLeak {
    static final Logger log = LogManager.getLogger(OOMEHeapLeak.class.getName());

    /**
     * Метод создаёт массивы, заполняя "кучу" (HEAP). Garbage Collector чистит "кучу" от массивов,
     * потерявших ссылку.
     * Затем в ArrayList<Double[]> добавляются массивы, пока "куча" не переполнится
     * и программа не завершится с исключением "OutOfMemoryError: Java heap space".
     */
    public void generateHeapOOME() {
        log.info("Очистка памяти с помощью Garbage Collector:");
        for (int i = 0; i < 50; i++) {
            long busyMemory1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            // создаём каждый раз новые экземпляры массивов, "освобождая" для уборки GC ранее созданные
            Double[] pollution = new Double[Integer.MAX_VALUE / 100];
            long busyMemory2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            log.info("На шаге {} занято памяти: {} байт", i + 1, busyMemory2);
            if (busyMemory1 > busyMemory2) {
                log.info("==========> Garbage Collector сработал <==========");
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                log.error(e.fillInStackTrace());
            }
        }

        log.info("");
        log.info("Завершение работы программы с ошибкой - OutOfMemoryError: Java Heap Space");
        List<Double[]> polluters = new ArrayList<>();

        while (true) {
            polluters.add(new Double[Integer.MAX_VALUE / 100]);
            log.info("Занято памяти: {} байт", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.error(e.fillInStackTrace());
            }
        }
    }
}
