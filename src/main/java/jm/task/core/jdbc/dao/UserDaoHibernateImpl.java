package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import static jm.task.core.jdbc.util.Util.newSession;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = newSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(256), lastName VARCHAR(256), age TINYINT CHECK (age > 0))").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table \"users\" was created!");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = newSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table \"users\" was deleted!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = newSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.printf("User with name %s - was added to the DataBase.\n", name);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = newSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            System.out.printf("User with id %d was deleted!\n", id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = newSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = newSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table \"users\" was cleaned!");
        }
    }
}
