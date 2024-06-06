package org.example.service;
import org.example.dbConfig.DB;
import org.example.model.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TicketService {
    Scanner sc = new Scanner(System.in);
    Connection connection;
    {try {
            connection = DB.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTicket(int trainId){
        Ticket ticket = new Ticket(trainId,1);
        String sql = "INSERT INTO tickets(train_id, user_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, trainId);
            statement.setInt(2, 1);
            statement.executeUpdate();
            System.out.println("Ticket created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating ticket: " + e.getMessage());
        }
    }
}
