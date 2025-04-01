package models;

import java.util.Date;

public class Note {

    private String title;
    private String thought;
    private Date date;
    private String category;


    public Note(String title, String thought, Date date, String category) {
        this.title = title;
        this.thought = thought;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nThought: " + thought + "\nDate: " + date + "\nCategory: " + category;
    }
}
