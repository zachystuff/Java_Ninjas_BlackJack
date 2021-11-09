package com.javaninjas.blackjack.service;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Introduction {
    private Prompter prompter = new Prompter(new Scanner(System.in));

    public Introduction() {

    }

    public void introduction() throws InterruptedException {
        Console.clear();
        System.out.println("\n \u001b[32m \n" + "GREETINGS PROFESSOR FALKEN.\n");
        TimeUnit.SECONDS.sleep(3);
        String prompt = prompter.prompt("\nSHALL WE PLAY A GAME? [Y OR N]\n", "y|Y|n|N", "PLEASE SELECT Y OR N. ");
        if ("y".equalsIgnoreCase(prompt)) {
            Console.clear();
            showWopr();
            showMenu();
            String game = prompter.prompt("\nPLEASE SELECT YOUR GAME [1-9]\n", "[1-9]", "INVALID SELECTION");
            switch (game) {
                case "1":
                    System.out.println("ENJOY YOUR GAME!");
                    TimeUnit.SECONDS.sleep(3);
                    break;
                case "2":
                    System.out.println("ACCESS DENIED DUE TO COPYRIGHT INFRINGEMENT");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case "3":
                    System.out.println("PEW PEW...EJECT! EJECT!...CRASH! THE END");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case "4":
                    System.out.println("ISN'T THIS JUST HIDE AND SEEK WITH GUNS?");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case "5":
                    System.out.println("SYSTEM ERROR: TOO MUCH SAND WHERE IT DOESN'T BELONG!");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case "6":
                    System.out.println("THIS GAME HAS BEEN REPLACED WITH DRONES");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case "7":
                    System.out.println("THIS GAME HAS BEEN DELETED DUE TO GRAPHIC CONTENT");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case "8":
                    System.out.println("COUGH...COUGH...THE END");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case "9":
                    System.out.println("EVERYONE IS DEAD...ARE YOU HAPPY NOW?");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("WOULDN'T YOU PREFER A GOOD GAME OF BLACKJACK?");
                    TimeUnit.SECONDS.sleep(4);
            }
        } else {
            System.out.println("LOGGING OFF: W.O.P.R");
            TimeUnit.SECONDS.sleep(4);

        }
    }

    private void showWopr() {
        System.out.println("\n\n");
        try {
            Files.lines(Path.of("resources", "wopr.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMenu() {
        System.out.println("\nLOGON: LIST GAMES");
        System.out.println("\n\n" +
                "1. BLACKJACK\n\n" +
                "2. FALKEN'S MAZE\n\n" +
                "3. FIGHTER COMBAT\n\n" +
                "4. GUERRILLA ENGAGEMENT\n\n" +
                "5. DESERT WARFARE\n\n" +
                "6. AIR-TO-GROUND ACTIONS\n\n" +
                "7. THEATERWIDE TACTICAL WARFARE\n\n" +
                "8. THEATERWIDE BIOTOXIC AND CHEMICAL WARFARE\n\n" +
                "9. GLOBAL THERMONUCLEAR WAR\n\n");
    }
}