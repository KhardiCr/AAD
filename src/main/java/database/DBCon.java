package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class DBCon {

    private static Connection connection;

    public Connection getConnection() {

        if (connection == null) {
            try {
                createConnection();
            } catch (SQLTimeoutException e) {
                System.out.println("Timeout de db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private void createConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s", SchemaDB.HOST,SchemaDB.PORT, SchemaDB.DATABASE);
        connection = DriverManager.getConnection(url,"root","toor");
    }

}
