package homework8;

/*
Java. Уровень 2. Урок 8.
"Написание сетевого чата. Часть II".

Сделать обновление списка пользователей в чате при отключении и подключении клиента.

Это только Server Client сетевого чата домашнего задания восьмого урока.
Client Chat сетевого чата восьмого урока находится в отдельном репозитории:
java_level_2_in_geekbrains_homework8_client_chat
*/

import homework8.server.MyServer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerApp {

    private static final int DEFAULT_PORT = 8086;
    private static String configsFile = "src/main/resources/configs/application-dev.properties";

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(configsFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int port;
        try {
            port = Integer.parseInt(properties.getProperty("server.port"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            port = DEFAULT_PORT;
        }

        try {
            new MyServer(port).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
