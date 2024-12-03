package com.spring.LoginPage.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/getUsers")
    List<User> getUsers() {
        return userService.getUsers();
    }




}
