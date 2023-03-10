package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Statement statement = Util.createSt()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(256), lastName VARCHAR(256), age TINYINT CHECK (age > 0))");
            System.out.println("Table \"users\" was created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try(Statement statement = Util.createSt()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Table \"users\" was deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = Util.createSt()) {
            statement.executeUpdate("INSERT INTO users(name, lastName, age) VALUES (\"%s\", \"%s\", %d)".formatted(name, lastName, age));
            System.out.printf("User with name %s - was added to the DataBase.\n", name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.createSt()) {
            statement.executeUpdate("DELETE FROM users WHERE id = " + id);
            System.out.printf("User with id %d was deleted!\n", id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = Util.createSt()) {
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
        try (Statement statement = Util.createSt()) {
            statement.executeUpdate("DELETE FROM users");
            System.out.println("Table \"users\" was cleaned!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
