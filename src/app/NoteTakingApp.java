package app;

import models.User;
import services.NoteService;
import java.util.Scanner;

public class NoteTakingApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User user = new User("DefaultUser");
        NoteService noteService = new NoteService(user);

        System.out.println("Welcome to the Note-Taking App!");

        while (true) {
            showMenu();
            int choice = getUserChoice();
            handleUserInput(choice, noteService);
        }
    }

    // Show menu options
    private static void showMenu() {
        System.out.println("\n1. Add a Note");
        System.out.println("2. View All Notes");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    // Get user input
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }

    // Handle user actions
    private static void handleUserInput(int choice, NoteService noteService) {
        switch (choice) {
            case 1:
                addNote(noteService);
                break;
            case 2:
                viewNotes(noteService);
                break;
            case 3:
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    // Add a new note
    private static void addNote(NoteService noteService) {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Thought: ");
        String thought = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        noteService.addNote(title, thought, category);
        System.out.println("Note added successfully!");
    }

    // View all notes
    private static void viewNotes(NoteService noteService) {
        System.out.println("\nYour Notes:");
        noteService.getAllNotes().forEach(note -> {
            System.out.println("Title: " + note.getTitle());
            System.out.println("Thought: " + note.getThought());
            System.out.println("Category: " + note.getCategory());
            System.out.println("----------------------");
        });
    }
}
