package lesson7.server.services;

public interface AuthenticationService {
    String getUsernameByLoginAndPassword(String login, String password);
}
