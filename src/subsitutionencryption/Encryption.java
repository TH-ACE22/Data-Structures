package subsitutionencryption;

// The Encryption class provides methods for encrypting messages using a Caesar cipher.
public class Encryption {

    // Encrypts the given message using the Caesar cipher with the specified key.
    // Non-alphabetic characters in the message remain unchanged.
    // Parameters:
    // - message: The message to be encrypted.
    // - key: The key to be used for encryption.
    // Returns:
    // The encrypted message.
    public static String encrypt(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();

        // Iterate over each character in the message
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            // Encrypt only alphabetic characters
            if (Character.isLetter(currentChar)) {
                char encryptedChar = encryptChar(currentChar, key);
                encryptedMessage.append(encryptedChar);
            } else {
                encryptedMessage.append(currentChar); // Non-alphabetic characters remain unchanged
            }
        }

        return encryptedMessage.toString();
    }

    // Encrypts a single character using the Caesar cipher with the specified key.
    // Parameters:
    // - c: The character to be encrypted.
    // - key: The key to be used for encryption.
    // Returns:
    // The encrypted character.
    public static char encryptChar(char c, int key) {
        char base = Character.isUpperCase(c) ? 'A' : 'a'; // Determine base character depending on case
        int encryptedCharCode = (c - base + key) % 26 + base; // Apply Caesar cipher shift
        return (char) encryptedCharCode;
    }
}
