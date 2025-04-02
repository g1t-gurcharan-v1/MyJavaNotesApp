package storage;

import models.User;

public class TestStorage {
    public static void main(String[] args) {
        // Create a user
        User user = new User("Alice");

        // Add some notes
        user.addNote("Motivation", "I didn't fail the test. I just found 100 ways to do it wrong. — Benjamin Franklin", "Personal");
        user.addNote("Inspiration", "Strength does not come from winning. Your struggles develop your strengths. — Arnold Schwarzenegger ", "Health");

        // Save to file
        Storage.saveNotes( user.getNotes());
        System.out.println("Notes saved successfully!");

        // Load from file
        User loadedUser = new User("LoadedUser");
        loadedUser.getNotes().addAll(Storage.loadNotes());
        if (loadedUser != null) {
            System.out.println("Loaded User: " + loadedUser.getUsername());
            loadedUser.displayNotes();
        }
    }
}

