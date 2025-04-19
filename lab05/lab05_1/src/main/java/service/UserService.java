package service;

import models.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean registerUser(String username, String password, String fullName, String email) {
        // Check if username or email already exists
        if (userRepository.findByUsername(username) != null) {
            return false;
        }

        if (userRepository.findByEmail(email) != null) {
            return false;
        }

        // Create and save new user
        User user = new User(username, password, fullName, email);
        return userRepository.save(user);
    }

    public boolean validateLogin(String username, String password) {
        return userRepository.validateLogin(username, password);
    }
}