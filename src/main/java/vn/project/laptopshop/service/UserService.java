package vn.project.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.project.laptopshop.domain.User;
import vn.project.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello() {
        return "hello from service";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {
        // Logic to save the user
        User savedUser = this.userRepository.save(user);
        System.out.println("User saved: " + savedUser);
        return savedUser;
    }
}
