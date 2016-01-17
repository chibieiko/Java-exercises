/**
 * FruitGame compares three characters.
 *
 * <p>
 *
 * The class FruitGame contains two methods. Result
 * determines the outcome of the game and fruit
 * decides which fruits the player gets.
 * 
 * @author Erika Sankari
 * @version 2015.1005
 * @since 1.8 
 */
class FruitGame {

    // Determines how long the game goes on.
    static int money = 25;
    // Array of 'fruits'.
    static char[] fruits = { '@', '!', '#'};
    // The outcome of the game.
    static char[] random = new char[fruits.length];
    // With money determines how long the game goes on.
    static int rounds = 0;                      
    // Randomly generated number.
    static int randNum;

    /**
     * Shows the result of the game.
     * 
     * <p> 
     *
     * Loops the game until there is no more money
     * left. Prints the result continuously.
     *
     * @param args command line parametres, not used.
     */
    public static void main(String[] args) {

        while (rounds < money) {
            result();
        }
    }

    /**
     * Determines the outcome.
     * 
     * <p> 
     *
     * Method compares the characters in array random.
     * If they are same in all indexes, then the player
     * has won and if not, then the player has lost.
     * The result is printed out.
     */
    public static void result() {

        fruit();

        if (random[0] == random[1] && random[0] == random[2]) {
            money += 1;
            System.out.println("You won!");                 
        } else {
            money -= 1;
            System.out.println("You lost!");                    
        }
    }

    /**
     * Creates an array of 'fruits'.
     * 
     * <p> 
     *
     * Creates a random number. Inserts characters to array
     * random from a random index in array fruits.
     *
     * 
     * @return random returns array random.
     */
    public static char[] fruit() {
        for (int a = 0; a < fruits.length; a++) {
            randNum = (int) (Math.random() * fruits.length); 
            random[a] = fruits[randNum];     
            System.out.print(random[a]);               
        }

        System.out.println("");
        return random;
    }
}
