package com.spring.LoginPage.registration;

import com.spring.LoginPage.event.RegistrationCompleteEvent;
import com.spring.LoginPage.user.User;
import com.spring.LoginPage.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register ")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;


    @PostMapping("/registerUser")
    public String registerUser(RegistrationRequest registrationRequest, HttpServletRequest request) {

        User user = userService.registerUser(registrationRequest);

        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user , applicationUrl(request)));

        return "Succesfully Created! Please check your email to complete your registration";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
