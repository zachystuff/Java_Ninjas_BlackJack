package com.javaninjas.blackjack.service;

public enum Cards {
    SPADES_ACE(0),
    SPADES_2(2),
    SPADES_3(3),
    SPADES_4(4),
    SPADES_5(5),
    SPADES_6(6),
    SPADES_7(7),
    SPADES_8(9),
    SPADES_9(9),
    SPADES_10(10),
    SPADES_JACK(10),
    SPADES_QUEEN(10),
    SPADES_KING(10),
    DIAMONDS_ACE(0),
    DIAMONDS_2(2),
    DIAMONDS_3(3),
    DIAMONDS_4(4),
    DIAMONDS_5(5),
    DIAMONDS_6(6),
    DIAMONDS_7(7),
    DIAMONDS_8(8),
    DIAMONDS_9(9),
    DIAMONDS_10(10),
    DIAMONDS_JACK(10),
    DIAMONDS_QUEEN(10),
    DIAMONDS_KING(10),
    HEART_ACE(0),
    HEART_2(2),
    HEART_3(3),
    HEART_4(4),
    HEART_5(5),
    HEART_6(6),
    HEART_7(7),
    HEART_8(8),
    HEART_9(9),
    HEART_10(10),
    HEART_JACK(10),
    HEART_QUEEN(10),
    HEART_KING(10),
    CLUBS_ACE(0),
    CLUBS_2(2),
    CLUBS_3(3),
    CLUBS_4(4),
    CLUBS_5(5),
    CLUBS_6(6),
    CLUBS_7(7),
    CLUBS_8(8),
    CLUBS_9(9),
    CLUBS_10(10),
    CLUBS_JACK(10),
    CLUBS_QUEEN(10),
    CLUBS_KING(10);

    private final int value;

    Cards(int value){
        this.value = value;
    };

    public int getValue() {
        return value;
    }
}