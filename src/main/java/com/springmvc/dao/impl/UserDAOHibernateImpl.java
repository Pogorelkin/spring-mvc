package com.springmvc.dao.impl;

import com.springmvc.dao.UserDAO;
import com.springmvc.entities.User;
import com.springmvc.exceptions.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
@Qualifier("jpa")
@CacheConfig(cacheNames = "users")
public class UserDAOHibernateImpl implements UserDAO {
    Logger logger = LoggerFactory.getLogger(UserDAOHibernateImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Cacheable
    @Override
    public List<User> findAll() {
        try (final Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            throw new DAOException("Couldn't get all Users from DB", e.getCause());
        } finally {
            sessionFactory.close();
        }
    }

    @Cacheable(key = "#id")
    @Override
    public User findById(long id) {
        User user = null;
        try (final Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
        } catch (Exception ex) {
            throw new DAOException("Couldn't get User from DB with id = " + id, ex.getCause());
        } finally {
            sessionFactory.close();
        }
        return user;
    }

    @Override
    @CacheEvict
    public long add(User entity) {
        long rows = 0;
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            rows = (long) session.save(entity);
            session.getTransaction().commit();
            return rows;
        } catch (Exception e) {
            throw new DAOException("Couldn't add User to DB. " + entity.toString(), e.getCause());
        } finally {
            sessionFactory.close();
        }
    }

    @CacheEvict(key = "#id")
    @Override
    public long update(long id, User entity) {
        long rows = 0;
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            entity.setUserId(id);
            rows = (long) session.save(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Couldn't update User  " + entity.toString(), ex.getCause());
        } finally {
            sessionFactory.close();
        }
        return rows;
    }

    @CacheEvict(key = "#id")
    @Override
    public long deleteById(long id) {
        long rows = 0;
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(String.format("from User e where e.id = %d", id));
            rows = (long) query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Couldn't delete User from DB with id =  " + id, e.getCause());
        } finally {
            sessionFactory.close();
        }
        return rows;
    }
}
