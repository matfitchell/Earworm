package com.earworm.registration;

import com.earworm.backendearworm.User;
import com.earworm.backendearworm.UserRole;
import com.earworm.backendearworm.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService, EmailValidator emailValidator) {
        this.userService = userService;
        this.emailValidator = emailValidator;
    }

    // @Autowired
    // public RegistrationService(EmailValidator emailValidator) {
    // this.emailValidator = emailValidator;
    // }

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return userService.addNewUser(new User(request.getDisplayName(), request.getUsername(), request.getEmail(),
                request.getPassword(), request.getDob(), request.getZipCode(), request.getPhone(), UserRole.USER));
    }

}
