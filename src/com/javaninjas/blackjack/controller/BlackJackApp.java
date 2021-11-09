package com.javaninjas.blackjack.controller;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.javaninjas.blackjack.service.Dealer;
import com.javaninjas.blackjack.service.Introduction;
import com.javaninjas.blackjack.service.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Controller Class for BlackJack game. Provides complete game flow through {@link #execute() execute} method. Provides
 * user input through a Prompter class written by Jay Rostosky.
 *
 * @author Alan Pottinger
 * @version 1.0
 */
public class BlackJackApp {
    //FIELDS
    private int numPlayers;
    private boolean gameOver = false;
    private final Dealer dealer = new Dealer();
    private static final Prompter prompter = new Prompter(new Scanner(System.in));


    // NO-ARGUMENT CTOR
    public BlackJackApp() {
    }

    // BUSINESS METHODS
    /**
     * Calls multiple methods in this class as well as other classes to guide client through a complete game of
     * BlackJack. After each round, client is prompted to {@link #playAgain() play again}. If client selects to play
     * another round, the game will reset Player and Dealer before the start of the next round. If client chooses not
     * to play another round, the application will exit.
     *
     * @see Introduction#introduction() introduction
     * @see #welcome() welcome
     * @see #getNumberOfPlayers() getNumberOfPlayers
     * @see #getPlayerNames() getPlayerNames
     * @see Dealer#initialDeal() initialDeal
     * @see #playerTurn() playerTurn
     * @see Dealer#dealerTurn() dealerTurn
     * @see #finalResult() finalResult
     * @see #playAgain() playAgain
     * @see #gameOver() gameOver
     */
    public void execute() throws InterruptedException {
        Introduction.introduction();
        Console.clear();
        welcome();
        getNumberOfPlayers();
        getPlayerNames();
        Console.clear();
        while (!isGameOver()) {
            getDealer().initialDeal();
            playerTurn();
            getDealer().dealerTurn();
            finalResult();
            playAgain();
        }
        gameOver();

    }

    /**
     * Retrieves the welcome ascii art file and displays it.
     */
    private void welcome() {
        System.out.println("\n\n");
        try {
            Files.lines(Path.of("resources", "banner1.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");
    }

    /**
     * Uses Prompter to prompt the client for the number of players. Range [1-6].
     */
    private void getNumberOfPlayers() {
        String number = prompter.prompt("Please select number of players [1-6]\n", "[1-6]", "\n" +
                "Invalid number of players! Please select between 1 and 6 players");
        setNumPlayers(Integer.parseInt(number));
    }

    /**
     * Uses Prompter to prompt the client for the player names.
     */
    private void getPlayerNames() {
        int count = 1;
        while (count <= getNumPlayers()) {
            String name = prompter.prompt("Player " + (count) + ": Please enter your name\n");
            Dealer.playerList.add(new Player(name));
            count++;
        }
    }

    /**
     * Guides each player through their turn.
     */
    private void playerTurn() throws InterruptedException {
        for (Player player : Dealer.getPlayerList()) {
            if (player.scoreHand() == 21) {  // Checks if the player has blackjack
                Console.clear();
                blackJack();  // Prints ascii blackjack banner.
                System.out.println("\n" + player.getName() + " has BLACKJACK!!!!");
                System.out.println(player.printHand());
                player.setBlackJack(true);
                TimeUnit.SECONDS.sleep(4);
            } else {
                boolean flag = true;
                while (flag) {
                    Console.clear();
                    // Displays dealer and player hand, then prompts player to hit or stand.
                    System.out.println("\n\n" + getDealer().getName() + " is showing \n" + getDealer().showTopCard() + "\n");
                    System.out.println(player.getName() + " has \n" + player.printHand());
                    System.out.println("\n" + player.getName() + "'s current score is " + player.scoreHand());
                    String response = prompter.prompt("\nWould you like to [h]it or [s]tand?\n", "s|S|h|H", "\nInvalid " +
                            "option! Please press either (h) or (s)\n");
                    if ("h".equalsIgnoreCase(response)) {  // Player hits
                        player.addCard(getDealer().dealCard());
                        if (player.scoreHand() > 21) {  // Checks if player has busted.
                            Console.clear();
                            busted();  // Prints ascii busted banner.
                            System.out.println("\nYou have Busted! Your score is " + player.scoreHand());
                            System.out.println(player.printHand());
                            player.setBusted(true);
                            flag = false;
                            TimeUnit.SECONDS.sleep(4);
                        }
                    } else {  // Player stands
                        flag = false;
                    }
                }
            }player.setScore(player.scoreHand());
        }
    }

    /**
     * Calculates the final result of game and displays result to screen.
     */
    private void finalResult() throws InterruptedException {
        Console.clear();
        if (getDealer().isBusted()) {  // Checks if dealer busted.
            congrats();  // Prints ascii Congratulations!!! banner.
            System.out.println("\n");
            Dealer.getPlayerList().stream().filter(player -> !player.isBusted()).  // Filters out any player who busted.
                    forEach(player -> System.out.println(player.getName() + " WINS!!!"));
        } else if (Dealer.getPlayerList().stream().allMatch(Player::isBusted)) { // Checks if all players busted.
            dealerWins();  // Prints ascii Dealer Wins banner
            // Checks if dealer and any play has blackjack
        } else if (Dealer.getPlayerList().stream().anyMatch(Player::HasBlackJack) && getDealer().HasBlackJack()) {
            congrats();
            Dealer.getPlayerList().stream().filter(Player::HasBlackJack) // Filters all players who have blackjack.
                    .forEach(player -> System.out.println(player.getName() + "Pushes"));
        } else {
            Collection<Player> winners = Dealer.getPlayerList().stream()  // Collects all players who won.
                    .filter(player -> player.getScore() > getDealer().getScore() && player.getScore() <= 21 || player.HasBlackJack())
                    .collect(Collectors.toList());
            Collection<Player> pushers = Dealer.getPlayerList().stream()  // Collects all players who tied dealer.
                    .filter(player -> player.getScore() == getDealer().getScore())
                    .collect(Collectors.toList());
            if (pushers.size() == 0 && winners.size() == 0) {  // checks if any plays won or tied dealer.
                dealerWins();
            } else {
                congrats();
                System.out.println("\n");
                winners.forEach(player -> System.out.println(player.getName() + " WINS!!!"));
                pushers.forEach(player -> System.out.println(player.getName() + " Pushes"));
            }
        }
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     *  Uses Prompter to prompt player to play again. If player selects yes, resets dealer, player, and deck for next
     *  round. If player selects no, sets gameOver field to true.
     */
    private void playAgain() {
        String replay = prompter.prompt("\n\n\nWould you like to play again? Select y or n.\n", "y|Y|n|N",
                "Invalid response");
        if ("y".equalsIgnoreCase(replay)) {
            getDealer().getHand().clear();
            getDealer().getDeck().clear();
            getDealer().setBusted(false);
            getDealer().setBlackJack(false);
            getDealer().setScore(0);
            Dealer.getPlayerList().forEach(player -> player.getHand().clear());
            Dealer.getPlayerList().forEach(player -> player.setBlackJack(false));
            Dealer.getPlayerList().forEach(player -> player.setBusted(false));
            Dealer.getPlayerList().forEach(player -> player.setScore(0));
        } else {
            setGameOver(true);
        }
    }

    /**
     * Retrieves the game over ascii art file and displays it.
     */
    private void gameOver() {
        Console.clear();
        System.out.println("\n\n");
        try {
            Files.lines(Path.of("resources", "gameover.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the dealer wins ascii art file and displays it.
     */
    private void dealerWins(){
        System.out.println("\n\n");
        try {
            Files.lines(Path.of("resources", "Dealerwin.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the congratulation ascii art file and displays it.
     */
    private void congrats(){
        System.out.println("\n");
        try {
            Files.lines(Path.of("resources", "Congrats.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the busted ascii art file and displays it.
     */
    private void busted() {
        System.out.println("\n\n");
        try {
            Files.lines(Path.of("resources", "Busted.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the welcome ascii art file and displays it.
     */
    private void blackJack() {
        System.out.println("\n\n");
        try {
            Files.lines(Path.of("resources","Blackjack.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ACCESSOR METHODS
    private boolean isGameOver() {
        return gameOver;
    }

    private void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    private int getNumPlayers() {
        return numPlayers;
    }

    public Dealer getDealer() {
        return dealer;
    }

}