package com.javaninjas.blackjack.service;


import com.apps.util.Console;
import com.javaninjas.blackjack.controller.BlackJackApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Dealer class for BlackJack game and extends Player class. It consists of Player List which is public and static
 * And deck of Cards
 * @author Subash KC
 * version 1.0
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
    public String showTopCard() {
        String blankCard = null;
        try {
            blankCard = Files.readString(Path.of("resources", "Blank.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String topCard = getHand().get(0).toString();
        List<String> handDisplay = List.of(topCard, blankCard);
        //splits each card into individual lines and stores each card in a list, then stores each List of card in a List.
        List<List<String>> split = handDisplay.stream().map(str -> Stream.of(str.split("\\r\\n?|\\n"))
                .collect(Collectors.toList())).collect(Collectors.toList());
        //joins each line of the cards together and returns a String of all the cards in the hand.
        return IntStream.range(0, 6).mapToObj(i -> split.stream().map(line -> line.get(i)).collect(Collectors.joining()))
                .collect(Collectors.joining(System.lineSeparator()));
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

    /**
     * Deals the cards to the players
     *
     * @return return cards after removing top card in the deck.
     */
    public Cards dealCard() {
        return deck.pop();
    }

    /**
     * Shows the player list for the game app
     *
     * @return Player list
     */
    public static List<Player> getPlayerList() {
        return playerList;
    }

    public LinkedList<Cards> getDeck() {
        return deck;
    }
}