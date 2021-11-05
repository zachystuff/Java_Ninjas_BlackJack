package com.javaninjas.blackjack.service;

public enum Cards {
    SPADE_ACE(11),
    SPADE_2(2),
    SPADE_3(3),
    SPADE_4(4),
    SPADE_5(5),
    SPADE_6(6),
    SPADE_7(7),
    SPADE_8(9),
    SPADE_9(9),
    SPADE_10(10),
    SPADE_JACK(10),
    SPADE_QUEEN(10),
    SPADE_KING(10),
    DIAMONDS_ACE(11),
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
    HEART_ACE(11),
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
    CLUBS_ACE(11),
    CLUBS_1(1),
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

}