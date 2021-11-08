package com.javaninjas.blackjack.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
        List<String> handDisplay = List.of(topCard,blankCard);
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
            System.out.println("\nDealer has a score of " + scoreHand());
            System.out.println(printHand());
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