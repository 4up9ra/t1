package com.example.t1.fourth;

import com.example.t1.fourth.config.AppConfig;
import com.example.t1.fourth.entity.User;
import com.example.t1.fourth.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class T1Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.create("ivan1");

        User ivan = userService.findByUsername("ivan1");

        List<User> users = userService.findAll();
        System.out.println("Found '" + users.size() + "' users");

        userService.deleteByUsername("ivan1");
    }
}