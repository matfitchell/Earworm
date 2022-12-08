package com.earworm.registration;

import com.earworm.backendearworm.User;
import com.earworm.backendearworm.UserRole;
import com.earworm.backendearworm.UserService;
import com.earworm.registration.token.ConfirmationToken;
import com.earworm.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public RegistrationService(UserService userService, EmailValidator emailValidator,
            ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
    }

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return userService.addNewUser(new User(request.getDisplayName(), request.getUsername(), request.getEmail(),
                request.getPassword(), request.getDob(), request.getZipCode(), request.getPhone(), UserRole.USER));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";
    }

}
