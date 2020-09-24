package ru.savin.homework07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Класс {@code GameOfLifeApp} позволяет запустить игру "Жизнь" с параметрами:
 * - имя входного файла
 * - имя финального файла
 * - количество поколений.
 * @author Savin Vladimir
 */
public class GameOfLifeApp {
    static final Logger log = LogManager.getLogger(GameOfLifeApp.class.getName());

    public static void main(String[] args) throws Exception {
        GameOfLifeApp gameOfLifeApp = new GameOfLifeApp();
        gameOfLifeApp.checkArguments(args);

        // читаем первое поколение из файла
        GameOfLife gameOfLife = new GameOfLife(FileUtilities.readFile(gameOfLifeApp.getFirstGenFileName(args)));

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isExit = false;
            while (!isExit) {
                log.info("");
                log.info("Введите № операции:");
                log.info("1 - запуск без потоков");
                log.info("2 - запуск с потоками");

                String line = br.readLine();

                switch (line) {
                    case "1":
                        long startTime = System.nanoTime();

                        // Запускаем процесс на каждом поколении
                        for (int i = 0; i < gameOfLifeApp.getGensQuantity(args); i++) {
                            gameOfLife.process();
                        }
                        isExit = true;

                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime);
                        log.info("Процесс занял: {} наносекунд (разделить на 1000000 для миллисекунд)", duration);
                        break;
                    case "2":
                        startTime = System.nanoTime();

                        // Запускаем процесс на каждом поколении
                        for (int i = 0; i < gameOfLifeApp.getGensQuantity(args); i++) {
                            gameOfLife.processWithThreads();
                        }
                        isExit = true;

                        endTime = System.nanoTime();
                        duration = (endTime - startTime);
                        log.info("Процесс занял: {} наносекунд (разделить на 1000000 для миллисекунд)", duration);
                        break;
                    default:
                        log.error("Введён некорректный номер операции");
                        break;
                }
            }
        }

        // выводим в консоль
        log.info("Результат последнего поколения:");
        gameOfLife.displayCurrentGen();

        // выводим в файл
        FileUtilities.writeFile(gameOfLifeApp.getLastGenFileName(args), gameOfLife.getCurrentGeneration());
    }

    private void checkArguments(String[] args) throws WrongParametersException {
        if (args.length < 3) {
            throw new WrongParametersException("Запустите с аргуметами: " +
                    "имя входного файла, имя финального файла, количество поколений.");
        }
    }

    private String getFirstGenFileName(String[] args) {
        return args[0];
    }

    private String getLastGenFileName(String[] args) {
        return args[1];
    }

    private int getGensQuantity(String[] args) {
        return Integer.parseInt(args[2]);
    }
}
