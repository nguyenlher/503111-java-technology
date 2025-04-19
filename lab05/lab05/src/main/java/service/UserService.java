package service;

import java.util.List;

import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    
    public UserService() {
        this.userRepository = new UserRepository();
    }
    
    public boolean registerUser(String username, String password, String email) {
        User user = new User(username, password, email);
        return userRepository.createUser(user);
    }
    
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }
    
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }
    
    public boolean deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
