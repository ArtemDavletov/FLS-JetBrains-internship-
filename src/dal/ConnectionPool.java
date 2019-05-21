package dal;

import java.sql.*;

public class ConnectionPool {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:FLS.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
