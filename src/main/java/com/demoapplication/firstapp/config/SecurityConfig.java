package com.demoapplication.firstapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    public static String[] permittedUrls = {
            "/"
            , "/register"
            , "/login"
            , "/db-console/**"
            , "/css/**"
            , "/fonts/**"
            , "/images/**"
            , "/js/**"
    };

    // creating the password encoder for encoding the user password before storing
    // in DB
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //use lambda based configuration from spring boot 6.1
        http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.requestMatchers(permittedUrls)
                        .permitAll().anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/?loggedin", true)
                        .failureUrl("/login?error").
                        permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/logout?success"));       

        // disable the below changes after hosting it live as it is only specific for h2
        // dB
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        
        return http.build();
    }

}
