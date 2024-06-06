package org.example.Discounts;

import java.time.LocalTime;

public class PeakHourDiscount implements Discount {

    //Ако пътувате с влак сутрин от 7:30 до 9:30 ч. или
    //следобед след 16:00 ч. до 19:30 ч. ("час пик"), трябва да заплатите пълната цена на билета.

    //. За влаковете между 9:30 ч. и 16:00 ч. и след 19:30 ч. получавате 5% отстъпка.
    @Override
    public double calculatePrice(double price) {
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        double discountPrice = 0;
        if ((hour == 9 && minute >= 30) || (hour >= 10 && hour <= 15) || (hour >= 19 && minute >= 30)) {
            discountPrice = price * 0.05;
            System.out.println("You have 5% discount for your ticket!");
        }else{
            System.out.println("You don't have any discount at this time of the day :(");
        }
        return discountPrice;
    }
}
