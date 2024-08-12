package com.security.SpringSecurityAuth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.security.SpringSecurityAuth.controllers.LoginController;

@SpringBootTest
class SpringSecurityAuthApplicationTests {

    @Autowired
    private LoginController loginController;

    @Test
    void contextLoads() {
        assertThat(loginController).isNotNull();
    }
}
