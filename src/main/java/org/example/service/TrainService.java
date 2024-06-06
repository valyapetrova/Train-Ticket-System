package org.example.service;
import org.example.dbConfig.DB;
import org.example.model.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainService {
    Scanner sc = new Scanner(System.in);
    Connection connection;

    {
        try {
            connection = DB.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TrainService() {
    }

    // List all trains
    public void listTrains() throws SQLException {
        String sql = "SELECT * FROM trains";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String fromPlace = resultSet.getString("from_place");
            String toPlace = resultSet.getString("to_place");
            String day = resultSet.getString("day");
            String departure = resultSet.getString("departure");
            String arrival = resultSet.getString("arrival");
            System.out.println("From: " + fromPlace + ", To: " + toPlace + ", Date: " + day + ", Departure: " + departure + ", Arrival: " + arrival);
        }
    }

    // Filter trains
    public int filterTrains() throws SQLException {
        while (true) {
            System.out.println("[From][To][Date][Departure][Arrival]");
            System.out.println("Enter filter criteria (from, to, date, departure, arrival):");
            String filterBy = sc.nextLine().trim().toLowerCase();
            System.out.println("Enter filter value:");
            String filterValue = sc.nextLine();
            String sql = "";
            switch (filterBy) {
                case "from":
                    sql = "SELECT * FROM trains WHERE from_place = ?";
                    break;
                case "to":
                    sql = "SELECT * FROM trains WHERE to_place = ?";
                    break;
                case "date":
                    sql = "SELECT * FROM trains WHERE day = ?";
                    break;
                case "departure":
                    sql = "SELECT * FROM trains WHERE departure = ?";
                    break;
                case "arrival":
                    sql = "SELECT * FROM trains WHERE arrival = ?";
                    break;
                default:
                    System.out.println("Invalid filter! Try again.");
                    filterTrains();
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, filterValue);
                resultSet(statement);
                break;
            }
        }
        System.out.println("Choose the ID of your train:");
        return Integer.parseInt(sc.nextLine());
    }

    // ResultSet function for easier use
    public void resultSet(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        int count = 0;
        while (resultSet.next()) {
            count++;
            System.out.println("[" + resultSet.getInt("id") + "]" +
                    " From: " + resultSet.getString("from_place") +
                    ", To: " + resultSet.getString("to_place") +
                    ", Date: " + resultSet.getString("day") +
                    ", Departure: " + resultSet.getString("departure") +
                    ", Arrival: " + resultSet.getString("arrival"));
        }
        if (count == 0) {
            System.out.println("No trains found matching the filter criteria.");
        }
    }
}
