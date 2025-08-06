package com.example.t1.fifth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class T1Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(T1Application.class);

        CommandLineRunner commandLineRunner = context.getBean(CommandLineRunner.class);
        commandLineRunner.runAllOperations();

    }
}