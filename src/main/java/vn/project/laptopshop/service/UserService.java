package vn.project.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.project.laptopshop.domain.Role;
import vn.project.laptopshop.domain.User;
import vn.project.laptopshop.repository.RoleRepository;
import vn.project.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void DeleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User handleSaveUser(User user) {
        // Logic to save the user
        User savedUser = this.userRepository.save(user);
        System.out.println("User saved: " + savedUser);
        return savedUser;
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
