package myDatabase;

import java.sql.*;

public class MyDB {

    public Connection connection;


    public void createConnection() throws SQLException {

        String url = "jdbc:sqlite:myData.db";
        connection = DriverManager.getConnection(url);

    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
