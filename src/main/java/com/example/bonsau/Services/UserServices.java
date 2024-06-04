package com.example.bonsau.Services;

import com.example.bonsau.Entities.User;
import com.example.bonsau.Repository.UserRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class UserServices {
    private final UserRepository userRepository;
    // Retrieve all products from the database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    // Add a new product to the database
    public User addUser(User user) {
        return userRepository.save(user);
    }
    // Update an existing product
    public User updateUser(@NotNull User user) {
        User existingUser =  userRepository.findById(user.getIduser())
                .orElseThrow(() -> new IllegalStateException("User with ID " +
                        user.getIduser() + " does not exist."));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setBirthDay(user.getBirthDay());
        existingUser.setDeleted(user.isDeleted());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }
    // Delete a product by its id
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }
}