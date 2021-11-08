package com.javaninjas.blackjack.service;

import java.util.*;

/**
 * Dealer class for BlackJack game and extends Player class. It consists of Player List which is public and static
 * And deck of Cards
 */
public class Dealer extends Player {
    //Fields and Attributes
    public static List<Player> playerList = new ArrayList<>();
    private LinkedList<Cards> deck;
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
            getPlayerList().forEach(player -> player.addCard(dealCard()));
            addCard(dealCard());
            round++;
        }
    }

    /**
     * Shuffles the deck
     */
    private void shuffle() {
        Collections.shuffle(deck);
    }

    private void loadCards() {
        //enum.value() returns enum constant as arrays
        Collection<Cards> cards = Arrays.asList(Cards.values());
        this.deck = new LinkedList<>(cards);
    }

    //Methods implements dealer to play
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

    /**
     * Deals the cards to the players
     * @return return cards after removing top card in the deck.
     */
    public Cards dealCard() {
        return deck.pop();
    }

    /**
     * Shows the player list for the game app
     * @return Player list
     */
    public static List<Player> getPlayerList() {
        return playerList;
    }

    public LinkedList<Cards> getDeck() {
        return deck;
    }
}