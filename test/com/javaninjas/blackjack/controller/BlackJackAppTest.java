package com.javaninjas.blackjack.controller;

import com.javaninjas.blackjack.service.Cards;
import com.javaninjas.blackjack.service.Dealer;
import com.javaninjas.blackjack.service.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlackJackAppTest {
    private BlackJackApp app;


    @Before
    public void setUp() {
        app = new BlackJackApp();
    }

//    @Test
//    public void finalResult() {
//        app.getDealer().playerList.add(new Player("Alan"));
//        app.getDealer().playerList.get(0).addCard(Cards.CLUBS_ACE);
//        app.getDealer().playerList.get(0).addCard(Cards.CLUBS_JACK);
//        app.getDealer().playerList.get(0).scoreHand();
//        app.getDealer().playerList.get(0).setBlackJack(true);
//        app.getDealer().addCard(Cards.CLUBS_9);
//        app.getDealer().addCard(Cards.CLUBS_2);
//        app.getDealer().addCard(Cards.CLUBS_10);
//
//        app.finalResult();
//    }
}