package subsitutionencryption;

// The Decryption class provides methods for decrypting messages encrypted using a Caesar cipher.
public class Decryption {

    // Decrypts the given cipher text using the Caesar cipher with the specified key.
    // Non-alphabetic characters in the cipher text remain unchanged.
    // Parameters:
    // - cipherText: The cipher text to be decrypted.
    // - key: The key to be used for decryption.
    // Returns:
    // The decrypted message.
    public static String decrypt(String cipherText, int key) {
        StringBuilder decryptedMessage = new StringBuilder();

        // Iterate over each character in the cipher text
        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);

            // Decrypt only alphabetic characters
            if (Character.isLetter(currentChar)) {
                char decryptedChar = decryptChar(currentChar, key);
                decryptedMessage.append(decryptedChar);
            } else {
                // Non-alphabetic characters remain unchanged
                decryptedMessage.append(currentChar);
            }
        }

        return decryptedMessage.toString();
    }

    // Decrypts a single character using the Caesar cipher with the specified key.
    // Parameters:
    // - c: The character to be decrypted.
    // - key: The key to be used for decryption.
    // Returns:
    // The decrypted character.
    private static char decryptChar(char c, int key) {
        int base = Character.isUpperCase(c) ? 'A' : 'a'; // Determine base character depending on case
        int decryptedCharCode = (c - base - key + 26) % 26 + base; // Apply Caesar cipher shift
        return (char) decryptedCharCode;
    }
}
