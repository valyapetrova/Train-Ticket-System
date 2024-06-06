package org.example.model;

public class Ticket {
    private int trainId;
    private int userId;
    private double totalPrice;

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
}
