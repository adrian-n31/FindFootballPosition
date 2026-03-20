package com.footballscout.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL") != null
                ? System.getenv("DB_URL")
                : "jdbc:mysql://localhost:3306/football_scout";
        String user = System.getenv("DB_USER") != null
                ? System.getenv("DB_USER")
                : "root";
        String password = System.getenv("DB_PASS") != null
                ? System.getenv("DB_PASS")
                : "root";
        return DriverManager.getConnection(url, user, password);
    }
}