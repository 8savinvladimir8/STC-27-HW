package ru.savin.homework10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Класс {@code SomeClassCompiler} позволяет скомпиллировать класс.
 * @author Savin Vladimir
 */
public class SomeClassCompiler {
    static final Logger log = LogManager.getLogger(SomeClassCompiler.class.getName());

    /**
     * Метод компиллирует класс.
     * @param className имя файла компиллируемого класса.
     */
    public void compileSomeClass(String className) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, className);
        if (result == 0) {
            log.info("Класс '{}' скомпилирован.", className);
        }
    }
}
