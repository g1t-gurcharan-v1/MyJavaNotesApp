package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Note;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "/Users/gurcharan/Desktop/notes.json";
    private static final Gson gson = new Gson();

    // Save notes to file
    public static void saveNotes(List<Note> notes) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(notes, writer);
        } catch (IOException e) {
            System.err.println("❌ Error saving notes: " + e.getMessage());
        }
    }

    // Load notes from file
    public static List<Note> loadNotes() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, new TypeToken<List<Note>>() {}.getType());
        } catch (IOException e) {
            System.err.println("❌ Error loading notes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
