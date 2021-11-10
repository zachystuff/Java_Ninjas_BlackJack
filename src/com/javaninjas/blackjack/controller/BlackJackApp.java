package com.javaninjas.blackjack.controller;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.javaninjas.blackjack.service.Dealer;
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
     * @see #dealerTurn() dealerTurn
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
            dealerTurn();
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

    public void dealerTurn() throws InterruptedException {
        Console.clear();
        if (Dealer.getPlayerList().stream().allMatch(Player::isBusted)) {
            System.out.println("\n\nAll players have Busted\n");
            TimeUnit.SECONDS.sleep(2);
        } else if (dealer.scoreHand() == 21) {
            System.out.println("\n\nDealer has BLACKJACK!\n" + dealer.printHand() + "\n");
            TimeUnit.SECONDS.sleep(3);
        } else {
            while (dealer.scoreHand() < 17) {
                Console.clear();
                System.out.println("\n\n" + dealer.getName() + " has\n" + dealer.printHand());
                TimeUnit.SECONDS.sleep(2);
                dealer.addCard(dealer.dealCard());
                if (dealer.scoreHand() > 21) {
                    Console.clear();
                    System.out.println("\n\nDealer Busts!");
                    System.out.println(dealer.printHand());
                    dealer.setBusted(true);
                    TimeUnit.SECONDS.sleep(2);
                }
            }
            Console.clear();
            System.out.println("\n\nDealer has a score of " + dealer.scoreHand());
            System.out.println(dealer.printHand());
        }
        dealer.setScore(dealer.scoreHand());
        TimeUnit.SECONDS.sleep(4);
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
            Dealer.getPlayerList().stream().peek(player -> player.getHand().clear())
                    .peek(player -> player.setBlackJack(false)).peek(player -> player.setBusted(false))
                    .forEach(player -> player.setScore(0));
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

    /**
     * Introduction Class for BlackJack game. A fun introduction screen based on the 80's movie "Wargames". Provides user
     * input through a Prompter class written by Jay Rostosky.
     *
     * @author Alan Pottinger
     * @version 1.0
     */
    private static class Introduction {

        private Introduction(){
        }
        /**
         * "Wargames" style introduction for blackjack game. Uses a Prompter to prompt client if they would like to see menu
         * or skip to blackjack game.
         */
        public static void introduction() throws InterruptedException {
            Console.clear();
            System.out.println("\n \u001b[32m \n" + "GREETINGS PROFESSOR FALKEN.\n");
            TimeUnit.SECONDS.sleep(3);
            String prompt = prompter.prompt("\nSHALL WE PLAY A GAME? [Y OR N]\n", "y|Y|n|N", "PLEASE SELECT Y OR N. ");
            if ("y".equalsIgnoreCase(prompt)) {
                Console.clear();
                showWopr();
                showMenu();
                String game = prompter.prompt("\nPLEASE SELECT YOUR GAME [1-9]\n", "[1-9]", "INVALID SELECTION");
                switch (game) {
                    case "1":
                        System.out.println("ENJOY YOUR GAME!");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "2":
                        System.out.println("ACCESS DENIED DUE TO COPYRIGHT INFRINGEMENT");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                        break;
                    case "3":
                        System.out.println("PEW PEW...EJECT! EJECT!...CRASH! THE END");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                        break;
                    case "4":
                        System.out.println("ISN'T THIS JUST HIDE AND SEEK WITH GUNS?");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                        break;
                    case "5":
                        System.out.println("SYSTEM ERROR: TOO MUCH SAND WHERE IT DOESN'T BELONG!");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                        break;
                    case "6":
                        System.out.println("THIS GAME HAS BEEN REPLACED WITH DRONES");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                        break;
                    case "7":
                        System.out.println("THIS GAME HAS BEEN DELETED DUE TO GRAPHIC CONTENT");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                        break;
                    case "8":
                        System.out.println("COUGH...COUGH...THE END");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                        break;
                    case "9":
                        System.out.println("EVERYONE IS DEAD...ARE YOU HAPPY NOW?");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                        TimeUnit.SECONDS.sleep(4);
                }
            } else {
                System.out.println("LOGGING OFF: W.O.P.R");
                TimeUnit.SECONDS.sleep(4);

            }
        }

        /**
         * Retrieves the wopr ascii art file and displays it.
         */
        private static void showWopr() {
            System.out.println("\n\n");
            try {
                Files.lines(Path.of("resources", "wopr.txt")).forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Displays "Wargames" style menu.
         */
        private static void showMenu() {
            System.out.println("\nLOGON: LIST GAMES");
            System.out.println("\n\n" +
                    "1. BLACKJACK\n\n" +
                    "2. FALKEN'S MAZE\n\n" +
                    "3. FIGHTER COMBAT\n\n" +
                    "4. GUERRILLA ENGAGEMENT\n\n" +
                    "5. DESERT WARFARE\n\n" +
                    "6. AIR-TO-GROUND ACTIONS\n\n" +
                    "7. THEATERWIDE TACTICAL WARFARE\n\n" +
                    "8. THEATERWIDE BIOTOXIC AND CHEMICAL WARFARE\n\n" +
                    "9. GLOBAL THERMONUCLEAR WAR\n\n");
        }
    }
}