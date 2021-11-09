package com.javaninjas.blackjack.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DealerTest {
    private Dealer dealer;
    private Player player1;
    private Player player2;
    private List<Cards> hand1;
    private List<Cards> hand2;
    private List<Cards> dealerHand;

    @Before
    public void setUp() {
        player1 = new Player("Smith");
        player2 = new Player("Sam");
        dealer = new Dealer();
        hand1 = player1.getHand();
        hand2 = player2.getHand();
        dealerHand = dealer.getHand();
    }

    @Test
    public void testDealCardAndDeckSize_whenAllThePlayersAndDealersReceivedTwoCards() {
        Dealer.playerList.add(player1);
        Dealer.playerList.add(player2);

        //In the beginning each player received two cards
        dealer.initialDeal();

        //make sure the deck size is reduced by 6
        LinkedList<Cards> deck = dealer.getDeck();
        assertEquals(46, deck.size());
    }

    @Test
    public void testInitialDeal_whenAllThePlayersReceivedTwoCards() {
        Dealer.playerList.add(player1);
        Dealer.playerList.add(player2);

        //make sure in the beginning each player and dealer has 2 cards in hand
        dealer.initialDeal();

        assertEquals(2, hand1.size());
        assertEquals(2, hand2.size());
        assertEquals(2, dealerHand.size());
    }

    @Test
    public void testDuplicationOfCard_whenPlayerReceivedTwoCards() {
        Dealer.playerList.add(player1);
        /*
         * Verifying the cards is not duplicated in player hand
         * Verifying player1 one card against player1 another card
         */
        dealer.initialDeal();
        for (Cards card : hand1) {
            assertEquals(false, hand1.get(0).equals(hand1.get(1)));
        }
    }

    @Test
    public void testDuplicationOfCard_whenPlayerOneReceivedTwoCardsAgainstPlayerTwoAndDeck() {
        Dealer.playerList.add(player1);
        Dealer.playerList.add(player2);
        /*
         * Verify the cards is not duplicated either of the player hands and deck
         * Verifying player1 card against deck, player 2 and dealer hand
         */
        dealer.initialDeal();
        LinkedList<Cards> deck = dealer.getDeck();
        for (Cards card : hand1) {
            assertEquals(false, hand2.contains(card));
            assertEquals(false, deck.contains(card));
            assertEquals(false, dealerHand.contains(card));
        }
    }

    @Test
    public void testDuplicationOfCard_whenPlayerTwoReceivedCardsAgainstPlayerOneAndDeck() {
        Dealer.playerList.add(player1);
        Dealer.playerList.add(player2);
        /*
         * Testing the card against player one and dealer card and the deck for duplicates
         */
        dealer.initialDeal();
        LinkedList<Cards> deck = dealer.getDeck();
        for(Cards card : hand2) {
            assertEquals(false, hand1.contains(card));
            assertEquals(false, deck.contains(card));
            assertEquals(false, dealerHand.contains(card));
        }
    }

    @Test
    public void testDuplicationOfCardInTheDeck_whenAllPlayersAndDealerReceivedCardAgainstEachPlayersAndDeck() {
        Dealer.playerList.add(player1);
        Dealer.playerList.add(player2);
        /*
         * Verifying the deck card against each player card and dealer card for the duplication
         *
         */
        dealer.initialDeal();
        LinkedList<Cards> deck = dealer.getDeck();
        for (Cards card : deck) {
            assertEquals(false, hand1.contains(card));
            assertEquals(false, hand2.contains(card));
            assertEquals(false, dealerHand.contains(card));
        }
    }

    @Test
    public void testDealCard_whenRemovedTheTopCardFromTheDeck() {
        Dealer.playerList.add(player1);
        Dealer.playerList.add(player2);

        //call the initialDeal() so the deck gets initialized
        dealer.initialDeal();
        LinkedList<Cards> deck = dealer.getDeck();

        //checking that the top card of the deck is returned by the dealCard() method
        Cards topCard = deck.peek();
        Cards dealCard = dealer.dealCard();

        assertEquals(topCard, dealCard);
    }
}