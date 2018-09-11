package com.company;

import jdk.dynalink.beans.StaticClass;

import javax.swing.text.DefaultEditorKit;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static List<GameResult> results = new ArrayList<>();

    public static void main(String[] args) {
        String playAgain;
        String userName;
        long time1;
        long time2;

        do {
            do {
                System.out.println("Please enter your name:");
                userName = scan.next();
            } while (userName == "");

            int myNum = rand.nextInt(100) + 1;
            int numberOfTries = 5;
            System.out.println(myNum);
            System.out.println(userName + ", guess the number from 1 to 100!");
            System.out.println("===============================");
            boolean looser = true;
            time1 = System.currentTimeMillis();
            for (int i = 0; i < numberOfTries; i++) {
                System.out.println("You have " + (numberOfTries - i) + " tries left.");

                //int userNum = scan.nextInt();
                int userNum = askNum();
                if (myNum > userNum) {
                    System.out.println("The number is greater.");
                    System.out.println("-------------------------------");
                } else if (myNum < userNum) {
                    System.out.println("The number is smaller.");
                    System.out.println("-------------------------------");
                } else {
                    System.out.println("+++++++++++++++++++++++++++++++");
                    System.out.println("BINGO, " + userName + "!!!");
                    looser = false;

                    GameResult r = new GameResult();
                    r.name = userName;
                    r.triesCount = i + 1; // herotenj!!!
                    results.add(r);
                    time2 = System.currentTimeMillis();
                    long timeSpent = (time2 - time1)/1000;
                    System.out.println("Time spent in seconds: " + timeSpent);
                    break;
                }

            }
            if (looser) {
                System.out.println("You loose!");
                System.out.println("The number is " + myNum);
                GameResult r = new GameResult();
                r.name = userName;
                r.triesCount = 9999; // herotenj!!!
                results.add(r);
                time2 = System.currentTimeMillis();
                long timeSpent = (time2 - time1)/1000;
                System.out.println("Time spent in seconds: " + timeSpent);
            }

            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("Do you want to play again? y/n");
            playAgain = askYN();
        } while (playAgain.equalsIgnoreCase("y"));
        showResults();
        System.out.println("BYE");
    }

    private static void showResults() {
        for (GameResult r : results) {
            System.out.println(r.name + " => " + r.triesCount);
        }
    }

    static String askYN() {
        String answer;
        do {
            answer = scan.next();
            if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                System.out.println("Answer y or n!");
                continue;
            } else {
                return answer;
            }
        } while (true);
    }

    static int askNum() {
        System.out.println("Please try to guess the number:");
        int userNum;
        do {
            try {
                userNum = scan.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Please enter the number");
                scan.next();
                continue;
            }
            if (userNum > 0 && userNum < 101) {
                return userNum;
            } else {
                System.out.println("The number should from 1 to 100. Try again:");
                continue;
            }
        } while (true);
    }
}
