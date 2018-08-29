package com.company;

import javax.swing.text.DefaultEditorKit;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int myNum = rand.nextInt(100) + 1;
        System.out.println(myNum);
        System.out.println("Guess the number from 1 to 100!");
        System.out.println("===============================");

        for (int i = 0; i < 3; i++) {
            System.out.println("You have " + (3-i) + " tries left. GO:");
            int userNum = scan.nextInt();
            if (myNum > userNum) {
                System.out.println("The number is greater.");
                System.out.println("-------------------------------");
            } else if (myNum < userNum){
                System.out.println("The number is smaller.");
                System.out.println("-------------------------------");
            } else {
                System.out.println("+++++++++++++++++++++++++++++++");
                System.out.println("BINGO!");
                return;
            }

        }
        System.out.println("You loose!");
    }
}
