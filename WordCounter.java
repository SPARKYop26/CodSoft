import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCounter extends JFrame {

    private JTextArea textArea;
    private JButton countButton;

    public WordCounter() {
        setTitle("Word Count Tool");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performWordCount();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(countButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void performWordCount() {
        String inputText = textArea.getText();
        String[] words = splitIntoWords(inputText);
        int totalWords = words.length;
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        Map<String, Integer> wordFrequency = countWordFrequency(words);

        String result = "Word Count: " + totalWords + "\n" +
                        "Unique Words: " + uniqueWords.size() + "\n\n" +
                        "Word Frequency:\n";
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            result += entry.getKey() + ": " + entry.getValue() + "\n";
        }

        JOptionPane.showMessageDialog(this, result);
    }

    private String[] splitIntoWords(String text) {
        return text.split("\\s+|\\p{Punct}");
    }

    private Map<String, Integer> countWordFrequency(String[] words) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return frequencyMap;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WordCounter gui = new WordCounter();
                gui.setVisible(true);
            }
        });
    }
}
