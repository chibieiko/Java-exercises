/**
 * Lottery creates a random array and compares it to Player's one.
 *
 * <p>
 *
 * The class Lottery contains three methods. checkSameNum
 * makes sure there are no same numbers in lottery row.
 * Method print prints out arrays. Compare compares the 
 * players row to the lottery row.
 * 
 * @author Erika Sankari
 * @version 2015.1007
 * @since 1.8 
 */
class Lottery {
    
    static int win = 0;
    
    /**
     * Shows the result of the game.
     * 
     * <p> 
     *
     * Introduces the variables. While loop to make sure the 
     * game continues until lottery has been won. LottoRow is 
     * created and money spent and years passed calculated.
     *
     * @param args command line parameters, not used.
     */
    public static void main(String[] args) {
        
        int money = 0;
        int weeks = 0;
        int years = 0;
        int[] playerRow = {7, 8, 12, 17, 24, 27, 30};
        int[] lottoRow = new int[playerRow.length];
        
        boolean millionaire = false;
        
        // Loop the game until jackpot.
        while (millionaire == false) {
            
            print(playerRow);
            
            // Create and assign values to lottery row.
            do {
                for (int a = 0; a < playerRow.length; a++) {
                    lottoRow[a] = (int) (Math.random() * 39 + 1);
                }
            } while (checkSameNum(lottoRow) == false);
            
            print(lottoRow);
            // Compare arrays.
            compare(playerRow, lottoRow);
            // Count the money spent and years gone.
            money += 1;
            System.out.println("Money spent: " + money + " euros");
            weeks += 1;

            if (weeks == 52) {
                years += 1;
                weeks = 0;
            }

            System.out.println("Years gone: " + years);
            System.out.println("");
            // Determine the outcome of the game.
            if (win == playerRow.length) {
                millionaire = true;
                System.out.println("Congrats you won a ton of air money! :D");
            }

            win = 0;
        }
    }

    /**
     * Checks that Math.random() doesn't generate two same numbers.
     *     
     * <p>
     *
     * For the sake of the game it is important that there are no same
     * numbers in the randomly generated lottery row. Method compares 
     * number by number that there are so similarities and returns a 
     * boolean accordingly.
     * 
     * @param lottoRow randomly generated lottorow from main method.
     * @return returns a boolean accordingly.
     */
    public static boolean checkSameNum(int[] lottoRow) {
        
        boolean sameNum = true;
        int compare;

        for (int a = 0; a < lottoRow.length - 1; a++) {
            compare = lottoRow[a];

            for (int b = a + 1; b < lottoRow.length; b++) {
                if (compare == lottoRow[b]) {
                    sameNum = false;
                    return sameNum;
                } else {
                    sameNum = true;
                }
            }
        }

        return sameNum;
    }

    /**
     * Prints an array.
     *     
     * <p>
     *
     * Simplifies code, because there are few times when en array
     * needs to be printed. 
     * 
     * @param array array to be printed.
     */
    public static void print(int[] array) {
        for (int a = 0; a < array.length; a++) {
            System.out.print(array[a] + " | ");
        }

        System.out.println("");
    }

    /**
     * Compares two arrays.
     *     
     * <p>
     *
     * Compares the content of integer arrays. Same values don't have
     * to be in corresponding indexes, the method works either way.
     * 
     * @param playerRow players lottery row.
     * @param lottoRow computer generated lottery row.
     * @return win returns the number of same values in arrays.
     */
    public static int compare(int[] playerRow, int[] lottoRow) {
        
        for (int a = 0; a < playerRow.length; a++) {
            for (int b = 0; b < lottoRow.length; b++) {
                if (playerRow[a] == lottoRow[b]) {
                    win++;
                }
            }
        }
        
        return win;
    }
}
