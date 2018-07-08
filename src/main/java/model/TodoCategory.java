package model;

public class TodoCategory {

    int id;
    private String category;

    public TodoCategory(int id, String category) {
        this.category = category;
        this.id = id;
    }

    public String toString() {
        return id + ". " + category;
    }

}
