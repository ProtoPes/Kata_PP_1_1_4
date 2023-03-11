package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private final String INSERT_USER = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Connection connection = Util.newConnection(false);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(256), lastName VARCHAR(256), age TINYINT CHECK (age > 0))");
            connection.commit();
            System.out.println("Table \"users\" was created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.newConnection(false);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();
            System.out.println("Table \"users\" was deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.newConnection(false);
            PreparedStatement prepStatement = connection.prepareStatement(INSERT_USER)) {
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setByte(3, age);
            prepStatement.executeUpdate();
            connection.commit();
            System.out.printf("User with name %s - was added to the DataBase.\n", name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.newConnection(false);
             PreparedStatement prepStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {
            prepStatement.setLong(1, id);
            prepStatement.executeUpdate();
            connection.commit();
            System.out.printf("User with id %d was deleted!\n", id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.newConnection(true);
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM users");
            List<User> userList = new ArrayList<>();
            while (res.next()) {
                User user = new User(res.getString(2), res.getString(3), res.getByte(4));
                user.setId(res.getLong(1));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.newConnection(false);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
            connection.commit();
            System.out.println("Table \"users\" was cleaned!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
