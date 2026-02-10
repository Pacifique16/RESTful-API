package com.auca.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RestfulApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);
    }
}
