package com.earworm.backendearworm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.earworm.registration.token.ConfirmationToken;
import com.earworm.registration.token.ConfirmationTokenService;

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
    private final ConfirmationTokenService confirmationTokenService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
            ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // public List<User> getUsers(int zipCode) {
    // return userRepository.findAllByZipcode(zipCode);
    // }

    public String addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
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
        // Fix to lang and
        // long?>----------------------------------------------------------------------------------
        // user.setZipCode(zipCode);
        user.setBio(bio);

    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user with email " + email + " not found"));
    }
    /* 
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
    */

}
