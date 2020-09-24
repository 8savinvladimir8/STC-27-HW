package ru.savin.homework10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс {@code SomeClassCreator} создаёт файл класса, добавив к нему метод с переданными строками кода.
 * @author Savin Vladimir
 */
public class SomeClassCreator {
    static final Logger log = LogManager.getLogger(SomeClassCreator.class.getName());

    /**
     * Метод созадёт файл класса, добавив к нему метод с переданными строками кода.
     * @param fileName имя создаваемого файла класса.
     * @param commandList список строк кода метода.
     */
    public void createSomeClass(String fileName, List<String> commandList) {
        File file = new File(".");
        String path = null;
        try {
            path = file.getCanonicalPath() + File.separator + fileName;
        } catch (IOException e) {
            log.error(e.fillInStackTrace());
        }

        List<String> classCode = new ArrayList<>();
        classCode.add(this.getClass().getPackage() + ";\n");
        classCode.add("public class SomeClass implements Worker {");
        classCode.add("    public void doWork() {");

        assert path != null;
        try (FileWriter fileWriter = new FileWriter(path, false)) {
            for (String s : commandList) {
                classCode.add("        " + s);
            }

            classCode.add("    }");
            classCode.add("}");

            fileWriter.write(String.join(System.lineSeparator(), classCode));
            fileWriter.flush();
        } catch (IOException e) {
            log.error(e.fillInStackTrace());
        }

        file = new File(path);
        if (file.exists() && file.isFile()) {
            log.info("Файл '{}' создан. Код добавлен в метод.", fileName);
        }
    }
}
