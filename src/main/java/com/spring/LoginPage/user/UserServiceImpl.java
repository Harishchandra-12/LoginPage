package com.spring.LoginPage.user;

import com.spring.LoginPage.exception.UserAlreadyExistsException;
import com.spring.LoginPage.registration.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.getUserByEmail(request.email());
        if(user.isPresent())
            throw new UserAlreadyExistsException("user with  email" + request.email() + "is already exists");

        User newUser = new User();

        newUser.setEmail(request.email());
        newUser.setRole(request.role());
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setIsEnabled(request.isEnabled());
        newUser.setPassword(passwordEncoder.encode(request.password()));

        return newUser;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail();
    }
}
