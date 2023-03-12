package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost/userDB";
    private static final String SQL_USER = "test777";
    private static final String PASSWD = "password";

    private static final Configuration configuration = new Configuration().addAnnotatedClass(User.class);
    private static final SessionFactory sessionFactory = configuration.buildSessionFactory();

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

    public static Session newSession() {
        return sessionFactory.getCurrentSession();
    }
}
