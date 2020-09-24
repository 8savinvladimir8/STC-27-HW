package ru.savin.homework07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс {@code FileUtilities} работает с файлами игры "Жизнь".
 * @author Savin Vladimir
 */
public class FileUtilities {
    static final Logger log = LogManager.getLogger(FileUtilities.class.getName());

    private FileUtilities() {}

    /**
     * Метод читает первое поколение из файла.
     * @param filName имя файла с первым поколением.
     * @return массив с первым поколением.
     */
    public static char[][] readFile(String filName) {
        char[][] currentGeneration = new char[1][1];
        boolean isArrayInitialized = false;
        int index = 0;

        try {
            File file = new File(filName);
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNext()) {
                    String str = sc.next();

                    // создаём двумерный массив, равный длине строки (длина массива и кол-во 'подмассивов' равны)
                    if (!isArrayInitialized) {
                        currentGeneration = new char[str.length()][str.length()];
                        isArrayInitialized = true;
                    }

                    // заполняем значениями из строки ячейки каждого 'подмассива'
                    for (int j = 0; j < str.length(); j++) {
                        currentGeneration[index][j] = str.charAt(j);
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            log.error(e.fillInStackTrace());
        }

        return currentGeneration;
    }

    /**
     * Метод записывает последнее поколение в файл.
     * @param fileName имя файла последнего поколения.
     * @param currentGeneration массив с текущим поколением.
     */
    public static void writeFile(String fileName, char[][] currentGeneration) {
        try (FileWriter fileWriter = new FileWriter(fileName, false))
        {
            StringBuilder sb = new StringBuilder();

            for (char[] chars : currentGeneration) {
                for (int j = 0; j < currentGeneration.length; j++) {
                    sb.append(chars[j]);
                }
                sb.append('\n');
            }

            fileWriter.write(sb.toString());
            fileWriter.flush();
        }
        catch(IOException e){
            log.error(e.fillInStackTrace());
        }
    }
}
