package com.javaninjas.blackjack.client;

import com.javaninjas.blackjack.controller.BlackJackApp;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        BlackJackApp app = new BlackJackApp();
        app.execute();
    }
}