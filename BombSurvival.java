package com.sankari;
import java.util.Scanner;

/**
 * Created by Sankari on 25.10.2015.
 */
public class BombSurvival {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // Create the array with correct size.
        String[][] gameboard = new String[10][10];
        // Input char '-' to all indexes in the array.
        for (int x = 0; x < gameboard.length; x++) {
            for (int y = 0; y < gameboard.length; y++) {
                gameboard[x][y] = "-";
            }
        }

        print(gameboard);
        bombs(gameboard);

        int pX = 0;
        int pY = 0;

        System.out.println("Choose you're place on the gameboard:");
        pX = check(pX);
        pY = check(pY);
        // Determine winning condition with boolean win.
        boolean win = true;
        // Place player on the gametable.
        if (gameboard[pX][pY].equals("B")) {
            win = false;
            gameboard[pX][pY] = "PB";
        } else {
            gameboard[pX][pY] = "P";
        }

        print(gameboard);
        // Print the correct outcome.
        if (win) {
            System.out.println("You survived the bombs, congrats!");
        } else {
            System.out.println("You lost, sorry");
        }
    }

    public static void bombs(String[][] gameboard) {

        // Number of bombs between 5 to 10.
        int bombNum = (int) (Math.random() * 6 + 5);
        // Place bombs on the gameboard.
        for (int a = 0; a < bombNum; a++) {
            int x = 0;
            int y = 0;
            // make sure the bombs don't go on top of each other.
            do {
                x = (int) (Math.random() * gameboard.length);
                y = (int) (Math.random() * gameboard.length);
            } while (gameboard[x][y].equals("B"));

            gameboard[x][y] = "B";
        }
    }

    public static void print(String[][] gameboard) {

        for (int x = 0; x < gameboard.length; x++) {
            for (int y = 0; y < gameboard.length; y++) {
                System.out.print(gameboard[x][y]);
            }

            System.out.println("");
        }

        System.out.println("");
    }

    public static int check(int num) {
        boolean loop;
        // Make sure input is a number.
        do {
            loop = true;
            String num1 = input.nextLine();
            try {
                num = Integer.parseInt(num1);
            } catch (NumberFormatException e) {
                System.err.println("Please type in a number between 0 to 9");
                loop = false;
            }
            // Make sure input doesn't go over the array index.
            if (num < 0 || num > 9) {
                loop = false;
                System.out.println("Please type in a number between 0 to 9");
            }

        } while (!loop);

        return num;
    }
}
