package subsitutionencryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

// The FrequencyAnalysis class provides methods for analyzing the frequency of characters in a text.
public class FrequencyAnalysis {

    // Analyzes the frequency of characters in the given content and returns a map containing
    // each character and its frequency.
    // Parameters:
    // - content: The text content to be analyzed.
    // Returns:
    // A map containing each character and its frequency.
    public Map<Character, Integer> analyse(String content) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Iterate over each character in the content
        for (char ch : content.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1); // Update frequency count for each character
        }

        return frequencyMap;
    }

    // Reads the content of the file specified by fileName, performs frequency analysis,
    // and returns a map containing each character and its frequency.
    // Parameters:
    // - fileName: The name of the file to be analyzed.
    // Returns:
    // A map containing each character and its frequency.
    public Map<Character, Integer> performFrequencyAnalysis(String fileName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName))); // Read file content
            return analyse(content); // Perform frequency analysis on the content
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage()); // Handle IO exception
            return new HashMap<>(); // Return an empty map in case of error
        }
    }
}
