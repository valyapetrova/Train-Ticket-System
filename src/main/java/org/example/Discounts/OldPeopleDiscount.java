package org.example.Discounts;

public class OldPeopleDiscount implements Discount{

    //Акo притежавате железопътна карта за хора над 60 години, получавате 34% отстъпка от
    //цената на закупения от вас билет.
    
    @Override
    public double calculatePrice(double price) {
        double newPrice = 0;
        double discount = 0;
        discount = price*34/100;
        newPrice = price - discount;

        return newPrice;
    }
}
