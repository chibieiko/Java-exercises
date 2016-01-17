import java.util.Scanner;

class bombSweeper {
    static Scanner input = new Scanner(System.in);  

    public static void main(String [] args){

        System.out.println("Please type in a size for the game:");
        // Check and correct input with method getInt().
        int gameSize = getInt();
        
        // Print the gameboard.
        String [] gameboard = new String[gameSize];
        for(int a=0; a<gameSize; a++) {
            gameboard[a] = "[ ]";
            System.out.print( gameboard[a] );
        }
        System.out.println(" ");

        // Place soldier on the gameboard.
        System.out.println("Choose your place soldier!");
        // Check and correct soldier input with getInt() and rightValue().
        int soldier = getInt();
        soldier = rightValue(soldier, gameSize - 1);
        gameboard[soldier] = "[S]";
        System.out.println("You stepped in:");
        for(int a=0; a<gameSize; a++) {
            System.out.print( gameboard[a] );
        }
        System.out.println(" ");

        // Place bombs on the gameboard. About one third of the gameboard are bombs.
        double bombsPerGame = gameSize * 0.35;
        bombsPerGame = (int) bombsPerGame;
        int bomb = 0;
        int bomb1 = gameSize;
        for(int a=0; a<bombsPerGame; a++) {
            // Prevent bomb having the same value twice.
            do {
                bomb = getRandom(0, gameSize - 1);
            } while(bomb == bomb1);
            if(gameboard[bomb] == "[S]") {
                gameboard[bomb] = "[BS]";
            } else {
                gameboard[bomb] = "[B]";            
            }
            bomb1 = bomb;
        }
        
        // Show the outcome of the game.
        System.out.println("Bomb(s) exploded in:");
        for(int a=0; a<gameSize; a++) {
            System.out.print( gameboard[a] );
        }
        System.out.println(" ");

        // Determine and print the outcome.
        for(int a=0; a<gameSize; a++) {
            if(gameboard[a] == "[BS]") {
                System.out.println("R.I.P. Soldier...");
                System.exit(1);
            }
        }
        System.out.println("Congrats Soldier! You're still alive:D");

        
    }
    // Method continues to loop until user gives a number in Integer-format.
    public static int getInt() {
        boolean loop;
        String size;
        int gameS = 0;
        do {
            loop = true;
            size = input.nextLine();
            try {
                gameS = Integer.parseInt(size);
             } catch (NumberFormatException e) {
                System.err.println("Please type in a whole number:");
                loop = false;
            }
        } while(loop == false);
        return gameS;
    }
    // Method continues to loop until user gives an Integer value in the right range.
    public static int rightValue(int p1, int max) {
        do {
            if(p1 >= 0 && p1 <= max) {
                return p1;
            } else {
                System.out.println("Please choose from " + 0 + " to " + max);
                p1 = getInt();
            }
        } while(p1 < 0 || p1 > max);
        return p1;
    }
    // Method provides a random Integer number in the right range.
    public static int getRandom(int min, int max){
        int randomNum = min + (int)(Math.random() * ((max - min) + 1));
        return randomNum;
    }
}