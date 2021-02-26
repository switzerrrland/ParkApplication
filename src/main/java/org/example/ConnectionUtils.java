package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.Constants.DB_PATH;
import static org.example.Constants.DRIVER_NAME;

public class ConnectionUtils {
    private static Connection connection;

    public static Connection createConnection() {
        try {
            Class.forName(String.valueOf(DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
        }

        try {
            connection = DriverManager.getConnection(String.valueOf(DB_PATH));
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");

        }
        return connection;
     }

     public static void closeConnection() {
         try {
             connection.close();
         } catch (SQLException e) {
             System.out.println("Can't close connection");
         }
     }
}
