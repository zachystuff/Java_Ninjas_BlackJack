package com.javaninjas.blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Player class for BlackJack game. This class provides scoreHand() method printHand() method, returns boolean hasBlackJack or isBusted
 *
 * @author Subash KC, Abdulrazak Yusuf
 * @version 1.0
 */

public class Player {
    private String name;
    private double playerBalance;
    private int score = 0;
    private List<Cards> hand = new ArrayList<>();
    private boolean isBusted = false;
    private boolean hasBlackJack = false;

    //Constructors

    /**
     * Requires a String name to create a player object
     * @param name
     */
    public Player(String name) {
        this.name = name;
    }
    /**
     * Class used to aggregate player action throughout the game
     *
     * @see #scoreHand() scoreHand
     * @see #printHand() printHand
     * @see #addCard(Cards) addCard
     * @see #getHand() getHand
     */

    //Business Method
    /**
     * Main scoring method for blackjack app
     * @return int
     */
    public int scoreHand() {
        // initialize aces to zero so we can do special scoring on aces
        int aces = 0;
        // maintains a local copy of score in the method
        int score = 0;
        for (Cards card : hand) {
            if (card.getValue() == 0) {
                aces++;
            }
            score += card.getValue();
        }
        if (aces != 0) {
            for (int i = 0; i < aces; i++) {
                if (score + 11 <= 21) {
                    score += 11;
                } else {
                    score += 1;
                }
            }
        }
        return score;
    }

    /**
     * Adds Cards to the players hand
     * @param card
     */
    public void addCard(Cards card) {
        hand.add(card);
    }

    public String getName() {
        return name;
    }
    /**
     * Prints out the players current hand side by side
     * @return String
     */
    public String printHand() {
        //creates a list of the toString() outputs from each card.
        List<String> handDisplay = hand.stream().map(Cards::toString).collect(Collectors.toList());
        //splits each card into individual lines and stores each card in a list, then stores each List of card in a List.
        List<List<String>> split = handDisplay.stream().map(str -> Stream.of(str.split("\\r\\n?|\\n"))
                        .collect(Collectors.toList())).collect(Collectors.toList());
        //joins each line of the cards together and returns a String of all the cards in the hand.
        return IntStream.range(0, 6).mapToObj(i -> split.stream().map(line -> line.get(i)).collect(Collectors.joining()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Retrieves the players' current hand
     */
    public List<Cards> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }

    public boolean isBusted() {
        return isBusted;
    }

    public boolean HasBlackJack() {
        return hasBlackJack;
    }

    public void setBlackJack(boolean hasBlackJack) {
        this.hasBlackJack = hasBlackJack;
    }

    @Override
    public String toString() {
        return "Player: " +
                "name='" + getName();
    }
}