package com.javaninjas.blackjack.client;

import com.javaninjas.blackjack.controller.BlackJackApp;

/**
 * This is the main class that instantiates BlackJackApp and calls execute().
 */
class Main {

    public static void main(String[] args) {
        BlackJackApp app = new BlackJackApp();
        app.execute();
    }
}