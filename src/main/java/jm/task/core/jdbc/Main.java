package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService test = new UserServiceImpl();
        test.createUsersTable();

        test.saveUser("Leonardo", "da Vinci", (byte) 67);
        test.saveUser("Donatello", "Betto Bardi", (byte) 80);
        test.saveUser("Michelangelo", "Buonarroti Simoni", (byte) 88);
        test.saveUser("Raffaello", "Santi", (byte) 37);

        List<User> users = test.getAllUsers();
        users.forEach(System.out::println);

        test.cleanUsersTable();
        test.dropUsersTable();
        Util.closeConn();
    }
}
