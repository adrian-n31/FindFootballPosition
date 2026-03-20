package com.footballscout.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:mysql://localhost:3306/football_scout";
    private static final String USER = System.getenv("DB_USER") != null
            ? System.getenv("DB_USER")
            : "root";
    private static final String PASSWORD = System.getenv("DB_PASS") != null
            ? System.getenv("DB_PASS")
            : "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}