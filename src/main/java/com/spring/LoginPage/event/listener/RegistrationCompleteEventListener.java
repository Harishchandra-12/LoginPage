package com.spring.LoginPage.event.listener;

import com.spring.LoginPage.event.RegistrationCompleteEvent;
import com.spring.LoginPage.user.User;
import com.spring.LoginPage.user.UserService;

import java.lang.RuntimeException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import com.spring.LoginPage.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserServiceImpl userService;
    private final JavaMailSender mailSender;
    private User user;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        //Get the new User
         user = event.getUser();

        // Create a verification token for user
        String verificationToken = UUID.randomUUID().toString();

        //Save verification token for user
        userService.saveUserVerificationToken( user , verificationToken);
        //Build the verification url
        String url = event.getApplicationUrl()+"/api/v1/register/verifyemail?token="+verificationToken;

        //Send the email

        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info(" Click the link to verify your registration : {}",url);
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ user.getFirstName()+ ", </p>"+
                "<p>Thank you for registering with us,"+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("dailycodework@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
