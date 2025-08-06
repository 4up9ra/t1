package com.example.t1.fourth.config;

import com.example.t1.fourth.dao.UserDao;
import com.example.t1.fourth.service.UserService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${app.database.jdbcUrl}")
    private String jdbcUrl;
    @Value("${app.database.username}")
    private String username;
    @Value("${app.database.password}")
    private String password;
    @Value("${app.database.driverClassName}")
    private String driverClassName;
    @Value("${app.database.maximumPoolSize}")
    private int maximumPoolSize;

    @Bean
    public UserDao userDao(DataSource dataSource) {
        return new UserDao(dataSource);
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);

        config.setMaximumPoolSize(maximumPoolSize);

        return new HikariDataSource(config);
    }
}