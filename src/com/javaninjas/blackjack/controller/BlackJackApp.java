package com.javaninjas.blackjack.controller;

import com.apps.util.Prompter;
import com.javaninjas.blackjack.service.Player;
import com.javaninjas.blackjack.service.PlayerFactory;
import com.javaninjas.blackjack.service.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class BlackJackApp {
    private int numPlayers;
    private Table table = new Table();
    private boolean gameOver = false;
    private Prompter prompter = new Prompter(new Scanner(System.in));

    public BlackJackApp(){

    }


    public void execute() {
        welcome();
        getNumberOfPlayers();
        getPlayerNames();
        while (!gameOver){
            Deck.shuffle();
            Dealer.initialDeal();
            playerTurn();
            dealerTurn();
            finalResult();
            playAgain();
        }
    }


    private void welcome() {
        // TODO: use asciiart for a welcome banner
        System.out.println("\n");
        System.out.println("W E L C O M E  T O  B L A C K J A C K");
        System.out.println("\n");
    }

    private void getNumberOfPlayers() {
        String number = prompter.prompt("Please select number of players [1-6]","\\d[1-6]", "\n" +
                "Invalid number of players! Please select between 1 and 6 players");
        setNumPlayers(Integer.parseInt(number));
    }

    private void getPlayerNames() {
        int count = 0;
        while (count < getNumPlayers()){
            String name = prompter.prompt("Player " + count + ": Please enter your name");
            table.addPlayer(count,PlayerFactory.createPlayer(name));
            count++;
        }
        table.addPlayer(getNumPlayers(), PlayerFactory.createPlayer("New_Dealer"));
    }



    // ACCESSOR METHODS
    private void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    private int getNumPlayers() {
        return numPlayers;
    }
}