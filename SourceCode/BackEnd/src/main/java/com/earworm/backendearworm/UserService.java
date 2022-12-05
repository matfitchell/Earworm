package com.earworm.backendearworm;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        // return List.of(new User("Noone", "HEllO@gmail.com", "password"));
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        boolean userExists = userRepository.existsById(username);
        if (!userExists) {
            throw new IllegalStateException("User with username " + username + " does not exist");
        }
        userRepository.deleteById(username);

    }

    // Name and email are optional
    @Transactional
    public void updateUser(String username, String name, String email) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new IllegalStateException("User with username " + username + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(user.getDisplayName(), name)) {
            user.setDisplayName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            user.setEmail(email);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user with email " + email + " not found"));
    }
}
