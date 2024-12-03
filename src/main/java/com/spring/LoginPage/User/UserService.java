package com.spring.LoginPage.User;

import com.spring.LoginPage.Registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> getUserByEmail(String email);


}
