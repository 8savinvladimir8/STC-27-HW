package ru.savin.homework01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс {@code HelloWorld} выводит в консоль сообщение 'Hello world!!!'.
 *
 * @author Savin Vladimir
 */
public class HelloWorld {
    static final Logger log = LogManager.getLogger(HelloWorld.class.getName());

    public static void main(String[] args) {
        log.info("Hello world!!!");
    }
}
