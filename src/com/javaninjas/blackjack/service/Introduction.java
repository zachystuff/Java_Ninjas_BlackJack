package com.javaninjas.blackjack.service;

import com.apps.util.Prompter;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Introduction {
    private Prompter prompter = new Prompter(new Scanner(System.in));

    public Introduction() {

    }

    public void introduction() throws InterruptedException {
        System.out.println("\nGREETINGS PROFESSOR FALKEN.\n");
        TimeUnit.SECONDS.sleep(5);
        String prompt = prompter.prompt("\nSHALL WE PLAY A GAME?\n", "y|Y|n|N", "PLEASE SELECT Y OR N. ");
        if ("y".equalsIgnoreCase(prompt)){
            showMenu();
            String game = prompter.prompt("\nPLEASE SELECT YOUR GAME [1-9]\n", "[1-9]", "INVALID SELECTION");
            switch (game){
                case "1":
                    System.out.println("ENJOY YOUR GAME!");
                    TimeUnit.SECONDS.sleep(5);
                    break;
                default:
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(5);
            }
        }

    }

    private void showMenu(){
        System.out.println("\nLOGON: LIST GAMES");
        System.out.println("\n" +
                "1. BLACKJACK\n" +
                "2. FALKEN'S MAZE\n" +
                "3. FIGHTER COMBAT\n" +
                "4. GUERRILLA ENGAGEMENT\n" +
                "5. DESERT WARFARE\n" +
                "6. AIR-TO-GROUND ACTIONS\n" +
                "7. THEATERWIDE TACTICAL WARFARE\n" +
                "8. THEATERWIDE BIOTOXIC AND CHEMICAL WARFARE\n" +
                "9. GLOBAL THERMONUCLEAR WAR");
    }
}