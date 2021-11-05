package com.javaninjas.blackjack.service;

import java.util.*;

/*
 * Deck contains the total amount of playing cards for a particular round
 */

public class Deck {
    private List<Cards> deck = loadDeck();

    private Deck(){}

    public static Deck getInstance() {
        Deck deck = new Deck();
        return deck;
    }

    private List<Cards> loadDeck() {
        List<Cards> deck = new LinkedList<>();
        Cards cards[] = Cards.values();
        for (Cards card: cards) {
            deck.add(card);
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }
}