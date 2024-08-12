package com.security.SpringSecurityAuth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.SpringSecurityAuth.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final UserRepository userRepository;

    public SpringSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf().disable() 
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/livre/create", "/livre/update/**", "/livre/delete/**")
                    .hasAuthority("ROLE_ADMIN"); 
                auth.requestMatchers("/livre/read", "/livre/borrow/**", "/livre/return/**")
                    .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN"); 
                auth.anyRequest().authenticated();
            })
            .httpBasic(); 

    return http.build();
}


 

    @Bean
public UserDetailsService userDetailsService() {
    return username -> {
        com.security.SpringSecurityAuth.models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) 
                .build();
    };
}


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
