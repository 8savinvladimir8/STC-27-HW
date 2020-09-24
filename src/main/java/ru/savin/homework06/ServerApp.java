package ru.savin.homework06;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс {@code ServerApp} представляет собой простой HTTP сервер, обрабатывающий HTTP-запросы.
 * Сервер запускается на порту 8000 (по адрес: http://localhost:8000/test).
 * На команды GET сервер возвращает список файлов в текущей диретории.
 * Пример команд GET:
 * curl localhost:8000/test
 * curl -X GET http://localhost:8000/test
 * curl -v -X GET http://localhost:8000/test
 * На прочие команды сервер возвращает ошибку 404.
 * Пример команды POST:
 * curl -v -X POST localhost:8000/test
 * Примечание: для команд типа POST нужно использовать ключ '-v' для вывода более подробных сообщений,
 * т.к. иначе ошибка 404 не будет отображаться.
 * @author Savin Vladimir
 */
public class ServerApp {
    static final Logger log = LogManager.getLogger(ServerApp.class.getName());

    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null);
        server.start();
        log.info("Сервер запущен на порту: {}", serverPort);
    }


    /**
     * Класс для обработки запросов.
     */
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            if ("GET".equals(httpExchange.getRequestMethod())) {
                // получаем список папок и файлов текущего каталога
                String response = FileUtilities.getDirsFiles(new File(".").getCanonicalPath());

                httpExchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                    os.flush();
                }
            } else {
                httpExchange.sendResponseHeaders(404, -1);
            }
            httpExchange.close();
        }
    }


    /**
     * Утилитный класс.
     */
    static class FileUtilities {
        private FileUtilities() { }

        /**
         * Метод получает путь к каталогу и возвращает его содержимое (имена папок и файлов)
         * в виде строки с символами переноса строки.
         * @param filePath путь к каталогу.
         * @return строка с именами папок и файлов, разделённых символами переноса строки.
         */
        public static String getDirsFiles(String filePath) {
            List<String> pathList = new ArrayList<>();
            pathList.add("\n");

            // Чтение полного списка папок и файлов каталога
            File[] filePathList = new File(filePath).listFiles();
            if (filePathList != null) {
                for (File file : filePathList) {
                    pathList.add(file.getName());
                }
            }
            return String.join(System.lineSeparator(), pathList);
        }
    }
}
