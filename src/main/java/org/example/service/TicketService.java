package org.example.service;
import org.example.Discounts.FamilyDiscount;
import org.example.Discounts.OldPeopleDiscount;
import org.example.Discounts.PeakHourDiscount;
import org.example.dbConfig.DB;
import org.example.model.DiscountCard;
import org.example.model.Ticket;

import java.io.CharArrayReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TicketService extends Ticket {
    Scanner sc = new Scanner(System.in);
    PeakHourDiscount pk = new PeakHourDiscount();
    OldPeopleDiscount op = new OldPeopleDiscount();
    FamilyDiscount fm = new FamilyDiscount();
    Connection connection;
    {try {
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
            if (resultSet.next())
            {
                totalPrice = resultSet.getDouble("price");
            } else {
                System.err.println("Price not found for train ID: " + trainId);
                return;
            }
            Ticket ticket = new Ticket(trainId, 1, totalPrice); // creating ticket
            ticketCart.add(ticket);   // Adding the ticket to the cart

            String insertTicketSql = "INSERT INTO tickets(train_id, user_id, total_price) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertTicketSql))
            {
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
        for (Ticket ticket : ticketCart){
            System.out.println(ticket);
        }
    }

    // Discounts on the total price
    public void appliedDiscounts(double totalPrice){
        double discountPrice = 0;
        System.out.println("Do you have discount card?[Y][N]");
        String answer = sc.nextLine();
        DiscountCard discountCard = addCardDetails();
        if (answer.equals("Y")) {
            if (discountCard != null && discountCard.getCardType() != null) {
                if (discountCard.getCardType().equalsIgnoreCase("elderly")) {
                    discountPrice = op.calculatePrice(totalPrice); // Elderly discount applied
                    System.out.printf("Total price is: %.2f lv.%n", pk.calculatePrice(discountPrice));   //peak hour discount
                } else if (discountCard.getCardType().equalsIgnoreCase("family")) {
                    discountPrice = fm.calculatePrice(totalPrice); // Family discount applied
                    System.out.printf("Total price is: %.2f lv.%n", pk.calculatePrice(discountPrice));   //peak hour discount
                } else {
                    System.out.printf("No discount applied. Total price is: %.2f lv.%n", totalPrice);
                }
            } else {
                System.out.printf("No discount card or card type is null. Total price is: %.2f lv.%n", totalPrice);
            }
        }
    }

    // Calculating the total sum of all tickets in the cart
    public double totalTicketPrice(){
        double totalTicketPrice = 0;
        for (Ticket ticket : ticketCart){
            totalTicketPrice += ticket.getTotalPrice();
        } System.out.printf("Total price is: %.2f lv.", totalTicketPrice);
        return totalTicketPrice;
    }
    public DiscountCard addCardDetails(){
        System.out.println("Enter the details of your card!");
        System.out.println("Card Type? [FAMILY][ELDERLY][STUDENT]");
        String cardType = sc.nextLine();
        System.out.println("Card number: ");
        int cardNum = Integer.parseInt(sc.nextLine());
        return new DiscountCard(cardType, cardNum);
    }
}
