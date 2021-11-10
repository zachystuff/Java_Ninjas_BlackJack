package com.javaninjas.blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Player class for BlackJack game. This class provides scoreHand() method, returns boolean hasBlackJack or isBusted
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
    public Player(String name) {
        this.name = name;
    }

    //Business Method
    /**
     * Main scoring method for blackjack app
     *
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

    public void addCard(Cards card) {
        hand.add(card);
    }

    public String getName() {
        return name;
    }
    /**
     * Prints out the players current hand side by side
     *
     */
    public String printHand() {
        List<String> handDisplay = hand.stream().map(Cards::toString).collect(Collectors.toList());
        List<List<String>> split = handDisplay.stream().map(x -> Stream.of(x.split("\\r\\n?|\\n")).collect(Collectors.toList()))
                .collect(Collectors.toList());

        return IntStream.range(0, 6).mapToObj(i -> split.stream().map(String -> String.get(i)).collect(Collectors.joining()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

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