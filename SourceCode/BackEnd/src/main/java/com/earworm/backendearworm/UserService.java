package com.earworm.backendearworm;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.earworm.security.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getUsers() {
        // return List.of(new User("Noone", "HEllO@gmail.com", "password"));
        return userRepository.findAll();
    }

    public String addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        // TODO send confirmation token
        return "User added";
    }

    /*
     * Fix exists by ID by actually passing id and changing repository from string
     * to long
     */
    public void deleteUser(Long id) {
        boolean userExists = userRepository.existsById(id);
        if (!userExists) {
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);

    }

    // Name and email are optional
    @Transactional
    public void updateUser(Long id, String username, int zipCode, String bio) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));

        if (username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username)) {
            user.setUsername(username);
        }
        // -------Change email if wanted-------
        // if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(),
        // email)) {
        // Optional<User> userOptional = userRepository.findUserByEmail(email);
        // if (userOptional.isPresent()) {
        // throw new IllegalStateException("Email taken");
        // }
        // user.setEmail(email);
        // }
        user.setZipCode(zipCode);
        user.setBio(bio);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user with email " + email + " not found"));
    }

}
