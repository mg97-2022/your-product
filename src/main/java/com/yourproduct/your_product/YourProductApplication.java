package com.yourproduct.your_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.security.SecureRandom;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableAsync
public class YourProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourProductApplication.class, args);
    }

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
}
