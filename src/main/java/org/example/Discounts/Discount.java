package org.example.Discounts;

public interface Discount {
    public default double calculatePrice(double price) {
        return price;
    }
}
