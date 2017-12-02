package com.company;

public class Card {


    private String suit;
    private String value;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    protected String getSuit() {
        return suit;
    }

    protected String getValue() {
        return value;
    }
}

