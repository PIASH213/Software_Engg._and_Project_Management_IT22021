import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a line of text:");
        String input = scanner.nextLine();

        // Convert to lowercase and split into words
        String[] words = input.toLowerCase().split("\\W+");  // Splits on non-word characters

        // TreeMap to store word -> frequency (sorted by word)
        TreeMap<String, Integer> wordCount = new TreeMap<>();

        for (String word : words) {
            if (word.isEmpty()) continue;

            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Display the frequencies
        System.out.println("\nWord Frequencies (sorted):");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet())
            System.out.println(entry.getKey() + " -> " + entry.getValue());


        scanner.close();
    }
}
