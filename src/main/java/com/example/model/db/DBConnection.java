package com.example.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    public static String password = "1s46p1k86n";
    public static String uri = "jdbc:mysql://localhost:3306/faculty";
    public static String user = "root";



    private DBConnection() {
    }

    public static synchronized Connection getInstance() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(uri, user, password);
        }
        return connection;
    }
}
