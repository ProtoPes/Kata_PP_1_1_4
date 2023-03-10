package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost/userDB";
    private static final String SQL_USER = "test777";
    private static final String PASSWD = "password";
    private static final Connection CONNECTION;

    static {
        try {
            CONNECTION = DriverManager.getConnection(URL, SQL_USER, PASSWD);
            if (!CONNECTION.isClosed()) {
                System.out.println("Connected to the DB!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Util() {}

    public static Statement createSt() throws SQLException {
        return CONNECTION.createStatement();
    }

    public static void closeConn() throws SQLException {
        CONNECTION.close();
        if (CONNECTION.isClosed()) {
            System.out.println("Disconnected from the DB!");
        }
    }
}
