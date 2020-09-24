package ru.savin.homework10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс {@code WorkerApp} позволяет:
 * - ввести строки метода создаваемого класса и создать класс;
 * - откомпиллировать созданный класс в рантайме;
 * - загрузить экземпляр класса кастомным загрузчиком;
 * - выполнить метод.
 * @author Savin Vladimir
 */
public class WorkerApp {
    static final Logger log = LogManager.getLogger(WorkerApp.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isEmpty = false;
        List<String> commandsList = new ArrayList<>();

        log.info("Введите строки метода. Для окончания нажмите Enter в пустой строке.");
        while (!isEmpty) {
            String s = sc.nextLine();
            if (!s.equals("")) {
                commandsList.add(s);
            } else {
                isEmpty = true;
            }
        }

        // создаём класс
        SomeClassCreator someClassCreator = new SomeClassCreator();
        someClassCreator.createSomeClass("SomeClass.java", commandsList);

        // компилируем класс
        SomeClassCompiler someClassCompiler = new SomeClassCompiler();
        someClassCompiler.compileSomeClass("SomeClass.java");

        // загружаем класс кастомным загрузчиком
        Class<?> someClass = new SomeClassLoader().findClass("SomeClass");

        // создаём экземпляр загруженного класса
        Object someObject = null;
        try {
            someObject = someClass.getDeclaredConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.fillInStackTrace());
        }

        // кастуем к интерфейсу и выполняем метод
        Worker worker = (Worker) someObject;
        assert worker != null;
        worker.doWork();
    }
}
