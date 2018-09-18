package com.company;

import jdk.dynalink.beans.StaticClass;

import javax.swing.text.DefaultEditorKit;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
                    time2 = System.currentTimeMillis();
                    r.timeSpent = (time2 - time1);
                    results.add(r);
                    System.out.println("Time spent in seconds: " + r.timeSpent / 1000);
                    break;
                }

            }
            if (looser) {
                System.out.println("You loose!");
                System.out.println("The number is " + myNum);
                GameResult r = new GameResult();
                r.name = userName;
                r.triesCount = 0; // herotenj!!!
                time2 = System.currentTimeMillis();
                r.timeSpent = (time2 - time1);
                results.add(r);

                System.out.println("Time spent in seconds: " + r.timeSpent / 1000);
            }

            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("Do you want to play again? y/n");
            playAgain = askYN();
        } while (playAgain.equalsIgnoreCase("y"));

        loadResults();
        showResults();
        saveResults();
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

    private static void saveResults() {
        File file = new File("top_scores.txt");
        try(PrintWriter out = new PrintWriter(file)) {
            for (GameResult r : results) {
                out.printf("%s %d %d\r\n", r.name, r.triesCount, r.timeSpent);
            }
        } catch (IOException e) {
            System.out.println("Cannot save to file");
        }

    }

    private static void loadResults() {
        File file = new File("top_scores.txt");
        try (Scanner in = new Scanner(file)) {

            while (in.hasNext()) {
                GameResult result = new GameResult();
                result.name = in.next();
                result.triesCount = in.nextInt();
                result.timeSpent = in.nextLong();
                results.add(result);
            }

        } catch ( IOException e) {
            System.out.println("Cannot load file");
        }
    }

    private static void showResults() {
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount)
                                          .thenComparingLong(r -> r.timeSpent))
                .limit(5)
                .forEach(r -> {
            System.out.printf("Name: %s Tries: %d Time: %dsec\n", r.name, r.triesCount, r.timeSpent / 1000);
        });

    }

    private static void showResults3() {
        System.out.println("Last games:");
        int count = Math.min(5, results.size());
        for (int i = 0; i < count; i++) {
            GameResult r = results.get(i);
            System.out.printf("Name: %s Tries: %d Time: %dsec\n", r.name, r.triesCount, r.timeSpent / 1000);
        }

    }

    private static void showResults2() {
        System.out.println("Last games:");
        int count = 5;
        if (results.size() < 5) {
            count = results.size();
        }
        for (int i = 0; i < count; i++) {
            GameResult r = results.get(i);
            System.out.printf("Name: %s Tries: %d Time: %dsec\n", r.name, r.triesCount, r.timeSpent / 1000);

            //System.out.printf("Name: %s Tries: %d Time: %dsec\n", results.get(i).name, results.get(i).triesCount, results.get(i).timeSpent / 1000);
        }

    }

    private static void showResults1() {
        int counter = 0;
        for (GameResult r : results) {
            System.out.printf("Name: %s Tries: %d Time: %dsec\n", r.name, r.triesCount, r.timeSpent / 1000);
            counter++;
            if (counter == 5) {
                break;
            }
        }
    }
}
