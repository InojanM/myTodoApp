package model;

public class TodoItem {

    public int categoryId;
    public String item;
    public boolean completed;
    public int id;

    public TodoItem(int categoryId, String item, boolean completed, int id) {
        this.categoryId = categoryId;
        this.item = item;
        this.completed = completed;
        this.id = id;
    }


    public String toString() {
        return item + " (" + id + ")";
    }

}
