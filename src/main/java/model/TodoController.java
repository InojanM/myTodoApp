package model;

import myDatabase.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoController {


    DBController dbController = new DBController();

    ArrayList<TodoCategory> todoCategories;
    ArrayList<TodoItem> todoItems;

    public TodoController() {
        todoCategories = new ArrayList<>();
        todoItems = new ArrayList<>();
    }

    private void addCategory(int id, String category) {
        todoCategories.add(new TodoCategory(id, category));
    }

    public void insertCategory(String category) throws SQLException {
        dbController.insertCategory(category);
    }

    public void insertItem(int categoryId, String item) throws SQLException {
        dbController.insertItem(categoryId, item);
    }

    public void insertCategoryToArrayList() throws SQLException {

        ResultSet resultSet = dbController.selectAllCategory();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String category = resultSet.getString(2);
            addCategory(id, category);
        }
    }

    private void addItem(int categoryId, String item, boolean completed, int id) {
        todoItems.add(new TodoItem(categoryId, item, completed, id));
    }

    public void insertItemToArrayList() throws SQLException {
        ResultSet resultSet = dbController.selectAllItems();
        while (resultSet.next()) {
            int categoryId = resultSet.getInt(1);
            String item = resultSet.getString(2);
            boolean completed = resultSet.getBoolean(3);
            int id = resultSet.getInt(4);
            addItem(categoryId, item, completed, id);
        }

    }

    public void setCompleted(int index) throws SQLException {
        dbController.setAsCompleted(index);
    }

    public int viewCategory() {
        int count = 1;
        for (TodoCategory tc : todoCategories) {
            System.out.println(tc);
            count++;
        }
        return count;
    }

    public int countItems() {
        int count = 1;
        for (TodoItem ti : todoItems) {
            count++;
        }
        return count;
    }

    public void viewItemByCategory() {
        for (TodoCategory tc : todoCategories) {
            System.out.println(tc);
            for (TodoItem ti : todoItems) {
                String mark;
                if (ti.completed == true) {
                    mark = "[X]";

                } else {
                    mark = "[ ]";
                }

                if (tc.id == ti.categoryId) {
                    System.out.println("\t" + "* " + mark + ti);

                }
            }
            System.out.println();
        }

    }

    public void viewItemByCompletedCategory() {

        for (TodoCategory tc : todoCategories) {
            System.out.println(tc);
            for (TodoItem ti : todoItems) {
                if (tc.id == ti.categoryId && ti.completed == true) {
                    System.out.println("\t" + "* " + "[x] " + ti);
                }
            }
            System.out.println();
        }

    }

    public void viewItemByUnCompletedCategory() {
        for (TodoCategory tc : todoCategories) {
            System.out.println(tc);
            for (TodoItem ti : todoItems) {
                if (tc.id == ti.categoryId && ti.completed == false) {
                    System.out.println("\t" + "* " + "[ ] " + ti);

                }
            }
            System.out.println();
        }

    }

    public void refreshArrayList() throws SQLException {
        todoItems.clear();
        todoCategories.clear();
        insertCategoryToArrayList();
        insertItemToArrayList();

    }

}

