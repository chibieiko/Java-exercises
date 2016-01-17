import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.File;

class SearchDifferenceCalc {
    public static void main(String [] args) throws IOException {
        Scanner input = new Scanner(System.in);

        String numbers = args[0];
        List<String> allLines = Files.readAllLines(new File(numbers).toPath(), Charset.defaultCharset());
        int [] array = new int [allLines.size()];
        int low = 0;
        int high = array.length - 1;
        int mid;
        long difference;
        int a = 0;

        // Load numbers from txt file to array.
        for(int i=0; i<allLines.size(); i++) {
            array[i] = Integer.parseInt(allLines.get(i));
        } 

        // Binary search.
        long startTime0 = System.nanoTime();
        while(low <= high) {
            mid = (low + high) / 2;
            if(array[mid] < Integer.parseInt(args[1])) {
                low = mid + 1;
            } else if (array[mid] > Integer.parseInt(args[1])) {
                high = mid - 1;
            } else {
                System.out.println("Binary Jackpot!:D"); 
                low = array.length;
            }
        }
        // Count the time used for the binary search.
        long estimatedTime0 = System.nanoTime() - startTime0;
        System.out.println("Binary search time in nanoseconds: " + estimatedTime0);

        // Sequence scan.
        long startTime1 = System.nanoTime();
        for(a=0; a<array.length; a++) {
            if(array[a] == Integer.parseInt(args[1])) {
                System.out.println("Sequence Jackpot!:D");
                a = array.length;
            }
        }
        long estimatedTime1 = System.nanoTime() - startTime1;
        System.out.println("Sequence search time in nanoseconds: " + estimatedTime1);

        // Count the difference in speed.
        difference = estimatedTime1 - estimatedTime0;
        System.out.println("Difference in times: " + difference);
    }
}