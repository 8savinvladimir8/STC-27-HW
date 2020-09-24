package ru.savin.homework10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс {@code SomeClassLoader} загружает класс для возможности создания его экземпляра.
 */
public class SomeClassLoader extends ClassLoader {
    /**
     * Метод загружает класс.
     * @param name имя загружаемого класса.
     * @return экземпляр Class, созданный из данных указанного класса.
     */
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("SomeClass.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(null, bytes, 0, bytes.length);
    }
}
