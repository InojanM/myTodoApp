package myDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {

    MyDB myDB = new MyDB();

    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public void insertCategory(String category) throws SQLException {

        myDB.createConnection();
        preparedStatement = myDB.connection.prepareStatement("INSERT INTO todoCategory(category) VALUES (?)");
        preparedStatement.setString(1, category);
        preparedStatement.execute();
        myDB.closeConnection();

    }

    public ResultSet selectAllCategory() throws SQLException {
        myDB.createConnection();
        statement = myDB.connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM todoCategory");
        return resultSet;


    }

    public void insertItem(int id, String item) throws SQLException {
        myDB.createConnection();
        preparedStatement = myDB.connection.prepareStatement("INSERT INTO todoItem(categoryId, item, completed) VALUES (?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, item);
        preparedStatement.setBoolean(3, false);
        preparedStatement.execute();
        myDB.closeConnection();

    }

    public ResultSet selectAllItems() throws SQLException {
        myDB.createConnection();
        statement = myDB.connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM todoItem");
        return resultSet;
    }


    public void setAsCompleted(int index) throws SQLException {
        myDB.createConnection();
        preparedStatement = myDB.connection.prepareStatement("UPDATE todoItem SET completed = ? WHERE id=" + index);
        preparedStatement.setBoolean(1, true);
        preparedStatement.execute();
        myDB.closeConnection();
    }


}
