package com.company;

public class Bankroll {

    private double bankroll = 0;

    Bankroll(int bankroll) {
        this.bankroll = bankroll;
    }

    double getBankroll() {
        return bankroll;
    }

    void deposit(double amount) {
        this.bankroll += amount;
    }

    void withdraw(double amount) {
        this.bankroll -= amount;
    }

    void win(double bet) {
        this.bankroll += bet;
    }

    void lose(double bet) {
        this.bankroll -= bet;
    }
}
