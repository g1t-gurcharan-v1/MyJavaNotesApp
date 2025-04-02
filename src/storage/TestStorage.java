package storage;

import models.User;

public class TestStorage {
    public static void main(String[] args) {
        // Create a user
        User user = new User("Alice");

        // Add some notes
        user.addNote("Java Basics", "Classes and objects explained", "Programming");
        user.addNote("Workout Plan", "Cardio and strength training", "Health");

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

