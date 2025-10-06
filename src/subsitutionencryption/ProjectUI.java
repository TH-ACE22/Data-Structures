package subsitutionencryption;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import java.nio.file.*;
import java.io.IOException;

public class ProjectUI extends JFrame {
    JLabel label1, label2, l3;
    JTextField textField1,textField2;
    JButton b1, b2, b3,b4 ;
    JLayeredPane layeredPane;

    private String readFileToString(String fileName) {
        try {
            Path filePath = Paths.get(fileName);
            byte[] fileBytes = Files.readAllBytes(filePath);
            return new String(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    ProjectUI() {
        setTitle("Subsitution Encryption");

        // Create a layered pane to manage the depth of components
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 480);

        // Load the image and create an icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/poc.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 480, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        // Create a label to hold the image and set its size to match the frame
        JLabel l11 = new JLabel(i3);
        l11.setBounds(0, 0, 800, 480);

        // Add the image label to the layered pane at the lowest depth
        layeredPane.add(l11, Integer.valueOf(-1));

        // Initialize UI components
        label1 = new JLabel("Encrypt / Decrypt");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Osward", Font.BOLD, 34));
        label1.setBounds(200, 50, 450, 40);

        label2 = new JLabel("Message");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Raleway", Font.BOLD, 24));
        label2.setBounds(90, 150, 375, 30);

        textField1 = new JTextField("Enter your message here..");
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Enter your message here..")) {
                    textField1.setText("");
                    textField1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setBackground(Color.WHITE);
                    textField1.setText("Enter your message here..");
                }
            }
        });
        textField1.setBounds(200, 150, 230, 30);
        textField1.setFont(new Font("Arial", Font.BOLD, 16));

        l3 = new JLabel("Key:");
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(140, 220, 375, 30);

        textField2= new JTextField("Enter key integer...");
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Enter key integer...")) {
                    textField2.setText("");
                    textField2.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setBackground(Color.WHITE);
                    textField2.setText("Enter key integer...");
                }
            }
        });
        textField2.setFont(new Font("Arial", Font.BOLD, 16));
        textField2.setBounds(200, 220, 230, 30);
        // ...

        b1 = new JButton("Encrypt");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(200, 300, 100, 30);

        b2 = new JButton("Decrypt");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(350, 300, 100, 30);

        b3 = new JButton("Frequency Analysis");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setBounds(200, 350, 250, 30);

// Add UI components to the layered pane above the image
        layeredPane.add(label1, Integer.valueOf(1)); // Higher depth for labels
        layeredPane.add(label2, Integer.valueOf(1));
        layeredPane.add(textField1, Integer.valueOf(1));
        layeredPane.add(l3, Integer.valueOf(1));
        layeredPane.add(textField2, Integer.valueOf(1));
        layeredPane.add(b1, Integer.valueOf(2)); // Higher depth for buttons
        layeredPane.add(b2, Integer.valueOf(2));
        layeredPane.add(b3, Integer.valueOf(2));

     //Instances of the other classes
        Decryption   decryption = new Decryption();
        FrequencyAnalysis  frequencyAnalysis = new FrequencyAnalysis();
        FrequencyAnalysisDecryption frequencyAnalysisDecryption = new FrequencyAnalysisDecryption();


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField1.getText();
                int key;

                // Check if the message contains only letters
                if (!message.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "Please enter a message with letters only.");
                    return;
                }

                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a message.");
                    return;
                }

                try {
                    key = Integer.parseInt(textField2.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid key.");
                    return;
                }

                String encryptedMessage = Encryption.encrypt(message, key);
                JOptionPane.showMessageDialog(null, "Encrypted Message: " + encryptedMessage);

                textField1.setText("");
                textField2.setText("");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField1.getText();
                int key;

                // Check if the message contains only letters
                if (!message.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "Please enter a message with letters only.");
                    return;
                }

                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a message.");
                    return;
                }

                try {
                    key = Integer.parseInt(textField2.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer for the key.");
                    return;
                }

                String decryptedMessage = Decryption.decrypt(message, key);
                JOptionPane.showMessageDialog(null, "Decrypted Message: " + decryptedMessage);

                textField1.setText("");
                textField2.setText("");
            }
        });


        b3.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select a Text File for Frequency Analysis");
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                        FrequencyAnalysis frequencyAnalysis = new FrequencyAnalysis();
                        Map<Character, Integer> frequencyMap = frequencyAnalysis.performFrequencyAnalysis(fileName);
                        StringBuilder frequencyResults = new StringBuilder("<html>Frequency Analysis:<br>");
                        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                            frequencyResults.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
                        }
                        frequencyResults.append("</html>");

                        // Create a JTextArea and JScrollPane for the frequency results
                        JTextArea textArea = new JTextArea(frequencyResults.toString());
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        scrollPane.setPreferredSize(new Dimension(400, 300));

                        // Display the JOptionPane with the JScrollPane
                        JOptionPane.showMessageDialog(null, scrollPane, "Frequency Analysis", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

        b3 = new JButton("Frequency Analysis");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setBounds(200, 350, 250, 30);

// New button for file upload and decryption options
         b4 = new JButton("Upload and Decrypt");
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b4.setBounds(200, 400, 250, 30);

// Add the new button to the layered pane
        layeredPane.add(b4, Integer.valueOf(2));

// Action listener for the new button
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select a Text File for Decryption");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                    // Prompt the user for decryption choice
                    Object[] options = {"Normal Decryption", "Frequency Analysis"};
                    int choice = JOptionPane.showOptionDialog(null, "Choose the decryption method:",
                            "Decryption Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[0]);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Normal decryption
                        int key;
                        try {
                            key = Integer.parseInt(JOptionPane.showInputDialog("Enter key integer:"));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid integer for the key.");
                            return;
                        }
                        // Assume readFileToString is a method that reads the file content into a string
                        String fileContent = readFileToString(fileName);
                        String decryptedMessage = decryption.decrypt(fileContent, key);
                        JOptionPane.showMessageDialog(null, "Decrypted Message: " + decryptedMessage);
                    } else if (choice == JOptionPane.NO_OPTION) {
                        // Frequency analysis
                        FrequencyAnalysis frequencyAnalysis = new FrequencyAnalysis();
                        Map<Character, Integer> frequencyMap = frequencyAnalysis.performFrequencyAnalysis(fileName);

                        // Build an HTML string with the frequency map entries displayed vertically
                        StringBuilder htmlBuilder = new StringBuilder("<html><body>");
                        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                            htmlBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("<br>");
                        }
                        htmlBuilder.append("</body></html>");

                        // Create a JEditorPane to display the HTML-formatted string
                        JEditorPane editorPane = new JEditorPane("text/html", htmlBuilder.toString());
                        editorPane.setEditable(false); // Make it non-editable

                        // Create a JScrollPane to allow scrolling
                        JScrollPane scrollPane = new JScrollPane(editorPane);
                        scrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size

                        // Display the JOptionPane with the JScrollPane
                        JOptionPane.showMessageDialog(null, scrollPane, "Frequency Analysis", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });






        add(layeredPane);

        // Set the frame properties
        getContentPane().setBackground(Color.WHITE);
        setSize(800, 480);
        setLocationRelativeTo(null); // center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProjectUI();
            }
        });
    }
}
