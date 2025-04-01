package models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private List<Note> notes;

    public User(String name){
        this.username=name;
        this.notes = new ArrayList<>();
    }

    // Method to add a Note to the User's list
    public void addNote(String title, String thought, String category) {
        Note note = new Note(title, thought, new java.util.Date(), category);
        this.notes.add(note);
    }

    // Method to get all the Notes
    public List<Note> getNotes() {
        return notes;
    }

    // Method to get the username
    public String getUsername() {
        return username;
    }

    // Method to print all notes for the user
    public void printNotes() {
        for (Note note : this.notes) {
            System.out.println(note);
        }
    }

    // Display all notes
    public void displayNotes() {
        if (notes.isEmpty()) {
            System.out.println(username + " has no notes.");
        } else {
            System.out.println(username + "'s Notes:");
            for (Note note : notes) {
                System.out.println(note);
                System.out.println("-------------------");
            }
        }
    }
}
