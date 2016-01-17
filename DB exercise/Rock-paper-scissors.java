import java.util.Scanner;
import java.util.InputMismatchException;

public class Rock-paper-scissors {

    static int playerWins = 0;
    static int p1Wins = 0;
    static int p2Wins = 0;
    static int p1Lost = 0;
    static int p2Lost = 0;
    static int rounds = 10;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int gameMode = 0;
        System.out.println("Playing alone = 1 or playing against a friend = 2");
        boolean loop = false;
        do {
            try {
                gameMode = input.nextInt();
                loop = false;
            } catch (NumberFormatException e) {
                loop = true;
                System.err.println("Please type in either 1 or 2");
            }
            if (gameMode == 1) {
                singleGame();
            } else if (gameMode == 2) {
                multiplayerGame();
            } else {
                loop = true;
                System.out.println("Please type in either 1 or 2");
            } 
        } while (loop = true); 
    }

    public static void winSequence(int playerX, int computerY) {

        String[][] array = { { "It's a tie!", "You win!", "You lost" },
                { "You Lost", "It's a tie!", "You win!" },
                { "You Win!", "You Lost", "It's a tie!" } };

        if (array[playerX][computerY].equals("You win!")) {
            playerWins++;
        }
        System.out.println(array[playerX][computerY]);
    }

    public static void winSequence2(int p1, int p2, String name1, String name2) {

        int[][] array = { { 0, 2, 1 },
                { 1, 0, 2 },
                { 2, 1, 0 } };

        if (array[p1][p2] == 1) {
            System.out.println(name1 + " wins!");
            p1Wins++;
            p2Lost++;
        } else if (array[p1][p2] == 2) {
            System.out.println(name2 + " wins!");
            p2Wins++;
            p1Lost++;
        } else if (array[p1][p2] == 0) {
            System.out.println("It's a tie!");
        }  
    }

    public static int checkInput(String choice) {
        boolean rightInput = false;
        int choiceHere = 0;
            do {
                if (rightInput == true) {
                    choice = input.nextLine();
                } 
                try {
                    choiceHere = Integer.parseInt(choice);
                    rightInput = false;
                    if (choiceHere < 0 || choiceHere > 2) {
                        rightInput = true;
                        System.out.println("You can only choose rock = 0, paper = 1 or scissors = 2");
                    }
                } catch (NumberFormatException e) {
                    rightInput = true;
                    System.err.println("You can only choose rock = 0, paper = 1 or scissors = 2");
                    System.err.println("So type in 0, 1 or 2");
                } catch (InputMismatchException e) {
                    System.err.println("Please type in 0, 1 or 2");
                }
            } while (rightInput == true);
            return choiceHere;
    }

    public static void singleGame() {
        System.out.println("Type in your player name: ");
        String error3 = input.nextLine();
        String name = input.nextLine();

        do {
            System.out.println("Rounds left: " + rounds);
            System.out.println("Choose rock = 0, paper = 1 or scissors = 2");
            String playerC = input.nextLine();
            int playerChoice = checkInput(playerC);

            int computerChoice = (int) (Math.random() * 3);
            System.out.println("Computer chose " + computerChoice);
            winSequence(playerChoice, computerChoice);
            rounds--;
            System.out.println("");
        } while (rounds > 0);
        System.out.println(name + " won " + playerWins + " time(s).");
        DatabaseConnection connect = new DatabaseConnection();
        connect.updatePlayerInfo(name, playerWins);

        System.out.println("Would you like to check the high score table: Y or N?");
        String error4 = input.nextLine();
        boolean checkInput = false;
        do {
            String seeTable = input.nextLine();
            if (seeTable.equals("Y")) {
                connect.testSelect();
                System.exit(1);
            } else if (seeTable.equals("N")) {
                System.out.println("See you another time!");
                System.exit(1);
            } else {
                System.out.println("Please answer either Y or N");
                checkInput = true;
            }
        } while (checkInput = true);
    }
    
    public static void multiplayerGame() {
        System.out.println("Player1 type in your player name: ");
        String error = input.nextLine();
        String name1 = input.nextLine();
        System.out.println("Player2 type in your player name: ");
        String name2 = input.nextLine();

        do {
            System.out.println("Rounds left: " + rounds);
            System.out.println(name1 + " choose rock = 0, paper = 1 or scissors = 2");
            String p1Ch = input.nextLine();
            int p1Choice = checkInput(p1Ch);
            System.out.println(name2 + " choose rock = 0, paper = 1 or scissors = 2");
            String p2Ch = input.nextLine();
            int p2Choice = checkInput(p2Ch);
            winSequence2(p1Choice, p2Choice, name1, name2);
            rounds--;
            System.out.println("");
        } while (rounds > 0);

        System.out.println(name1 + " won " + p1Wins + " time(s).");
        System.out.println(name2 + " won " + p2Wins + " time(s).");
        if (p1Wins > p2Wins) {
            System.out.println("Overall winner is " + name1);
        } else if (p2Wins > p1Wins) {
            System.out.println("Overall winner is " + name2);
        } else {
            System.out.println("It's a tie!");
        }
        
        DatabaseConnection connect = new DatabaseConnection();
        connect.updateMultiInfo(name1, p1Wins, p1Lost);
        connect.updateMultiInfo(name2, p2Wins, p2Lost);

        System.out.println("Would you like to check the multiplayer high score table: Y or N?");
        String error2 = input.nextLine();
        boolean checkInput = false;
        do {
            String seeTable = input.nextLine();
            if (seeTable.equals("Y")) {
                connect.selectMulti();
                System.exit(1);
            } else if (seeTable.equals("N")) {
                System.out.println("See yas another time!");
                System.exit(1);
            } else {
                checkInput = true;
                System.out.println("Please answer either Y or N");
            }
        } while (checkInput = true);
    }
}
