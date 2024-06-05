package org.example;

import org.example.dbConfig.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    DB db = new DB();

    public void Start() {
        String command = sc.nextLine();
        switch (command) {
            case "register":
                try {
                    db.registerUser();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case "login":
                try {
                    db.loginUser();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
