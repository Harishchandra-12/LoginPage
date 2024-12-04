package com.spring.LoginPage.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable) // Explicitly disable CORS if needed
                .csrf(AbstractHttpConfigurer::disable) // Explicitly disable CSRF if needed
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/register/**").permitAll()
                        .requestMatchers("/api/v1/user/**").hasAnyAuthority("USER", "ADMIN")
                )
                .formLogin(form -> form.defaultSuccessUrl("/home", true)) // Customize login behavior
                .logout(logout -> logout.logoutUrl("/logout").permitAll()); // Customize logout behavior

        return http.build();
    }



}
