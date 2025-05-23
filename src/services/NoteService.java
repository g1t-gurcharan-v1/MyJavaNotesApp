package services;

import models.Note;
import models.User;
import storage.Storage;
import java.util.List;

public class NoteService {
    private final User user;

    // Constructor
    public NoteService(User user) {
        this.user = user;
        loadNotesFromStorage(); // Load existing notes at startup
    }

    // Add a new note
    public void addNote(String title, String thought, String category) {
//        Note newNote = new Note(title, thought, category);
        user.addNote(title, thought, category);
        saveNotesToStorage();
    }

    // Get all notes
    public List<Note> getAllNotes() {
        return user.getNotes();
    }

    // Save notes to file
    public void saveNotesToStorage() {
        Storage.saveNotes(user.getNotes());
    }

    // Load notes from file
    public void loadNotesFromStorage() {
        List<Note> loadedNotes = Storage.loadNotes();
        for (Note note : loadedNotes) {
            user.addNote(note.getTitle(), note.getThought(), note.getCategory());
        }
    }
}

