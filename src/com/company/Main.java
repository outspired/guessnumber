package com.company;

import javax.swing.text.DefaultEditorKit;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String playAgain;
        do {

            int myNum = rand.nextInt(100) + 1;
            int numberOfTries = 5;
            System.out.println(myNum);
            System.out.println("Guess the number from 1 to 100!");
            System.out.println("===============================");
            boolean looser = true;
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
                    System.out.println("BINGO!");
                    looser = false;
                    //return;
                    break;
                }

            }
            if (looser) {
                System.out.println("You loose!");
                System.out.println("The number is " + myNum);
            }
            System.out.println("///////////////////////////////");
            System.out.println("Do you want to play again? y/n");
            playAgain = askYN();
        } while (playAgain.equalsIgnoreCase("y"));
        System.out.println("BYE");
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
