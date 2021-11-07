package com.javaninjas.blackjack.service;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
    //Fields and Attributes
    public static List<Player> playerList = new ArrayList<>();
    //TODO: add deck
    //Constructors
    public Dealer() {
        super("Dealer");
    }

    //Start Dealing where all players and dealer get two cards initially
    public Cards showTopCard(){
        return getHand().get(0);
    }

    public void initialDeal() {
        loadCards();
        shuffle();
        int round = 1;
        while (round <= 2) {
            for (Player player : getPlayerList()) {
                player.addCard(dealCard());
            }
            addCard(dealCard());
            round++;
        }
    }

    public void dealerTurn() {
        if (Dealer.getPlayerList().stream().allMatch(Player::isBusted)) {
            System.out.println("\nAll players have Busted\n");
        } else if (scoreHand() == 21) {
            System.out.println("\nDealer has BLACKJACK! " + getHand() + "\n");
        } else {
            while (scoreHand() < 17) {
                addCard(dealCard());
                if (scoreHand() > 21) {
                    System.out.println("\nDealer Busts!\n");
                    setBusted(true);
                }
            }
            System.out.println("\nDealer has a score of " + scoreHand() + " " + getHand());
        }
        setScore(scoreHand());
    }

    //TODO: load card logic
    public Cards dealCard() {
        return deck.pop();
    }

    //TODO: add shuffle
    public static List<Player> getPlayerList() {
        return playerList;
    }

    public LinkedList<Cards> getDeck() {
        return deck;
    }
}