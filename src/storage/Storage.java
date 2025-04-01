package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.User;

import java.io.*;
import java.lang.reflect.Type;

public class Storage {
    private static final String FILE_PATH = "/Users/gurcharan/Desktop/Notes_Json_Folder/notes.json"; // File to store notes
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Save user data (notes) to JSON
    public static void saveUser(User user) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(user, writer); // Convert User object to JSON and write to file
        } catch (IOException e) {
            System.out.println("Error saving notes: " + e.getMessage());
        }
    }

    // Load user data (notes) from JSON
    public static User loadUser() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type userType = new TypeToken<User>() {}.getType();
            return gson.fromJson(reader, userType); // Convert JSON back to User object
        } catch (FileNotFoundException e) {
            System.out.println("No saved notes found. Starting fresh.");
            return new User("Default User"); // If file doesn't exist, return a new User
        } catch (IOException e) {
            System.out.println("Error loading notes: " + e.getMessage());
            return null;
        }
    }
}

