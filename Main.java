package com.company;

import java.util.Scanner;


public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scanner2 = new Scanner(System.in);
    private static Game game = new Game();
    protected static Bankroll playerBankroll = new Bankroll(0);

    public static void main(String[] args) {
        boolean quit = false;
        int choice;
        while (!quit) {
            printInstructions();
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    showBankroll();
                    break;
                case 1:
                    bankrollChange();
                    break;
                case 2:
                    game.gameOn();
                    break;
                case 3:
                    System.out.println("Withdrawing: " + playerBankroll.getBankroll() + " Euro");
                    quit = true;
                    break;
            }
        }
    }


    private static void printInstructions() {
        System.out.println("\n\nWelcome to blackjack!");
        System.out.println("\nPress ");
        System.out.println("\t 0 - To show your bankroll");
        System.out.println("\t 1 - To Deposit or Withdraw money");
        System.out.println("\t 2 - To play a game of blackjack");
        System.out.println("\t 3 - To Cash out and quit");
    }

    private static void showBankroll(){
        System.out.println("Your bankroll is: " + playerBankroll.getBankroll());
    }

    private static void bankrollChange() {
        boolean noChoice = false;
        while (!noChoice) {
            System.out.println("Press 1 if you want to deposit money\npress 2 if you want to withdraw money\npress 3 to go back");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("How much do you want to deposit?");
                double addChoice = scanner.nextDouble();
                playerBankroll.deposit(addChoice);
                System.out.println("Your bankroll is: " + playerBankroll.getBankroll());
                noChoice = true;
            } else if (choice == 2) {
                System.out.println("How much do you want to withdraw?");
                double depositChoice = scanner2.nextDouble();
                if (depositChoice <= playerBankroll.getBankroll()){
                playerBankroll.withdraw(depositChoice);
                System.out.println("Your bankroll is: " + playerBankroll.getBankroll());
                noChoice = true;}
                else {
                    System.out.println("You don't have that much money! \nwithdrew " + playerBankroll.getBankroll() + " euro instead");
                    playerBankroll.withdraw(playerBankroll.getBankroll());
                }
            } else if (choice == 3) {
                System.out.println("Cancelled");
                noChoice = true;
            } else {
                System.out.println("Invalid choice, try again");
                noChoice = false;
            }
        }
    }

}
