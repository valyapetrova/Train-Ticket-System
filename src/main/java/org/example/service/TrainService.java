package org.example.service;
import org.example.dbConfig.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public TrainService() {}

    // List all trains
    public void listTrains() throws SQLException{
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
    public int filterTrains() throws SQLException{
        System.out.println("[From][To][Date][Time][Arrival]");
        String filterBy = sc.nextLine();
        System.out.println("Enter filter for your train:");
        String filterValue = sc.nextLine();
        String sql = "SELECT * FROM train_schedule WHERE ";
        switch (filterBy.toLowerCase()) {
            case "from" -> sql += "from_place = ?";
            case "to" -> sql += "to_place = ?";
            case "date" -> sql += "day = ?";
            case "time" -> sql += "departure = ?";
            case "arrival" -> sql += "arrival = ?";
            default -> {
                System.out.println("Invalid filter! Try again");
                filterTrains();
            }
        }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, filterValue);
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
                return -1;
            }
            System.out.println("Choose the ID of your train:");
            return Integer.parseInt(sc.nextLine());

}

}
