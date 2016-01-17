import java.util.Scanner;
/**
 * MemoCards is a memory card game.
 *
 * <p>
 *
 * The class MemoCards contains four methods. There are 
 * methods for printing an array, comparing array values,
 * restoring values and checking correct user input.
 * 
 * @author Erika Sankari
 * @version 2015.1022
 * @since 1.8 
 */
class MemoCards {

    // Scans user input.
    static Scanner input = new Scanner(System.in);
    // First user guess for x axel.
    static int guess1X;
    // First user guess for y axel.
    static int guess1Y;
    // Second user guess for x axel.
    static int guess2X;
    // Second user guess for y axel.
    static int guess2Y;
    // Boolean to help with printing.
    static boolean value1 = false;
    // Boolean to help with printing.
    static boolean value2 = false;
    // Boolean that loops the game until it's finished.
    static boolean loop = false;

    /**
     * Loops the game.
     * 
     * <p> 
     *
     * Do while loop to make sure the 
     * game continues until all letters have been matched.
     * Different booleans to help with printing. Array
     * blank created for reference.
     *
     * @param args command line parameters, not used.
     */
    public static void main(String [] args) {

        char[][] gametable = {{'e', 't', 'e', 'g'},
            {'g', 'r', 'p', 'k'},
            {'t', 'k', 's', 'h'},
            {'h', 'r', 'p', 's'}};
        char[][] blank = {{'-', '-', '-', '-'},
            {'-', '-', '-', '-'},
            {'-', '-', '-', '-'},
            {'-', '-', '-', '-'}};
        
        print(gametable, blank);
        do {
            value1 = false;
            value2 = false;
            System.out.println("Choose a card, pick a number between 0 - 3");
            guess1Y = check(input.nextLine());
            System.out.println("And another number between 0 - 3");
            guess1X = check(input.nextLine());
            value1 = true;
            print(gametable, blank);
            value1 = false;
            System.out.println("Choose another card, pick a number between 0 - 3");
            guess2Y = check(input.nextLine()); 
            System.out.println("And another number between 0 - 3");
            guess2X = check(input.nextLine());  
            value2 = true;   
            value1 = true;
            compare(gametable, blank);
            print(gametable, blank);

        } while (loop == false);

    }
    /**
     * Compares whether the two arrays are identical.
     *     
     * <p>
     *
     * When the arrays are identical, the methods returns boolean 
     * loop, which ends the game. 
     * 
     * @param gametable array with the card values.
     * @param blank a comparison array.
     * @return loop returns a boolean accordingly.
     */
    public static boolean compare(char[][] gametable, char[][] blank) {

        boolean sure = true;

        for (int x = 0; x < gametable.length; x++) {
            for (int y = 0; y < gametable.length; y++) {
                if (gametable[y][x] == blank[y][x] && sure == true) {
                    loop = true;
                } else {
                    loop = false;
                    sure = false;
                }
            }
        }
        return loop;

    }

    /**
     * Prints the game progress.
     *     
     * <p>
     *
     * Inserts values to array blank according to game rules.
     * Prints the game progress continuously. 
     * 
     * @param gametable array with the card values.
     * @param blank a comparison array.
     */
    public static void print(char[][] gametable, char[][] blank) {

        for (int y = 0; y < gametable.length; y++) {
            for (int x = 0; x < gametable.length; x++) {
                if (y == guess1Y && x == guess1X && value1 == true) {
                    blank[y][x] = gametable[y][x];
                    if (value1 == true && value2 == true) {
                        restore(gametable, blank); 
                    }
                } else if (y == guess2Y && x == guess2X && value2 == true) {
                    blank[y][x] = gametable[guess2Y][guess2X];
                    if (value1 == true && value2 == true) {
                        restore(gametable, blank);
                    }
                }

                System.out.print(blank[y][x]);
            }     
            
            System.out.println("");
        }
    }     

    /**
     * Compares whether the two 'cards' have the same value.
     *     
     * <p>
     *
     * If the 'cards' have the same value, the value is inserted to 
     * array blank in correct index. This enables the correct printing 
     * of the game progress and ultimately the end of the game. 
     * 
     * @param gametable array with the card values.
     * @param blank a comparison array.
     */
    public static void restore(char[][] gametable, char[][] blank) {
       
        if (gametable[guess1Y][guess1X] != gametable[guess2Y][guess2X]) {
            blank[guess1Y][guess1X] = '-';
            blank[guess2Y][guess2X] = '-';  
        } else if (gametable[guess1Y][guess1X] == gametable[guess2Y][guess2X]) {
            blank[guess1Y][guess1X] = gametable[guess2Y][guess2X];             
        } 
    }   
    
    /**
     * Checks correct user input.
     *     
     * <p>
     *
     * Makes sure the user input is an integer value. Also makes sure
     * the value is between 0 to 3. Prints an error message if input
     * is incorrect and asks a new input.
     * 
     * @param player user's original input in String format.
     * @return place user's input in integer format.
     */
    public static int check(String player) {

        boolean yea = true;
        int place = 0;
        do {
            yea = true;
            try {
            place = Integer.parseInt(player);
            } catch (NumberFormatException e) {
                System.err.println("Please, a number between 0 - 3");
                player = input.nextLine();
                yea = false;
            }

            if (place < 0 || place > 3) {
                System.out.println("Please, a number between 0 - 3");
                player = input.nextLine();
                yea = false;
            }

        } while (yea == false);

        return place;
    }
}