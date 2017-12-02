package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scanner2 = new Scanner(System.in);
    private static int playerHandValue;
    private static int bankHandvalue;
    private ArrayList<Card> used = new ArrayList<>();
    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> bankHand = new ArrayList<>();


    public void gameOn(){
        boolean noBust = true;
        boolean noBet = true;
        double betAmount = 0;
        FIRST_LOOP: {
            if(Main.playerBankroll.getBankroll() == 0){
                System.out.println("You have no money to gamble with yet. Please deposit first");
                break FIRST_LOOP;}

            System.out.println("How much do you want to bet?");
            while (noBet) {
                double betAmount2 = scanner.nextDouble();
                if (betAmount2 <= Main.playerBankroll.getBankroll()) {
                    betAmount = betAmount2;
                    noBet = false; }
                else {
                    System.out.println("You do not have that much money, try again!");
                }
            }
            playerHand.add(drawcard()); playerHand.add(drawcard());
            playerHandValue = handValueCalc();
            handPrinter(playerHand);
            if(playerHandValue == 21) {
                Main.playerBankroll.win(betAmount);
                System.out.println("Blackjack! \n" + "You win: " + betAmount + "Your bankroll is: " + Main.playerBankroll.getBankroll() );
                break FIRST_LOOP;}
            else {
                   while (noBust) {
                        System.out.println("press 1 to draw a card and any other number to stand");
                        int tempChoice = scanner2.nextInt();
                        if (tempChoice == 1){
                            playerHand.add(drawcard());
                            handPrinter(playerHand);
                            playerHandValue = handValueCalc();
                            if (playerHandValue > 21){
                                Main.playerBankroll.lose(betAmount);
                                System.out.println("You overdrawed! You lose! \n Your bankroll is: " + Main.playerBankroll.getBankroll());
                                used.clear();
                                playerHand.clear();
                                bankHand.clear();
                                break FIRST_LOOP;
                            }
                        }
                        else {noBust = false;}
                   }

                   {   do {
                       bankHand.add(drawcard());
                       bankHandvalue = bankValueCalc();}
                       while (bankHandvalue < 17);
                   }
                System.out.println("The bank has got: "); handPrinter(bankHand);

            }

            if(bankHandvalue > 21){
                Main.playerBankroll.win(betAmount);
                System.out.println("The bank overdrawed! You win! \nYour bankroll is: " + Main.playerBankroll.getBankroll());
            } else if(bankHandvalue < playerHandValue){
                Main.playerBankroll.win(betAmount);
                System.out.println("You win! \nYour bankroll is: " + Main.playerBankroll.getBankroll());
            } else if (bankHandvalue > playerHandValue){
                Main.playerBankroll.lose(betAmount);
                System.out.println("You lose! \nYour bankroll is: " + Main.playerBankroll.getBankroll());
            } else {
                System.out.println("It's a push! \nYour bankroll is: " + Main.playerBankroll.getBankroll());
            }
        }

        used.clear();
        playerHand.clear();
        bankHand.clear();

    }


    private Card drawcard() {
        String[] suitList = {" of Hearts", " of Clubs", " of Spades", " of Diamonds"};
        String[] valueList = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        boolean cardChecked = false;
        Card tempCard = null;
        while (!cardChecked) {
            Card tempCard2 = new Card(valueList[ThreadLocalRandom.current().nextInt(0, 12)], suitList[ThreadLocalRandom.current().nextInt(0, 3)]);
            if (!cardCheck(tempCard2.getSuit(), tempCard2.getValue())){
                tempCard = tempCard2;
                cardChecked = true;
            }
        }
        used.add(tempCard);
        return tempCard;
    }

    private boolean cardCheck(String suitName, String suitValue) {
        for (int i = 0; i < used.size(); i++) {
            if (used.get(i).getSuit().contains(suitName) && (used.get(i).getValue().contains(suitValue)))
                return true;
        }
        return false;
    }

    private int valueCalc(String value, int valueType){
        if (value.contains("Ace")){
            if(valueType < 11){return 11;}
            else {return 1;}
        }
        else if(value.contains("Jack") || (value.contains("Queen") || value.contains("King"))){
            return 10;}
        else {
            return Integer.parseInt(value);
        }

    }

    private void handPrinter(ArrayList<Card> tempArray) {
        for (int i = 0; i < tempArray.size(); i++) {
            System.out.println(tempArray.get(i).getValue() + tempArray.get(i).getSuit() + " ");
        }
    }

    private int handValueCalc(){
    int count = 0;
    for(int i = 0;i<playerHand.size();i++){
        int tempValue = valueCalc(playerHand.get(i).getValue(), playerHandValue);
        count += tempValue;
    }
    return count;
}

    private int bankValueCalc(){
        int count = 0;
        for(int i = 0;i<bankHand.size();i++){
            int tempValue = valueCalc(bankHand.get(i).getValue(), bankHandvalue);
            count += tempValue;
        }
        return count;
    }

}

