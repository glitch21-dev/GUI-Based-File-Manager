import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.*;

public class FileMangerGUI extends JFrame {
    private JTextArea outputArea;
    private JTextField inputField;

    public FileMangerGUI() {
        // Frame setup
        setTitle("Basic File Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top input panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Enter file/directory name:"));
        inputField = new JTextField(20);
        topPanel.add(inputField);

        // Center output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Bottom button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        String[] buttons = {
                "Create File", "Write To File", "Read File", "Delete File",
                "List Files", "Copy File", "Move File", "Search File"
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.addActionListener(this::handleAction);
            buttonPanel.add(btn);
        }

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleAction(ActionEvent e) {
        String command = e.getActionCommand();
        String filename = inputField.getText().trim();

        try {
            switch (command) {
                case "Create File":
                    createFile(filename);
                    break;
                case "Write To File":
                    writeFile(filename);
                    break;
                case "Read File":
                    readFile(filename);
                    break;
                case "Delete File":
                    deleteFile(filename);
                    break;
                case "List Files":
                    listFiles(filename);
                    break;
                case "Copy File":
                    copyFile(filename);
                    break;
                case "Move File":
                    moveFile(filename);
                    break;
                case "Search File":
                    searchFile(filename);
                    break;
            }
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    // === FILE OPERATIONS ===

    private void createFile(String filename) throws IOException {
        File file = new File(filename);
        if (file.createNewFile()) {
            outputArea.setText("File created: " + file.getName());
        } else {
            outputArea.setText("This file already exists.");
        }
    }

    private void writeFile(String filename) throws IOException {
        String content = JOptionPane.showInputDialog(this, "Enter text to write:");
        if (content != null) {
            try (FileWriter writer = new FileWriter(filename, true)) {
                writer.write(content + "\n");
            }
            outputArea.setText("Successfully wrote to " + filename);
        }
    }

    private void readFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            outputArea.setText("File does not exist.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        outputArea.setText(sb.toString());
    }

    private void deleteFile(String filename) {
        File file = new File(filename);
        if (file.delete()) {
            outputArea.setText("Deleted: " + filename);
        } else {
            outputArea.setText("Failed to delete file.");
        }
    }

    private void listFiles(String path) {
        File folder = new File(path);
        if (folder.isDirectory()) {
            String[] files = folder.list();
            outputArea.setText("--- Files in Directory ---\n");
            for (String f : files) {
                outputArea.append(f + "\n");
            }
        } else {
            outputArea.setText("Invalid directory path.");
        }
    }

    private void copyFile(String source) throws IOException {
        String dest = JOptionPane.showInputDialog(this, "Enter destination filename:");
        if (dest != null) {
            Files.copy(Paths.get(source), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            outputArea.setText("File copied to " + dest);
        }
    }

    private void moveFile(String source) throws IOException {
        String dest = JOptionPane.showInputDialog(this, "Enter destination filename:");
        if (dest != null) {
            Files.move(Paths.get(source), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            outputArea.setText("File moved to " + dest);
        }
    }
    //Search method

    private void searchFile(String path) {
        String name = JOptionPane.showInputDialog(this, "Enter filename to search:");
        if (name != null) {
            File folder = new File(path);
            if (folder.isDirectory()) {
                boolean found = false;
                for (String f : folder.list()) {
                    if (f.equalsIgnoreCase(name)) {
                        found = true;
                        break;
                    }
                }
                outputArea.setText(found ? "File found!" : "File not found.");
            } else {
                outputArea.setText("Invalid directory path.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileMangerGUI::new);
    }
}
