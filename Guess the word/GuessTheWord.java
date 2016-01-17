import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.File;

public class GuessTheWord {
    public static void main(String [] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String textFileName = "wordFile.txt";
        List<String> allLines = Files.readAllLines(new File(textFileName).toPath(), Charset.defaultCharset());
        // Randomly assign one of the words from wordFile.txt to final String word.
        int index = (int) (Math.random() * (allLines.size()));
        final String word = allLines.get(index);
        int length = word.length();
        char guess = 'o';
        char word1 = 'o';
        int b = 0;
        char [] result = new char [length];
        String start = new String();
        String finalResult = new String();

        do {
            System.out.println("Guess the word, input any letter");
            // Only do this in the beginning of the program.
            if(b == 0) {
                // Determine a starting point for result, then convert and print it as a String.
                for(int a = 0; a < length; a++) {
                    result [a] = '-'; 
                } 
                finalResult = new String(result);
            }            
            System.out.println(finalResult);
            guess = input.nextLine().charAt(0);
                /*
                Compare the char guess character to all the characters
                in String word. If equal, add it to char[] result in the 
                right index point.
                */
            for(int c = 0; c < length; c++) {
                word1 = word.charAt(c);
                if(word1 == guess) {
                    result [c] = guess;
                } 
            }   
            // Convert result to String finalResult.    
            finalResult = new String(result);
            // Prevent the program starting do-while loop from first if.
            b = b + 1;
        }
        while(!(word.equals(finalResult)));
        // do-while loop ends only when the right word is guessed.
        System.out.println("You guessed the word! : " + finalResult);
    }
}