package com.spring.LoginPage.user;

import com.spring.LoginPage.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);

    String validateToken(String theToken);
    void saveUserVerificationToken(User user, String verificationToken);
}
