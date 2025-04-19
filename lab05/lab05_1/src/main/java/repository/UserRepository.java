package repository;

import dao.UserDAO;
import models.User;

public class UserRepository {
    private final UserDAO userDAO;

    public UserRepository() {
        userDAO = new UserDAO();
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public boolean save(User user) {
        return userDAO.save(user);
    }

    public boolean validateLogin(String username, String password) {
        return userDAO.validateLogin(username, password);
    }
}