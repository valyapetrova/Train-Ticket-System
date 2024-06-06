package org.example.model;

public class DiscountCard {
    private String cardType;
    private int cardNum;
    public DiscountCard(String cardType, int cardNum) {
        this.cardType = cardType;
        this.cardNum = cardNum;
    }
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
