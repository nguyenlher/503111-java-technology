package repository;

import java.util.List;

import dao.UserDAO;
import model.User;

public class UserRepository {
    private final UserDAO userDAO;

    public UserRepository() {
        this.userDAO = new UserDAO();
    }

    public boolean createUser(User user) {
        return userDAO.add(user);
    }

    public User getUserById(Long id) {
        return userDAO.get(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public boolean updateUser(User user) {
        return userDAO.update(user);
    }

    public boolean deleteUser(Long id) {
        return userDAO.delete(id);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
