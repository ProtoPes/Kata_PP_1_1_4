package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost/userDB";
    private static final String SQL_USER = "test777";
    private static final String PASSWD = "password";

    private Util() {}

    public static Connection newConnection(boolean autoCommit) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, SQL_USER, PASSWD);
        connection.setAutoCommit(autoCommit);
        return connection;
    }

    public static void closeConn(Connection connection) throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
