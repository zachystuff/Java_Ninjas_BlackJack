package com.javaninjas.blackjack.service;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Cards> hand = new ArrayList<>();
    private int score = 0;

    public void addCard(Cards card) {
        hand.add(card);
    }

    public int scoreHand() {
        int aces = 0;
        for (Cards card : hand) {
            if(card.getValue() == 0) {
                aces++;
            }
            this.score += card.getValue();
        }

        if (aces != 0) {
            for (int i = 0; i < aces; i++) {
                if(getScore() + 11 < 21){
                    this.score+=11;
                } else {
                    this.score+=1;
                }
            }
        }
        return getScore();
    }

    public int getScore() {
        return score;
    }
}