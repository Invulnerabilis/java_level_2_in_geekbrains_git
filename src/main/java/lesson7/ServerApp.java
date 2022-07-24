package lesson7;

/*
Java. Уровень 2. Урок 7. "Написание сетевого чата. Часть I".

1. Разобраться с кодом.

2. * Реализовать личные (приватные) сообщения, если клиент пишет «/pm nick_такой-то Привет», то только клиенту с ником nick_такой-то должно прийти сообщение «Привет».

* Реализовать вызов метода unSubscribe. Как убрать пользователя из общего списка всех клиентов при его отключение.
Т.е., исправить когда окно какого-то пользователя уже закрыто, но оно остаётся не завершённым.
*/

import lesson7.server.MyServer;

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
