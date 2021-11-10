package com.javaninjas.blackjack.service;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new Player("Smith");
    }

    @Test
    public void testScoreHand_whenNonPictureCardsAreOnTheTable() {
        Cards card1 = Cards.CLUBS_9;
        Cards card2 = Cards.DIAMONDS_7;
        player.addCard(card1);
        player.addCard(card2);
        int score = card1.getValue() + card2.getValue();
        assertEquals(score, player.scoreHand());
    }

    @Test
    public void testScoreHand_whenFaceCardsAreOnTheTable() {
        Cards card1 = Cards.DIAMONDS_JACK;
        Cards card2 = Cards.DIAMONDS_JACK;
        player.addCard(card1);
        player.addCard(card2);
        int score = card1.getValue() + card2.getValue();
        assertEquals(score, player.scoreHand());
    }

    @Test
    public void testScoreHand_whenAcesOnTheTable() {
        Cards card1 = Cards.HEART_ACE;
        Cards card2 = Cards.DIAMONDS_7;
        Cards card3 = Cards.CLUBS_3;
        player.addCard (card1);
        player.addCard(card2);
        player.addCard(card3);
        int score = 21;
        assertEquals(score, player.scoreHand());
    }

    @Test
    public void testScoreHand_whenAllAcesOnTheTableForSinglePlayer() {
        Cards card1 = Cards.HEART_ACE;
        Cards card2= Cards.DIAMONDS_ACE;
        Cards card3 = Cards.SPADES_ACE;
        Cards card4 = Cards.CLUBS_ACE;
        Cards card5 = Cards.DIAMONDS_7;
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);
        player.addCard(card4);
        player.addCard(card5);

        int score = 21;
        assertEquals(score, player.scoreHand());
    }

    @Test
    public void testScoreHand_whenAlreadyTwentyOneButPlayerDecidedToHit() {
        Cards card1 = Cards.HEART_ACE;
        Cards card2 = Cards.DIAMONDS_9;
        Cards card3 = Cards.SPADES_ACE;
        Cards card4 = Cards.HEART_7;

        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);
        player.addCard(card4);

        int score = 18;
        assertEquals(score, player.scoreHand());
    }

    @Test
    public void testScoreHand_whenFaceCardAndAceBothOnTheTable() {
        Cards card1 = Cards.HEART_ACE;
        Cards card2 = Cards.SPADES_QUEEN;

        player.addCard(card1);
        player.addCard(card2);

        int score = 21;
        assertEquals(score, player.scoreHand());
    }

    @Test
    public void testIsBusted_whenTrue() { //testing when set as true
        player.setBusted(true);
        assertEquals(true, player.isBusted());
    }

    @Test
    public void testIsBusted_whenFalseDefault() { //checking busted when default is false
        Player player = new Player("Smith");
        assertEquals(false, player.isBusted());
    }

    @Test
    public void testIsBusted_whenFalseSetManually() { //testing when false is set manually
        player.setBusted(false);
        assertEquals(false, player.isBusted());
    }

    @Test
    public void testAddCard_whenPlayerGetCard() {
        Cards card1 = Cards.HEART_ACE;
        Cards card2 = Cards.SPADES_QUEEN;

        player.addCard(card1);
        player.addCard(card2);

        List<Cards> hand = player.getHand();
        /*
         * check that the size is equal
         */
        assertEquals(2, hand.size());
        /*
         *checking the order of card
         */
        assertEquals(card1, hand.get(0));
        assertEquals(card2, hand.get(1));
    }
}