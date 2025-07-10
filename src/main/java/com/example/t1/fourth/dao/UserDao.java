package com.example.t1.fourth.dao;

import com.example.t1.fourth.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String CREATE_USER_STATEMENT = "INSERT INTO users (username) VALUES(?)";
    private static final String FIND_BY_USERNAME_STATEMENT = "SELECT * FROM users WHERE username = ?";
    private static final String FIND_ALL_STATEMENT = "SELECT * FROM users";
    private static final String DELETE_BY_USERNAME_STATEMENT = "DELETE FROM users WHERE username = ?";

    public void create(String username) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(CREATE_USER_STATEMENT)) {

            statement.setString(1, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User with username='" + username + "' created");
            } else {
                System.out.println("User with username='" + username + "' not created");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while executing sql request: " + e);
            throw new RuntimeException();
        }
    }

    public User findByUsername(String username) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_BY_USERNAME_STATEMENT)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long foundId = resultSet.getLong("id");
                String foundUsername = resultSet.getString("username");
                User user = new User(foundId, foundUsername);
                System.out.println("User " + user + " found");
                return user;
            } else {
                System.out.println("User with username='" + username + "' not found");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while executing sql request: " + e);
            throw new RuntimeException();
        }
    }

    public List<User> findAll() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_STATEMENT)) {

            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                long foundId = resultSet.getLong("id");
                String foundUsername = resultSet.getString("username");
                User user = new User(foundId, foundUsername);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("Error occurred while executing sql request: " + e);
            throw new RuntimeException();
        }
    }

    public void deleteByUsername(String username) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_BY_USERNAME_STATEMENT)) {

            statement.setString(1, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User with username='" + username + "' deleted");
            } else {
                System.out.println("User with username='" + username + "' not found");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while executing sql request: " + e);
            throw new RuntimeException();
        }
    }
}