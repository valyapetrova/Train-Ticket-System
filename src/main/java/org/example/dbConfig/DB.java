package org.example.dbConfig;
import org.example.verification.Regex;

import java.sql.*;
import java.util.Scanner;


public class DB {
    Scanner sc = new Scanner(System.in);
    Regex regex = new Regex();

    public static Connection connect() throws SQLException {

        // Get database credentials from DatabaseConfig class
        var jdbcUrl = DatabaseConfig.getDbUrl();
        var user = DatabaseConfig.getDbUsername();
        var password = DatabaseConfig.getDbPassword();

        // Open a connection
        return DriverManager.getConnection(jdbcUrl, user, password);
    }

    public void registerUser() throws SQLException {
        Connection connection = connect();
        System.out.println("Welcome to User Registration!");
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        Regex regex = new Regex();
        if (!regex.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return;
        }
        if (!regex.isValidPassword(password)) {
            System.out.println("Invalid password format!");
            return;
        }

        String sql = "INSERT INTO users (username,email,password) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, password);

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("User registration successful!");
        } else {
            System.out.println("User registration failed!");
        }
        statement.close();
        connection.close();
    }

    public boolean loginUser() throws SQLException {
        Connection connection = connect();
        System.out.println("Welcome to User Login!");
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        boolean loginSuccessful = resultSet.next();
        resultSet.close();
        statement.close();
        connection.close();

        if (loginSuccessful) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password!");
        }
        return loginSuccessful;
    }
}