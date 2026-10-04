package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection connection;
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/btvn_buoi8";
        String username = "root";
        String password = "";
        try {
            connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}