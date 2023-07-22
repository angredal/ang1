package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory factory;

    public UserDaoHibernateImpl() {
        factory = HibernateUtil.getSessionFactory();
    }


    @Override
    public void createUsersTable() {}

    @Override
    public void dropUsersTable() {}

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<User> fromUser = session.createQuery("FROM User", User.class).list();
            session.getTransaction().commit();
            return fromUser;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
