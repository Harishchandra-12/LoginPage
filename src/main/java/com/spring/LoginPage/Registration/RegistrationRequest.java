package com.spring.LoginPage.Registration;


public record RegistrationRequest(String firstName,
         String LastName,
         String email,
         String password,
         String role,
         Boolean isEnabled)   {
}


