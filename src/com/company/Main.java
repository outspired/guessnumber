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
        System.out.println("Guess the number from 1 to 100:");

        int userNum = scan.nextInt();
/*
        while (myNum != userNum) {
            if (myNum > userNum) {
                System.out.println("try bigger");
            } else {
                System.out.println("try smaller");
            }
            System.out.println("next try:");
            userNum = scan.nextInt();
        }
        System.out.println("bingo!");
*/
        for (int i = 0; i < 2; i++) {
            if (myNum > userNum) {
                System.out.println("i = " + i);
                System.out.println("try bigger");
                System.out.println("you have " + (2-i) + " tries left. Next try:");
                userNum = scan.nextInt();
            } else if (myNum < userNum){
                System.out.println("i = " + i);
                System.out.println("try smaller");
                System.out.println("you have " + (2-i) + " tries left. Next try:");
                userNum = scan.nextInt();
            } else {
                System.out.println("i = " + i);
                System.out.println("bingo!");
                return;

            }

        }
        System.out.println("looser");
    }
}
