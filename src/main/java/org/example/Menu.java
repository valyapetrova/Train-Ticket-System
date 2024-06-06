package org.example;

import org.example.dbConfig.DB;
import org.example.service.TicketService;
import org.example.service.TrainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    DB db = new DB();
    TrainService trainService = new TrainService();
    TicketService ticketService = new TicketService();

    public void start() throws SQLException {
        System.out.println("Welcome to Vals' railway station!");
        System.out.println("[REGISTER] [LOGIN]\n");
        String command = sc.nextLine();
        switch (command) {
            case "register":
                db.registerUser();
                System.out.println("Would you like to login? [Y][N]");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    if (db.loginUser()) {
                        welcomeLoggedUser();
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                }
                break;
            case "login":
                if(db.loginUser()){
                    welcomeLoggedUser();
                }else{
                    System.out.println("error! try again!");
                }
                break;
            default:
                System.out.println("Would you like to return to the main menu? [Y][N]");
                answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    start();
                } else {
                    System.out.println("Exiting program...");
                }
                break;
        }
    }
    public void welcomeLoggedUser() throws SQLException {
        System.out.println("[TICKETS] [TRAINS] [INFO]");
        String command = sc.nextLine();
        switch (command) {
            case "tickets":
                int trainId = trainService.filterTrains();
                ticketService.createTicket(trainId);
                System.out.println("More tickets? [Y][N]");
                if (sc.nextLine().equalsIgnoreCase("Y")) {
                    trainId = trainService.filterTrains();
                    ticketService.createTicket(trainId);
                    double totalPrice = ticketService.totalTicketPrice();
                    ticketService.appliedDiscounts(totalPrice);
                } else {
                    double totalPrice = ticketService.totalTicketPrice();
                    ticketService.appliedDiscounts(totalPrice);
                }
                break;

            case "trains":
                trainService.listTrains();
                trainId = trainService.filterTrains();
                ticketService.createTicket(trainId);
                break;

            default:
                System.out.println("Would you like to return to the main menu? [Y][N]");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    start();
                } else {
                    System.out.println("Exiting program...");
                }
                break;
        }
    }
}
