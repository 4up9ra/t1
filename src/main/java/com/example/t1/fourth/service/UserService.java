package com.example.t1.fourth.service;

import com.example.t1.fourth.dao.UserDao;
import com.example.t1.fourth.entity.User;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void create(String username) {
        userDao.create(username);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void deleteByUsername(String username) {
        userDao.deleteByUsername(username);
    }
}