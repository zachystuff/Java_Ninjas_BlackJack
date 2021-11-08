package com.javaninjas.blackjack.service;

import java.util.ArrayList;
import java.util.List;

public class Player {
    /**
     *
     */
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
                if (getScore() + 11 < 21) {
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