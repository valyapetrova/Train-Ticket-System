package org.example.Discounts;

import java.util.Scanner;

public class FamilyDiscount implements Discount{

    //Ако пътувате с дете (под 16 години), можете да
    //получите 50% отстъпка от всеки билет, ако притежавате семейна железопътна карта, в
    //противен случай получавате 10% отстъпка

    @Override
    public double calculatePrice(double price) {
        double newPrice = 0;
        newPrice = price * 50 / 100;
        return newPrice;
    }

    public boolean yearsValidation(int age){
        return age >= 1 && age <= 16;
    }
}

