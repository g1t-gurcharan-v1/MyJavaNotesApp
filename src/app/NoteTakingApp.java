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

    private static void showMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add a Note");
        System.out.println("2. View All Notes");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

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
        }
    }

    private static void addNote(NoteService noteService) {
        String title, thought, category;

        while (true) {
            System.out.print("Enter Title: ");
            title = scanner.nextLine().trim();
            if (!title.isEmpty()) break;
            System.out.println("Title cannot be empty. Please enter a valid title.");
        }

        while (true) {
            System.out.print("Enter Thought: ");
            thought = scanner.nextLine().trim();
            if (!thought.isEmpty()) break;
            System.out.println("Thought cannot be empty. Please enter your note.");
        }

        System.out.print("Enter Category (optional): ");
        category = scanner.nextLine().trim();

        noteService.addNote(title, thought, category);
        System.out.println("✅ Note added successfully!");
    }

    private static void viewNotes(NoteService noteService) {
        System.out.println("\n--- Your Notes ---");
        var notes = noteService.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            notes.forEach(note -> {
                System.out.println("Title: " + note.getTitle());
                System.out.println("Thought: " + note.getThought());
                System.out.println("Category: " + note.getCategory());
                System.out.println("----------------------");
            });
        }
    }
}
