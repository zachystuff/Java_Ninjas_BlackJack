package com.javaninjas.blackjack.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Enum Class for BlackJack game. Provide visual representation and scoring for cards used during game.
 *
 * @author Abdulrazak Yusuf
 * @version 1.0
 */

public enum Cards {
    SPADES_ACE("SPADES_ACE.txt", 0),
    SPADES_2("SPADES_2.txt",2),
    SPADES_3("SPADES_3.txt",3),
    SPADES_4("SPADES_4.txt",4),
    SPADES_5("SPADES_5.txt",5),
    SPADES_6("SPADES_6.txt",6),
    SPADES_7("SPADES_7.txt",7),
    SPADES_8("SPADES_8.txt",9),
    SPADES_9("SPADES_9.txt",9),
    SPADES_10("SPADES_10.txt",10),
    SPADES_JACK("SPADES_JACK.txt",10),
    SPADES_QUEEN("SPADES_QUEEN.txt",10),
    SPADES_KING("SPADES_KING.txt",10),
    DIAMONDS_ACE("DIAMONDS_ACE.txt",0),
    DIAMONDS_2("DIAMONDS_2.txt",2),
    DIAMONDS_3("DIAMONDS_3.txt",3),
    DIAMONDS_4("DIAMONDS_4.txt",4),
    DIAMONDS_5("DIAMONDS_5.txt",5),
    DIAMONDS_6("DIAMONDS_6.txt",6),
    DIAMONDS_7("DIAMONDS_7.txt",7),
    DIAMONDS_8("DIAMONDS_8.txt",8),
    DIAMONDS_9("DIAMONDS_9.txt",9),
    DIAMONDS_10("DIAMONDS_10.txt",10),
    DIAMONDS_JACK("DIAMONDS_JACK.txt",10),
    DIAMONDS_QUEEN("DIAMONDS_QUEEN.txt",10),
    DIAMONDS_KING("DIAMONDS_KING.txt",10),
    HEART_ACE("HEART_ACE.txt",0),
    HEART_2("HEART_2.txt",2),
    HEART_3("HEART_3.txt",3),
    HEART_4("HEART_4.txt",4),
    HEART_5("HEART_5.txt",5),
    HEART_6("HEART_6.txt",6),
    HEART_7("HEART_7.txt",7),
    HEART_8("HEART_8.txt",8),
    HEART_9("HEART_9.txt",9),
    HEART_10("HEART_10.txt",10),
    HEART_JACK("HEART_JACK.txt",10),
    HEART_QUEEN("HEART_QUEEN.txt",10),
    HEART_KING("HEART_KING.txt",10),
    CLUBS_ACE("CLUBS_ACE.txt",0),
    CLUBS_2("CLUBS_2.txt",2),
    CLUBS_3("CLUBS_3.txt",3),
    CLUBS_4("CLUBS_4.txt",4),
    CLUBS_5("CLUBS_5.txt",5),
    CLUBS_6("CLUBS_6.txt",6),
    CLUBS_7("CLUBS_7.txt",7),
    CLUBS_8("CLUBS_8.txt",8),
    CLUBS_9("CLUBS_9.txt",9),
    CLUBS_10("CLUBS_10.txt",10),
    CLUBS_JACK("CLUBS_JACK.txt",10),
    CLUBS_QUEEN("CLUBS_QUEEN.txt",10),
    CLUBS_KING("CLUBS_KING.txt",10);

    /**
     *
     * @see #Cards(String display, int value) () constructor
     * @see #getValue() getValue
     *
     */
    private int value;
    private String display;

    /**
     * Sets display to text file.
     * Sets value to value of card
     *
     * @throws IOException
     */

    Cards(String display, int value) {
        try {
            this.display = Files.readString(Path.of("resources", display));
            this.value = value;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return Value of Card
     *
     */

    public int getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    /**
     * Custom #toString() prints out the image of the card
     *
     */
    @Override
    public String toString() {
        return getDisplay();
    }
}