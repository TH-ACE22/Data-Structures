package subsitutionencryption;

import java.util.*;
import java.util.stream.Collectors;

// The FrequencyAnalysisDecryption class provides methods for analyzing the frequency of characters in a text and decrypting based on frequency analysis.
public class FrequencyAnalysisDecryption {

    // Analyzes the frequency of characters in the given content and returns a map containing
    // each character and its frequency.
    public Map<Character, Integer> analyse(String content) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Iterate over each character in the content
        for (char ch : content.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1); // Update frequency count for each character
        }

        return frequencyMap;
    }

    // A method to decrypt a message based on frequency analysis
    public String decryptByFrequencyAnalysis(String encryptedMessage, Map<Character, Double> languageFrequency) {
        Map<Character, Integer> encryptedFrequency = analyse(encryptedMessage);
        Map<Character, Character> substitutionMap = new LinkedHashMap<>();

        // Sort the frequency maps
        Map<Character, Double> sortedLanguageFrequency = languageFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Map<Character, Integer> sortedEncryptedFrequency = encryptedFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Create the initial substitution map
        Iterator<Map.Entry<Character, Double>> languageIterator = sortedLanguageFrequency.entrySet().iterator();
        for (Map.Entry<Character, Integer> entry : sortedEncryptedFrequency.entrySet()) {
            if (languageIterator.hasNext()) {
                substitutionMap.put(entry.getKey(), languageIterator.next().getKey());
            }
        }

        // Apply the substitution map to the encrypted message
        StringBuilder decryptedMessage = new StringBuilder();
        for (char ch : encryptedMessage.toCharArray()) {
            decryptedMessage.append(substitutionMap.getOrDefault(ch, ch));
        }

        return decryptedMessage.toString();
    }
}
