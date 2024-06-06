package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int trainId;
    private int userId;
    private double totalPrice;
    public List<Ticket> ticketCart = new ArrayList<>(); // To store all the tickets during the reservation
                                                         // and sum them later
    public Ticket(int trainId, int userId, double totalPrice) {
        this.trainId = trainId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
    public Ticket(){}

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
    public String toString() {
        return "Ticket: Train ID - " + trainId + ", User ID - " + userId + ", Total Price -" + totalPrice + "lv." ;
    }
}
