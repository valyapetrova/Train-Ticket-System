package org.example.service;

import org.example.dbConfig.DB;

import java.sql.Connection;
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
    public void createTicket(){
        System.out.println("");

        return ;
    }
}
