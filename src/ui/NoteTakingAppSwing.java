package ui;

import models.Note;
import models.User;
import services.NoteService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NoteTakingAppSwing extends JFrame {

    private JTextField titleField;
    private JTextField categoryField;
    private JTextArea thoughtArea;
    private JButton addButton;
    private JPanel notesPanel;

    private NoteService noteService;

    public NoteTakingAppSwing() {
        // Set up the main frame
        setTitle("Note Taking App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize NoteService
        User user = new User("DefaultUser");
        noteService = new NoteService(user);

        // Colors and Fonts
        Color headerColor = new Color(33, 150, 243); // Light Blue
        Color inputPanelColor = new Color(240, 240, 240); // Light Gray
        Color buttonColor = new Color(76, 175, 80); // Green
        Font headerFont = new Font("Arial", Font.ITALIC, 23);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font noteFont = new Font("Arial", Font.PLAIN, 14);

        // Header Section
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(headerColor);
        JLabel headerLabel = new JLabel("\" Every note you write is a whisper to your future self \"");
        headerLabel.setFont(headerFont);
        headerLabel.setForeground(Color.WHITE);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 35));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Input Section
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(inputPanelColor);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(titleLabel, gbc);

        titleField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(categoryLabel, gbc);

        categoryField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(categoryField, gbc);

        JLabel thoughtLabel = new JLabel("Your Thoughts:");
        thoughtLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(thoughtLabel, gbc);

        thoughtArea = new JTextArea(5, 20);
        thoughtArea.setLineWrap(true);
        thoughtArea.setWrapStyleWord(true);
        JScrollPane thoughtScrollPane = new JScrollPane(thoughtArea);
        gbc.gridx = 1;
        inputPanel.add(thoughtScrollPane, gbc);

        addButton = new JButton("Add Note");
        addButton.setFont(buttonFont);
        addButton.setBackground(buttonColor);
        addButton.setForeground(Color.black);
        addButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(addButton, gbc);

        add(inputPanel, BorderLayout.WEST);

        // Notes List Section
        JPanel notesListPanel = new JPanel();
        notesListPanel.setLayout(new BorderLayout());
        notesListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel notesLabel = new JLabel("Saved Notes");
        notesLabel.setFont(labelFont);
        notesLabel.setForeground(headerColor);
        notesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notesListPanel.add(notesLabel, BorderLayout.NORTH);

        // Panel to hold individual notes
        notesPanel = new JPanel();
        notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.Y_AXIS));
        notesPanel.setBackground(Color.WHITE);
        JScrollPane notesScrollPane = new JScrollPane(notesPanel);
        notesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        notesScrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smoother scrolling
        notesListPanel.add(notesScrollPane, BorderLayout.CENTER);

        add(notesListPanel, BorderLayout.CENTER);

        // Add Actions
        addButton.addActionListener(e -> addNote());

        // Populate list with existing notes
        refreshNotes();
    }

    /**
     * Adds a new note using the input fields and refreshes the list
     */
    private void addNote() {
        String title = titleField.getText();
        String category = categoryField.getText();
        String thought = thoughtArea.getText();

        if (!title.isEmpty() && !thought.isEmpty()) {
            Note note = new Note(title, category, thought);
            noteService.addNote(note.getTitle(), note.getThought(), note.getCategory()); // Store the note
            refreshNotes(); // Refresh the display
            clearFields(); // Clear input fields
        } else {
            JOptionPane.showMessageDialog(this, "Both Title and Your Thoughts are required.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Refreshes the notes list from the NoteService and displays each note in a JTextArea for text wrapping
     */
    private void refreshNotes() {
        notesPanel.removeAll(); // Clear existing notes
        List<Note> notes = noteService.getAllNotes(); // Fetch all notes
        for (Note note : notes) {
            // Create a JTextArea for each note
            JTextArea noteTextArea = new JTextArea();
            noteTextArea.setText("Title: " + note.getTitle() + "\n\n"
                    + "Category: " + note.getCategory() + "\n\n"
                    + "Thought: " + note.getThought());
            noteTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
            noteTextArea.setLineWrap(true);
            noteTextArea.setWrapStyleWord(true);
            noteTextArea.setEditable(false);
            noteTextArea.setBackground(new Color(245, 245, 245)); // Light gray background
            noteTextArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)), // Border color
                    BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Inner padding

            notesPanel.add(noteTextArea);
            notesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between notes
        }
        notesPanel.revalidate();
        notesPanel.repaint();
    }

    /**
     * Clears all input fields
     */
    private void clearFields() {
        titleField.setText("");
        categoryField.setText("");
        thoughtArea.setText("");
    }

    /**
     * Main method to run the application
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NoteTakingAppSwing app = new NoteTakingAppSwing();
            app.setVisible(true);
        });
    }
}