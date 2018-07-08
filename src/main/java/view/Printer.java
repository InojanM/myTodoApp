package view;

import helper.HelperFunction;
import model.TodoController;
import myDatabase.MyDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Printer {

    HelperFunction helperFunction = new HelperFunction();
    TodoController todoController = new TodoController();
    Scanner input = new Scanner(System.in);
    MyDB myDB = new MyDB();


    private void menu() {
        System.out.println("1. Insert Category");
        System.out.println("2. Insert Item");
        System.out.println("3. Set As Completed");
        System.out.println("4. View Items By Category");
        System.out.println("5. View Completed Items By Category");
        System.out.println("6. View Un Completed Items By Category");
        System.out.println("0. Exit");
    }


    public void view() throws SQLException {
        menu();
        System.out.println();
        boolean condition = true;


        while (condition) {


            todoController.insertCategoryToArrayList();
            todoController.insertItemToArrayList();

            myDB.createConnection();
            System.out.print("Choose your choice [Range 0 - 6] : ");
            int choice = helperFunction.intRangeValidate(0, 6, helperFunction.intNumberValidate(input.nextLine()));


            if (choice == 0) {
                condition = false;
            } else if (choice == 1) {

                System.out.print("Enter the new category name: ");
                String category = input.nextLine();
                todoController.insertCategory(category);
                todoController.refreshArrayList();
                System.out.println("ALL ITEMS BY CATEGORIES");
                System.out.println();
                todoController.viewItemByCategory();
                System.out.println("=================================");

            } else if (choice == 2) {

                System.out.print("Enter new todo item: ");
                String item = input.nextLine();

                System.out.println();

                todoController.refreshArrayList();
                int max = todoController.viewCategory();

                System.out.println("Enter your choice [Range 1 - " + max + " ] : ");
                int categoryNum = helperFunction.intRangeValidate(1, max, helperFunction.intNumberValidate(input.nextLine()));

                todoController.insertItem(categoryNum, item);
                todoController.refreshArrayList();
                System.out.println("ALL ITEMS BY CATEGORIES");
                System.out.println();
                todoController.viewItemByCategory();
                System.out.println("=================================");


            } else if (choice == 3) {

                todoController.refreshArrayList();

                todoController.viewItemByCategory();
                System.out.println("Select completed item number [ Range 1 - " + todoController.countItems() + "] : ");

                int itemNum = helperFunction.intRangeValidate(1, todoController.countItems(), helperFunction.intNumberValidate(input.nextLine()));

                todoController.setCompleted(itemNum);
                todoController.refreshArrayList();
                System.out.println("ALL ITEMS BY CATEGORIES");
                System.out.println();
                todoController.viewItemByCategory();
                System.out.println("=================================");


            } else if (choice == 4) {


                todoController.refreshArrayList();
                System.out.println("ALL ITEMS BY CATEGORIES");
                System.out.println();
                todoController.viewItemByCategory();
                System.out.println("=================================");


            } else if (choice == 5) {

                todoController.refreshArrayList();
                System.out.println();
                System.out.println("COMPLETED ITEMS ONLY");
                todoController.viewItemByCompletedCategory();
                System.out.println("=================================");


            } else if (choice == 6) {

                todoController.refreshArrayList();
                System.out.println();
                System.out.println("UN-COMPLETED ITEMS ONLY");
                System.out.println();
                todoController.viewItemByUnCompletedCategory();
                System.out.println("=================================");

            }

            menu();


        }


    }


}
