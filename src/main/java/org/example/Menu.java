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
        System.out.println("[REGISTER] [LOGIN]\n[TICKETS] [TRAINS] [INFO]");
        String command = sc.nextLine();
        switch (command) {
            case "register" -> {
                try {
                    db.registerUser();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Would you like to login? [Y][N]");
                if (sc.nextLine().equals("Y")){
                    try {
                        db.loginUser();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            case "login" -> {
                try {
                    db.loginUser();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                welcomeLoggedUser();

            }
            case "tickets" -> trainService.filterTrains();
            case "trains" -> {
                int trainId = trainService.filterTrains();
                ticketService.createTicket(trainId);
                System.out.println("More tickets? [Y][N]");
                if(sc.nextLine().equals("Y")){
                    trainId = trainService.filterTrains();
                    ticketService.createTicket(trainId);
                    double totalPrice = ticketService.totalTicketPrice();
                    ticketService.appliedDiscounts(totalPrice);
                }else{
                    System.out.println("Bye!");
                }
            }
            default -> {
                System.out.println("Would you like to return to the main menu? [Y][N]");
                String answer = sc.nextLine();
                if (answer.equals("Y")) {
                    start();
                } else {
                    break;
                }
            }
        }
}
        public void welcomeLoggedUser() throws SQLException {
            System.out.println("[TICKETS] [TRAINS] [INFO]");
            String command = sc.nextLine();
            switch (command) {
                case "tickets" -> System.out.println("a");
                case "trains" -> {
                    trainService.filterTrains();
                    ticketService.createTicket(trainService.filterTrains());    // Takes the id of the chosen train
                }
                default -> {
                    System.out.println("Would you like to return to the main menu? [Y][N]");
                    String answer = sc.nextLine();
                    if (answer.equals("Y")) {
                        start();
                    } else {
                        break;
                    }
                }
            }
        }
}
