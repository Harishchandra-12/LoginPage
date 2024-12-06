package com.spring.LoginPage.registration;

import com.spring.LoginPage.event.RegistrationCompleteEvent;
import com.spring.LoginPage.registration.token.VerificationToken;
import com.spring.LoginPage.registration.token.VerificationTokenRepository;
import com.spring.LoginPage.user.User;
import com.spring.LoginPage.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register ")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final VerificationTokenRepository tokenRepository;


    @PostMapping("/registerUser")
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request) {

        User user = userService.registerUser(registrationRequest);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user , applicationUrl(request)));

        return "Succesfully Created! Please check your email to complete your registration";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().getIsEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
