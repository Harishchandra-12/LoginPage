package com.spring.LoginPage.event.listener;

import com.spring.LoginPage.event.RegistrationCompleteEvent;
import com.spring.LoginPage.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        //Get the new User
        User user = event.getUser();

        // Create a verification token for user
        String verificationToken = UUID.randomUUID().toString();

        //Save verification token for user

        //Build the verification url
        String url = event.getApplicationUrl()+"/api/v1/register/verifyemail?token="+verificationToken;

        //Send the email
        log.info(" Click the link to verify your registration : {}",url);


    }
}
