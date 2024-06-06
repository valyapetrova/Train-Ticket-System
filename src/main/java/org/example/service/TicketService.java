package org.example.service;
import org.example.dbConfig.DB;
import org.example.model.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TicketService extends Ticket {
    Scanner sc = new Scanner(System.in);
    Connection connection;
    {
        try {
            connection = DB.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public TicketService(int trainId, int userId, double totalPrice) {
        super(trainId, userId, totalPrice);}

    public TicketService() {}

    public void createTicket(int trainId) throws SQLException {
        String calculatePriceSql = "SELECT price FROM trains WHERE id = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(calculatePriceSql)) {
            selectStatement.setInt(1, trainId);
            ResultSet resultSet = selectStatement.executeQuery();
            double totalPrice = 0.0;
            if (resultSet.next()) {
                totalPrice = resultSet.getDouble("price");
            } else {
                System.err.println("Price not found for train ID: " + trainId);
                return;
            }
            String insertTicketSql = "INSERT INTO tickets(train_id, user_id, total_price) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertTicketSql)) {
                statement.setInt(1, trainId);
                statement.setInt(2, 1);
                statement.setDouble(3, totalPrice);
                statement.executeUpdate();
                System.out.println("Ticket created successfully.");
            } catch (SQLException e) {
                System.err.println("Error creating ticket: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving price: " + e.getMessage());
        }
    }
}
