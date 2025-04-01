import models.User;

public class Main {
    public static void main(String[] args) {




        System.out.println("Hello, World!");

        // Create a User object
        User user = new User("John Doe");

        // Add some notes to the user
        user.addNote("Meeting Notes", "Discussed project timelines.", "Work");
        user.addNote("Grocery List", "Milk, Eggs, Bread", "Personal");

        // Print all the notes of the user
        user.printNotes();
    }





    }