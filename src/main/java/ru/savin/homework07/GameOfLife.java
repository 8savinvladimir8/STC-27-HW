package ru.savin.homework07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Класс {@code GameOfLife} имитирует игру "Жизнь".
 * @author Savin Vladimir
 */
public class GameOfLife {
    static final Logger log = LogManager.getLogger(GameOfLife.class.getName());
    private final char[][] currentGeneration;
    private final char[][] nextGeneration;
    private final int universeSize;

    public GameOfLife(char[][] currentGeneration) {
        this.currentGeneration = currentGeneration;
        this.nextGeneration = new char[currentGeneration.length][currentGeneration.length];
        this.universeSize = currentGeneration.length;
    }

    /**
     * Метод возвращает массив с текущим поколением.
     * @return массив с текущим поколением.
     */
    public char[][] getCurrentGeneration() {
        return currentGeneration;
    }

    /**
     * Однопоточная генерация нового поколения.
     */
    public void process() {
        for (int i = 0; i < universeSize; i++) {
            for (int j = 0; j < universeSize; j++) {
                makeAlive(i, j);
            }
        }
        // после генерации нового поколения копируем его в текущее
        copyNextGenIntoCurrent();
    }

    /**
     * Многопоточная генерация нового поколения.
     */
    public void processWithThreads() {
        ArrayList<Thread> threads = new ArrayList<>();
        final int NUM_OF_THREADS = 2;
        for (int tid = 0; tid < NUM_OF_THREADS; tid++) {
            int threadLocalRowStart = tid * universeSize / NUM_OF_THREADS;
            int threadLocalRowEnd = (tid + 1) * universeSize / NUM_OF_THREADS;
            Thread t = new Thread(() -> {
                for (int i = threadLocalRowStart; i < threadLocalRowEnd; i++) {
                    for (int j = 0; j < universeSize; j++) {
                        makeAlive(i, j);
                    }
                }
            });
            t.start();
            threads.add(t);
        }

        try {
            // в основном потоке ждём, пока все остальные потоки не закончат свою работу
            for (Thread t : threads) {
                t.join();
            }
        } catch (Exception e) {
            log.error(e.fillInStackTrace());
        }
        // после генерации нового поколения копируем его в текущее
        copyNextGenIntoCurrent();
    }

    private void makeAlive(int x, int y) {
        // отмечаем клетку живой или мёртвой в следующем поколении
        boolean isAlive = checkAlive(x, y);
        if (isAlive) {
            nextGeneration[x][y] = '*';
        } else {
            nextGeneration[x][y] = '_';
        }
    }

    private boolean checkAlive(int x, int y) {
        // определяем статус ячейки в следующем поколении
        int aliveNeighboursQuantity = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i != x || j != y)
                        && i >= 0 && j >= 0
                        && i < currentGeneration.length && j < currentGeneration.length
                        && currentGeneration[i][j] == '*') {
                    aliveNeighboursQuantity++;
                }
            }
        }

        // в пустой (мёртвой) клетке, рядом с которой ровно три живые клетки, зарождается жизнь
        if (currentGeneration[x][y] == '_') {
            return (aliveNeighboursQuantity == 3);
        }
        // либо если у живой клетки есть две или три живые соседки, то эта клетка продолжает жить
        if (currentGeneration[x][y] == '*') {
            return (aliveNeighboursQuantity == 2 || aliveNeighboursQuantity == 3);
        }
        // иначе, если соседей меньше двух или больше трёх, клетка умирает («от одиночества» или «от перенаселённости»)
        return false;
    }

    private void copyNextGenIntoCurrent() {
        // после генерации нового поколения копируем его в текущее
        for (int i = 0; i < universeSize; i++) {
            System.arraycopy(nextGeneration[i], 0, currentGeneration[i], 0, universeSize);
        }
    }

    public void displayCurrentGen() {
        // выводим текущее поколение
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < universeSize; i++) {
            sb.setLength(0);
            for (int j = 0; j < universeSize; j++) {
                sb.append(currentGeneration[i][j]);
            }
            log.info(sb.toString());
        }
    }
}
