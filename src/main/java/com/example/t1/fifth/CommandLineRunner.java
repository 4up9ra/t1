package com.example.t1.fifth;

import com.example.t1.fifth.entity.User;
import com.example.t1.fifth.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CommandLineRunner {

    private final UserService userService;

    public CommandLineRunner(UserService userService) {
        this.userService = userService;
    }

    public void runAllOperations() {
        User createdUser = userService.create("ivan1");
        System.out.println("Created user: " + createdUser);

        Optional<User> user = userService.findByUsername("ivan1");
        if (user.isPresent()) {
            System.out.println("Found user: " + user.get());
        } else {
            System.out.println("User with username ivan1 not found");
        }

        List<User> users = userService.findAll();
        System.out.println("Found '" + users.size() + "' users");

        userService.deleteByUsername("ivan1");
    }
}