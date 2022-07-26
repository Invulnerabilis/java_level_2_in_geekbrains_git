package homework8.server;

import homework8.server.handlers.ClientHandler;
import homework8.server.models.User;
import homework8.server.services.AuthenticationService;
import homework8.server.services.impl.SimpleAuthenticationServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final ServerSocket serverSocket;
    private final AuthenticationService authenticationService;
    private final ArrayList<ClientHandler> clients;

    public MyServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        authenticationService = new SimpleAuthenticationServiceImpl();
        clients = new ArrayList<>();
    }


    public void start() {
        System.out.println("CЕРВЕР ЗАПУЩЕН!");
        System.out.println("---------------");

        try {
            while (true) {
                waitAndProcessNewClientConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitAndProcessNewClientConnection() throws IOException {
        System.out.println("Ожидание клиента...");
        Socket socket = serverSocket.accept();
        System.out.println("Клиент подключился!");

        processClientConnection(socket);
    }

    private void processClientConnection(Socket socket) throws IOException {
        ClientHandler handler = new ClientHandler(this, socket);
        handler.handle();
    }

    public synchronized void subscribe(ClientHandler handler) throws IOException {
        clients.add(handler);
        sendAllUsersServerMessage(getAllUsers());
    }

    public synchronized void unSubscribe(ClientHandler handler) throws IOException {
        clients.remove(handler);
        sendAllUsersServerMessage(getAllUsers());
    }

    public String getAllUsers() {
        StringBuilder sb = new StringBuilder(" -conditionUsers ");
        List<String> usernameList = authenticationService.getAll().stream().map(User::getUsername).toList();
        for (String username : usernameList) {
            boolean flag = false;
            for (ClientHandler clientHandler :
                    clients) {
                if (username.equals(clientHandler.getUsername())) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                sb.append("ONLINE------->");
            } else {
                sb.append("OFFLINE------->");
            }
            sb.append(username).append(" ");
        }
        return sb.toString();
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public boolean isUsernameBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void stop() {
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("ЗАВЕРШЕНИЕ РАБОТЫ");
        System.exit(0);
    }

    public synchronized void broadcastMessage(ClientHandler sender, String message) throws IOException {
        for (ClientHandler client : clients) {
/*            if (client == sender) {
                continue;
            }*/
            client.sendMessage(sender.getUsername(), message);
        }
    }


    public synchronized void sendPrivateMessage(ClientHandler sender, String recipient, String privateMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(recipient)) {
                client.sendMessage(sender.getUsername(), privateMessage, true);
            }
        }
    }

    public synchronized void sendAllUsersServerMessage(String message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendServerMessage(message);
        }
    }

    public synchronized void broadcastServerMessage(ClientHandler sender, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            client.sendServerMessage(message);
        }
    }
}
