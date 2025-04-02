package models;

public class Note {

    private String title;
    private String thought;
    private String category;


    public Note(String title, String thought, String category) {
        this.title = title;
        this.thought = thought;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nThought: " + thought + "\nCategory: " + category;
    }
}
