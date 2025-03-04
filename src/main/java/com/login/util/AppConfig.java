package com.login.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
	@Bean
    public Helper helper() {
        return new Helper("");
    }
}