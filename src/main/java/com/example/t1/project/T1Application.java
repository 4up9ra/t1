package com.example.t1.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class T1Application {

    public static void main(String[] args) {
        SpringApplication.run(T1Application.class);
    }
}