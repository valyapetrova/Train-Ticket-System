package org.example.service;
import org.example.dbConfig.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainService {
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
}
