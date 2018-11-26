package cz.nigol.zpravodaj.services;

import java.util.List;

import cz.nigol.zpravodaj.entities.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
    User saveUser(User user);
    List<User> getActiveUsers();
}
