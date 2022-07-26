package homework8.server.services;

import homework8.server.models.User;

import java.util.List;

public interface AuthenticationService {
    String getUsernameByLoginAndPassword(String login, String password);

    List<User> getAll();
}
