package ru.savin.homework12;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.savin.homework10.SomeClassCompiler;
import ru.savin.homework10.SomeClassCreator;
import ru.savin.homework10.SomeClassLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс {@code OOMEMetaspaceLeak} демонстрирует вывод ошибки "OutOfMemoryError: Metaspace".
 * @author Savin Vladimir
 */
public class OOMEMetaspaceLeak {
    static final Logger log = LogManager.getLogger(OOMEMetaspaceLeak.class.getName());

    /**
     * Метод создаёт экземпляры ClassLoader и загружает в них класс до тех пор,
     * пока програма не завершится с исключением "OutOfMemoryError: Metaspace".
     */
    public void generateMetaspaceOOME() {
        // создаём класс
        List<String> commandsList = new ArrayList<>();
        commandsList.add("int a;");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("try {a=0;} finally {");
        commandsList.add("a=0;");
        commandsList.add("}}}}}}}}}}}}");

        SomeClassCreator someClassCreator = new SomeClassCreator();
        someClassCreator.createSomeClass("SomeClass.java", commandsList);

        // компилируем класс
        SomeClassCompiler someClassCompiler = new SomeClassCompiler();
        someClassCompiler.compileSomeClass("SomeClass.java");

        log.info("Загружаем класс:");
        List<ClassLoader> classLoaderList = new ArrayList<>();
        while (true) {
            // загружаем класс кастомным загрузчиком
            SomeClassLoader someClassLoader = new SomeClassLoader();
            someClassLoader.findClass("SomeClass");
            classLoaderList.add(someClassLoader);
        }
    }
}
