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
        List<List<String>> split = handDisplay.stream().map(x -> Stream.of(x.split("\\r\\n?|\\n")).collect(Collectors.toList()))
                .collect(Collectors.toList());

        return IntStream.range(0, 6).mapToObj(i -> split.stream().map(String -> String.get(i)).collect(Collectors.joining()))
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

    //Methods implements dealer to play
    public void dealerTurn() throws InterruptedException {
        Console.clear();
        if (Dealer.getPlayerList().stream().allMatch(Player::isBusted)) {
            System.out.println("\n\nAll players have Busted\n");
            TimeUnit.SECONDS.sleep(2);
        } else if (scoreHand() == 21) {
            System.out.println("\n\nDealer has BLACKJACK!\n" + printHand() + "\n");
            TimeUnit.SECONDS.sleep(3);
        } else {
            while (scoreHand() < 17) {
                Console.clear();
                System.out.println("\n\n" + getName() + " has\n" + printHand());
                TimeUnit.SECONDS.sleep(2);
                addCard(dealCard());
                if (scoreHand() > 21) {
                    Console.clear();
                    System.out.println("\n\nDealer Busts!");
                    System.out.println(printHand());
                    setBusted(true);
                    TimeUnit.SECONDS.sleep(2);
                }
            }
            Console.clear();
            System.out.println("\n\nDealer has a score of " + scoreHand());
            System.out.println(printHand());
        }
        setScore(scoreHand());
        TimeUnit.SECONDS.sleep(4);
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