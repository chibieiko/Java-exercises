// package com.sankari;
import java.util.Scanner;

/**
 * Runs TicTacToe -game.
 *
 * <p>
 *
 * Class TicTacToe contains nine methods. Each method has their own
 * unique action. For example the methods check that the player input
 * is correct, print the gameboard and place marks on the gameboard.
 *
 * <p>
 *
 * Copyright (c) 2015. Erika Sankari. All Rights Reserved.
 *
 * @author Erika Sankari
 * @version 2015.1213
 * @since 1.8
 */
public class TicTacToe {

    /**
     * Takes in player input.
     */
    static Scanner input = new Scanner(System.in);

    /**
     * Determines the winning streak.
     */
    static int wStreak;

    /**
     * Gameboard for the game.
     */
    static String[][] gameboard;

    /**
     * Height of the gameboard.
     */
    static int height;

    /**
     * Width of the gameboard.
     */
    static int width;

    /**
     * Loops the game.
     *
     * <p>
     *
     * Sets up the gameboard and winning streak according to player's wishes.
     * Uses the method fillgb() to fill the gameboard with empty 'boxes', prints
     * the gameboard with method printgb() and starts the game in method play().
     * Enables replay.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        // Boolean playAgain enables replay.
        boolean playAgain;

        // Loops the game until player gets bored of playing it.
        do {
            playAgain = false;
            System.out.println("Welcome to TicTacToe! :D");
            // Height and width limited so that the game looks smart.
            System.out.println("Type in the height of the gameboard:");
            height = checkInt(1, 26) + 1;

            System.out.println("Type in the width of the gameboard:");
            width = checkInt(1, 26) + 1;

            // Counts the maximum value for winning streak.
            int max = height - 1;

            if (max < width) {
                max = width - 1;
            }

            if (max > 3) {
                System.out.println("Type in the winning streak, min 3");
                System.out.println("max " + max + " (pretty damn hardcore!)");
                wStreak = checkInt(max, 0);
            // Defaults winning streak as 3 if length and width are 3.
            } else {
                wStreak = 3;
            }

            gameboard = new String[height][width];
            fillgb("[ ]");
            printgb();
            play();

            boolean wrongAnswer = false;
            System.out.println("Would you like to play again? (Y or N)");
            do {
                char replay = input.nextLine().toUpperCase().charAt(0);

                if (replay == 'Y') {
                    playAgain = true;
                    wrongAnswer = true;
                } else if (replay == 'N') {
                    System.out.println("Thanks for playing!");
                    System.exit(1);
                } else {
                    System.err.println("Please answer Y or N");
                    wrongAnswer = false;
                }
            } while (wrongAnswer == false);
        } while (playAgain == true);
    }

    /**
     * Checks player input and returns a valid Integer.
     *
     * <p>
     *
     * Checks that player input is an Integer value and within the
     * range that is specified. If not, the method asks the player to input
     * a new value and loops until valid value has been given. Different
     * situations cater to different valid values wanted.
     *
     * @param situation determines which situation is on
     * @param max indicates the max value
     * @return num valid Integer
     */
    public static int checkInt(int situation, int max) {
        // Indicates if the input is valid.
        boolean correct;
        // Valid value, which will be returned.
        int num = 0;

        do {
            correct = true;
            try {
                String test = input.nextLine();
                num = Integer.parseInt(test);
            } catch (NumberFormatException e) {
                System.err.println("Please type in a whole number");
                correct = false;
            }

            // Checks input for height and width.
            if (situation == 1) {
                if (num < 3 || num > max) {
                    System.err.println("Minimum number acceptable is 3 and " +
                            "maximum is " + max);
                    correct = false;
                }

            // Checks input for coordinates.
            } else if (situation == 2) {
                if (num < 1 || num >= max) {
                    System.err.println("Minimum number acceptable is 1 and " +
                            "maximum is " + (max - 1));
                    correct = false;
                }

            // Checks input for winning streak.
            } else {
                if (num < 3 || num > situation) {
                    System.err.println("Minimum number acceptable is 3 and " +
                            "maximum is " + situation);
                    correct = false;
                }
            }
        } while (correct == false);

        return num;
    }

    /**
     * Fills the gameboard.
     *
     * <p>
     *
     * Fills the String type array gameboard with empty 'boxes' aka
     * "[ ]" and with number grids that help the player to place
     * their mark on the gameboard.
     *
     * @param mark used to fill the array
     */
    public static void fillgb(String mark) {

        // Creates variable x.
        int x;
        // Grid number for the columns.
        int gridX = 0;
        // Grid number for the rows.
        int gridY = 0;

        for (int y = 0; y < height; y++) {
            x = 0;
            gameboard[y][x] = Integer.toString(gridY);

            for (x = 0; x < width; x++) {
                if (y == 0) {
                    gameboard[y][x] = Integer.toString(gridX);
                } else if (x != 0) {
                    gameboard[y][x] = mark;
                }

                gridX++;
            }

            gridY++;
        }
    }

    /**
     * Prints the gameboard.
     *
     * <p>
     *
     * Prints the grid and the contents of the gameboard. The grid is printed
     * accordingly to match the 'boxes' and the size of the array.
     */
    public static void printgb() {
        System.out.println("Winning streak is: " + wStreak);

        for (int y = 0; y < height; y++) {
            System.out.println("");

            for (int x = 0; x < width; x++) {
                if (y == 0) {
                    if (y == 0 & x == 0) {
                        System.out.print("  ");
                    } else if (y == 0 & x > 10) {
                        System.out.print(gameboard[y][x] + " ");
                    } else {
                        System.out.print(" " + gameboard[y][x] + " ");
                    }
                } else if (x == 0) {
                    if (y < 10) {
                        System.out.print(gameboard[y][x] + " ");
                    } else {
                        System.out.print(gameboard[y][x]);
                    }
                } else {
                    System.out.print(gameboard[y][x]);
                }
            }
        }

        System.out.println("");
    }

    /**
     * Coordinates the gameplay.
     *
     * <p>
     *
     * Player and computer each take turns to place their mark on the gameboard.
     * The game situation is checked constantly and the gameboard printed to
     * help keep track of the game. When the winner is found or if it's a tie
     * then a correct print is printed.
     */
    public static void play() {
        // Determines whether player input is valid.
        boolean placement;
        // Determines when player wins.
        boolean p1Wins;
        // Determines when computer wins.
        boolean comWins;

        System.out.println("");
        System.out.println("Player 1 you're playing as 'X'. Choose x and y " +
                "coordinates to place your mark and beat the computer AI.");
        System.out.println("Use the grid as guidance.");

        do {
            // Lets the player put their mark on the gameboard.
            do {
                System.out.println("Type in your y coordinate (row):");
                int pPlaceY = checkInt(2, height);
                System.out.println("Type in your x coordinate (column):");
                int pPlaceX = checkInt(2, width);
                placement = markPlacement(pPlaceY, pPlaceX, "[X]");
            } while (placement == false);

            p1Wins = whoWins("[X]");
            // Computer plays if player hasn't won and it isn't a tie yet.
            if (tie() == false & p1Wins == false) {
                computerPlay();
            }

            comWins = whoWins("[O]");
            printgb();
        } while (p1Wins == false & comWins == false & tie() == false);

        if (p1Wins == true) {
            System.out.println("Player one is the winner! Congratulations!");
        } else if (comWins == true) {
            System.out.println("The computer AI is the winner this time.");
        } else {
            System.out.println("It's a tie!");
        }
    }

    /**
     * Checks if marks clash.
     *
     * <p>
     *
     * Checks if player's or computer's mark is already in the coordinates
     * where player or computer wants to put their next mark. Returns a
     * boolean accordingly.
     *
     * @param coY mark's y coordinate
     * @param coX mark's x coordinate
     * @param mark player or computer's mark
     * @return tie true if all's good or false when marks clash
     */
    public static boolean markPlacement(int coY, int coX, String mark) {
        // Determines whether the mark placement is valid.
        boolean placement = false;

        if (gameboard[coY][coX].equals("[ ]")) {
            gameboard[coY][coX] = mark;
            placement = true;
        // Prints error message only for the player, not for the computer.
        } else if (mark.equals("[X]")) {
            System.err.println("There is already a mark there!");
        }

        return placement;
    }

    /**
     * Generates computer's mark's coordinates.
     *
     * <p>
     *
     * Randomly generates computer's mark's y and x coordinates and instantly
     * checks whether the coordinates are valid aka if it is possible for
     * computer to place its mark in the gameboard with the coordinates.
     * Loops until it is possible to place the mark on the gameboard.
     */
    public static void computerPlay() {
        // Determines whether it is possible to insert computer's mark
        // in the coordinates.
        boolean placement;
        // Mark's x coordinate.
        int comX;
        // Mark's y coordinate.
        int comY;
        do {
            comY = (int) (Math.random() * (height - 1)) + 1;
            comX = (int) (Math.random() * (width - 1)) + 1;
            placement = markPlacement(comY, comX, "[O]");
        } while (placement == false);

        System.out.println("");
        System.out.println("Computer placed 'O' in coordinates y:" + comY +
                ", x:" + comX);
    }

    /**
     * Checks if the game has come to a tie.
     *
     * <p>
     *
     * Checks if there are any empty 'boxes' left on the gameboard. If there
     * aren't any, it means the game has come to tie and the game is over.
     * Returns a boolean accordingly.
     *
     * @return tie false or true depending
     */
    public static boolean tie() {
        // Determines whether the game has come to a tie or not.
        boolean tie = true;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (gameboard[y][x].equals("[ ]")) {
                    tie = false;
                }
            }
        }

        return tie;
    }

    /**
     * Checks whether the winning streak has been reached.
     *
     * <p>
     *
     * Goes through the array until it finds the mark. Creates String marks
     * first by going straight up, then to the right, then down towards
     * South-East and lastly up towards North-East. After a step in each
     * direction it compares String marks to String win which holds the
     * winning streak. If it is a match, the game has been won.
     *
     * @param mark determines which mark to check
     * @return match boolean whether winning streak has been reached or not
     */
    public static boolean whoWins(String mark) {
        // The winning streak.
        String win = "";
        // String which is compared to the winning streak.
        String marks;
        // Determines whether the String win and String marks match.
        boolean match = false;

        // Creates the winning streak.
        for (int a = 0; a < wStreak; a++) {
            win += mark;
        }

        // Goes through the gameboard and when it encounters the mark it
        // checks whether winning streak has been reached.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (gameboard[y][x].equals(mark)) {
                    marks = "";
                    // Checks horizontally down.
                    for (int a = y; a < height; a++) {
                        marks += gameboard[a][x];

                        if (win.equals(marks)) {
                            match = true;
                        }
                    }

                    marks = "";
                    // Checks vertically right.
                    for (int a = x; a < width; a++) {
                        marks += gameboard[y][a];

                        if (win.equals(marks)) {
                            match = true;
                        }
                    }

                    marks = "";
                    // Checks down towards South-East.
                    int b = x;

                    for (int a = y; a < height & b < width; a++) {
                        marks += gameboard[a][b];

                        if (win.equals(marks)) {
                            match = true;
                        }

                        b++;
                    }

                    marks = "";
                    // Checks up towards North-East.
                    b = x;

                    for (int a = y; a > 0 & b < width; a--) {
                        marks += gameboard[a][b];

                        if (win.equals(marks)) {
                            match = true;
                        }

                        b++;
                    }
                }
            }
        }

        return match;
    }
}
