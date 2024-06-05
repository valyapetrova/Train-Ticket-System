package org.example;

import org.example.dbConfig.DB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        try (var connection =  DB.connect()){
            menu.start();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}